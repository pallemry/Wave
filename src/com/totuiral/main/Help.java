package com.totuiral.main;


import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import com.totuiral.main.Game.State;



public class Help extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	static int currpage=1;
	private int x=275,y=160, timer=0, timerCount=35;
	private Random r = new Random();	
	public Help(Game game, Handler handler){
		this.game=game;
		this.handler=handler;
	}
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == State.Help) {
			if (mouseOver(mx, my, 15, 15, 60, 30)) {
				if (Menu.SoundOn)
				AudioPlayer.getSound("Click").play();
				game.gameState= State.Menu;
				handler.clearEnemies();
				for (int i=0; i<10; i++) {
					handler.addObject(new MenuParticle(r.nextInt(Game.width-50), r.nextInt(Game.height-50), null, handler));
				}
			}else if (mouseOver(mx, my,320,11,140,25)) {
				if (Menu.SoundOn)
				AudioPlayer.getSound("Click").play();
				handler.clearEnemies();
				currpage--;
				currpage= (int) Game.clampCycle(currpage, 1, 2);
				if (currpage==1) {
					handler.addObject(new EnemyInstance(321, 380,580, 420, 400, 380, ID.BasicEnemy, handler));
				}
			}else if (mouseOver(mx, my, 470,11,140,25)) {
				if (Menu.SoundOn)
				AudioPlayer.getSound("Click").play();
				handler.clearEnemies();
				currpage++;
				currpage= (int) Game.clampCycle(currpage, 1, 2);
				if (currpage==1) {
					handler.addObject(new EnemyInstance(321, 380,580, 420, 400, 380, ID.BasicEnemy, handler));
				}
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
	
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		Font font = new Font("arial", 1, 15);
		Font settingInstanceFont = new Font("arial", 1, 22);
		Font font2 = new Font("arial", 1, 30);
		if (currpage==1) {
			g.setFont(font2);
			g.drawRect(380,320,210,110);
			g.drawString("In this game you are playing", 100, 80);
			g.drawString("a dot, Either a", 100, 113);
			g.drawString(".", 437, 115);
			g.drawString("or a", 340, 113);
			g.drawString("Depends on who you play.", 100, 146);
			g.drawString("Your goal is to achieve the", 100, 210);
			g.drawString("highest score and survive.", 100, 243);
			g.drawString("In order to do this, you will need to", 100, 307);
			g.drawString("avoid the enemies", 100, 338);
			g.drawString("as shown here;", 100, 370);
			g.fillRect(300,88,30,30);
			
			
			g.setColor(Color.green);
			
			g.fillRect(405,88,30,30);
			handler.render(g);
			
		}
		
		if (currpage==2) {
			g.setFont(settingInstanceFont);
			g.setColor(new Color(16, 16, 16));
			g.fillRect(150,253,100,30);
			
			g.setColor(Color.white);
			
			g.drawRect(150,253,100,30);
			g.setColor(Color.red);
			g.drawString("Settings", y, x);
			g.setColor(Color.white);
			g.drawString("Settings", 158, 276);
			
			g.setFont(font2);
			
			g.drawString("You can also heal back up", 100, 80);
			g.drawString("by taking the HP Pack", 100, 113);
			g.drawString("Represented by", 100, 146);
			g.drawString("If you would like you could ", 100, 210);
			g.drawString("configure your settings by clicking", 100, 243);
			g.drawString("on", 100, 276);
			g.drawString("like changing the", 260, 277);
			g.drawString("difficuly and more!", 100, 312);
			g.drawString("Good luck!", 100, 392);
			g.drawString("^", 260, 388);
			g.drawString("^", 280, 388);
			g.drawString("_", 270, 388);
			g.setColor(Color.blue);
			 g.fillRect(335,122,30,30);
		}
		g.setColor(Color.white);
		g.drawLine(90,0,90,Game.height);
		g.setFont(font);
		g.drawString("Back", 28, 35);
		g.drawRect(15,15,60,30);
		g.drawRect(15,15,60,30);
		g.drawRect(320,11,140,25);
		g.drawRect(470,11,140,25);
		
		g.setFont(font2);
		g.setColor(new Color(15,200,157));
		g.drawString("Page "+currpage+" out of "+2, 100, 35);
		//
		Font fontForPage = new Font("arial", 1, 20);
		g.setFont(fontForPage);
		g.setColor(new Color(150,200,15));
		g.drawString("<< Prev page", 325, 30);
		g.drawString("Next page >>", 480, 31);
		
		
		
		
		

	}public void tick() {
		timer++;
		if (timer>=timerCount) {
			if (x==278) { x=275; y=160;}
			else if (x==275) {x=278; y=157;}
			timer=0;
		}
		
	}
	
}





















