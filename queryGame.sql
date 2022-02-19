
CREATE database GAMETTT
GO
use GAMETTT
go
CREATE TABLE USERS
(
	USERID char(20) PRIMARY KEY,
	PASS CHAR(100) not null,
	NAME NVARCHAR(30)NOT NULL,
	ROLElEVEL INT CHECK(ROLELEVEL IN(0,1)),
	USERONL INT CHECK(USERONL IN (0,1)) DEFAULT 0
)
CREATE TABLE RESULTS
(
	RESULTID INT IDENTITY PRIMARY KEY,
	TIMERESULT DATETIME DEFAULT GETDATE(),
	USERMARK1 INT CHECK(USERMARK1>=0),
	USERMARK2 INT CHECK(USERMARK2>=0),
	USERNAME1 NVARCHAR(30),
	USERNAME2 NVARCHAR(30),
	RESULT NVARCHAR(50),
	USERID1 char(20) foreign key(USERID1) references USERS(USERID),
	USERID2 char(20) foreign key(USERID2) references USERS(USERID)
)
GO

INSERT INTO USERS(USERID,NAME,PASS,ROLElEVEL) VALUES('hiep', N'Hoàng Hiệp', '202cb962ac59075b964b07152d234b70', 1)--matkhau: 123
INSERT INTO USERS(USERID,NAME,PASS,ROLElEVEL) VALUES('tam', N'Hữu Tâm', '202cb962ac59075b964b07152d234b70', 0)--matkhau: 123
INSERT INTO RESULTS(USERMARK1,USERMARK2,USERNAME1,USERNAME2,RESULT,userid1,userid2) VALUES(2,1,N'Hữu Tâm',N'Hoàng Hiệp',N'Hoàng Hiệp','tam','hiep')

SELECT * FROM USERS
SELECT * FROM RESULTS

go
create PROC ThemUser(@useid varchar(20), @name nvarchar(50), @pass varchar(100), @rolelevel int, @kq nvarchar(500) output)
as
set @kq = ''
if exists(select * from USERS where UserID = @useid)
	set @kq = N'UserID đã có.'+ char(10)
if exists(select * from USERS where NAME = @name)
	set @kq += N'Tên người chơi đã có.'+ char(10)
if @rolelevel not in (0,1)
	set @kq += N'Quyền phải là: 0,1.'
if @kq = ''
begin
	insert into USERS(USERID, NAME, PASS, ROLElEVEL) values (@useid, @name, @pass, @rolelevel)
	set @kq = N'Đã đăng kí tài khoản mới thành công.'
end
go

alter proc ThemResults(@USERMARK1 INT, @USERMARK2 INT, @USERNAME1 NVARCHAR(50), @USERNAME2 NVARCHAR(50), 
@RESULT NVARCHAR(50), @userid1 char(20), @userid2 char(20), @KQ nvarchar(500) output)
as
set @kq = ''
if @USERMARK1 not in (0,1,2)
	set @kq = 'Điểm người chơi X phải là 0 hoặc 1 hoặc 2'+char(10)
if @USERMARK2 not in (0,1,2)
	set @kq += 'Điểm người chơi O phải là 0 hoặc 1 hoặc 2'+char(10)
if @kq = ''
	insert into RESULTS(USERMARK1,USERMARK2,USERNAME1,USERNAME2,RESULT,USERID1,USERID2) values(@USERMARK1,@USERMARK2,@USERNAME1,@USERNAME2,@RESULT,@USERID1,@USERID2)
go
