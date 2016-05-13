-- reset if running again
DROP PROCEDURE search_all_users IF EXISTS;
DROP PROCEDURE search_all_merchants IF EXISTS;
DROP PROCEDURE search_all_goods IF EXISTS;
DROP PROCEDURE search_all_orders IF EXISTS;
DROP PROCEDURE search_all_carts IF EXISTS;
DROP TABLE goods IF EXISTS;
DROP TABLE merchants IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE orders IF EXISTS;
DROP TABLE carts IF EXISTS;

DROP PROCEDURE search_all_users IF EXISTS;
DROP PROCEDURE search_all_merchants IF EXISTS;
DROP PROCEDURE search_all_goods IF EXISTS;
DROP PROCEDURE search_all_orders IF EXISTS;
DROP PROCEDURE search_all_carts IF EXISTS;

CREATE TABLE goods
(
  mid     varchar(32) UNIQUE NOT NULL,      ---spid(10)+ 3 + 6
  mname   varchar(32) NOT NULL,
  mprice  bigint  NOT NULL,
  mspid   varchar(10) NOT NULL,
  msaled  int default 0,
  mmount  int default 0,
  mpress  varchar(32),
  mauthor varchar(32),
  mpubtime  TIMESTAMP default '0001-01-01 00:00:00.000000',
  mpath   varchar(32),
  mtag    varchar(32),
  CONSTRAINT PK_goods PRIMARY KEY (mid),
);
PARTITION TABLE goods ON COLUMN mid;
CREATE INDEX i_mtag ON goods (mtag);
CREATE PROCEDURE search_all_goods as SELECT * FROM goods;

INSERT INTO goods(mid, mname, mprice, mspid, mauthor, mpress,mtag) VALUES
('1000000000000000001','高性能MySQL',10800,'1000000000','Schwartz','人民邮电出版社','计算机|数据库|mysql');
INSERT INTO goods(mid, mname, mprice, mspid, mpress,mtag) VALUES
('1000000000000000002','高可用MySQL',9800,'1000000000','人民邮电出版社','计算机|数据库|mysql');
INSERT INTO goods(mid, mname, mprice, mspid, mpress,mtag) VALUES
('1000000000000000003','深入浅出MySQL',10800,'1000000000','人民邮电出版社','计算机|数据库|mysql');
INSERT INTO goods(mid, mname, mprice, mspid,mpress, mtag) VALUES
('1000000000000000004','C++ Primer',10800,'1000000000','人民邮电出版社','计算机|c++|');
INSERT INTO goods(mid, mname, mprice, mspid,mpress, mtag) VALUES
('1000000000000000005','STL源码剖析',10800,'1000000000','人民邮电出版社','计算机|c++|stl|');
INSERT INTO goods(mid, mname, mprice, mspid, mpress,mtag) VALUES
('1000000000000000000','深入理解Java虚拟机',10800,'1000000000','人民邮电出版社','计算机|java|');

INSERT INTO goods(mid, mname, mprice, mspid, mauthor, mpress,mtag) VALUES
('1000000001000000001','高性能MySQL',10800,'1000000001','Schwartz','人民邮电出版社','计算机|数据库|mysql');
INSERT INTO goods(mid, mname, mprice, mspid, mpress,mtag) VALUES
('1000000001000000002','高可用MySQL',9800,'1000000001','人民邮电出版社','计算机|数据库|mysql');
INSERT INTO goods(mid, mname, mprice, mspid, mpress,mtag) VALUES
('1000000001000000003','深入浅出MySQL',10800,'1000000001','人民邮电出版社','计算机|数据库|mysql');
INSERT INTO goods(mid, mname, mprice, mspid,mpress, mtag) VALUES
('1000000001000000004','C++ Primer',10800,'1000000001','人民邮电出版社','计算机|c++|');
INSERT INTO goods(mid, mname, mprice, mspid,mpress, mtag) VALUES
('1000000001000000005','STL源码剖析',10800,'1000000001','人民邮电出版社','计算机|c++|stl|');
INSERT INTO goods(mid, mname, mprice, mspid, mpress,mtag) VALUES
('1000000001000000000','深入理解Java虚拟机',10800,'1000000001','人民邮电出版社','计算机|java|');

INSERT INTO goods(mid, mname, mprice, mspid, mauthor, mpress,mtag) VALUES
('1000000002000000001','高性能MySQL',10800,'1000000002','Schwartz','人民邮电出版社','计算机|数据库|mysql');
INSERT INTO goods(mid, mname, mprice, mspid, mpress,mtag) VALUES
('1000000002000000002','高可用MySQL',9800,'1000000002','人民邮电出版社','计算机|数据库|mysql');
INSERT INTO goods(mid, mname, mprice, mspid, mpress,mtag) VALUES
('1000000002000000003','深入浅出MySQL',10800,'1000000002','人民邮电出版社','计算机|数据库|mysql');
INSERT INTO goods(mid, mname, mprice, mspid,mpress, mtag) VALUES
('1000000002000000004','C++ Primer',10800,'1000000002','人民邮电出版社','计算机|c++|');
INSERT INTO goods(mid, mname, mprice, mspid,mpress, mtag) VALUES
('1000000002000000005','STL源码剖析',10800,'1000000002','人民邮电出版社','计算机|c++|stl|');
INSERT INTO goods(mid, mname, mprice, mspid, mpress,mtag) VALUES
('1000000002000000000','深入理解Java虚拟机',10800,'1000000002','人民邮电出版社','计算机|java|');

CREATE TABLE merchants
(
  spid      varchar(10) UNIQUE NOT  NULL,   --- 10
  sppasswd  varchar(32) NOT NULL,
  spname    varchar(32) NOT NULL,
  spamt     bigint default 0,
  sprank    tinyint default 0,
  sppthone  varchar(12),
  spaddress varchar(64),
  PRIMARY KEY(spid),
);
PARTITION TABLE merchants ON COLUMN spid;
CREATE INDEX i_spname ON merchants (spname);
CREATE PROCEDURE search_all_merchants as SELECT * FROM merchants;

INSERT INTO merchants(spid,sppasswd, spname, sppthone) VALUES
('1000000000','123456' ,'新华书店', '18888889999');
INSERT INTO merchants(spid,sppasswd, spname, sppthone) VALUES
('1000000001','123456' ,'齐鲁书局', '18888889999');
INSERT INTO merchants(spid,sppasswd, spname, sppthone) VALUES
('1000000002','123456' ,'读一斋', '18888889999');


CREATE TABLE users
(
  uid       varchar(32) UNIQUE NOT NULL,  ---12
  upasswd   varchar(32) NOT NULL,
  uname     varchar(64) NOT NULL,
  usex      varchar(2),
  uphone    varchar(12) NOT NULL,
  umail     varchar(64) NOT NULL,
  uamt      bigint default 100000,
  upayed    bigint default 0,
  uage      int default 0,
  PRIMARY KEY(uid),
);
PARTITION TABLE users ON COLUMN uid;
CREATE INDEX i_uphone ON users(uphone);
CREATE PROCEDURE search_all_users as SELECT * FROM users;

INSERT INTO users(uid, upasswd,uname, uphone, umail) VALUES
('100000000000', '123456','foogle', '18182428522', '1071380275@qq.com');
INSERT INTO users(uid, upasswd,uname, uphone, umail) VALUES
('100000000001', '123456','foo', '18182428522', '1071380275@qq.com');

CREATE TABLE orders
(
  did       varchar(32) UNIQUE NOT NULL,      ---uid(12)+date(8)+addnum(6)
  uid       varchar(32) NOT NULL,
  recname   varchar(32) NOT NULL,
  recaddr   varchar(128)  NOT NULL,
  recphtone varchar(12) NOT NULL,
  passcode  varchar(10),
  dprice    bigint  NOT NULL,
  dstate    tinyint default 1,		---1:代支付 2：支付成功 3：支付失败
  createtime  TIMESTAMP default '1000-01-01 00:00:00',
  PRIMARY KEY(did),
);
PARTITION TABLE orders ON COLUMN did;
CREATE INDEX i_uc ON orders(uid, createtime);
CREATE PROCEDURE search_all_orders as SELECT * FROM orders;

INSERT INTO orders(did, uid, recname, recaddr, recphtone, dprice) VALUES
('10000000000020160404000001', '100000000000', '张三', '陕西省西安市西安电子科技大学南校区', '18800001111', 25000);


CREATE TABLE carts
(
  cid       varchar(32) UNIQUE NOT NULL,      ---spid(10)+date(8)+addnum(6)
  uid       varchar(32) NOT NULL,
  mid       varchar(32) NOT NULL,
  spid      varchar(10) NOT  NULL,
  mname     varchar(32) NOT NULL,
  mprice    bigint  NOT NULL,
  cnum      int default 1,
  isbuy     tinyint default 0,
  paytime   TIMESTAMP default '1000-01-01 00:00:00',
  PRIMARY KEY (cid),
);
PARTITION TABLE carts ON COLUMN cid;
CREATE INDEX i_up ON carts(uid, paytime);
CREATE PROCEDURE search_all_carts as SELECT * FROM carts;

INSERT INTO carts(cid, uid, mid, spid, mname, mprice,isbuy) VALUES
('100000000020160606111111','100000000000', '1000000000000000001','1000000000','高性能MySQL', 10800, 1);
INSERT INTO carts(cid, uid, mid, spid, mname, mprice) VALUES
('100000000020160606111112','100000000000', '1000000000000000002','1000000000','高可用MySQL', 9800);
INSERT INTO carts(cid,uid, mid, spid, mname, mprice) VALUES
('100000000020160606111113','100000000000', '1000000000000000003','1000000000','深入浅出MySQL', 10800);
INSERT INTO carts(cid,uid, mid, spid, mname, mprice) VALUES
('100000000020160606111114','100000000000', '1000000001000000001','1000000001','高性能MySQL', 10800);
INSERT INTO carts(cid,uid, mid, spid, mname, mprice) VALUES
('100000000020160606111155','100000000000', '1000000001000000002','1000000001','高可用MySQL', 9800);
INSERT INTO carts(cid,uid, mid, spid, mname, mprice) VALUES
('100000000020160606111115','100000000000', '1000000001000000003','1000000001','深入浅出MySQL', 10800);
INSERT INTO carts(cid,uid, mid, spid, mname, mprice) VALUES
('100000000020160606111116','100000000000', '1000000002000000001','1000000002','高性能MySQL', 10800);
INSERT INTO carts(cid,uid, mid, spid, mname, mprice) VALUES
('100000000020160606111117','100000000000', '1000000002000000002','1000000002','高可用MySQL', 9800);
INSERT INTO carts(cid,uid, mid, spid, mname, mprice) VALUES
('100000000020160606111118','100000000000', '1000000002000000003','1000000002','深入浅出MySQL', 10800);
