## Project: học lập trình game.

-https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq
	
## Tính năng

- Di chuyển nhân vật 2D.
- Thu thập chìa khóa, dùng chìa khóa mở cữa.
- thu thập rương báu và kết thúc game.

## Cập nhật

- click T để mở bảng đếm thời gian vẽ các thứ ở mỗi vòng lặp.
- Tỉ lệ các hình ảnh khi nhập chúng
	
## Cách chạy

1. Mở project trong Eclipse.
2. Chạy class `Main.java` bằng cách click chuột phải → Run As → Java Application.

## Yêu cầu hệ thống

- Java SE 17 trở lên.
	
## [1.0.1] - 2025-08-06

### Lỗi 

### Sửa lỗi

### PS

- Không nên tạo một phiên bản mới trong vòng lặp trò chơi trừ khi thực sự cần thiết

### Comment

- Khi khởi tạo và nhập hình ảnh và cũng gọi phương thức này là công cụ tiện ích lấy hình ảnh đã được tỉ lệ và cũng thiết lập va chạm
- Tối ưu hóa thời gian vẽ

### So sánh

| So sánh                    | Loại 1 (scale & setUp)                         | Loại 2 (viết tay)                          |
|---------------------------|------------------------------------------------|--------------------------------------------|
| Hiệu năng (với ảnh nhỏ)   | ⭐⭐ (tốt, nhưng scale tốn thêm chút CPU)       | ⭐⭐⭐ (tốt hơn chút nếu ảnh đã đúng size)   |
| Tái sử dụng               | ✅ Tốt                                         | ❌ Không                                   |
| Dễ mở rộng                | ✅ Dễ thêm tile mới                            | ❌ Khó                                     |
| Quản lý tileSize dễ dàng  | ✅ Có                                          | ❌ Không                                   |
| Viết code                 | ⭐ Ngắn gọn                                    | ❌ Dài dòng, trùng lặp                     |


