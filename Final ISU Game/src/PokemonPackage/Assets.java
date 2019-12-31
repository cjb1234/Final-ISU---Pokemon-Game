package PokemonPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {

    public static BufferedImage grass_1, grass_2, grass_3;

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void initialization(){
        grass_1 = loadImage("images/Grass_Tile_One.png");
        grass_2 = loadImage("images/Grass_Tile_Two.png");
        grass_3 = loadImage("images/Grass_Tile_Three.png");
    }
}
