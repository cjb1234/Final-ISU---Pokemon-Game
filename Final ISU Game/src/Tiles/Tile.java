package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //Static
    public static Tile[] tiles = new Tile[25];
    public static Tile grassTile_1 = new GrassTile_1(),
                       grassTile_2 = new GrassTile_2(),
                       grassTile_3 = new GrassTile_3();

    //Class
    public static final int tileWidth = 64,
                            tileHeight = 64;

    protected BufferedImage texture;
    protected final int tileId;

    public Tile(BufferedImage texture, int tileId){
        this.texture = texture;
        this.tileId = tileId;

        tiles[tileId] = this;
    }

    //not used yet
    public void update(){

    }
    public void render(Graphics g, int y, int x){
        g.drawImage(texture, x, y, tileWidth, tileHeight, null);
    }

    //Change to isWalkable, opposite of the video
    public boolean isWalkable(){
        return true;
    }

    //not used
    public int getId(){
        return  tileId;
    }

}
