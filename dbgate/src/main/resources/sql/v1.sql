create table account(
    id int primary key auto_increment,#auto_increment只是MySQL特有的
    uuid varchar(20) ,
    userName varchar(20),
    password varchar(10),
    emailAddress varchar(10),
    nickName varchar(20),
    lastUpdateTime date,
    avatarURL varchar(20),
    phoneNnumber varchar(20)
);