package com.totuiral.main;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CoinAnimation extends GameObject {
	Random random = new Random();
	Handler handler;
	Map<String, Coin> coinMap = new HashMap<String, Coin>();
	public CoinAnimation(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x=x;
		this.y=y;
		
		for (int i = 0; i < 4; i++) {
			coinMap.put(Integer.toString(i), new Coin(x, y, ID.CoinInstance, handler, 0,0));
			handler.addObject(coinMap.get(Integer.toString(i)));
		}
		
	}

	float fictiveX=-2;
	public void tick() {
		fictiveX+=0.18f;
		
		for (int i = 0; i < 4; i++) {
			if (i==1 || i==0) {
				coinMap.get(Integer.toString(i)).y=(float) ((Math.pow(fictiveX,2))*1.0/5+((i+1)*0.75))+coinMap.get(Integer.toString(i)).y;
			}
			else {
				coinMap.get(Integer.toString(i)).y=(float) ((Math.pow(fictiveX,2))*1.0/5+((i-1)*0.75))+coinMap.get(Integer.toString(i)).y;
			}
			switch (i) {
				case 0,1:
					{coinMap.get(Integer.toString(i)).x -= 1+i; break;}
				default:
					{coinMap.get(Integer.toString(i)).x += 1+i-2; break;}
			}
		}
	}

	public void render(Graphics g) {
		
	}
	public Rectangle getBounds() {
		return null;
	}

}
