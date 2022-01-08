package com.totuiral.main;

import java.awt.image.*;

public class SpriteSheet {

	private BufferedImage sprite;

	public SpriteSheet(BufferedImage ss) {
		this.sprite = ss;
	}

	public BufferedImage grapImage(int row, int col, int width, int height) {

		BufferedImage croppedImage = sprite.getSubimage((row * 32 - 32), (col * 32) - 32, width, height);

		return croppedImage;

	}
}
