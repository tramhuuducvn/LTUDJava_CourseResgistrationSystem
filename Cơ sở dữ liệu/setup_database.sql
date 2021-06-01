
 create database CourseRegistrationSystem;

# drop table DangKy;
# drop table ChiTiet;
# drop table DotDangKyHocPhan;
# drop table LichHoc;
# drop table SinhVien;
# drop table LopHoc;
# drop table GiaoVu;
# drop table HocKy;
# drop table MonHoc;


-- mỗi giáo vụ có một magv để phân biệt với các giáo vụ khác và taikhoan là duy nhất
create table GiaoVu (
  magv char(12) primary key, 
  hoten nvarchar(50),
  taikhoan char(50), -- cú pháp = GV + [magv], ví dụ: GV19120484
  matkhau char(50), -- mật khẩu mặc định là magv
  unique(taikhoan)
);

-- mỗi sinh viên có một mssv để phân biệt với các giáo vụ khác và taikhoan là duy nhất
-- và thuộc về duy nhất một lớp học nào đó
create table SinhVien (
  mssv char(12) primary key,
  hoten nvarchar(50),
  gioitinh nvarchar(3) check (gioitinh in (N'Nam',N'Nữ')),
  taikhoan char(50),-- cú pháp = SV + [mssv], ví dụ: SV19120484
  matkhau char(50),--  mật khẩu mặc định là mssv
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
  trangthai bit, -- nếu là 1 thì đó là học kỳ hiện tại, chỉ có duy nhất một bộ có thuộc tính này = 1, còn lại đều bằng 0
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
-- cứ mỗi thông tin lịch học sẽ có một mã thông tin để phân biệt
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

create table DotDangKyHocPhan(
  madot int primary key, -- mỗi đợt đăng ký học phần sẽ có một mã riếng để phân biệt với tất cả các đợt đăng ký học phần khác
  namhoc int,
  hocky char(3),
  STT int, -- thứ tự các đợt đăng ký học phần trong 1 học kỳ
  ngaybatdaudangky date,
  ngayketthucdangky date
);

create table ChiTiet(
  mathongtin int,
  madot int,
  primary key(mathongtin, madot)
);

alter table SinhVien add constraint FK_SV_LOP foreign key(lop) references LopHoc(malop);
alter table DangKy add constraint FK_DK_SV foreign key(mssv) references SinhVien(mssv);
alter table DangKy add constraint FK_DK_LH foreign key(mathongtin) references LichHoc(mathongtin);
alter table LichHoc add constraint FK_LH_HK foreign key(namhoc, hocky) references HocKy(namhoc, mahocky);
alter table LichHoc add constraint FK_LH_MH foreign key(mamonhoc) references MonHoc(mamonhoc);
alter table DotDangKyHocPhan add constraint FK_DDKHP_HK foreign key(namhoc, hocky) references HocKy(namhoc, mahocky);
alter table ChiTiet add constraint FK_CT_DDKHP foreign key(madot) references DotDangKyHocPhan(madot);
alter table ChiTiet add constraint FK_CT_LichHoc foreign key(mathongtin) references LichHoc(mathongtin);

-- lưu ý các thông tin sau chỉ mang mục đích học thuật, tham khảo và thí dụ
-- thông tin được lấy ngẫu nhiên từ file excel của các bảng điểm môn học em đã học được thầy cô chia sẻ cho lớp

insert into GiaoVu(magv, hoten, taikhoan, matkhau) values
('100', N'Nguyễn Văn Khiết', 'GV100', '100'),
('101', N'Mai Anh Tuấn', 'GV101', '101'),
('102', N'Hồ Tuấn Thanh', 'GV102', '102');

insert into LopHoc(malop, siso, sisonam, sisonu) values
('19CTT1', 0, 0, 0),
('19CTT2', 0, 0, 0),
('19CTT3', 0, 0, 0),
('19CTT4', 0, 0, 0);

insert into SinhVien(mssv, hoten, gioitinh, taikhoan, matkhau, lop, email) values
('19120120', N'Phạm Hữu Phước', N'Nam', 'SV19120120', '19120120', '19CTT1', '19120120@student.hcmus.edu.vn'),
('19120142', N'Nguyễn Thị Phương Trang', N'Nữ', 'SV19120142', '19120142', '19CTT1', '19120142@student.hcmus.edu.vn'),
('19120147', N'Lê Anh Tuấn', N'Nam', 'SV19120147', '19120147', '19CTT1', '19120147@student.hcmus.edu.vn'),
('19120153', N'Lê Đoàn Phương Uyên', N'Nữ', 'SV19120153', '19120153', '19CTT2', '19120153@student.hcmus.edu.vn'),
('19120151', N'Nguyễn Trí Tuệ', N'Nam', 'SV19120151', '19120151', '19CTT2', '19120151@student.hcmus.edu.vn'),
('19120160', N'Đàm Thị Xuân Ý', N'Nữ', 'SV19120160', '19120160', '19CTT2', '19120160@student.hcmus.edu.vn'),
('19120477', N'Lê Văn Định', N'Nam', 'SV19120477', '19120477', '19CTT3', '19120477@student.hcmus.edu.vn'),
('19120484', N'Trầm Hữu Đức', N'Nam', 'SV19120484', '19120484', '19CTT3', '19120484@student.hcmus.edu.vn'),
('19120495', N'Nguyễn Nhật Duy', N'Nam', 'SV19120495', '19120495', '19CTT3', '19120495@student.hcmus.edu.vn'),
('19120707', N'Đào Xuân Tung', N'Nam', 'SV19120707', '19120707', '19CTT4', '19120707@student.hcmus.edu.vn'),
('19120729', N'Bùi Ngọc Thảo Vy', N'Nữ', 'SV19120729', '19120729', '19CTT4', '19120729@student.hcmus.edu.vn'),
('19120617', N'Mạch Vi Phong', N'Nam', 'SV19120617', '19120617', '19CTT4', '19120617@student.hcmus.edu.vn');

insert into HocKy(namhoc, mahocky, ngaybatdau, ngayketthuc, trangthai) values
(2020, 'HK1', '2020-01-01', '2020-05-31', 0),
(2020, 'HK2', '2020-07-01', '2020-11-30', 1),
(2021, 'HK1', '2021-01-01', '2021-05-31', 0);

insert into MonHoc(mamonhoc, tenmonhoc, sotinchi) values
('THTH', N'Toán học tổ hợp', 4),
('CSDL', N'Cơ sở dữ liệu', 4),
('PPLTHDT', N'Phương pháp lập trình hướng đối tượng', 4),
('LTUDJAVA', N'Lập trình ứng dụng Java', 4);

insert into LichHoc(mathongtin, namhoc, hocky, mamonhoc, giaovien, phonghoc, ngaythu, cahoc, slots) values
(1, 2020, 'HK1', 'THTH', N'Bùi Anh Tuấn', 'E204', 3, 3, 120),
(2, 2020, 'HK2', 'LTUDJAVA', N'Nguyễn Văn Khiết', 'E204', 4, 3, 100);

insert into DangKy(mathongtin, mssv) values
(1, 19120484),
(2, 19120484),
(1, 19120477),
(2, 19120477),
(1, 19120495),
(2, 19120495),
(1, 19120120),
(2, 19120707),
(2, 19120729);

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

insert into DotDangKyHocPhan(madot, namhoc, hocky, STT, ngaybatdaudangky, ngayketthucdangky) values
(1, 2020, 'HK1', 1, '2019-12-15', '2019-12-20'),
(2, 2020, 'HK1', 2, '2019-12-25', '2020-01-01'),
(3, 2020, 'HK1', 3, '2020-01-07', '2019-01-10');



