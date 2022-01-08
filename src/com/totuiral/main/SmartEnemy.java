package com.totuiral.main;

import java.awt.*;



public class SmartEnemy extends GameObject {
	private Handler handler;
	private GameObject player2;
	private GameObject player;
	private GameObject currPlayer;
	
	public static boolean KilledPlayer1, KilledPlayer2;
	
	
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler=handler;
		
		for (int i=0; i<handler.object.size(); i++)
		{
			if(handler.object.get(i).getID() == ID.player)
			{
				player = handler.object.get(i); break;
			}
			else if(handler.object.get(i).getID() == ID.Player2)
			{
				player2 = handler.object.get(i); break;
			}
		}
		velX=0;
		velY=0;
		
	}

	public void tick(){
		
		x+=velX;
		y+=velY;
		if (player!=null && player2!=null && !KilledPlayer1 && !KilledPlayer2)
		{
			float distance1 = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
			float distance2 = (float) Math.sqrt((x-player2.getX())*(x-player2.getX()) + (y-player2.getY())*(y-player2.getY()));
			if (distance1<distance2)
			{
				currPlayer=player;
			}else if(distance2<distance1)
			{
				currPlayer=player2;
			}else {
				currPlayer=player;
			}
		}else if(player!=null && KilledPlayer2)
		{
			currPlayer=player;
		}else if (player2!=null && KilledPlayer1) {
			currPlayer=player2;
		}else if (player!=null && !KilledPlayer1 && !KilledPlayer2) {
			currPlayer=player;
		}
		if (currPlayer!=null) {
			float diffx  = x - currPlayer.getX() - 16;
			float diffy  = y - currPlayer.getY() - 16;
			float distance = (float) Math.sqrt((x-currPlayer.getX())*(x-currPlayer.getX()) + (y-currPlayer.getY())*(y-currPlayer.getY()));
			
			velX =  ((-1/distance)* diffx)*2; 
			
			velY =  ((-1/distance)* diffy)*2;
		
		
		//if (y<=0 || y>=Game.height-50)  velY*=-1;
		//if (x<=0 || x>=Game.width-50)  velX*=-1;
		
		    handler.addObject(new Trail(x,y, ID.Trail, Color.CYAN , 16, 16,(float) 0.02, handler));
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x,(int) y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {	
		return new Rectangle((int)x,(int) y, 16,16);
	}

}