package com.totuiral.main;
import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject {
	private Handler handler;
	private Random r= new Random();
	private Color colorUIResource;
	private int HSBcolor;
	private int dir;

	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		dir = r.nextInt(3);
		if (dir==0) {
			velX=9; velY=2; 
		} else if (dir==1) {
			velX=2; velY=9; 
		} else if (dir==2) {
			velX=5; velY=5; 
		}
		float bright = r.nextFloat()*0.1f+0.90f;
		float sat = r.nextFloat()*0.1f+0.90f;
		HSBcolor = Color.HSBtoRGB((float) Math.random(),sat, bright);
		colorUIResource= new Color(HSBcolor);

		
		
	}
	
	public void tick(){
		
		y+=velY;
		x+=velX;
		if (y<=0 || y>=Game.height-50)  velY*=-1;
		if (x<=0 || x>=Game.width-50)  velX*=-1;
		
		handler.addObject(new Trail(x, y, ID.Trail, colorUIResource , 16, 16,(float) 0.02, handler));
		
		
		
	}

	public void render(Graphics g) {
		g.setColor(colorUIResource);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override 
	public Rectangle getBounds() {	
		return new Rectangle((int)x, (int)y, 16,16);
	}

}