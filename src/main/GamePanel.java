package main;

import entities.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;
    KeyboardHandler keyboardHandler = new KeyboardHandler();
    Thread gameThread;
    TileManager tileManager=new TileManager(this);
    Player player=new Player(this, keyboardHandler);
    //int playerX = 100;
    //int playerY = 100;
    //int playerSpeed = 4;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            currentTime =System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer +=(currentTime - lastTime);
            lastTime=currentTime;
            if(delta>=1) {
                // Update info about character positions
                update();
                // draw the screen with updated info
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount=0;
                timer=0;
            }

        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2=(Graphics2D)g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
