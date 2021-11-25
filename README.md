# Spring-boot-mvc-mem

__Featuriz__ guide to *Spring Framework 5*.

## Technology and Version

| __Spring__ | __Version__ |
| --- | --- |
| Spring Boot | 2.6 |
| Spring web | 5.3.13 |
| Spring Security | 5.6.0 |
| Spring Test | 5.3.13 |
| Thymeleaf | 3.0.12.RELEASE |
| Spring JPA | 5.3.13 |
| Mariadb | 5.3.13 |
| Lombok | 5.3.13 |
| Validation | 5.3.13 |
| Bootstrap | 5.0.2 |

- Database based authentication is provided.

#### How to use
This is a maven based spring boot project. Use maven to build this project and just run.  
_No other dependencies_

## NOTE

### IndexController
| __Page__ | __Role__ | __Info__ |
| --- | --- | --- |
| / | - | Homepage |
| /login | - | Custom Login Form |
| /registration | - | Custom Registration Form |
| /user | USER | Only user can view |
| /admin | ADMIN | Only admin can view |
| /all | ADMIN, USER | Only admin nd user can view |
| /anonymous | anonymous | Only anonymous can view |
| /accessDenied | - | Anyone can view |


### Login

| __Username__ | __Password__ |
| --- | --- |
| admin | adminPass |
| user1 | user1Pass |
| user2 | user2Pass |

__This is for education purpose only.__