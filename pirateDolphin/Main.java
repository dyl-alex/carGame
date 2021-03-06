import java.awt.*;
import javax.swing.JFrame;

public class Main extends JFrame {

    public Main() {
        initUI();
    }

    public void initUI() {
        add(new Board());

        setResizable(false);
        pack();

        setTitle("Car Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible(true);
            
        });

    }
}
