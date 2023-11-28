# INDITEX PRICES

Este README proporciona instrucciones detalladas sobre cómo ejecutar y probar el programa "Inditex-Prices", junto con explicaciones adicionales sobre la lógica del código.

### Requisitos Previos

Antes de ejecutar el programa, asegúrate de tener instalado:

- [Java](https://www.java.com/) (se recomienda Java 8 o superior)
- [Maven](https://maven.apache.org/)

### Configuración del Proyecto

1. Clonar el Repositorio:

```
git clone https://github.com/BladimirDelBarrioPizarro/Inditex-Prices
cd Inditex-Prices
   ```
2. Compilar el Proyecto:   
```
mvn clean install
```
### Ejecución del Programa 
```
java -jar target/prices-0.0.1-SNAPSHOT.jar
```
### Ejecución de los Test
```
mvn test
```

## Lógica del Negocio:

La clase `PricesServiceImpl` es el corazón de la aplicación. Aquí, se implementa el método `getPrices`, que recupera los precios según ciertos criterios.

En la aplicación, la obtención de precios se realiza mediante una consulta a la base de datos utilizando Spring Data JPA. La siguiente línea de código representa la consulta:
```
pricesRepository.findByStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(date, productId, brandId);
```
Este método de consulta se basa en convenciones de nomenclatura de Spring Data JPA. La consulta busca registros en la tabla de precios donde la fecha de inicio sea menor o igual a la fecha proporcionada (date), y donde el identificador del producto (productId) y el identificador de la marca (brandId) coincidan. La lista resultante se ordena por prioridad de forma descendente.

El resultado de la consulta se almacena en una lista de objetos Price.

La lógica de selección del precio aplicable se encuentra en el método applicablePrice, donde se ordenan las tarifas por fecha de inicio y prioridad de manera descendente. En este proceso, la lista de tarifas se organiza primero por fecha de inicio de forma descendente y luego por prioridad de forma descendente. La primera tarifa en la lista ordenada tiene la mayor prioridad.

```
  private Price applicablePrice(List<Price> prices) {
        prices.sort(
                Comparator.comparing(Price::getStartDate).reversed()
                        .thenComparing(Price::getPriority).reversed()
        );
        return prices.get(0);
    }
```

### Ejemplos de peticiónes 

```
http://localhost:8080/api/prices?date=2020-06-14T21:00&productId=35455&brandId=1
```
```
http://localhost:8080/api/prices?date=14/06/2020 21:00&productId=35455&brandId=1
```
Para la configuración del controlador se utiliza la anotación @InitBinder para personalizar la configuración de enlace de datos de Spring.
La función initBinder se llama durante la inicialización del controlador y se encarga de configurar el WebDataBinder, que es responsable de vincular los parámetros de la solicitud a los argumentos del controlador.

### Detalles del Código
@InitBinder: Esta anotación indica que el método es utilizado para inicializar el WebDataBinder, que es responsable de la vinculación de datos entre los parámetros de solicitud y los objetos del controlador.

- WebDataBinder: Es una clase que proporciona métodos para personalizar la vinculación de datos en Spring MVC.

- registerCustomEditor: Este método se utiliza para registrar un editor personalizado para un tipo específico (en este caso, LocalDateTime).

- CustomLocalDateTimeEditor: Es una clase que extiende PropertyEditorSupport y se encarga de la conversión de texto (formato de fecha) a objetos LocalDateTime utilizando varios formateadores de fecha.

Se configuro la aplicación para los siguientes patrones pudiendo añadir cualquier patrón.
Se tomaron las / para las fechas y se omitieron los segundos al hacer las peticiones en horas y minutos.

```
yyyy-MM-dd'T'HH:mm: Formato ISO extendido.
dd/MM/yyyy HH:mm: Formato de día/mes/año con hora y minutos.
yyyy/MM/dd HH:mm: Formato de año/mes/día con hora y minutos.
MM/dd/yyyy HH:mm: Formato de mes/día/año con hora y minutos.
```
### OpenApi
Disponemos de la descripción OpenApi aqui

```
http://localhost:8080/v3/api-docs
```
```
http://localhost:8080/swagger-ui/index.html
```
En Swagger/OpenAPI, las fechas generalmente se representan en formato ISO 8601. Asegúrate de que estás proporcionando la fecha en el formato correcto cuando pruebas tu API a través de Swagger.

Ejemplo de formato ISO 8601 para una fecha y hora:

```
2023-11-24T12:34:56
```



