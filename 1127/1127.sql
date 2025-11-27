use sampledb;

insert into student (name, dept, id) values ('김철수', '컴퓨터시스템', '1091011'),
('최고봉', '멀티미디어', '0792012'),
('이기자', '컴퓨터공학', '0494013');

select name, dept, id 
from student 
where dept='컴퓨터공학';

update student set dept='컴퓨터공학' where name='최고봉';

delete from student where name='김철수';
delete from student where name='최고봉';
delete from student where name='이기자';
delete from student where name='Hong';


select *
from student;

