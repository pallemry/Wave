package com.totuiral.main;

import java.awt.Color;

import java.awt.Graphics;


public class HUD {

	public static float HEALTH=100+Shop.MaxHpAdditon;
	public static float HEALTH2=0;
	float greenVal=255;
	private float greenVal2=255;
	private int score=0;
	private int level=1;
	private int highScore=0;
	public void tick() {
		HEALTH= Game.clamp( HEALTH, 0, 100+Shop.MaxHpAdditon);
		HEALTH2= Game.clamp( HEALTH2,0, 100+Shop.MaxHpAdditon);
		
		greenVal =  Game.clamp(greenVal, 0, 255);
		
		greenVal2 =  Game.clamp(greenVal2, 0, 255);
		
		greenVal = (float) (Game.convertNumToDiffRange(HEALTH,100+Shop.MaxHpAdditon,0 , 254, 0));
		greenVal2 = (float) (Game.convertNumToDiffRange(100+Shop.MaxHpAdditon-HEALTH2,100+Shop.MaxHpAdditon, 0, 254, 0));
		
		score++;
		if (score>highScore) highScore=score;
		
	}
	
	
	
	public void render(Graphics g) {
			g.setColor(Color.gray);
			g.fillRect(15,15,(int)((100+Shop.MaxHpAdditon)*2),32); 
			g.setColor(new Color(150, (int)greenVal, 0));
			g.fillRect(15,15,(int)((HEALTH)*2),32);
			g.setColor(Color.white);
			g.drawRect(15,15,(int)((100+Shop.MaxHpAdditon)*2),32);
			
			if (Settings.amountOfPlayers==2) {
				g.setColor(new Color(150, (int)greenVal2, 0));
				g.fillRect(400-Shop.MaxHpAdditon*2,15,(int)((100+Shop.MaxHpAdditon)*2),32);
				g.setColor(Color.gray);
				g.fillRect(400-Shop.MaxHpAdditon*2,15,(int)(HEALTH2*2),32);
				g.setColor(Color.white);
				g.drawRect(400-Shop.MaxHpAdditon*2,15,(int)((100+Shop.MaxHpAdditon)*2),32);
				g.drawString(Settings.Player2name, 400-Shop.MaxHpAdditon*2, 12);
				
			
			}
			g.drawString(Integer.toString(Handler.getNumCoins()),15,100);
			g.drawString("Score: "+score, 15, 64);
			g.drawString("Level: "+level, 15, 80);
			g.drawString(pauseHeader()+"pause", 15, 12);
			g.drawString(cheat, 15, 120);

	}
	private String pauseHeader() {
		if (Game.paused) return "un";
		return "";
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) { 
		this.score=score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) { 
		this.level=level;
	}

	public void reset() {
		level=1; score=0; HEALTH=100+Shop.MaxHpAdditon; HEALTH2=0;
		Spawn.reset();
	}
	public static void resetHealth() {
		HEALTH=100+Shop.MaxHpAdditon; HEALTH2=0;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore=highScore;
	}


	private static String cheat="";
	public static void renderCheatCode(String s) {
		cheat = s.contains("/") ? s : "";
	}
	
	
}









