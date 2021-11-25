# Spring-boot-mvc-db

__Featuriz__ guide to *Spring Framework 5*.

## Technology and Version

| __Spring__ | __Version__ |
| --- | --- |
| Spring Boot | 2.6.0 |
| Spring web | 5.3.13 |
| Spring Security | 5.6.0 |
| Spring Test | 5.3.13 |
| Thymeleaf | 3.0.12.RELEASE |
| Spring JPA | 2.6.0 |
| Mariadb | 2.7.4 |
| Lombok | 1.18.22 |
| Validation | 2.6.0 |
| Bootstrap | 5.0.2 |

- Database based authentication is provided.

#### How to use
This is a maven based spring boot project. Use maven to build this project and just run.  
_No other dependencies_

#### Database
Create an empty database with the name _tut_sb_mvc_db_ 
    - If already exist - truncate 
    - spring.sql.init.mode=never    __Note:__ Change to always, only for __first run__
    - data will be automatically added from data.sql from resources folder.
    
#### Error
If there is no role in DB table, then it will throw error while registering new User.

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
| sudhakar | sudhakar |
| user1 | user1Pass |
| user2 | user2Pass |

__This is for education purpose only.__