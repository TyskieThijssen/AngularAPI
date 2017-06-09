drop database if exists AngularAPI;

create database AngularAPI;

use AngularAPI;

create table users(
    userName	varchar(50)	not null,
    password	varchar(50)	not null,
    primary key pk_users (userName)
);