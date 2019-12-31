package States;

import PokemonPackage.Game;

import java.awt.*;

import static Tiles.Tile.*;

public class GameState extends State{

    public GameState(Game game){
        super(game);
    }

    public void update() {

    }
    public void render(Graphics g) {
        tiles[0].render(g, 0, 0);
        tiles[1].render(g, 64, 0);
        tiles[2].render(g, 0, 64);
    }
}
