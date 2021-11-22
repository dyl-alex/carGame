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
    private Image rock; 
    private Image road;

    private int delay = 140;

    private int carX = 250;
    private int carY = 420;

    private int screenX = 500;
    private int screenY = 500;

    public boolean isRunning = false;

    public Board() {
        initScreen();

    }

    private void initScreen() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(screenX,screenY));
        loadImages();
        isRunning = true;
        initGame();
    }

    private void loadImages() {
        ImageIcon irock = new ImageIcon("pirateDolphin\\images\\rock.png");
        rock = irock.getImage();

        ImageIcon icar = new ImageIcon("C:\\Users\\dyale\\OneDrive\\Desktop\\School\\pirateDolphin\\pirateDolphin\\images\\car.png");
        car = icar.getImage();

        ImageIcon iroad = new ImageIcon("C:\\Users\\dyale\\OneDrive\\Desktop\\School\\pirateDolphin\\pirateDolphin\\images\\road.png");
        road = iroad.getImage();
        ImageIcon iroad1 = new ImageIcon("C:\\Users\\dyale\\OneDrive\\Desktop\\School\\pirateDolphin\\pirateDolphin\\images\\road.png");
        road1 = iroad.getImage();
    }

    private void initGame() {
        timer = new Timer(delay, this);
    }

    public void cycleRoad(Image road) {
        
        while(isRunning) {
            
        }
    }

    public void paint(Graphics g) {
        g.drawImage(road, -100, 1, null);

        g.drawImage(car, carX, carY, null);
    }

    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        
    }

    private void doDrawing(Graphics g) {
        if (isRunning) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void KeyPressed(KeyEvent e) {
            int key = e.getKeyCode();
        }
    }
}


