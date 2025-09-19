import javax.swing.*;
import java.awt.*;

public class FrameMenu extends JFrame {
    private Image BackgroundMenu;

    public FrameMenu() {
        setTitle("Meteorite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        BackgroundMenu = new ImageIcon("photo/bkMenu.png").getImage();
        add(new FrameMenuPanel());
        setVisible(true);
    }
    public void Button (){
        JButton button = new JButton();
        setLayout(new GridLayout(2,3));
        add(button);
    }

    class FrameMenuPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(BackgroundMenu, 0, 0, getWidth(), getHeight(), this);

            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            g.setColor(Color.WHITE);
            g.drawString("Meteorite Game", 350, 50);
        }

    }

}
