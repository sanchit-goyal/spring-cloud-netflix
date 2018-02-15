# spring-cloud-netflix

## Eureka-Service
http://localhost:1111/

## Employee-Service
http://localhost:3333/employees

** using Zuul
http://localhost:1111/api/employee-service/employees

## Department Service
http://localhost:4444/departments

** using Zuul
http://localhost:1111/api/department-service/departments


## Lookup Service

** Using FeignClient
http://localhost:2222/lookup/departments

** Spring RestTemplate
http://localhost:2222/lookup/employees	
