package com.totuiral.main;
import java.awt.image.BufferedImage;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
/**
 * class which is used to make coins which then<br>
 * the player can collect and then use to buy upgrades in<br>
 * the <code><u>Shop</u></code>. this class extends <code><u>GameObject</u></code>
 * @see com.totuiral.main.GameObject
 * @see com.totuiral.main.Shop
 * @author yisha
 *
 */
public class Coin extends GameObject{
	
	public BufferedImage[] coinAnimation = new BufferedImage[7];
	private int[] PositionsX = new int[7];
	private float alpha=0.99f;
	private Handler handler;
	private boolean movable;
	float Duraiton=0.05f;
	public Coin(float x, float y, ID id, Handler handler, int velX, int velY) {
		super(x, y, id);
		this.x=x;
		this.y=y;
		this.id=id;
		this.velX=velX;
		this.velY=velY;
		this.handler=handler;
		movable=true;
		Duraiton=0.00f;
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		CreateAnimation(ss, id);
	}
	
	public Coin(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x=x;
		this.y=y;
		this.id=id;
		this.handler=handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		CreateAnimation(ss, id);
	}

	float timer=0;
	
	public void tick() {
		//x+=velX; y+=velY;
		timer+=0.085;
		timer = Game.clampCycle(timer, 0, 6);
		ChangeAlpha();
	}

	BufferedImage img;
	int[] XAdders= {7,4,1,0,-1,4,-7};
	public void render(Graphics g) {
		img = coinAnimation[(int) timer];
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		g.drawImage(img,(int) (x)+XAdders[(int)timer],(int) y,null);
		//drawBound(g);	
		
	}

	@SuppressWarnings("unused")
	private void drawBound(Graphics g) {
		Rectangle r = getBounds();
		g.setColor(ColorUIResource.red);
		g.drawString(Integer.toString((int)timer),(int)x+7,(int)y);
		g.drawRect(r.x, r.y,r.width,r.height);
	}

	public Rectangle getBounds() {
		return new Rectangle(PositionsX[(int) timer],(int)y,img.getWidth(),img.getHeight());		
	}
	
	boolean fading;
	int count=0;
	public void fadeOut() {
		fading=true;
		count++;
		if (count==1) {
			Handler.numCoins++;
			Spawn.gainKeep_Coins++;
		}
	}
	public void CreateAnimation(SpriteSheet ss, ID id) {
			for (int i = 0; i < coinAnimation.length; i++) {
				switch (i) {
				case 0: {
					coinAnimation[i]= ss.grapImage(1, 3, 5, 21);
					PositionsX[i]=(int) (7+x);
					break; 
				}
				case 1:{
					coinAnimation[i] =ss.grapImage(2, 3, 12, 21);
					PositionsX[i]=(int) (4+x);
					break;
				}
				case 2:{
					coinAnimation[i] =ss.grapImage(3, 3, 18, 21);
					PositionsX[i]=(int) (1+x);
					break;
				}
				case 3:{
					coinAnimation[i] =ss.grapImage(4, 3, 20, 21);
					PositionsX[i]= (int) (0+x);
					break;
				}
				case 4:{
					coinAnimation[i] =ss.grapImage(1, 4, 18, 21);
					PositionsX[i] = (int) (-1+x);
					break;
				}
				case 5:{
					coinAnimation[i] =ss.grapImage(2, 4, 12, 21);
					PositionsX[i] = (int) (+4+x);
					break;
				}
				case 6:{
					coinAnimation[i] =ss.grapImage(3, 4, 4, 21);
					PositionsX[i] = (int) (-7+x);
					break;
				}
				default:
					break;
				}
			}
		
	}
	public void ChangeAlpha() {
		if (fading && alpha>0.01f) {
			alpha-=Duraiton;
			if (!movable)
				y-=3;
		}
		if (fading && alpha<=0.01) {
			handler.removeObject(this);
		}
	}
}