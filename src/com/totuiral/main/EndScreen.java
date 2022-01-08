package com.totuiral.main;


import java.awt.*;

import java.awt.event.*;
import java.util.Random;

import com.totuiral.main.Game.State;



public class EndScreen extends MouseAdapter{

	private Game game;
	private Handler handler;
	private HUD hud;
	private float fade=0;
	private float oppositeFade=0;
	private Random r= new Random();
	static boolean done=false;

	public EndScreen(Game game , Handler handler, HUD hud) {
		this.game=game;
		this.handler=handler;
		this.hud=hud;

	}
	 
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState== State.EndScreen) {
			if (mouseOver(mx, my, 65,290,160,70)) 
			{ 
				System.exit(1);
			}
			else if (mouseOver(mx, my, 395,290,160,70)) {
				game.gameState=State.Menu;
				if (Menu.SoundOn){
				AudioPlayer.getMusic("SUSSY").stop();
				AudioPlayer.getSound("Click").play();
				}
				hud.reset();
				handler.clearEnemies();
				reset();
				for (int i=0; i<10; i++) {
					handler.addObject(new MenuParticle(r.nextInt(Game.width-50), r.nextInt(Game.height-50), null, handler));
				}
			}
			else if (mouseOver(mx, my,230,290,160,70)) {
				game.gameState=State.Game;
				hud.reset();
				handler.clearEnemiesAndPlayer();
				reset();
				handler.addObject(new Player(Game.width/2-32,Game.height/2-32, ID.player, handler, game));
				if (Settings.amountOfPlayers==2) handler.addObject(new Player(Game.width/2-64,Game.height/2-64, ID.Player2, handler, game));
				handler.addObject(new BasicEnemy(r.nextInt(Game.width-20),r.nextInt(Game.height-20), ID.BasicEnemy, handler, game));
			}
	
		}
	}
	public void reset() {
		// TODO Auto-generated method stub
		fade=0; oppositeFade=0;
		SmartEnemy.KilledPlayer1=false;
		SmartEnemy.KilledPlayer2=false;
	}


	public void mouseReleased() {
		
	}
	
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x+width) {
			if (my> y && my< y+ height) {
				return true;
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
		
		
		
		g.setColor(new Color(0,0,0,(int)oppositeFade));
		
		g.fillRect(0,0,Game.width, Game.height);
		
		
		g.setColor(new Color(255,255,255,(int)fade));
		
		
		int width=500;
		int height=300;
		g.setColor(new Color(16,16,16,(int)fade));
		g.fillRect((Game.width-width)/2-width/50,(Game.height-height)/2-height/15,width,height);
		
		g.setColor(new Color(255,0,0,(int)fade));
		g.fillRect(350,90,33,40);
		g.fillRect(190,115,20,15);
		g.fillRect(290,105,20,15);
		g.fillRect(440,105,20,15);
		
		g.setColor(new Color(0,0,0,(int)fade));
		g.fillRect(65,290,160,70);
		g.fillRect(395,290,160,70);
		g.fillRect(230,290,160,70);
		
		
		g.setColor(new Color(255,255,255,(int)fade));
		
		g.drawRect((Game.width-width)/2-width/50,(Game.height-height)/2-height/15,width,height);
		Font font = new Font("arial", 1,70);
		g.setFont(font);
		g.drawString("Game Over",125,135);
		Font font2 = new Font("arial", 1,30);
		g.setFont(font2);
		g.drawString("Highest Score: ",80,175);
		g.drawString(Integer.toString(hud.getHighScore()),470,175);
		g.drawString("Score reached:",80,218);
		g.drawString(Integer.toString(hud.getScore()),470,218);
		
		g.drawString("Coins gained:",80,261);
		g.drawString(Integer.toString(Spawn.gainKeep_Coins),500,261);
		
		
		Font font3 = new Font("arial", 1,55); g.setFont(font3);
		
		g.drawString("Quit",85,344);
		g.drawString("Retry",242,344);
		g.drawString("Menu ",405,347);
		
		g.drawRect(65,290,160,70);
		g.drawRect(395,290,160,70);
		g.drawRect(230,290,160,70);
		
		g.drawLine(70,187,550,187);
		g.drawLine(70,230,550,230);
		
		
	}
	public void tick() {
		
		
		if (game.gameState==State.EndScreen && !done) {
			fade+=4.5f;
			oppositeFade+=2.5;
			oppositeFade= Game.clamp(oppositeFade, 0, 255);
			fade= Game.clamp(fade, 0, 255);
			
		}
		if (fade>=255 && oppositeFade>=255) done=true; 
		
		
		
	}
		
}
