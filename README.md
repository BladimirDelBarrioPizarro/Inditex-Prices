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

La lógica de selección del precio aplicable se encuentra en el método applicablePrice, donde se ordenan las tarifas por fecha de inicio y prioridad de manera descendente. En este proceso, la lista de tarifas se organiza primero por fecha de inicio de forma descendente y luego por prioridad de forma descendente. La primera tarifa en la lista ordenada tiene la mayor prioridad.






