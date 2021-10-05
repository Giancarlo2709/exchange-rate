# Proyecto Tipo de Cambio

El proyecto se puede ejecutar de la siguiente forma:

```
mvn spring-boot:run
```

O bien, mediante Docker:

```
docker build -t "exchange-rate" .
docker run -d -it -p 8080:8080 --name=exchange-rate exchange-rate
```

