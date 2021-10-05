# Tipo de Cambio

## CircleCi
[![CircleCI](https://circleci.com/gh/Giancarlo2709/exchange-rate/tree/master.svg?style=svg)](https://circleci.com/gh/Giancarlo2709/exchange-rate/tree/master)

Ejecución con maven:

```
mvn spring-boot:run
```

Ejecución con Docker:

```
docker build -t "exchange-rate" .
docker run -d -it -p 8090:8090 --name=exchange-rate exchange-rate
```

