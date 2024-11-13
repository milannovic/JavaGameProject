package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int mapTileNum[][];
    public TileManager(GamePanel gp){
        this.gp=gp;
        tiles=new Tile[10];
        mapTileNum=new int[gp.maxScreenColumn][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }
    public void getTileImage(){
        try {
            tiles[0]=new Tile();
            File f0 = new File("directory/tiles/grass.png");
            tiles[0].image = ImageIO.read(f0);

            tiles[1]=new Tile();
            File f1 = new File("directory/tiles/wall.png");
            tiles[1].image = ImageIO.read(f1);

            tiles[2]=new Tile();
            File f2 = new File("directory/tiles/water.png");
            tiles[2].image = ImageIO.read(f2);

            tiles[3]=new Tile();
            File f3 = new File("directory/tiles/sand.png");
            tiles[3].image = ImageIO.read(f3);

            tiles[4]=new Tile();
            File f4 = new File("directory/tiles/earth.png");
            tiles[4].image = ImageIO.read(f4);

            tiles[5]=new Tile();
            File f5 = new File("directory/tiles/tree.png");
            tiles[5].image = ImageIO.read(f5);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void loadMap(){
        try{
            //InputStream is = getClass().getResourceAsStream("directory/maps/map1.txt");
            FileReader fMap= new FileReader("directory/maps/map1.txt");
            BufferedReader br=new BufferedReader(fMap);
            int col = 0;
            int row = 0;
            while(col<gp.maxScreenColumn && row<gp.maxScreenRow){
                String line = br.readLine();
                while(col<gp.maxScreenColumn){
                    String numbers[]=line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col== gp.maxScreenColumn){
                    col=0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        //g2.drawImage(tiles[0].image,0,0,gp.tileSize,gp.tileSize,null);
        //g2.drawImage(tiles[1].image,48,0,gp.tileSize,gp.tileSize,null);
        //g2.drawImage(tiles[2].image,96,0,gp.tileSize,gp.tileSize,null);
        int col=0;
        int row=0;
        int x=0;
        int y=0;
        while(col<gp.maxScreenColumn && row<gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tiles[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x+=gp.tileSize;
            if(col == gp.maxScreenColumn){
                col=0;
                x=0;
                row++;
                y+=gp.tileSize;
            }
        }

    }
}
