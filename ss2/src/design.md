RESTful API Design – Task & User Management

1. Tổng quan

Hệ thống quản lý Task (công việc) và User (người dùng).

Mỗi Task thuộc về một User.

Dữ liệu trao đổi ở dạng JSON.

Tuân thủ quy tắc RESTful: danh từ số nhiều, đúng HTTP Method.

2. Endpoints

2.1. Lấy danh sách

GET /tasks → Lấy toàn bộ công việc

GET /users → Lấy toàn bộ người dùng

2.2. Tạo mới

POST /tasks

Body: { "id","title", "description", "priority": "high|medium|low", "userId": 1 }

POST /users

Body: { "id","name", "email", "role": "member|admin" }

2.3. Cập nhật

PATCH /tasks/{id}

Body: { "status": "completed|pending" }

PATCH /users/{id}

Body: { "role": "member|admin" }

2.4. Xóa

DELETE /tasks/{id} → Xóa công việc

DELETE /users/{id} → Xóa người dùng

2.5. Truy vấn nâng cao

GET /tasks?priority=high → Tìm công việc có mức độ ưu tiên cao

GET /tasks?priority=high&userId=1 → Tìm công việc ưu tiên cao của user id=1

GET /users/{id}/tasks → Liệt kê toàn bộ công việc của một user

2.6. Gán công việc cho người dùng

POST /users/{id}/tasks

Body: { "taskId": 123 }

3. Validation cơ bản

title không được rỗng.

priority chỉ nhận giá trị: high, medium, low.

role chỉ nhận giá trị: member, admin.

userId phải tồn tại trong bảng Users.