package com.totuiral.main;

import java.awt.*;

public class EnemyInstance extends GameObject {
	private Handler handler;
	private int maxx, maxy, minx, miny;
	public EnemyInstance(int minx, int miny,int maxx, int maxy, int x, int y,ID id, Handler handler) {
		super(x, y, id);
		velX=2;
		velY=2;
		this.handler=handler;
		this.minx = minx; this.miny=miny;
		this.maxx=maxx; this.maxy=maxy;
	}

	public void tick(){
		y+=velY;
		x+=velX;
		if (y<=minx || y>=maxy)  velY*=-1;
		if (x<=miny || x>=maxx)  velX*=-1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red , 10, 10,(float) 0.02, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 10, 10);
	}

	@Override 
	public Rectangle getBounds() {	
		return new Rectangle((int)x, (int)y, 16,16);
	}

}