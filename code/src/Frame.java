import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Frame extends JFrame {
    public Frame() {
        this(10); // ถ้าไม่ส่งจำนวนมา จะเริ่มด้วย 10 ลูก
    }

    public Frame(int asteroidCount) {
        setTitle("Meteorite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        add(new MeteorPanel(asteroidCount));
        setVisible(true);
    }

    /* ================== MeteorPanel ================== */
    public static class MeteorPanel extends JPanel {
        private final Image background = Toolkit.getDefaultToolkit().getImage("photo/bk.png");
        private final Image meteorImg  = Toolkit.getDefaultToolkit().getImage("photo/photo2.png");

        private final List<Meteor> meteors = new ArrayList<>();
        private final Random rng = new Random();

        private final Timer timer;
        private static final int METEOR_SIZE = 50; // อุกกาบาตทุกลูกเท่ากัน

        public MeteorPanel(int count) {
            setBackground(Color.BLACK);

            // สร้างอุกกาบาตจำนวนตามที่รับมา
            for (int i = 0; i < count; i++) {
                meteors.add(randomMeteor());
            }

            // ลูปอัปเดตเกม (ประมาณ 60 FPS)
            timer = new Timer(16, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (Meteor m : meteors) {
                        if (m.alive) {
                            m.move(getWidth(), getHeight());
                        }
                    }
                    checkCollisions();
                    repaint();
                }
            });
            timer.start();
        }

        private Meteor randomMeteor() {
            int x = rng.nextInt(900);
            int y = rng.nextInt(500);
            int dx = rng.nextBoolean() ? (rng.nextInt(3) + 2) : -(rng.nextInt(3) + 2);
            int dy = rng.nextBoolean() ? (rng.nextInt(3) + 2) : -(rng.nextInt(3) + 2);
            return new Meteor(x, y, dx, dy, METEOR_SIZE);
        }

        // ตรวจว่าชนกันไหม
        private void checkCollisions() {
            for (int i = 0; i < meteors.size(); i++) {
                Meteor m1 = meteors.get(i);
                if (!m1.alive) continue;
                for (int j = i + 1; j < meteors.size(); j++) {
                    Meteor m2 = meteors.get(j);
                    if (!m2.alive) continue;

                    Rectangle r1 = new Rectangle(m1.x, m1.y, m1.size, m1.size);
                    Rectangle r2 = new Rectangle(m2.x, m2.y, m2.size, m2.size);

                    if (r1.intersects(r2)) {
                        m2.alive = false; // ถ้าชนให้ลูกที่สองหายไป
                    }
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

            // วาดอุกกาบาต
            int remaining = 0;
            for (Meteor m : meteors) {
                if (m.alive) {
                    g.drawImage(meteorImg, m.x, m.y, m.size, m.size, this);
                    remaining++;
                }
            }

            // แสดงจำนวนอุกกาบาตที่เหลือ
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 22));
            g.drawString("Remaining: " + remaining, 20, 30);
        }

        /* ================== Meteor ================== */
        class Meteor {
            int x, y, dx, dy, size;
            boolean alive = true;

            Meteor(int x, int y, int dx, int dy, int size) {
                this.x = x;
                this.y = y;
                this.dx = dx;
                this.dy = dy;
                this.size = size;
            }

            void move(int W, int H) {
                x += dx;
                y += dy;

                // ชนขอบซ้าย/ขวา
                if (x < 0 || x > W - size) {
                    dx = -dx;
                    if (dx > 0) dx++;   // ถ้าไปขวา → เร็วขึ้น
                    else dx--;          // ถ้าไปซ้าย → เร็วขึ้น (เป็นลบมากขึ้น)
                }

                // ชนขอบบน/ล่าง
                if (y < 0 || y > H - size) {
                    dy = -dy;
                    if (dy > 0) dy++;
                    else dy--;
                }
            }
        }
    }
}
