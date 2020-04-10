import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks;
    Block ball;
    Block paddle;

    JFrame mainFrame;
    JFrame startScreen;

    Thread thread;

    void reset() {
        blocks = new ArrayList<Block>();
        ball = new Block(237, 435, 35, 25, "ball.png");
        paddle = new Block(174, 480, 150, 25, "paddle.png");

        for(int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60 + 2), 0, 60, 25, "green.png"));
        }
        for(int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60 + 2), 25, 60, 25, "blue.png"));
        }
        for(int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60 + 2), 50, 60, 25, "yellow.png"));
        }
        for(int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60 + 2), 75, 60, 25, "red.png"));
        }

        addKeyListener(this);
        setFocusable(true);
    }

//    Animate animate;

    BlockBreakerPanel(JFrame frame, JFrame startScreen) {

        this.mainFrame = frame;
        this.startScreen = startScreen;

        reset();

        for(int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60 + 2), 0, 60, 25, "green.png"));
        }
        for(int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60 + 2), 25, 60, 25, "blue.png"));
        }
        for(int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60 + 2), 50, 60, 25, "yellow.png"));
        }
        for(int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60 + 2), 75, 60, 25, "red.png"));
        }

        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        blocks.forEach(block -> {
            block.draw(g, this);
        });
        ball.draw(g, this);
        paddle.draw(g, this);
    }
    public void update() {
        ball.x += ball.movX;

        if (ball.x > (getWidth() - 25) || ball.x < 0) {
            ball.movX *= -1;
        }

        if (ball.y < 0 || ball.intersects(paddle)) {
            ball.movY *= -1;
        }

        ball.y += ball.movY;

        if(ball.y > getHeight()) {
            thread = null;
            reset();
            mainFrame.setVisible(false);
            startScreen.setVisible(true);
        }

        blocks.forEach(block -> {
            if (ball.intersects(block) && !block.destroyed) {
                block.destroyed = true;
                ball.movY *= -1;
            }
        });
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            thread = new Thread(() -> {
                while(true) {
                    update();
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
        if (keyEvent.getKeyCode() == keyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            paddle.x += 15;
        }
        if (keyEvent.getKeyCode() == keyEvent.VK_LEFT && paddle.x > 0) {
            paddle.x -= 15;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


}
