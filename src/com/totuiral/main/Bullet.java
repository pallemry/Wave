package com.totuiral.main;

import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {
	private Handler handler;
	Random r = new Random();
	Color c;
	BufferedImage in;

	public Bullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = (float) (Math.random() * (5 - -5 + 1) + -5);
		velY = (float) (Math.random() * (5));
		this.handler = handler;
		c = new Color(217, 103, 33);
	}

	public void tick() {
		y += velY;
		x += velX;
		if (y >= Game.height)
			handler.removeObject(this);
		if (x >= Game.width)
			handler.removeObject(this);

		handler.addObject(new Trail(x, y, ID.Trail, c, 16, 16, (float) 0.2, handler));
	}

	public void render(Graphics g) {
		g.setColor(c);
		g.fillRect((int) x, (int) y, 16, 16);
	}

	@Override /** @inheritDoc */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}