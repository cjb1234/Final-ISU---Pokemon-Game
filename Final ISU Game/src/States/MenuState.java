package States;

import PokemonPackage.Assets;
import PokemonPackage.Game;

import java.awt.*;

public class MenuState extends State{

    public MenuState(Game game){
        super(game);
    }

    public void update() {
    }
    public void render(Graphics g){
        g.drawImage(Assets.grass_1, 32, 0, null);
        }
    }

