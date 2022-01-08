package com.totuiral.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class HPpack extends GameObject{
	
	

	public HPpack(int x, int y, HUD hud, ID id, Handler handler) {
		super(x, y, id);
	}

	static int count= 0;
	public void tick() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 30, 30);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int) y, 30,30);
	}

}
