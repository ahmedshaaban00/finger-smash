package fingerSmash;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
 
public class FingerSmash extends StateBasedGame{
 
	/*
	 * DECLARES
	 */
	
	// Game state identifiers
    public static final int MENU = 0;
    public static final int CREDITS = 1;
    public static final int GAME = 2;
 	// Application Properties
    public static final int WIDTH   = 500;
    public static final int HEIGHT  = 500;
    public static final int FPS     = 120;
    public static final double VERSION = 1.7;
    
    
    /*
     * CODE
     */
    
    public static void main(String[] args) {
    	try {
			
			AppGameContainer container = new AppGameContainer(new FingerSmash("Finger Smash v" + VERSION));
			container.setDisplayMode(WIDTH, HEIGHT, false);// Configuring Game Container and setting width & height & fullscreen options
			container.setTargetFrameRate(FPS);
			container.setShowFPS(false);
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public FingerSmash(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }
 
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        // TODO Auto-generated method stub
    	addState(new Menu());
    	addState(new Credits());
    	addState(new Game());
    }
 
}