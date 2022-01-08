package com.totuiral.main;

import java.awt.*;
import java.awt.Color;

public class FastEnemy extends GameObject {
	private Handler handler;
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		if (Settings.Difficulty==1) { velX= 1.3f; velY =8;}
		if (Settings.Difficulty==2) { velX=2; velY=10;}
		if (Settings.Difficulty==3) { velX =2.5f; velY=11;}
		this.handler=handler;
	}

	public void tick(){
		y+=velY;
		x+=velX;
		if (y<=0 || y>=Game.height-50)  velY*=-1;
		if (x<=0 || x>=Game.width-50)  velX*=-1;
		
		handler.addObject(new Trail(x, y, ID.Trail, new Color(50, 0, 120) , 16, 16,(float) 0.02, handler));
	}

	public void render(Graphics g) {
		g.setColor(new Color(50, 0, 120));
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {	
		return new Rectangle((int)x, (int)y, 16,16);
	}

}