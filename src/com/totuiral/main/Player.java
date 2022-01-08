package com.totuiral.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.totuiral.main.Game.State;

public class Player extends GameObject {
	
	public static boolean shieldActive;

	private static  int shieldTimer=0;

	private Handler handler;
	
	private Game game;
	int count=0, keep=0; Color color;

	public BufferedImage playerImage;

	public BufferedImage player2Image;

	private BufferedImage shieldImage;
	
	private double alpha = 0.04;
	public Player(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler=handler;
		this.id=id;
		this.game=game;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		playerImage =  game.flipImage(ss.grapImage(1, 1, 32, 32), 1);
		player2Image = game.flipImage(ss.grapImage(1, 2, 32, 32), 1);
		shieldImage = ss.grapImage(4, 4, 24, 32);
	}
	/**
	 * <br> 
	 * @see Handler#tick
	 */
	public void tick(){
		
		y+=velY;
		x+=velX;
		x=Game.clamp(x, 0, Game.width-48);
		y=Game.clamp(y, 0, Game.height-70);
		
		if (id==ID.player) color=Color.blue;
		else if (id==ID.Player2) color=Color.white;
		handler.object.add(new Trail(x, y, ID.PlayerTrail, color, 32, 32, (float)0.05, handler));
		if (!shieldActive) {
			if (alpha>0.04 && alpha<1) {
				alpha-=0.05;
			}
			else {
				alpha=0.04;
			}
			collision();
		}
		else if (shieldActive && shieldTimer>0) {
			if (alpha<0.99 && alpha>=0.03) {
				alpha+=0.05;
			}
			else {
				alpha=0.99;
			}
			shieldTimer--;
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject temp = handler.object.get(i);
				if (temp.getID() == ID.Coin) {
					if (getBounds().intersects(temp.getBounds())) {
						Coin coin = (Coin) temp;
						coin.fadeOut();
						if (AudioPlayer.getSound("Done").playing() && (Menu.SoundOn)) 
						{
							AudioPlayer.getSound("Done").stop();
							AudioPlayer.getSound("Done").play();
						
						}
						else {
							if (Menu.SoundOn)
							AudioPlayer.getSound("Done").play(); }
							
						
					}
				}
			}
			
		}
		else if (shieldActive && shieldTimer<=0) {
			shieldActive=false;
		}
	}
	private void collision() {
		for (int i=0; i<handler.object.size(); i++)
		{
			GameObject temp = handler.object.get(i);
			
			if (temp.getID() == ID.BasicEnemy || temp.getID() == ID.FastEnemy || temp.getID()==ID.SlowEnemy || temp.getID() == ID.SmartEnemy || temp.getID()== ID.BossEnemy
					|| temp.getID()== ID.BulletEnemy)
			{
				if (getBounds().intersects(temp.getBounds() ) && id==ID.player )
				{
					if (Menu.SoundOn){
					if (!AudioPlayer.getSound("Hit").playing()) {
						AudioPlayer.getSound("Hit").play(1,0.1f);
					}
				}
					if (Settings.Difficulty==1) HUD.HEALTH-=0.7f;
					if (Settings.Difficulty==2) HUD.HEALTH-=1.5f;
					if (Settings.Difficulty==3) HUD.HEALTH-=2.5;
		
				}
				else if (getBounds().intersects(temp.getBounds() ) && id==ID.Player2)
				{
					if (Menu.SoundOn){
						if (!AudioPlayer.getSound("Hit").playing()) {
							AudioPlayer.getSound("Hit").play(1,0.1f);
						}
					}
					if (Settings.Difficulty==1) HUD.HEALTH2+=0.7f;
					if (Settings.Difficulty==2) HUD.HEALTH2+=1.5f;
					if (Settings.Difficulty==3) HUD.HEALTH2+=2.5; //2.3 hp reduction for hard
				}
			}
			if(Settings.Difficulty==3) { 
				if (temp.getID()==ID.Trail) {
					if (getBounds().intersects(temp.getBounds()) && id==ID.player) {
						HUD.HEALTH-=0.06f;
					}
					else if(getBounds().intersects(temp.getBounds()) && id== ID.Player2){ 
						HUD.HEALTH2+=0.06;
					}
				}
			}
			
			if ((HUD.HEALTH<=0 && temp.getID()== ID.player))
			{
				handler.removeObject(temp);
				SmartEnemy.KilledPlayer1=true;
				if (!handler.isIn(ID.Player2)) {

					game.gameState= State.EndScreen;
					if (Menu.SoundOn){
						AudioPlayer.getMusic("SUSSY").play();
					}
					EndScreen.done=false;
				}
				
			}
			if (HUD.HEALTH2>=(100+Shop.MaxHpAdditon) && temp.getID()== ID.Player2)
			{
				handler.removeObject(temp); 
				SmartEnemy.KilledPlayer2=true;
				if (!handler.isIn(ID.player)) {
					game.gameState= State.EndScreen;
					if (Menu.SoundOn){
						AudioPlayer.getMusic("SUSSY").play();					
					}			
					EndScreen.done=false;

				}
				
			}
			if (temp.getID() == ID.HPpack)
			{
				if (getBounds().intersects(temp.getBounds() ) && id==ID.player)
				{
					if (Settings.Difficulty==1) HUD.HEALTH+=60;
					if (Settings.Difficulty==2) HUD.HEALTH+=45;
					if (Settings.Difficulty==3) HUD.HEALTH+=25;
					handler.removeObject(temp);
		
				}
				else if (getBounds().intersects(temp.getBounds() ) && id==ID.Player2)
				{
					if (Settings.Difficulty==1) HUD.HEALTH2-=60f;
					if (Settings.Difficulty==2) HUD.HEALTH2-=45f;
					if (Settings.Difficulty==3) HUD.HEALTH2-=25f;
					handler.removeObject(temp);
				}
			}
			if (temp.getID() == ID.Coin) {
				if (getBounds().intersects(temp.getBounds())) {
					Coin coin = (Coin) temp;
					coin.fadeOut();
					if (Menu.SoundOn){
						if (AudioPlayer.getSound("Done").playing()) {
							AudioPlayer.getSound("Done").stop();
							AudioPlayer.getSound("Done").play();
						}
					}
					else {
						if (Menu.SoundOn)
						AudioPlayer.getSound("Done").play();
					}
						
					
				}
			} 
			
		}
	}

	public void render(Graphics g) {
		
		
		
		if (id==ID.player) 
			g.drawImage(playerImage, (int)x, (int)y , null);
		else if (id==ID.Player2) 
			g.drawImage(player2Image, (int)x, (int)y , null);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)alpha));
		g.drawImage(shieldImage,(int)x+22, (int)y, null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.9999f));
		

	}
	
	public Rectangle getBounds() {	
		return new Rectangle((int)x, (int)y, 33,33);
	}

	public static void startShield() {
		shieldActive=true; shieldTimer=250;
	}
	
	

}
	