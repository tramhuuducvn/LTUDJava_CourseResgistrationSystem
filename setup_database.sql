Setup: Cơ sở dữ liệu

+ Giáo vụ: 
	- magv: primary key, char(12)
	- họ tên: nvachar(50)
	- tài khoản: duy nhất, char(50)
	- mật khẩu: char(50)
	- Quyền: quy ước 1 là quyền truy cập giáo viên, 0 là quyền truy cập sinh viên

+ Sinh viên: 
	- mssv: primary key, char(12)
	- họ tên: nvachar(50)
	- giới tính: nam hoặc nữ
	- tài khoản: duy nhất, char(50)
	- mật khẩu: char(50)
	- lớp: char(7)
	- email: duy nhất, char(50)
	- Quyền: quy ước 1 là quyền truy cập giáo viên, 0 là quyền truy cập sinh viên

+ Học kỳ:
	- mã học kỳ: char() ví dụ HK1, HK2,...
	- năm học: int
	- ngày bắt đầu: date
	- ngày kết thúc: date
	~primary key(năm học, mã học kỳ)

+ Lớp học:
	- mã lớp: char(7), primary key
	- sỉ số: int, thuộc tính suy diễn
	- sỉ số nam: int, thuộc tính suy diễn
	- sỉ số nữ: int, thuộc tính suy diễn

+ Môn học:
	- mã môn học: char(10), primary key
	- tên môn học: nvachar(100)
	- số tín chỉ: int

-- Học kỳ đó sẽ có môn học nào?
-- Môn đó học ở phòng nào, mấy giờ, thứ mấy trong tuần?
-- Môn đó thì có sinh viên nào học? 
-- Bảng sau sẽ làm việc đó
-- Bảng sẽ tự động thêm, cập nhật, xóa và sửa khi sinh viên đăng ký học phần
+ Chi tiết:
	- năm học:
	- mã học kỳ:
	- mã môn học:
	- giáo viên: nvachar(50)
	- slots: int
	- phòng học: char(5)
	- ngày thứ: int phạm vi giá trị chỉ trong  (2, 3, 4, 5, 6) quy ước thứ 7, cn nghĩ nên ko xét
	- ca học: int phạm vi giá trị chỉ trong (1,2,3,4)
	- mssv đăng ký học:

-- Về phần lưu giữ giá học kỳ hiện tại sẽ được lưu trong file .dat hoặc là file .txt
-- Thông tin lưu bao gồm: năm học, mã học kỳ, ngày bắt đầu ngày kết thúc.
