package com.totuiral.main;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import com.totuiral.main.Game.State;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private HUD hud;
	private EndScreen endScreen;
	private Random r=new Random();;
	private int x=27,y=526, timer=0, timerCount=35,x2=110,y2=103;
	private boolean[] isintwaiting= new boolean[4];
	public Menu(Game game , Handler handler, HUD hud, EndScreen endScreen) {
		this.game=game;
		this.handler=handler;
		this.hud=hud;
		this.endScreen=endScreen;
		for (int i=0; i<10; i++) {
			handler.addObject(new MenuParticle(r.nextInt(Game.width-50), r.nextInt(Game.height-50), ID.BasicEnemy, handler));
		}
	}  
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState==State.Menu) {
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				if (Menu.SoundOn)
				AudioPlayer.getSound("Click").play();
				
				Game.waitTime(200);
				isintwaiting[0]=true;
				
			}else if (mouseOver(mx , my, 210, 250, 200, 64)) {
				Game.waitTime(200);
				isintwaiting[1]=true;
				if (Menu.SoundOn)
				AudioPlayer.getSound("Click").play();
			}else if (mouseOver(mx, my, 516, 5, 100, 30)) {
				Game.waitTime(200);
				isintwaiting[2]=true;
				if (Menu.SoundOn)
				AudioPlayer.getSound("Click").play();
			}else if (mouseOver(mx, my, 210, 350, 200, 64)) {
				Game.waitTime(200);
				isintwaiting[3]=true;
				if (Menu.SoundOn)
				AudioPlayer.getSound("Click").play();
			}
			else if (mouseOver(mx, my, -3, 43, Game.shopping_cart_icon.getWidth(), Game.shopping_cart_icon.getHeight())) {
				synchronized (game){try {game.wait(500);} catch (Exception es){es.printStackTrace();}}
				game.gameState=State.Shop;
				handler.clearEnemies();
			}else if ( (mouseOver(mx, my, 5,83,SoundOffImg.getWidth(), SoundOffImg.getHeight()) && !SoundOn) || 
					   (mouseOver(mx, my, 5,83,SoundOnImg.getWidth(), SoundOnImg.getHeight()) && SoundOn) ){
				if (SoundOn){
					if (AudioPlayer.getMusic("Menu").playing()) AudioPlayer.getMusic("Menu").pause();
					SoundOn=false;
				}else if (!SoundOn){
					if (!AudioPlayer.getMusic("Menu").playing()) AudioPlayer.getMusic("Menu").resume();
					SoundOn=true;
				}
			}
			
		}
		else if (game.gameState==State.Game){
			if (Game.paused) {
				if (mouseOver(mx, my, 180,170,250,60)) {
					if (SoundOn)
					AudioPlayer.getSound("Click").play();
					Game.paused=false;
				}
				else if (mouseOver(mx, my, 180,240,250,60)) {
					Game.paused=false;
					if (SoundOn)
					AudioPlayer.getSound("Click").play();
					game.gameState=State.Menu;
					hud.reset();
					handler.clearEnemiesAndPlayer();
					for (int i=0; i<10; i++) {
						handler.addObject(new MenuParticle(r.nextInt(Game.width-50), r.nextInt(Game.height-50), null, handler));
					}
					
				}
				else if (mouseOver(mx, my, 180,310,250,60)) {
					if (SoundOn)
					AudioPlayer.getSound("Click").play();
					System.exit(1);
				}
			}
			else if (!Game.paused) {
				if (mouseOver(mx, my,566,412,50,15))
					Game.paused=true;
			}
		}
		 
		
	}


	public void mouseReleased(MouseEvent e) {
		
	}
	
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x+width) {
			if (my> y && my< y+ height) {
				return true;
			}
		}
		return false;
	}
	static boolean SoundOn=true;
	SpriteSheet spriteSheet = new SpriteSheet(Game.sprite_sheet);
	BufferedImage img = spriteSheet.grapImage(4, 3, 20, 21);
	BufferedImage SoundOnImg = spriteSheet.grapImage(2, 2, 30, 21);
	BufferedImage SoundOffImg = spriteSheet.grapImage(3, 2, 30, 21);

	public void render(Graphics g) {
		if (game.gameState==State.Menu) {
		g.setColor(new Color(0,0,0,120));
		
		g.fillRect(0,0,Game.width,Game.height);
		
		g.setColor(new Color(16,16,16));
		g.fillRect(210,150,200,64);
		g.fillRect(210,250,200,64);
		g.fillRect(210,350,200,64);
		g.fillRect(516,5,100,30);
		g.setColor(ColorUIResource.black);
		g.fillRect(3,21,100,25);
		g.setColor(ColorUIResource.white);
		
		if (Game.waiting && isintwaiting[0]) {
			g.setColor(new Color(50,50,50));
			g.fillRect(210,150,200,64);
		}
		if (Game.waiting && isintwaiting[1]) {
			g.setColor(new Color(50,50,50));
			g.fillRect(210,250,200,64);
		}
		if (Game.waiting && isintwaiting[3]) {
			g.setColor(new Color(50,50,50));
			g.fillRect(210,350,200,64);
		}
		if (Game.waiting && isintwaiting[2]) {
			g.setColor(new Color(50,50,50));
			g.fillRect(516,5,100,30);
		}
		g.setColor(Color.white);
		g.drawString("High score:"+hud.getHighScore(),5,18);
		Font font = new Font("arial", 1,80);
		
		
		
		FontUIResource mainMenuBGFont = new FontUIResource("arial", 1, 85); 
		g.setFont(mainMenuBGFont);
		g.setColor(Color.black);
		g.drawString("Main Menu", 100, 100);
		g.setFont(font);
		g.setColor(new Color(21, 16, 163));
		g.drawString("Main Menu", x2, y2+1);
		g.setColor(new Color(25, 18, 252));
		g.drawString("Main Menu", 110, 100);
		g.setColor(Color.white);
		
		Font font2 = new Font("arial", 1, 22);
		Font font3 = new Font("arial", 1, 30);
		g.setFont(font3);
		
		g.drawRect(210,150,200,64);
		g.setColor(Color.red);
		g.drawString("Start", 275, 193);
		g.drawString("Help", 278, 293);
		g.drawString("Quit", 279, 393);
		
		g.setColor(Color.white);
		g.drawString("Start", 275, 191);
		
		
		
		
		g.setColor(Color.white);
		g.drawRect(210,250,200,64);
		g.drawString("Help", 278, 291);
		
		g.setColor(Color.white);
		g.drawRect(210,350,200,64);
		g.drawString("Quit", 279, 391);
		
		g.drawRect(516,5,100,30);
		g.setFont(font2);
		g.setColor(Color.red);
		g.drawString("Settings", y, x);
		g.setColor(Color.white);
		g.drawString("Settings", 524, 28);
		g.drawImage(img, 5,24,null);
		g.drawString(Integer.toString(Handler.getNumCoins()),32,42);
		g.drawRect(3,21,100,25);
		g.setColor(Color.red);
		
		g.setXORMode(Color.WHITE);
		if (SoundOn){
			g.drawImage(SoundOnImg, 5, 83, null);
		}else g.drawImage(SoundOffImg, 5, 83, null);

		g.setXORMode(new ColorUIResource(red,gr,b));
		g.drawImage(Game.shopping_cart_icon, -3, 43, null);
		g.setXORMode(Color.black);
		
		}
		if (Game.paused && game.gameState== State.Game) {
			
			g.setColor(ColorUIResource.DARK_GRAY);
			
			g.fillRect(180,170,250,60);
			g.fillRect(180,240,250,60);
			g.fillRect(180,310,250,60);
			
			
			g.setColor(ColorUIResource.white);
			FontUIResource f  = new FontUIResource("arial", 1, 80); g.setFont(f);
			g.drawString("Paused",160,150);
			FontUIResource f2  = new FontUIResource("arial", 1, 50); g.setFont(f2);
			g.drawRect(180,170,250,60);
			g.drawRect(180,240,250,60);
			g.drawRect(180,310,250,60);
			g.drawString("Countinue",182,215);
			g.drawString("Main Menu",178,285);
			g.drawString("Quit game",182,355);
			g.setColor(ColorUIResource.black);
			
			
			
		}else if (!Game.paused && game.gameState==State.Game) {
			g.setColor(ColorUIResource.white);
			g.drawString("Pause",570,425);
			g.drawRect(566,412,50,15);
		}
		
		
		
	}
	int red=1, gr=1, b=1;
	private int state=0;
	public void tick() {
		timer++;
		if (timer>=timerCount) {
			if (x==30) { x=27; y=526; }
			else if (x==27) {x=30; y=523; }
			timer=0;
		}
		if (!AudioPlayer.getMusic("Menu").playing() && game.gameState != State.EndScreen && !Game.paused && Menu.SoundOn) {
			AudioPlayer.getMusic("Menu").loop();
		}
		else if (AudioPlayer.getMusic("Menu").playing() && game.gameState == State.EndScreen && !Game.paused && Menu.SoundOn) {
			AudioPlayer.getMusic("Menu").pause();
		}
		changeShoppingCartRGB(5);
		
		if (!Game.waiting && game.gameState==State.midGame && isintwaiting[0]) {
			game.gameState=State.Game;
			isintwaiting[0]=false;
			hud.reset();
			handler.clearEnemies();
			endScreen.reset();
			handler.addObject(new Player(Game.width/2-32,Game.height/2-32, ID.player, handler, game));
			if (Settings.amountOfPlayers==2) handler.addObject(new Player(Game.width/2-64,Game.height/2-64, ID.Player2, handler, game));
			handler.addObject(new BasicEnemy(r.nextInt(Game.width-20),r.nextInt(Game.height-20), ID.BasicEnemy, handler, game));
		}else if (!Game.waiting && game.gameState==State.midGame && isintwaiting[1]) {
			game.gameState=State.Help;
			isintwaiting[1]=false;
			hud.reset();
			handler.clearEnemies();
			Help.currpage=1;
			handler.addObject(new EnemyInstance(321, 380,580, 420, 400, 380, ID.BasicEnemy, handler));
		}else if (!Game.waiting && game.gameState==State.midGame && isintwaiting[2]) {
			game.gameState=State.Settings;
			isintwaiting[2]=false;
			handler.clearEnemies();
			game.gameState = State.Settings;
		}else if (!Game.waiting && game.gameState==State.midGame && isintwaiting[3]) {
			System.exit(1);
		}
	}
	public void changeShoppingCartRGB(int times) {
		for (int i = 0; i < times; i++) {
			if(state == 0){
			    gr++;
			    if(gr == 255)
			        state = 1;
			}
			if(state == 1){
			    red--;
			    if(red == 0)
			        state = 2;
			}
			if(state == 2){
			    b++;
			    if(b == 255)
			        state = 3;
			}
			if(state == 3){
			    gr--;
			    if(gr == 0)
			        state = 4;
			}
			if(state == 4){
			    red++;
			    if(red == 255)
			        state = 5;
			}
			if(state == 5){
			    b--;
			    if(b == 0)
			        state = 0;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
