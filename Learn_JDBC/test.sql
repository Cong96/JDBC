--�����û���.
create table wangcc_user(
     id number(10),
     name binary(30),
     birthday date,
     salary number(10,3),
     primary key(id)
);
insert into wangcc_user values(1,'kobe',to_date('1978-08-23','yyyy-mm-dd'),20000.1);
select * from wangcc_user;
create sequence u_seq 
minvalue 1 maxvalue 99999999
start with 1
increment by 1
nocache 
nocycle
---������
create or replace trigger trg_inert_id
before insert on wangcc_user
for each row 
 begin
   select u_seq.nextval into:new.id from dual;
   end;
   insert into wangcc_user(name,birthday,salary) values('james',to_date('1978-08-23','yyyy-mm-dd'),20000.1);
   ----�洢����
create or replace procedure getname(
       u_id in number,
       u_name out varchar2
)
As
begin 
  select name into u_name from wangcc_user where id=u_id;
  end getname;
  
select * from wangcc_user;
-----����һ���α�
--����һ�������α�  
CREATE OR REPLACE PACKAGE PKG_PUB_UTILS IS  
    --��̬�α�  
    TYPE REFCURSOR IS REF CURSOR;  
END PKG_PUB_UTILS; 
---����һ���洢����
create or replace procedure u_getsalary 
(
       ret_code out number,
       ret_msg out varchar2,
       ret_cur out PKG_PUB_UTILS.refcursor,
       in_salary in number
       
)
is 
begin 
  ret_code:=0;
  ret_msg:='�����ɹ�';
  open ret_cur for
  select * from wangcc_user  where salary<in_salary;
  exception
    when others then
      ret_code:=1;
      ret_msg:='�������:' || SQLCODE || CHR(13) || '������Ϣ:' || SQLERRM;  
      end u_getsalary;
      --����������f_get_salary
      create or replace function f_get_salary(v_salary in number)
      return pkg_test.refcursor
      is
      stock_cursor pkg_test.refcursor;
      begin
        open stock_cursor for
        select * from wangcc_user where salary<v_salary;
        return stock_cursor;
        end;
  
select * from wangcc_user;

create table user_blob(
id number(10),
name varchar2(20),
image Blob,
resume Clob,
primary key(id)
);

create sequence blob_seq
minvalue 1 maxvalue 99999
start  with 1
 increment by 1
nocache
nocycle
select * from user_blob for update;