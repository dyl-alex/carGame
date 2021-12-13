import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    public LinkedList<Image> rockQueue = new LinkedList<Image>();
    public Stack<Integer> scoreStack = new Stack<Integer>();
    public Random rand = new Random();
    public HashMap<Integer, Integer> rockMap = new HashMap<Integer,Integer>();
    private Timer timer;

    Popup t;

    public JPanel popUpBackground = new JPanel();
    public JButton gameOver = new JButton();

    private Image car;
    private Image rock;
    private Image rock1;
    private Image road;
    private Image road1;

    private int roadX = -100;
    private int roadY = 0;

    public int count = 0;
    public double vel = 3;

    private int road1X = -100;
    private int road1Y = -625;

    private int carX = 250;
    private int carY = 420;

    private int rockX = 105;
    private int rockY = -110;

    private int rock1X = 365;
    private int rock1Y = -500;

    private int screenX = 500;
    private int screenY = 500;

    public boolean isRunning = false;
    public boolean right = false;
    public boolean left = false;

    public JLabel score = new JLabel();
    public JLabel currentScore = new JLabel();
    public JLabel highScore = new JLabel();
    public JPanel scoreHolder = new JPanel();
    public JPanel currentScoreHolder = new JPanel();


    public Board() {
        rockQueue.add(rock);
        
        popUpBackground.setLayout(null);

        gameOver.setBounds(150, 50, 200, 50);
        gameOver.setText("Restart?");
        popUpBackground.setBackground(new Color(0,0,0,64));
        popUpBackground.setPreferredSize(new Dimension(500,500));
        popUpBackground.add(gameOver);

        currentScore.setBounds(100,50, 50,50);
        currentScore.setSize(new Dimension(300,300));
        currentScore.setVisible(true);
        currentScore.setText(String.valueOf(count));
        
        scoreHolder.setBounds(0, 200, 500, 50);
        score.setLocation(250,250);
        score.setFont(new Font("serif", Font.PLAIN, 30));
        highScore.setFont(new Font("serif", Font.PLAIN, 30));

        score.setSize(new Dimension(300,300));
        scoreHolder.add(score);
        scoreHolder.add(highScore);

        scoreStack.push(0);

        popUpBackground.add(scoreHolder);

        gameOver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rand1 = rand.nextInt(3);
                int rand2 = rand.nextInt(3);

                count = 0;

                rockX = rockMap.get(rand1);
                rock1X = rockMap.get(rand2);
                rockY = -110;
                rock1Y = -500;

                t.hide();
                timer.start();
            }
        });
        
        timer = new Timer(5, this);
        timer.start();

        rockMap.put(0, 105);
        rockMap.put(1,235);
        rockMap.put(2,365);

        initScreen();
    }

    public void initScreen() {
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new TAdapter());

        setPreferredSize(new Dimension(screenX,screenY));
        loadImages();
        isRunning = true;

        validate();

        initGame();
    }

    private void loadImages() {
        ImageIcon irock = new ImageIcon("C:\\Users\\dyale\\OneDrive\\Desktop\\School\\pirateDolphin\\pirateDolphin\\images\\rock.png");
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
            rock1X = rockMap.get(pos);
            count += 1;
        } else if (rockY >= 500) {
            rockY -= 800;
            int pos = rand.nextInt(3);
            rockX = rockMap.get(pos);
            count += 1;
        } else {
            rockY += vel;
            rock1Y += vel;
        }        
        //
        if (((rockY + 104 >= carY && rockY <= 485) && (carX == rockX + 15)) || ((rock1Y + 104 >= carY && rock1Y <= 485) && (carX == rock1X + 15))) {
            score.setText("Your Score: " + String.valueOf(count));
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

        if (count > scoreStack.peek()) {
            scoreStack.push(count);
        }

        t = PopupFactory.getSharedInstance().getPopup(this, popUpBackground, 433, 122);
        t.show();
        highScore.setText("|| High Score: " + String.valueOf(scoreStack.peek()));
        
        
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


