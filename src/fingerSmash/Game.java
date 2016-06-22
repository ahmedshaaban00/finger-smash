package fingerSmash;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
 
public class Game extends BasicGameState{
 
	/*
	 * DECLARES
	 */
	
	// General
	Input input;
	public static boolean pause = false;
	// Fonts
	java.awt.Font UIFont1;
	org.newdawn.slick.UnicodeFont uniFont;
	java.awt.Font UIFont2;
	org.newdawn.slick.UnicodeFont uniFont2;
	// Arrows Booleans
	public static boolean upArrow = false;
	public static boolean downArrow = false;
	public static boolean rightArrow = false;
	public static boolean leftArrow = false;
	// Strings & Ints
	public static int score = 0;
	public static int highScore = 0;
	public static String overlay = "res/blank.png";
	public static String gameOver = "res/blank.png";
	public static String gameOverScore = "";
	public static String gameOverText = "";
	public static String gameOverHighScore = "";

	// Default Controller Images Resources
	public static String controllerUp = "res/arrow-up-circle.png";
	public static String controllerDown = "res/arrow-down-circle.png";
	public static String controllerRight = "res/arrow-right-circle.png";
	public static String controllerLeft = "res/arrow-left-circle.png";
	// Enemy Variables
	public static String enemy = "res/arrow-up-square.png";
	public static int enemyX = 228;
	public static int enemyY = -43;
	public static int number = 1;
	public static double speed;
	public static double maxSpeed = 14.0;
	
	
	/*
	 * CODE
	 */
	 
	// KEY UP Method
	public static void upArrow(boolean status){
		if (status){
			upArrow = true;
			controllerUp = "res/arrow-up-active-circle.png";
		}
		else{
			upArrow = false;
			controllerUp = "res/arrow-up-circle.png";
		}
	}
	// KEY DOWN Method
	public static void downArrow(boolean status){
		if (status){
			downArrow = true;
			controllerDown = "res/arrow-down-active-circle.png";
		}
		else{
			downArrow = false;
			controllerDown = "res/arrow-down-circle.png";
		}
	}
	// KEY RIGHT Method
	public static void rightArrow(boolean status){
		if (status){
			rightArrow = true;
			controllerRight = "res/arrow-right-active-circle.png";
		}
		else{
			rightArrow = false;
			controllerRight = "res/arrow-right-circle.png";
		}
	}
	// KEY LEFT Method
	public static void leftArrow(boolean status){
		if (status){
			leftArrow = true;
			controllerLeft = "res/arrow-left-active-circle.png";
		}
		else{
			leftArrow = false;
			controllerLeft = "res/arrow-left-circle.png";
		}
	}
	
	// Game Over Method
	public void gameOver(boolean status){
		if (status){
			overlay = "res/overlay.png";
			gameOver = "res/game-over.png";
			gameOverScore = "SCORE    " + String.valueOf(score);
			gameOverHighScore = "HIGHSCORE    " + String.valueOf(highScore);
			gameOverText = "Press SPACE or Enter to retry";
			Menu.lose.play();
			Menu.mainMusic.pause();
		}
		else{
			overlay = "res/blank.png";
			gameOver = "res/blank.png";
			gameOverScore = "";
			gameOverHighScore = "";
			gameOverText = "";
			Menu.lose.stop();
			if(!Menu.mainMusic.playing()){
				Menu.mainMusic.play();
				Menu.mainMusic.loop();
			}
		}
	}
	// Creatin Enemy Method
    public void createEnemy(){
    	if(speed < maxSpeed){
    		speed += 0.2;
    	}
    	enemyY = -43;
		number = 1 + (int)(Math.random() * 4);
		switch(number){
			case 1:
				enemy = "res/arrow-up-square.png";
				break;
			case 2:
				enemy = "res/arrow-down-square.png";
				break;
			case 3:
				enemy = "res/arrow-right-square.png";
				break;
			case 4:
				enemy = "res/arrow-left-square.png";
				break;
		}
    }
    // Checking Enemy and Controller Direction Method
    public boolean checkEnemy(){
    	if(number == 1 && upArrow){
			return true;
    	}
    	if(number == 2 && downArrow){
			return true;
    	}
    	if(number == 3 && rightArrow){
			return true;
    	}
    	if(number == 4 && leftArrow){
			return true;
    	}
    	return false;
    }
    
    @SuppressWarnings({ "unchecked" })
	@Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
    	input = container.getInput();
    	// Resetting some variables on initial of the game
    	pause = false;
    	score = 0;
    	enemyY = -43;
    	speed = 8.0;
    	gameOver(false);
    	
    	// Font initiating
    	try {
    		// Small font
    		UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/fonts/arcade.TTF"));
			UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 24.f); //You can change "PLAIN" to "BOLD" or "ITALIC"... and 16.f is the size of your font 
			uniFont = new org.newdawn.slick.UnicodeFont(UIFont1); 
			uniFont.addAsciiGlyphs(); 
			uniFont.getEffects().add(new ColorEffect(java.awt.Color.black)); // default color for custom font
			uniFont.addAsciiGlyphs();
			uniFont.loadGlyphs();
			// Big font
			UIFont2 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/fonts/arcade.TTF"));
			UIFont2 = UIFont1.deriveFont(java.awt.Font.PLAIN, 40.f); //You can change "PLAIN" to "BOLD" or "ITALIC"... and 16.f is the size of your font 
			uniFont2 = new org.newdawn.slick.UnicodeFont(UIFont2); 
			uniFont2.addAsciiGlyphs(); 
			uniFont2.getEffects().add(new ColorEffect(java.awt.Color.black)); // default color for custom font
			uniFont2.addAsciiGlyphs();
			uniFont2.loadGlyphs();
		} 	catch(Exception e){
				e.printStackTrace();
		}
	}
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
    	g.setBackground(Color.white); // Game Background
		g.setAntiAlias(true); // Use Anti Alias
		g.setColor(Color.black); // Preset Color Red
		// Score String
		uniFont.drawString(360, 28, "SCORE " + score);
		// Controller Images
		Image controllerUpImg = new Image(controllerUp);
		g.drawImage(controllerUpImg, 228, 396);
		Image controllerDownImg = new Image(controllerDown);
		g.drawImage(controllerDownImg, 228, 443);
		Image controllerLeftImg = new Image(controllerLeft);
		g.drawImage(controllerLeftImg, 179, 443);
		Image controllerRightImg = new Image(controllerRight);
		g.drawImage(controllerRightImg, 277, 443);
		// Enemy Images
		Image enemyImg = new Image(enemy);
		g.drawImage(enemyImg, enemyX, enemyY);
		// Overlay Image
		Image overlayImg = new Image(overlay);
		g.drawImage(overlayImg,0,0);
		// Gameover Image
		Image gameOverImg = new Image(gameOver);
		g.drawImage(gameOverImg,183,89);
		// Gameover Score String
		uniFont2.drawString(178, 210, gameOverScore);
		// Gameover High Score String
		uniFont2.drawString(135, 240, gameOverHighScore);
		// Gameover Text
		uniFont.drawString(80, 300, gameOverText);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    	// Keep the game working if pause isn't true
		if(!pause){
	    	// Create Enemy
	    	if(enemyY > 396){
	    		if(checkEnemy()){
	    			score += 1;
	    			createEnemy();
	    		}
	    		else{
	    			pause = true;
	    			gameOver(true);
	    		}
	    	}
	    	else{
    			enemyY += speed;
	    	}
	    	// Get Highscore
	    	if (score > highScore){
	    		highScore = score;
	    	}
//	    	// Loop when music stops
//	    	if (!Menu.mainMusic.playing()){
//	    		Menu.mainMusic.loop();
//	    	}
	    		    	
	    	// Trigger Custom Methods when each arrow key is pressed or released
			// Key UP
			if (container.getInput().isKeyDown(Input.KEY_UP))
			{
				upArrow(true);
			}
			else{
				upArrow(false);
			}
			// Key DOWN
			if (container.getInput().isKeyDown(Input.KEY_DOWN))
			{
				downArrow(true);
			}
			else{
				downArrow(false);
			}
			// Key LEFT
			if (container.getInput().isKeyDown(Input.KEY_LEFT))
			{
				leftArrow(true);
			}
			else{
				leftArrow(false);
			}
			// Key RIGHT
			if (container.getInput().isKeyDown(Input.KEY_RIGHT))
			{
				rightArrow(true);
			}
			else{
				rightArrow(false);
			}
			// Escape key press to go to menu state
			if(input.isKeyDown(Keyboard.KEY_ESCAPE)){
				game.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			}
    	}
		else{
			// Escape key press to go to menu state
			if(input.isKeyDown(Keyboard.KEY_ESCAPE)){
				gameOver(false);
				game.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			}
			// Space and Return key press to retry
			if(input.isKeyDown(Keyboard.KEY_SPACE) || input.isKeyDown(Keyboard.KEY_RETURN)){
				game.getState(2).init(container, game);
				game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			}
			
		}
		
		
    }
 
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 2;
    }
 
}