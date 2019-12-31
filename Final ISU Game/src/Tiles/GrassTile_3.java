package Tiles;

import PokemonPackage.Assets;

public class GrassTile_3 extends Tile{

    public GrassTile_3(){
        super(Assets.grass_3, 2);
    }

    public boolean isWalkable(){
        return false;
    }
}
