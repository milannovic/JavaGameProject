package entities;

import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Player extends Entity {
    GamePanel gp;
    KeyboardHandler keyboardHandler;

    public Player(GamePanel gp, KeyboardHandler keyboardHandler) {
        this.gp=gp;
        this.keyboardHandler=keyboardHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            File f1 = new File("directory/player/stickmanup1.png");
            File f2 = new File("directory/player/stickmanup2.png");
            File f3 = new File("directory/player/stickmandown1.png");
            File f4 = new File("directory/player/stickmandown2.png");
            File f5 = new File("directory/player/stickmanleft1.png");
            File f6 = new File("directory/player/stickmanleft2.png");
            File f7 = new File("directory/player/stickmanright1.png");
            File f8 = new File("directory/player/stickmanright2.png");
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            left1 = ImageIO.read(f5);
            left2 = ImageIO.read(f6);
            right1 = ImageIO.read(f7);
            right2 = ImageIO.read(f8);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyboardHandler.up || keyboardHandler.down
        || keyboardHandler.left || keyboardHandler.right) {

            if (keyboardHandler.up) {
                direction = "up";
                y -= speed;
            }
            if (keyboardHandler.down) {
                direction = "down";
                y += speed;
            }
            if (keyboardHandler.left) {
                direction = "left";
                x -= speed;
            }
            if (keyboardHandler.right) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        /*g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);*/
        BufferedImage image = null;
        //AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f); // 1.0f for fully opaque
        //g2.setComposite(ac);
        switch (direction){
            case "up":
                if(spriteNum==1){
                    image=up1;
                }
                if(spriteNum==2){
                    image=up2;
                }
                break;
            case "down":
                if(spriteNum==1){
                    image=down1;
                }
                if(spriteNum==2){
                    image=down2;
                }
                break;
            case "left":
                if(spriteNum==1){
                    image=left1;
                }
                if(spriteNum==2){
                    image=left2;
                }
                break;
            case "right":
                if(spriteNum==1){
                    image=right1;
                }
                if(spriteNum==2){
                    image=right2;
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize, gp.tileSize, null);

    }
}
