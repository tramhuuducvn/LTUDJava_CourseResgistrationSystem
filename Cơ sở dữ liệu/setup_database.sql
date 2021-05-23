---> Giải thích Cơ sở dữ liệu

/*
+ Giáo vụ: 
	- magv: primary key, char(12) -- mỗi giáo vụ có một mã duy nhất để phân biệt với các giáo vụ khác
	- họ tên: nvachar(50)
	- tài khoản: duy nhất, char(50) -- mỗi giáo vụ có một tên tài khoản duy nhất
	- mật khẩu: char(50)

+ Sinh viên: 
	- mssv: primary key, char(12) -- mỗi sinh viên có một mã số sinh viên duy nhất để phần biệt với các sinh viên khác
	- họ tên: nvachar(50)
	- giới tính: nam hoặc nữ
	- tài khoản: duy nhất, char(50) -- tài khoản là duy nhất
	- mật khẩu: char(50)
	- lớp: char(7)
	- email: duy nhất, char(50)

+ Học kỳ:
	- mã học kỳ: char(3) ví dụ HK1, HK2,... -- mã học kỳ là duy nhất để phân biệt với các học kỳ khác trong cùng năm học
	- năm học: int
	- ngày bắt đầu: date
	- ngày kết thúc: date
	~primary key(năm học, mã học kỳ) 

+ Lớp học:
	- mã lớp: char(7), primary key -- mỗi lớp có một mã duy nhất
	- sỉ số: int, thuộc tính suy diễn
	- sỉ số nam: int, thuộc tính suy diễn
	- sỉ số nữ: int, thuộc tính suy diễn

+ Môn học:
	- mã môn học: char(10), primary key -- mỗi môn học có một mã duy nhất
	- tên môn học: nvachar(100)
	- số tín chỉ: int

-- Học kỳ đó sẽ có môn học nào?
-- Môn đó học ở phòng nào, mấy giờ, thứ mấy trong tuần?
-- Môn đó thì có sinh viên nào học? 
-- Bảng sau sẽ làm việc đó
-- Bảng sẽ tự động thêm, cập nhật, xóa và sửa khi sinh viên đăng ký học phần
+ Chi tiết: -- thực thể yếu
	- năm học: int
	- mã học kỳ: char(3)
	- mã môn học: char(10)
	- giáo viên: nvarchar(50)
	- slot: int
	- phòng học: char(5)
	- ngày thứ: int phạm vi giá trị chỉ trong  (2, 3, 4, 5, 6) quy ước thứ 7, cn nghĩ nên ko xét
	- ca học: int phạm vi giá trị chỉ trong (1,2,3,4)
	- mssv đăng ký học: char(12)
	-- Quy ước là mỗi (năm học, học kỳ, môn học, phòng học, ca học) có duy nhất một giáo viên dạy và số slots duy nhất
	-- nhưng có thể có nhiều sinh viên học ở bảng quan hệ này hầu như là cả một là khóa chính 

+ Đăng ký: -- bảng này chỉ chứa duy nhất một bộ, hay một dòng vì chỉ có duy nhất một học kỳ của một năm học nào đó được đặt làm học kỳ hiện tại
	- năm học hiện tại: int
	- học kỳ hiện tại: char(3)
	- thời gian bắt đầu đăng ký học phần: date time
	- thời gian kết thúc đăng ký học phần: date time
*/

create database CourseRegistrationSystem;

create table GiaoVu (
  magv char(12) primary key,
  hoten nvarchar(50),
  taikhoan char(50),
  matkhau char(50)
);

create table SinhVien (
  mssv char(12) primary key,
  hoten nvarchar(50),
  gioitinh nvarchar(3) check (gioitinh in (N'Nam',N'Nữ')),
  taikhoan char(50),
  matkhau char(50),
  lop char(7),
  email char(50)
);

create table HocKy (
  namhoc int,
  mahocky char(3),
  ngaybatdau date,
  ngayketthuc date,
  primary key(namhoc, mahocky)
);

create table LopHoc (
  malop char(7) primary key,
  siso int,
  sisonam int,
  sisonu  int
);

create table MonHoc (
  mamonhoc char(10) primary key,
  tenmonhoc nvarchar(100),
  sotinchi int
);

create table ChiTiet (
  namhoc int,
  hocky char(3),
  mamonhoc char(10),
  giaovien nvarchar(50),
  slot int,
  phonghoc char(5),
  ngaythu int,
  cahoc int,
  mssv char(12)
);

create table DangKy (
  namhochientai int,
  hockyhientai char(3),
  thoigianbatdaudangky datetime,
  thoigianketthucdangky datetime,
  primary key(namhochientai, hockyhientai)
);

alter table SinhVien add constraint FK_SV_LOP foreign key(lop) references LopHoc(malop);
alter table ChiTiet add constraint FK_CT_SV foreign key(mssv) references SinhVien(mssv);
alter table ChiTiet add constraint FK_CT_HK foreign key(namhoc, hocky) references HocKy(namhoc, mahocky);
alter table ChiTiet add constraint FK_CT_MH foreign key(mamonhoc) references MonHoc(mamonhoc);

-- lưu ý các thông tin sau chỉ mang mục đích học thuật, tham khảo và thí dụ
-- thông tin được lấy ngẫu nhiên từ file excel của các bảng điểm môn học em đã học được thầy cô chia sẻ cho lớp

insert into GiaoVu(magv, hoten, taikhoan, matkhau) values
('100', N'Nguyễn Văn Khiết', 'nguyenvankhiet', 'nguyenvankhiet'),
('101', N'Mai Anh Tuấn', 'maianhtuan', 'maianhtuan'),
('102', N'Hồ Tuấn Thanh', 'hoanhtuan', 'hoanhtuan');

insert into LopHoc(malop, siso, sisonam, sisonu) values
('19CTT1', 0, 0, 0),
('19CTT2', 0, 0, 0),
('19CTT3', 0, 0, 0),
('19CTT4', 0, 0, 0);

insert into SinhVien(mssv, hoten, gioitinh, taikhoan, matkhau, lop, email) values
('19120120', N'Phạm Hữu Phước', N'Nam', '19120120', '19120120', '19CTT1', '19120120@student.hcmus.edu.vn'),
('19120142', N'Nguyễn Thị Phương Trang', N'Nữ', '19120142', '19120142', '19CTT1', '19120142@student.hcmus.edu.vn'),
('19120147', N'Lê Anh Tuấn', N'Nam', '19120147', '19120147', '19CTT1', '19120147@student.hcmus.edu.vn'),
('19120153', N'Lê Đoàn Phương Uyên', N'Nữ', '19120153', '19120153', '19CTT2', '19120153@student.hcmus.edu.vn'),
('19120151', N'Nguyễn Trí Tuệ', N'Nam', '19120151', '19120151', '19CTT2', '19120151@student.hcmus.edu.vn'),
('19120160', N'Đàm Thị Xuân Ý', N'Nữ', '19120160', '19120160', '19CTT2', '19120160@student.hcmus.edu.vn'),
('19120477', N'Lê Văn Định', N'Nam', '19120477', '19120477', '19CTT3', '19120477@student.hcmus.edu.vn'),
('19120484', N'Trầm Hữu Đức', N'Nam', '19120484', '19120484', '19CTT3', '19120484@student.hcmus.edu.vn'),
('19120495', N'Nguyễn Nhật Duy', N'Nam', '19120495', '19120495', '19CTT3', '19120495@student.hcmus.edu.vn'),
('19120707', N'Đào Xuân Tung', N'Nam', '19120707', '19120707', '19CTT4', '19120707@student.hcmus.edu.vn'),
('19120729', N'Bùi Ngọc Thảo Vy', N'Nữ', '19120729', '19120729', '19CTT4', '19120729@student.hcmus.edu.vn'),
('19120617', N'Mạch Vi Phong', N'Nam', '19120617', '19120617', '19CTT4', '19120617@student.hcmus.edu.vn');

insert into HocKy(namhoc, mahocky, ngaybatdau, ngayketthuc) values
(2020, 'HK1', '2020-01-01', '2020-05-31'),
(2020, 'HK2', '2020-07-01', '2020-11-30');

insert into MonHoc(mamonhoc, tenmonhoc, sotinchi) values
('THTH', N'Toán học tổ hợp', 4),
('CSDL', N'Cơ sở dữ liệu', 4),
('PPLTHDT', N'Phương pháp lập trình hướng đối tượng', 4),
('LTUDJAVA', N'Lập trình ứng dụng Java', 4);

insert into DangKy(namhochientai, hockyhientai, thoigianbatdaudangky, thoigianketthucdangky) values
(2020, 'HK2', '2020-06-15 09:00:00', '2020-06-20 18:00:00');

update LopHoc
set siso = (select count(mssv) from SinhVien where lop = '19CTT1' group by lop)
where malop = '19CTT1';

update LopHoc
set sisonam = (select count(mssv) from SinhVien where lop = '19CTT1' and gioitinh = N'Nam' group by lop)
where malop = '19CTT1';

update LopHoc
set sisonu = (select count(mssv) from SinhVien where lop = '19CTT1' and gioitinh = N'Nữ' group by lop)
where malop = '19CTT1';

update LopHoc
set siso = (select count(mssv) from SinhVien where lop = '19CTT2' group by lop)
where malop = '19CTT2';

update LopHoc
set sisonam = (select count(mssv) from SinhVien where lop = '19CTT2' and gioitinh = N'Nam' group by lop)
where malop = '19CTT2';

update LopHoc
set sisonu = (select count(mssv) from SinhVien where lop = '19CTT2' and gioitinh = N'Nữ' group by lop)
where malop = '19CTT2';

update LopHoc
set siso = (select count(mssv) from SinhVien where lop = '19CTT3' group by lop)
where malop = '19CTT3';

update LopHoc
set sisonam = (select count(mssv) from SinhVien where lop = '19CTT3' and gioitinh = N'Nam' group by lop)
where malop = '19CTT3';

update LopHoc
set sisonu = (select count(mssv) from SinhVien where lop = '19CTT3' and gioitinh = N'Nữ' group by lop)
where malop = '19CTT3';

update LopHoc
set siso = (select count(mssv) from SinhVien where lop = '19CTT4' group by lop)
where malop = '19CTT4';

update LopHoc
set sisonam = (select count(mssv) from SinhVien where lop = '19CTT4' and gioitinh = N'Nam' group by lop)
where malop = '19CTT4';

update LopHoc
set sisonu = (select count(mssv) from SinhVien where lop = '19CTT4' and gioitinh = N'Nữ' group by lop)
where malop = '19CTT4';

update LopHoc
set siso = 0
where siso is null;

update LopHoc
set sisonam = 0
where sisonam is null;

update LopHoc
set sisonu = 0
where sisonu is null;