import javax.swing.*;

public class Runner {

    public static void main (String[] args) {
        JFrame frame = new JFrame("Space Invader");

        JFrame startScreen = new JFrame();
        JButton start = new JButton("Start Game");

        BlockBreakerPanel panel = new BlockBreakerPanel(frame, startScreen);

        start.addActionListener(actionEvent -> {
            startScreen.setVisible(false);
            frame.setVisible(true);
        });

        //GameScreen
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
        frame.setSize(490, 600);
        frame.setResizable(false);

        //StartScreen
        startScreen.getContentPane().add(start);
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setVisible(true);
        startScreen.setSize(490, 600);
        startScreen.setResizable(false);

    }
}
