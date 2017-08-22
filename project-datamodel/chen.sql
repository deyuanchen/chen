/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2017/8/22 21:06:12                           */
/*==============================================================*/



drop trigger tib_upms_log
/

drop trigger tib_upms_organization
/

drop trigger tib_upms_permission
/

drop trigger tib_upms_role
/

drop trigger tib_upms_role_permission
/

drop trigger tib_upms_system
/

drop trigger tib_upms_user
/

drop trigger tib_upms_user_permission
/

drop trigger tib_upms_user_role
/

drop trigger tib_user_organization
/

alter table upms_permission
   drop constraint FK_UPMS_PER_REFERENCE_UPMS_SYS
/

alter table upms_role_permission
   drop constraint FK_UPMS_ROL_REFERENCE_UPMS_PER
/

alter table upms_role_permission
   drop constraint FK_UPMS_ROL_REFERENCE_UPMS_ROL
/

alter table upms_user_permission
   drop constraint FK_UPMS_USE_REFERENCE_UPMS_USE
/

alter table upms_user_permission
   drop constraint FK_UPMS_USE_REFERENCE_UPMS_PER
/

alter table upms_user_role
   drop constraint FK_UPMS_USE_REFERENCE_UPMS_US2
/

alter table upms_user_role
   drop constraint FK_UPMS_USE_REFERENCE_UPMS_ROL
/

alter table user_organization
   drop constraint FK_USER_ORG_REFERENCE_UPMS_USE
/

alter table user_organization
   drop constraint FK_USER_ORG_REFERENCE_UPMS_ORG
/

drop table upms_log cascade constraints
/

drop table upms_organization cascade constraints
/

drop table upms_permission cascade constraints
/

drop table upms_role cascade constraints
/

drop table upms_role_permission cascade constraints
/

drop table upms_system cascade constraints
/

drop table upms_user cascade constraints
/

drop table upms_user_permission cascade constraints
/

drop table upms_user_role cascade constraints
/

drop table user_organization cascade constraints
/

drop sequence log_id
/

drop sequence organization_id
/

drop sequence permission_id
/

drop sequence role_id
/

drop sequence role_permission_id
/

drop sequence system_id
/

drop sequence user_id
/

drop sequence user_organization_id
/

drop sequence user_permission_id
/

drop sequence user_role_id
/

create sequence log_id
increment by 1
start with 1
 minvalue 1
 maxvalue 999999
/

create sequence organization_id
increment by 1
start with 1
 minvalue 1
 maxvalue 999999
/

create sequence permission_id
increment by 1
start with 1
 minvalue 1
 maxvalue 999999
/

create sequence role_id
increment by 1
start with 1
 minvalue 1
 maxvalue 999999
/

create sequence role_permission_id
increment by 1
start with 1
 minvalue 1
 maxvalue 99999
/

create sequence system_id
increment by 1
start with 1
 minvalue 1
 maxvalue 9999999
/

create sequence user_id
increment by 1
start with 1
 minvalue 1
 maxvalue 999999
/

create sequence user_organization_id
increment by 1
start with 1
 minvalue 1
 maxvalue 99999
/

create sequence user_permission_id
increment by 1
start with 1
 minvalue 1
 maxvalue 9999999
/

create sequence user_role_id
increment by 1
start with 1
 minvalue 1
 maxvalue 999999
/

/*==============================================================*/
/* Table: upms_log                                              */
/*==============================================================*/
create table upms_log  (
   log_id               INT                             not null,
   description          VARCHAR(100),
   username             VARCHAR(20),
   start_time           TIMESTAMP,
   spend_time           INT,
   base_path            VARCHAR(100),
   uri                  VARCHAR(200),
   url                  VARCHAR(200),
   method               VARCHAR(10),
   parameter            CLOB,
   user_agent           VARCHAR(200),
   ip                   VARCHAR(30),
   result               CLOB,
   permissions          VARCHAR(100),
   constraint PK_UPMS_LOG primary key (log_id)
)
/

comment on table upms_log is
'操作日志'
/

comment on column upms_log.log_id is
'编号'
/

comment on column upms_log.description is
'操作描述'
/

comment on column upms_log.username is
'操作用户'
/

comment on column upms_log.start_time is
'操作时间'
/

comment on column upms_log.spend_time is
'消耗时间'
/

comment on column upms_log.base_path is
'根路径'
/

comment on column upms_log.uri is
'URI'
/

comment on column upms_log.url is
'URL'
/

comment on column upms_log.method is
'请求类型'
/

comment on column upms_log.parameter is
'请求参数'
/

comment on column upms_log.user_agent is
'用户标识'
/

comment on column upms_log.ip is
'IP地址'
/

comment on column upms_log.result is
'响应结果'
/

comment on column upms_log.permissions is
'权限值'
/

/*==============================================================*/
/* Table: upms_organization                                     */
/*==============================================================*/
create table upms_organization  (
   organization_id      INT                             not null,
   pid                  INT,
   name                 VARCHAR(20),
   description          VARCHAR(100),
   ctime                TIMESTAMP,
   constraint PK_UPMS_ORGANIZATION primary key (organization_id)
)
/

comment on table upms_organization is
'组织'
/

comment on column upms_organization.organization_id is
'编号'
/

comment on column upms_organization.pid is
'所属上级'
/

comment on column upms_organization.name is
'组织名称'
/

comment on column upms_organization.description is
'组织描述'
/

comment on column upms_organization.ctime is
'创建时间'
/

/*==============================================================*/
/* Table: upms_permission                                       */
/*==============================================================*/
create table upms_permission  (
   permission_id        int                             not null,
   system_id            int,
   pid                  int,
   name                 VARCHAR(20),
   type                 int,
   permission_value     VARCHAR(50),
   uri                  VARCHAR(100),
   icon                 VARCHAR(50),
   status               int,
   ctime                TIMESTAMP,
   orders               int,
   constraint PK_UPMS_PERMISSION primary key (permission_id)
)
/

comment on table upms_permission is
'权限'
/

comment on column upms_permission.permission_id is
'编号'
/

comment on column upms_permission.system_id is
'所属系统'
/

comment on column upms_permission.pid is
'所属上级'
/

comment on column upms_permission.name is
'名称'
/

comment on column upms_permission.type is
'类型（1:目录, 2::菜单 , 3:按钮）'
/

comment on column upms_permission.permission_value is
'权限值'
/

comment on column upms_permission.uri is
'路径'
/

comment on column upms_permission.icon is
'图标'
/

comment on column upms_permission.status is
'状态（0:禁止, 1:正常）'
/

comment on column upms_permission.ctime is
'创建时间'
/

comment on column upms_permission.orders is
'排序'
/

/*==============================================================*/
/* Table: upms_role                                             */
/*==============================================================*/
create table upms_role  (
   role_id              int                             not null,
   name                 VARCHAR(20),
   title                VARCHAR(20),
   description          VARCHAR(1000),
   ctime                TIMESTAMP,
   orders               int,
   constraint PK_UPMS_ROLE primary key (role_id)
)
/

comment on table upms_role is
'角色'
/

comment on column upms_role.role_id is
'编号'
/

comment on column upms_role.name is
'角色名称'
/

comment on column upms_role.title is
'角色标题'
/

comment on column upms_role.description is
'角色描述'
/

comment on column upms_role.ctime is
'创建时间'
/

comment on column upms_role.orders is
'排序'
/

/*==============================================================*/
/* Table: upms_role_permission                                  */
/*==============================================================*/
create table upms_role_permission  (
   role_permission_id   int                             not null,
   role_id              int,
   permission_id        int,
   constraint PK_UPMS_ROLE_PERMISSION primary key (role_permission_id)
)
/

comment on table upms_role_permission is
'角色权限关联表'
/

comment on column upms_role_permission.role_permission_id is
'编号'
/

comment on column upms_role_permission.role_id is
'角色编号'
/

comment on column upms_role_permission.permission_id is
'权限编号'
/

/*==============================================================*/
/* Table: upms_system                                           */
/*==============================================================*/
create table upms_system  (
   system_id            int                             not null,
   icon                 VARCHAR(50),
   banner               VARCHAR(50),
   theme                VARCHAR(50),
   basepath             VARCHAR(100),
   status               int,
   name                 VARCHAR(20),
   title                VARCHAR(20),
   descrition           VARCHAR(300),
   ctime                TIMESTAMP,
   orders               int,
   constraint PK_UPMS_SYSTEM primary key (system_id)
)
/

comment on table upms_system is
'系统'
/

comment on column upms_system.system_id is
'编号'
/

comment on column upms_system.icon is
'图标'
/

comment on column upms_system.banner is
'背景'
/

comment on column upms_system.theme is
'主题'
/

comment on column upms_system.basepath is
'根目录'
/

comment on column upms_system.status is
'状态（-1:黑名单，1：正常）'
/

comment on column upms_system.name is
'系统名称'
/

comment on column upms_system.title is
'系统标题'
/

comment on column upms_system.descrition is
'系统描述'
/

comment on column upms_system.ctime is
'创建时间'
/

comment on column upms_system.orders is
'排序'
/

/*==============================================================*/
/* Table: upms_user                                             */
/*==============================================================*/
create table upms_user  (
   user_id              int                             not null,
   username             VARCHAR(20),
   password             VARCHAR(32),
   salt                 VARCHAR(32),
   realname             VARCHAR(20),
   avatar               VARCHAR(50),
   phone                VARCHAR(20),
   email                VARCHAR(50),
   sex                  int,
   locked               int,
   ctime                TIMESTAMP,
   constraint PK_UPMS_USER primary key (user_id)
)
/

comment on table upms_user is
' 用户'
/

comment on column upms_user.user_id is
'编号'
/

comment on column upms_user.username is
'账号'
/

comment on column upms_user.password is
'密码MD5(密码+盐)'
/

comment on column upms_user.salt is
'盐'
/

comment on column upms_user.realname is
'姓名'
/

comment on column upms_user.avatar is
'头像'
/

comment on column upms_user.phone is
'电话'
/

comment on column upms_user.email is
'邮箱'
/

comment on column upms_user.sex is
'性别'
/

comment on column upms_user.locked is
'状态'
/

comment on column upms_user.ctime is
'创建时间'
/

/*==============================================================*/
/* Table: upms_user_permission                                  */
/*==============================================================*/
create table upms_user_permission  (
   user_permission_id   int                             not null,
   user_id              int,
   permission_id        int,
   type                 int,
   constraint PK_UPMS_USER_PERMISSION primary key (user_permission_id)
)
/

comment on table upms_user_permission is
'用户权限关联表'
/

comment on column upms_user_permission.user_permission_id is
'编号'
/

comment on column upms_user_permission.user_id is
'用户编号'
/

comment on column upms_user_permission.permission_id is
'权限编号'
/

comment on column upms_user_permission.type is
'权限类型（-1:减权限, 1: 增权限）'
/

/*==============================================================*/
/* Table: upms_user_role                                        */
/*==============================================================*/
create table upms_user_role  (
   user_role_id         int                             not null,
   user_id              int                             not null,
   role_id              int                             not null
)
/

comment on table upms_user_role is
' 用户角色关联表'
/

comment on column upms_user_role.user_role_id is
'编号'
/

comment on column upms_user_role.user_id is
'用户编号'
/

comment on column upms_user_role.role_id is
'角色编号'
/

/*==============================================================*/
/* Table: user_organization                                     */
/*==============================================================*/
create table user_organization  (
   user_organization_id int                             not null,
   user_id              int,
   organization_id      int,
   constraint PK_USER_ORGANIZATION primary key (user_organization_id)
)
/

comment on table user_organization is
'用户组织关联表'
/

comment on column user_organization.user_organization_id is
'编号'
/

comment on column user_organization.user_id is
'用户编号'
/

comment on column user_organization.organization_id is
'组织编号'
/

alter table upms_permission
   add constraint FK_UPMS_PER_REFERENCE_UPMS_SYS foreign key (system_id)
      references upms_system (system_id)
/

alter table upms_role_permission
   add constraint FK_UPMS_ROL_REFERENCE_UPMS_PER foreign key (permission_id)
      references upms_permission (permission_id)
/

alter table upms_role_permission
   add constraint FK_UPMS_ROL_REFERENCE_UPMS_ROL foreign key (role_id)
      references upms_role (role_id)
/

alter table upms_user_permission
   add constraint FK_UPMS_USE_REFERENCE_UPMS_USE foreign key (user_id)
      references upms_user (user_id)
/

alter table upms_user_permission
   add constraint FK_UPMS_USE_REFERENCE_UPMS_PER foreign key (permission_id)
      references upms_permission (permission_id)
/

alter table upms_user_role
   add constraint FK_UPMS_USE_REFERENCE_UPMS_US2 foreign key (user_id)
      references upms_user (user_id)
/

alter table upms_user_role
   add constraint FK_UPMS_USE_REFERENCE_UPMS_ROL foreign key (role_id)
      references upms_role (role_id)
/

alter table user_organization
   add constraint FK_USER_ORG_REFERENCE_UPMS_USE foreign key (user_id)
      references upms_user (user_id)
/

alter table user_organization
   add constraint FK_USER_ORG_REFERENCE_UPMS_ORG foreign key (organization_id)
      references upms_organization (organization_id)
/


create trigger tib_upms_log before insert
on upms_log for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "log_id" uses sequence log_id
    select log_id.NEXTVAL INTO :new.log_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create trigger tib_upms_organization before insert
on upms_organization for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "organization_id" uses sequence organization_id
    select organization_id.NEXTVAL INTO :new.organization_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create trigger tib_upms_permission before insert
on upms_permission for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "permission_id" uses sequence permission_id
    select permission_id.NEXTVAL INTO :new.permission_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create trigger tib_upms_role before insert
on upms_role for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "role_id" uses sequence role_id
    select role_id.NEXTVAL INTO :new.role_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create trigger tib_upms_role_permission before insert
on upms_role_permission for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "role_permission_id" uses sequence role_permission_id
    select role_permission_id.NEXTVAL INTO :new.role_permission_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create trigger tib_upms_system before insert
on upms_system for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "system_id" uses sequence system_id
    select system_id.NEXTVAL INTO :new.system_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create trigger tib_upms_user before insert
on upms_user for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "user_id" uses sequence user_id
    select user_id.NEXTVAL INTO :new.user_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create trigger tib_upms_user_permission before insert
on upms_user_permission for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "user_permission_id" uses sequence user_permission_id
    select user_permission_id.NEXTVAL INTO :new.user_permission_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create trigger tib_upms_user_role before insert
on upms_user_role for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "user_role_id" uses sequence user_role_id
    select user_role_id.NEXTVAL INTO :new.user_role_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create trigger tib_user_organization before insert
on user_organization for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column "user_organization_id" uses sequence user_organization_id
    select user_organization_id.NEXTVAL INTO :new.user_organization_id from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/

