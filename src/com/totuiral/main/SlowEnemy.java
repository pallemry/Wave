package com.totuiral.main;

import java.awt.*;
import java.awt.Color;

public class SlowEnemy extends GameObject {
	private Handler handler;
	public SlowEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		if (Settings.Difficulty==1) { velX= 7.5f; velY =1.5f;}
		if (Settings.Difficulty==2) { velX=9; velY=2;}
		if (Settings.Difficulty==3) { velX =10.15f; velY=2.23f;}
		this.handler=handler;
	}

	public void tick(){
		y+=velY;
		x+=velX;
		if (y<=0 || y>=Game.height-50)  velY*=-1;
		if (x<=0 || x>=Game.width-50)  velX*=-1;
		
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, new Color(85, 189, 47) , 16, 16,(float) 0.02, handler));
	}

	public void render(Graphics g) {
		g.setColor(new Color(85, 189, 47));
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {	
		return new Rectangle((int)x, (int)y, 16,16);
	}

}