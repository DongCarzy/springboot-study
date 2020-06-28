create table user
(
  id       int(11) auto_increment
  comment '主键'
    primary key,
  username varchar(255) not null
  comment '用户名',
  password varchar(255) not null
  comment '密码',
  age      int(11)          null
  comment '年龄'
)
