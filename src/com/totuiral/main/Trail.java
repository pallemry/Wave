package com.totuiral.main;

import java.awt.*;

public class Trail extends GameObject {

	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int width, height;
	private float life;

	public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.color = color;
		this.handler = handler;
		this.height = height;
		this.width = width;
		this.life = life;
	}

	@Override
	public void tick() {
		if (alpha > life) {
			alpha -= life - 0.001;
		} else
			handler.removeObject(this);

	}

	@Override
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(maketrans(alpha));
		g2d.setColor(color);
		g2d.fillRect((int) x, (int) y, width, height);
		g2d.setComposite(maketrans(1));

	}

	private AlphaComposite maketrans(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

}
