create
database blind_dog_date;
use
blind_dog_date;

drop table if exists `sample`;
create table `sample`
(
    id         bigint auto_increment primary key,
    title      text not null comment '제목',
    content    text not null comment '내용',
    created_at datetime null comment '등록날짜',
    updated_at datetime null comment '수정날짜'
) comment '예시 테이블';


drop table if exists `user`;
create table `user`
(
    id         bigint auto_increment primary key,
    name       varchar(16) not null comment '이름',
    email      varchar(32) not null comment '이메일',
    created_at datetime null comment '등록날짜',
    updated_at datetime null comment '수정날짜'
) comment '사용자 테이블';