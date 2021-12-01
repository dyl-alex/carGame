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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.TabExpander;

public class Board extends JPanel implements ActionListener {
    public LinkedList<Image> rockQueue = new LinkedList<Image>();
    public Random rand = new Random();
    public HashMap<Integer, Integer> rockMap = new HashMap<Integer,Integer>();
    private Timer timer;

    public JButton gameOver = new JButton();
    public JPanel endPanel = new JPanel();

    private Image car;
    private Image rock;
    private Image rock1;
    private Image road;
    private Image road1;

    private int roadX = -100;
    private int roadY = 0;

    private int road1X = -100;
    private int road1Y = -625;

    private int carX = 250;
    private int carY = 420;

    private int rockX = 0;
    private int rockY = -110;

    private int rock1X = 0;
    private int rock1Y = -500;

    private int screenX = 500;
    private int screenY = 500;

    public boolean isRunning = false;
    public boolean right = false;
    public boolean left = false;


    public Board() {
        gameOver.setPreferredSize(new Dimension(100,100));
        endPanel.setPreferredSize(new Dimension(400,400));
        endPanel.add(gameOver);

        timer = new Timer(10, this);
        timer.start();

        rockMap.put(0, 105);
        rockMap.put(1,235);
        rockMap.put(2,365);

        initScreen();

        rockQueue.add(rock);
    }

    public void initScreen() {
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new TAdapter());

        setPreferredSize(new Dimension(screenX,screenY));
        loadImages();
        isRunning = true;



        initGame();
    }

    private void loadImages() {
        ImageIcon irock = new ImageIcon("pirateDolphin\\images\\rock.png");
        rock = irock.getImage();
        rock1 = irock.getImage();

        ImageIcon icar = new ImageIcon("C:\\Users\\dyale\\OneDrive\\Desktop\\School\\pirateDolphin\\pirateDolphin\\images\\car.png");
        car = icar.getImage();

        ImageIcon iroad = new ImageIcon("C:\\Users\\dyale\\OneDrive\\Desktop\\School\\pirateDolphin\\pirateDolphin\\images\\road.png");
        road = iroad.getImage();
        road1 = iroad.getImage();
    }

    public void initGame() {
        if (roadY >= 500 || road1Y >= 500) {
            roadY -= 625;
            road1Y -= 625;
        } else {
            roadY += 3;
            road1Y += 3;    
        }
        //
        if (rock1Y >= 500) {
            rock1Y -= 800;
            int pos = rand.nextInt(3);
            System.out.println(pos);
            rock1X = rockMap.get(pos);
        } else if (rockY >= 500) {
            rockY -= 800;
            int pos = rand.nextInt(3);
            System.out.println(pos);
            rockX = rockMap.get(pos);
        } else {
            rockY += 3;
            rock1Y += 3;
        }
        //
        if (((rockY + 104 >= carY) && (carX == rockX + 15)) || ((rock1Y + 104 >= carY) && (carX == rock1X + 15))) {
            System.out.println("Game over");
            gameOver();
        }

    }

    public void paint(Graphics g) {
        g.drawImage(road, roadX, roadY, null);
        g.drawImage(road1, road1X, road1Y, null);
        g.drawImage(car, carX, carY, null);

        g.drawImage(rock, rockX, rockY, null);
        g.drawImage(rock1, rock1X, rock1Y, null);
    }

    public void update(Graphics g) {
        
        paint(g);
    }

    public void gameOver() {
        timer.stop();

        add(endPanel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Toolkit.getDefaultToolkit().sync();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
        initGame();
    }

    public class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_A && !(carX == 120)) {
                carX -= 130;
                System.out.println(carX);
            }
            if (key == KeyEvent.VK_D && !(carX == 380)) {
                carX += 130;
                System.out.println(carX);
            }
        }
    }
}


