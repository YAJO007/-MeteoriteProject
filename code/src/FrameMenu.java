import javax.swing.*;
import java.awt.*;

public class FrameMenu extends JFrame {
    private Image backgroundMenu;
    private int asteroidCount = 5;
    private static final int MIN = 0;
    private static final int MAX = 50;

    public FrameMenu() {
        setTitle("Meteorite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        backgroundMenu = new ImageIcon("photo/bkMenu.png").getImage();

        FrameMenuPanel panel = new FrameMenuPanel();
        panel.setLayout(null);

        JLabel countLabel = new JLabel("Asteroids: " + asteroidCount);
        countLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        countLabel.setForeground(Color.WHITE);
        countLabel.setBounds(380, 140, 260, 40);
        panel.add(countLabel);

        // ปุ่มลด
        JButton minusBtn = new JButton("-");
        minusBtn.setBounds(360, 200, 120, 40);
        minusBtn.addActionListener(e -> {
            if (asteroidCount > MIN) {
                asteroidCount--;
                countLabel.setText("Asteroids: " + asteroidCount);
            }
        });
        panel.add(minusBtn);

        // ปุ่มเพิ่ม
        JButton plusBtn = new JButton("+");
        plusBtn.setBounds(520, 200, 120, 40);
        plusBtn.addActionListener(e -> {
            if (asteroidCount < MAX) {
                asteroidCount++;
                countLabel.setText("Asteroids: " + asteroidCount);
            }
        });
        panel.add(plusBtn);

        // ปุ่ม Start
        JButton startBtn = new JButton("Start");
        startBtn.setBounds(440, 260, 120, 40);
        startBtn.addActionListener(e -> {
            // ปิดหน้าต่างเมนู
            FrameMenu.this.dispose();
            // เปิดหน้าเกมจากอีกไฟล์ชื่อ Frame.java
            new Frame(asteroidCount);
        });
        panel.add(startBtn);

        // ปุ่ม Exit
        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(440, 320, 120, 40);
        exitBtn.addActionListener(e -> System.exit(0));
        panel.add(exitBtn);

        add(panel);
        setVisible(true);
    }

    class FrameMenuPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundMenu, 0, 0, getWidth(), getHeight(), this);

            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            g.setColor(Color.WHITE);
            g.drawString("Meteorite Game", 350, 60);
        }
    }

    // ตัวอย่าง FrameGame สำหรับทดสอบ
    static class FrameGame extends JFrame {
        FrameGame(int count) {
            setTitle("Meteorite Game - Playing");
            setSize(1000, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JLabel label = new JLabel("Game started with " + count + " asteroids",
                    SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 28));
            add(label);

            setVisible(true);
        }
    }
}