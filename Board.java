import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Image car;
    private Image ball; 

    private int screenX = 500;
    private int screenY = 500;

    public Board() {
        initScreen();
    }

    private void initScreen() {
        addKeyListener(new TAdapter());
        setBackground(color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(screenX,screenY));
        loadImages();
        initGame();
    }

    private void loadImages() {
        ImageIcon irock = new ImageIcon("pirateDolphin\\images\\rock.png");
        rock = irock.getImage();

        ImageIcon icar = new ImageIcon("pirateDolphin\\images\\car.png");
        car = icar.getImage();

    }
}
