package com.totuiral.main;

import java.awt.image.*;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A class that allows loading image from the res folder
 * 
 * @see Game
 * @since 1.10
 * @author yisha
 *
 */
public class BufferedImageLoader {

	private BufferedImage image;

	public BufferedImage loadImage(String path) throws IOException {
		try {
			image = ImageIO.read(new FileInputStream("res/" + path));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}