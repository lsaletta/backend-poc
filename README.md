# GetPrices with Spring-Boot

Este proyecto implementa una POC como micro-servicio (API) utilizando Spring-boot y Maven para la obtención de precios
aplicando filtros. Leer el [enunciado](https://github.com/lsaletta/backend-poc/blob/master/enunciado.md) para más
información.

## Como utilizar

Abrir una consola y ejecutar:

```
git clone https://github.com/lsaletta/backend-poc.git
cd backend-poc
mvn clean install
java -jar target/poc-0.0.1-SNAPSHOT.jar
```

## USO DE LA API

Despues de seguir los pasos anteriores, estará la API arrancada en: `http://localhost:8080`

Se ha añadido al micro-servicio Swagger. URL Swagger: http://localhost:8080/swagger-ui-custom.html

El modelo definido para los precios es el siguiente:

```
{
    "brand_id": 0,
    "start_date": "2021-06-11T09:21:38.123Z",
    "end_date": "2021-06-11T09:21:38.123Z",
    "price_list": 0,
    "product_id": 0,
    "priority": 0,
    "price": 0,
    "currency": "string"
}
```

Un ejemplo de peticion seria:

#### Inputs:

- application_date: 2020-06-14 16:00:00
- product_id: 35455
- brand_id: 1

#### Request:

`curl --location --request GET 'http://localhost:8080/backend/price?application_date=2020-06-14%2016%3A00%3A00&product_id=35455&brand_id=1'`

#### Response:

```
{
    "brand_id": 1,
    "start_date": "2020-06-14T15:00:00",
    "end_date": "2020-06-14T18:30:00",
    "price_list": 2,
    "product_id": 35455,
    "priority": 1,
    "price": 25.45,
    "currency": "EUR"
}
```