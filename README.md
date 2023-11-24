# INDITEX PRICES

Este README proporciona instrucciones detalladas sobre cómo ejecutar y probar el programa "Prices", junto con explicaciones adicionales sobre la lógica del código.

### Requisitos Previos

Antes de ejecutar el programa, asegúrate de tener instalado:

- [Java](https://www.java.com/) (se recomienda Java 8 o superior)
- [Maven](https://maven.apache.org/)

### Configuración del Proyecto

1. Clonar el Repositorio:

```
git clone https://github.com/BladimirDelBarrioPizarro/Inditex-Prices
cd tu_repositorio
   ```
2. Compilar el Proyecto:   
```
mvn clean install
```
### Ejecución del Programa: 
```
java -jar target/prices-0.0.1-SNAPSHOT.jar
```
### Test
```
mvn test
```

## Lógica del Negocio:

La clase `PricesServiceImpl` es el corazón de la aplicación. Aquí, se implementa el método `getPrices`, que recupera los precios según ciertos criterios.

En la aplicación, la obtención de precios se realiza mediante una consulta a la base de datos utilizando Spring Data JPA. La siguiente línea de código representa la consulta:
```
List<Price> prices = pricesRepository.findByStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(date, productId, brandId);
```

La lógica de selección del precio aplicable se encuentra en el método applicablePrice, donde se ordenan las tarifas por fecha de inicio y prioridad de manera descendente. En este proceso, la lista de tarifas se organiza primero por fecha de inicio de forma descendente y luego por prioridad de forma descendente. La primera tarifa en la lista ordenada tiene la mayor prioridad.
pricesRepository: Hace referencia al repositorio de Spring Data JPA asociado a la entidad Price. Este repositorio extiende la interfaz JpaRepository y es utilizado para interactuar con la base de datos.

findByStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc: Este método de consulta se basa en convenciones de nomenclatura de Spring Data JPA. La consulta busca registros en la tabla de precios donde la fecha de inicio sea menor o igual a la fecha proporcionada (date), y donde el identificador del producto (productId) y el identificador de la marca (brandId) coincidan. La lista resultante se ordena por prioridad de forma descendente.

El resultado de la consulta se almacena en una lista de objetos Price.

### Ejemplos de peticiónes 

```
http://localhost:8080/api/prices?date=2020-06-14T21:00&productId=35455&brandId=1
```
```
http://localhost:8080/api/prices?date=14/06/2020 21:00&productId=35455&brandId=1
```
Para la configuración del controlador se utiliza la anotación @InitBinder para personalizar la configuración de enlace de datos de Spring.
La función initBinder se llama durante la inicialización del controlador y se encarga de configurar el WebDataBinder, que es responsable de vincular los parámetros de la solicitud a los argumentos del controlador.

Se configuro la aplicación para los siguientes patrones pudiendo añadir cualquier patrón.
Se tomaron las / para las fechas y se omitieron los segundos al hacer las peticiones horas y minutos.

```
yyyy-MM-dd'T'HH:mm
dd/MM/yyyy HH:mm
yyyy/MM/dd HH:mm
MM/dd/yyyy HH:mm
```


