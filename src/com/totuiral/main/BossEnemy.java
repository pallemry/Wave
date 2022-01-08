package com.totuiral.main;

import java.awt.*;
import java.util.Random;

/**
 * reprsenting the boss enemy that shows up in level 7-9 the boss shoots out
 * enemy bullets which the player has to avoid in order to survive <br>
 * Inheriting the GameObject
 * 
 * @author yisha
 *
 */
public class BossEnemy extends GameObject {
	private Handler handler;

	private int timer = 100;
	private int timer2 = 100 / 2;
	Random r = new Random();

	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = 0;
		velY = (float) 1.5;

		this.handler = handler;
	}

	public void tick() {
		y += velY;
		x += velX;
		if (timer <= 0) {
			velY = 0;
			timer2--;
		} else
			timer--;
		if (timer2 <= 0) {
			if (velX == 0) {
				velX = (float) 2.5;
			}
			if (velX > 0)
				velX += 0.05f;
			else if (velX < 0)
				velX -= 0.05f;

			velX = Game.clamp(velX, -10, 10);

			int numOfChance = 0;
			if (Settings.Difficulty == 1) {
				numOfChance = 12;
			}
			if (Settings.Difficulty == 2) {
				numOfChance = 10;
			}
			if (Settings.Difficulty == 3) {
				numOfChance = 7;
			}
			int spawn = r.nextInt(numOfChance);
			if (spawn == 0)
				handler.addObject(new Bullet((int) x + 48, (int) y + 48, ID.BulletEnemy, handler));
		}
		// if (y<=0 || y>=Game.height-50) velY*=-1;
		if (x <= 0 || x >= Game.width - 112)
			velX *= -1;

		// handler.addObject(new Trail(x, y, ID.Trail, Color.red , 96, 96,(float) 0.02,
		// handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 96, 96);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 96, 96);
	}

}