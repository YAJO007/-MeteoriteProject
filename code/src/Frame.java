import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private Image background;
    private Image meteor;

    public Frame() {
        setTitle("Meteorite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        background = new ImageIcon("photo/photo1.jpg").getImage();
        meteor = new ImageIcon("photo/photo2.png").getImage();

        add(new MeteorPanel());
        setVisible(true);
    }

    public class MeteorPanel extends JPanel {
            Image background = Toolkit.getDefaultToolkit().getImage("photo/bk.png");
            Image meteor = Toolkit.getDefaultToolkit().getImage("photo/photo2.png");

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(),getHeight(), this);
                g.drawImage(meteor, 100, 100, 40, 40, this);


                g.setFont(new Font("Times New Roman", Font.BOLD, 20));
                g.setColor(Color.WHITE);
                g.drawString("Meteorite", 10, 20);

            }
        }

    }


