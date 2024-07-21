# blindog-date
소개(dog)팅 - 반려동물 소개 프로젝트

### 기술셋
Spring boot 3.2.2</br>
webflux</br>
r2dbc</br>
coroutine</br>
mysql 8.4.0</br>


### mongdb 권한 생성 방법

use bdd;

db.createUser({
user:'theo',
pwd:'theo',
roles: [
{
    "role" : "userAdminAnyDatabase",
    "db" : "admin"
},
{
    "role" : "dbAdminAnyDatabase",
    "db" : "admin"
},
{
    "role" : "readWriteAnyDatabase",
    "db" : "admin"
}]});