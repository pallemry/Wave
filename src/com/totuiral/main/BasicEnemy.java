package com.totuiral.main;

import java.awt.*;

import java.awt.image.*;

/**
 * Basic enemy which goes in a consistent {@code X & Y} speeds by default in
 * addition to that its {@code ID} ({@link ID#BasicEnemy} ) is also used for
 * {@link MenuParticle} to make the menu look a little nicer or in the
 * {@link EnemyInstance} class which is used to make the Help section a little
 * more playful and fun to watch. <br>
 * <br>
 * This class inherits <b> {@code GameObject} </b> class
 * 
 * @see com.totuiral.main.GameObject
 * @see com.totuiral.main.MenuParticle
 * @see com.totuiral.main.EnemyInstance
 * @author yisha
 *
 */
public class BasicEnemy extends GameObject {
	private Handler handler;
	private Game game;
	private BufferedImage BasicEnemy_Image;

	public BasicEnemy(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);

		if (Settings.Difficulty == 1) {
			velX = 4;
			velY = 4;
		}
		if (Settings.Difficulty == 2) {
			velX = 5;
			velY = 5;
		}
		if (Settings.Difficulty == 3) {
			velX = 6.15f;
			velY = 6.15f;
		}
		this.handler = handler;
		this.game = game;
		SpriteSheet spriteSheet = new SpriteSheet(Game.sprite_sheet);
		BasicEnemy_Image = spriteSheet.grapImage(2, 1, 16, 16);
	}

	/**
	 * {@inheritDoc}
	 */
	public void tick() {
		y += velY;
		x += velX;
		if (y <= 0 || y >= Game.height - 50) {
			velY *= -1;
			BasicEnemy_Image = game.rotateImageByDegrees(BasicEnemy_Image, 90);
		}
		if (x <= 0 || x >= Game.width - 50) {
			velX *= -1;
			BasicEnemy_Image = game.rotateImageByDegrees(BasicEnemy_Image, 90);
		}

		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, (float) 0.02, handler));
	}

	/**
	 * {@inheritDoc}
	 */
	public void render(Graphics g) {
		g.drawImage(BasicEnemy_Image, (int) x, (int) y, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}