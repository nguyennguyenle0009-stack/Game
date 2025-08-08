## Project: học lập trình game.

- https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq
	
## Tính năng

- Di chuyển nhân vật 2D.
- Thu thập chìa khóa, dùng chìa khóa mở cữa(drop).
- Thu thập rương báu và kết thúc game(drop).
- Tạm dừng và tiếp tục.
- Sự kiện (Hố sát thương, Hồ hồi máu, Ô dịch chuyển) Advanced Mechanics

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

- Khi người chơi liên tục đi qua hố, họ liên tục bị sát thương và nhận được thông báo

### Sửa lỗi

- Thêm các phản ứng đối tượng
- chỉ tạo sự kiện một lần để mỗi sự kiện có giá trị boolean và kiểm tra xem sự kiện đã xảy ra trong quá khứ hay chưa

### PS

### Comment

- Khi khởi tạo và nhập hình ảnh và cũng gọi phương thức này là công cụ tiện ích lấy hình ảnh đã được tỉ lệ và cũng thiết lập va chạm.
- Tối ưu hóa thời gian vẽ.
- Tạo npc, thiết lập di chuyển cho npc, thiết lập va chạm với npc.
- Nói chuyện với npc.
- Làm npc quay về phía người chơi khi nói chuyện, chuỗi string xuống 1 dòng.
- Thiết lập font chữ
- Sự kiện (Hố sát thương, Hồ hồi máu, Ô dịch chuyển) Advanced Mechanics

### So sánh

| So sánh                    | Loại 1 (scale & setUp)                         | Loại 2 (viết tay)                          |
|---------------------------|------------------------------------------------|--------------------------------------------|
| Hiệu năng (với ảnh nhỏ)   | ⭐⭐ (tốt, nhưng scale tốn thêm chút CPU)       | ⭐⭐⭐ (tốt hơn chút nếu ảnh đã đúng size)   |
| Tái sử dụng               | ✅ Tốt                                         | ❌ Không                                   |
| Dễ mở rộng                | ✅ Dễ thêm tile mới                            | ❌ Khó                                     |
| Quản lý tileSize dễ dàng  | ✅ Có                                          | ❌ Không                                   |
| Viết code                 | ⭐ Ngắn gọn                                    | ❌ Dài dòng, trùng lặp                     |

### Ý tưởng

- Will you show how to add events to objects too? Also, when the player keeps walking through the pit they constantly take damage and get message, can we make it so event only happens if they leave and go back through the pit?
- We will add object reactions eventually (we've already done it in the first half but it will be handled in more object oriented way in this second half). For the event keeps happening when you are going to the same direction, there are two ways to fix. The one is creating some margin so the event won't happen again until player moves certain amount of pixels from where the event happened. This will be implemented when we add stairs in the future. The second one is  making the event one time only so each event has boolean value and check if the event has already happened in the past or not. For the second method, you'll probably want to handle it by creating an event tile class. Maybe I will make a video for this.
- I created a method to take in a direction and move the player backwards if they step on a pit. You could also create some sort of timer where the player is invincible for a second or so after taking damage


