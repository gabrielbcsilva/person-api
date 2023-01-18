# Person-API
Uma API para gerenciamento de pessoas.

<h3 align="center">
    <img alt="Logo" title="#logo" width="170px" src="https://th.bing.com/th/id/R.dbcaa7ed35b8487009399f9a6d31b585?rik=mwesn%2bUxdj4VOA&pid=ImgRaw&r=0">
    <img alt="Logo" title="#logo" width="200px" src="https://4.bp.blogspot.com/-ou-a_Aa1t7A/W6IhNc3Q0gI/AAAAAAAAD6Y/pwh44arKiuM_NBqB1H7Pz4-7QhUxAgZkACLcBGAs/s1600/spring-boot-logo.png">
   
</h3>

## Sobre

Esta API que tem como objetivo cadastrar criar uma pessoa e adicionar seu endereço `/person`.

## Features

Esta API fornece endpoints e ferramentas HTTP para:

* Criar uma pessoa `POST//person`.
* Editar uma pessoa `UPDATE//person/:id`
* Consultar uma pessoa `GET//person/:id`
* Listar pessoas `GET//person/`

**Body:**

```json
{
    "name": "Gabriel",
    "birthDate": "1995-03-18"
}
```

**Onde:**

`name` - É o nome da pessoa.

`birthDate` - A data de nascimento.



* Criar endereço para pessoa `POST//person/:personId/adress`
* Listar endereços da pessoa `GET//person/:personId/adress`

**Body:**

```json
{
    "street": "Rua",
    "cep": "00000",
    "number" : "1",
    "city" : "Cidade"
}
```

* Poder informar qual endereço é o principal da pessoa `PUT//person/:personId/`


**Body:**

```json
{
    "name": "Gabriel",
    "birthDate": "1995-03-15",
    "principalAdress":{
        "id":"1"
    }
}
```

Retornos possíveis:

* 201 - Created: Tudo ocorreu como esperado.
* 400 - Bad Request: A requisição não foi aceita, geralmente devido à falta de um parâmetro obrigatório ou JSON inválido.
* 404 - Not Found: Id informado não foi encontrado.
* 500 - Server Errors: Erro interno.



### Tecnologias utilizadas

Este projeto foi desenvolvido utilizando:

* **Java 1.8 (Java Development Kit - JDK: 1.8 )**
* **Spring Boot 2.7.7**
* **Maven**
* **JUnit 5**
* **H2Dialect**
* **Postman**
* **Heroku**

### Testes

* Para a fase de teste unitários, você pode executar:

```bash
mvn clean test
```

### Documentação

* Postman (development environment): [https://documenter.getpostman.com/view/21229006/2s8ZDVZiHN](https://documenter.getpostman.com/view/21229006/2s8ZDVZiHN)