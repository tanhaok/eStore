# eStore
> [Architecture](https://drive.google.com/file/d/1wbXkCoW1Cp1-LE5fbcnRx1MuTrOiz_Zq/view?usp=sharing)

![image](https://github.com/tanhaok/eStore/assets/101847895/f62301d5-09fa-4689-81bd-1076a4d80501)



## Components
> will have a component named `cmd`. This component was written by Rust. Will have some simple func (init data, run destination service, scraping data and insert into db, validation order component if can...)


|Component|Language|Unittest|Framework|Comment|
|--|--|--|--|--|
|Api | Java | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=tanhao111_eStore_Api&metric=coverage)](https://sonarcloud.io/summary/new_code?id=tanhao111_eStore_Api) | Spring Boot | api gateway |
|Auth | Java  | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=tanhao111_eStore_auth&metric=coverage)](https://sonarcloud.io/summary/new_code?id=tanhao111_eStore_auth)| Spring Boot | identity service |
|Cart | Go | 0 | Gin | cart service |
|Email| Java | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=tanhao111_eStore_Email&metric=coverage)](https://sonarcloud.io/summary/new_code?id=tanhao111_eStore_Email) |Spring Boot | email service, send email...|
|Media| Java | 0 |Spring Boot | media service, handle file and blog...|
|Order| Python | 0 | Express | order service, handle after order success|
|Payment| Java | 0 | Spring Boot |payment service, payment before process to order service|
|Product| Java | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=tanhao111_eStore_Product&metric=coverage)](https://sonarcloud.io/summary/new_code?id=tanhao111_eStore_Product) | Spring Boot | product service, crud with product|
|Rating| Python | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=tanhao111_eStore_rating&metric=coverage)](https://sonarcloud.io/summary/new_code?id=tanhao111_eStore_rating) | Fast Api | rating service, rate star and comment of product|
|Search| Java | 80% | Spring Boot & Elastic Search | search service integrate with Elastic search|
|Inventory | Go | 80% | Gin | Inventory Service |
|Ui Api| Typescript | 0 | Typescript base | api for ui, package to work with backend|
|Ui Auth| Typescript | 0 | ReactJs | auth service for ui|
|Ui Home| Typescript | 0 | ReactJs | home base|
|Ui Product| Typescript | 0 | ReactJs | handle ui for product| 

## CI Status

This use Github action for CI and CD. and also use Aws for deployment

https://www.tailwindawesome.com/resources/flowbite-admin-dashboard/demo
