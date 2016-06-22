package fingerSmash;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
 
public class Credits extends BasicGameState{
	
	/*
	 * DECLARES
	 */
	
	Input input;
	
	
	/*
	 * CODE
	 */
	 
     //Mouse Click Method
    public boolean mouseClick(int minX, int maxX, int minY, int maxY){
    	if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    		int mx = Mouse.getX();
			int my = 500 - Mouse.getY();
			if((mx > minX && mx < maxX) && (my > minY && my < maxY)){
				return true;
			}
    	}
		return false;
    }
 
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
    	input = container.getInput();
 
    }
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
    	g.setColor(Color.black);
    	Image creditsBtn = new Image("res/menu-btn.png");
		g.drawImage(creditsBtn, 14, 412);
		Image credits = new Image("res/credits.png");
		g.drawImage(credits, 131, 90);
		Image creditsNames = new Image("res/credits-names.png");
		g.drawImage(creditsNames, 94, 177);
		
    }
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    	if(mouseClick(14, 87, 412, 485)){
    		Menu.click.play();
    		game.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    	if (!Menu.mainMusic.playing()){
    		Menu.mainMusic.loop();
    	}
    }
 
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 1;
    }
 
}