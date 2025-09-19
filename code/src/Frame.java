import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private Image background;
    private Image meteor;

    public Frame() {
        setTitle("Meteorite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        background = new ImageIcon("photo/photo1.jpg").getImage();
        meteor = new ImageIcon("photo/photo2.png").getImage();

        add(new GamePanel());
        setVisible(true);
    }

    public class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(background, 0, 0 ,getWidth(), getHeight(), this);

            g2.drawImage(meteor, 100, 100, 40, 40, this);

        }
    }

}
