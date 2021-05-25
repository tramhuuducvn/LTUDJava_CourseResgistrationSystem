create database CourseRegistrationSystem;

-- drop table DangKy;
-- drop table SinhVien;
-- drop table LopHoc;
-- drop table GiaoVu;
-- drop table HocKy;
-- drop table MonHoc;
-- drop table LichHoc;

-- mỗi giáo vụ có một magv để phân biệt với các giáo vụ khác và taikhoan là duy nhất
create table GiaoVu (
  magv char(12) primary key, 
  hoten nvarchar(50),
  taikhoan char(50),
  matkhau char(50),
  unique(taikhoan)
);

-- mỗi sinh viên có một mssv để phân biệt với các giáo vụ khác và taikhoan là duy nhất
-- và thuộc về duy nhất một lớp học nào đó
create table SinhVien (
  mssv char(12) primary key,
  hoten nvarchar(50),
  gioitinh nvarchar(3) check (gioitinh in (N'Nam',N'Nữ')),
  taikhoan char(50),
  matkhau char(50),
  lop char(7),
  email char(50),
  unique(taikhoan)
);

-- học kỳ có các mã học kỳ để phân biệt với các học kỳ khác trong cùng một năm học
create table HocKy (
  namhoc int,
  mahocky char(3),
  ngaybatdau date,
  ngayketthuc date,
  primary key(namhoc, mahocky)
);

-- mỗi lớp học có một mã lớp để phân biệt và có ít nhất một sinh viên
create table LopHoc (
  malop char(7) primary key,
  siso int,
  sisonam int,
  sisonu  int
);

-- mỗi môn học có một mã môn học riêng để phân biệt
create table MonHoc (
  mamonhoc char(10) primary key,
  tenmonhoc nvarchar(100),
  sotinchi int
);

create table DangKy(
	mathongtin int,
  mssv char(12),
  primary key(mathongtin, mssv)
);
-- cứ mỗi thông tin đăng ký sẽ có một mã thông tin để phân biệt
-- mục đich là giảm số thuộc tính của khóa chính
create table LichHoc (
	mathongtin int primary key,
  namhoc int,
  hocky char(3),
  mamonhoc char(10),
  giaovien nvarchar(50),
  phonghoc char(5),
  ngaythu int,
  cahoc int,
  slots int
);

alter table SinhVien add constraint FK_SV_LOP foreign key(lop) references LopHoc(malop);
alter table DangKy add constraint FK_DK_SV foreign key(mssv) references SinhVien(mssv);
alter table DangKy add constraint FK_DK_LH foreign key(mathongtin) references LichHoc(mathongtin);
alter table LichHoc add constraint FK_LH_HK foreign key(namhoc, hocky) references HocKy(namhoc, mahocky);
alter table LichHoc add constraint FK_LH_MH foreign key(mamonhoc) references MonHoc(mamonhoc);


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