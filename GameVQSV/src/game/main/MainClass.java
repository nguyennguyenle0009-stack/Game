package game.main;

import javax.swing.JFrame;

public class MainClass {
    public static void main(String[] args) {
    	
        JFrame f = new JFrame("hello"); // Tạo cửa sổ ứng dụng
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Tắt chương trình khi đóng cửa sổ
        f.setResizable(false); // Không cho phép thay đổi kích thước cửa sổ
        f.setTitle("Game VSQV"); // Đặt tiêu đề cửa sổ

        GamePanel game = new GamePanel(); // Tạo panel chứa nội dung game
//    	System.out.println(game.screenWidth);
//    	System.out.println(game.screenHeight);
        f.add(game); // Thêm GamePanel vào JFrame
        f.pack(); // Tự điều chỉnh kích thước JFrame theo kích thước GamePanel
        f.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
        f.setVisible(true); // Hiển thị cửa sổ
        
        game.setUpGame();
        game.startGameThread(); // Bắt đầu vòng lặp game
    }

}
   