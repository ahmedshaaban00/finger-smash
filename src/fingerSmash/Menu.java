package fingerSmash;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.Image;
 
public class Menu extends BasicGameState{
	
	 /*
	 * DECLARES
	 */

	Input input;
	public static Music mainMusic;
	public static Sound click;
	public static Sound lose;
	
	/*
	 * CODE
	 */
	
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
    	input = container.getInput();
    	// Creating & Starting background music
    	mainMusic = new Music("res/sounds/background.ogg");
    	mainMusic.play();
		mainMusic.loop();
		// Creating Click sound
    	click = new Sound("res/sounds/click.ogg");
    	// Creating Lose Sound
    	lose = new Sound("res/sounds/lose.ogg");
	}
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
    	g.setBackground(Color.white); // Game Background
		g.setAntiAlias(true); // Use Anti Alias
		g.setColor(Color.black); // Preset Color Black
		// Rendering Images
		Image logo = new Image("res/finger-smash.png");
		g.drawImage(logo, 147, 95);
		Image playBtn = new Image("res/play-btn.png");
		g.drawImage(playBtn, 184, 211);
		Image creditsBtn = new Image("res/credits-btn.png");
		g.drawImage(creditsBtn, 14, 412);
		
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    		int mx = Mouse.getX();
			int my = 500 - Mouse.getY();
			// Credits button click
			if((mx > 14 && mx < 87) && (my > 412 && my < 485)){
				click.play();
				game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			}
			// Play button click
			if((mx > 184 && mx < 315) && (my > 211 && my < 341)){
				click.play();
				game.getState(2).init(container, game);
				game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			}
    	}
        // Space & Return key press to go to game state
		if(input.isKeyDown(Keyboard.KEY_SPACE) || input.isKeyDown(Keyboard.KEY_RETURN)){
			game.getState(2).init(container, game);
			game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
    }
 
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }
 
}