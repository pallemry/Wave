package com.totuiral.main;

import java.awt.Canvas;
import java.awt.*;
import java.awt.image.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.geom.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

/**
 * The Game class of the game "Wave2" <br>
 * <br>
 * This class is the main class which handles all of the game Objects themselves
 * <br>
 * including {@link GameObject} | {@link Shop} | {@link Menu} | {@link Settings}
 * etc.
 * 
 * @author yisha
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -360961108030713769L;
	public static final int width = 640, height = width / 12 * 9;
	private Thread thread;
	private boolean running = false;
	static boolean waiting = false;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Help help;
	private Menu menu;
	private Shop shop;
	private Settings settings;
	private EndScreen endScreen;
	static int time, timeToWait;
	int x = 1;
	public static boolean paused = false;

	/**
	 * Indicates the current game state e.g. <br>
	 * The game states are:
	 * <ul>
	 * <li>Menu
	 * <li>Game
	 * <li>Help
	 * <li>Settings
	 * <li>EndScreen
	 * <li>Shop
	 * </ul>
	 * <br>
	 * Although there is one more state which was <br>
	 * not included above. see <b><code> {@link #midGame}</b></code>, because it
	 * <br>
	 * is used in animations and not as an actual <br>
	 * gameSate which is usually checked.
	 * 
	 * @see Game
	 */
	public enum State {
		Menu, Game, Help, Settings, EndScreen, midGame, Shop
	};

	public State gameState = State.Menu;

	public static BufferedImage sprite_sheet;
	public static BufferedImage shopping_cart_icon;
	public static BufferedImage shop_Image;

	/**
	 * game constructor. initialize the different instance classes
	 * 
	 * @throws IOException
	 */
	public Game() throws IOException {

		BufferedImageLoader loader = new BufferedImageLoader();
		shopping_cart_icon = loader.loadImage("Shopping.png");
		sprite_sheet = loader.loadImage("SpriteSheet.png");
		shop_Image = loader.loadImage("Shop.png");
		shopping_cart_icon = resize(shopping_cart_icon, 35, 35);
		shop_Image = Game.resize(shop_Image, 585, 439);

		AudioPlayer.load();

		handler = new Handler(this);
		help = new Help(this, handler);
		shop = new Shop(handler, this);
		hud = new HUD();
		spawn = new Spawn(handler, hud, this);
		endScreen = new EndScreen(this, handler, hud);
		menu = new Menu(this, handler, hud, endScreen);
		settings = new Settings(this, handler);

		this.addKeyListener(new KeyInput(handler, settings, this));
		this.addKeyListener(new KeyInputForSettings(settings));
		this.addMouseListener(endScreen);
		this.addMouseListener(menu);
		this.addMouseListener(help);
		this.addMouseListener(settings);
		this.addMouseListener(shop);
		Handler.setNumCoins(9999);
		new Window(width, height, "test", this);
		Handler.numCoins += 100;
		start();
	}

	/** Creates a new thread and starts running the game */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/** stops the current thread */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static int frames;
	double avg = 0;
	long count = 0;

	/**
	 * the run method, which calculates time and calls the tick and render method 
	 * {@inheritDoc}
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				handler.printFrames();
				// System.out.print("\nFPS: "+frames);
				FpsRecords.add(frames);
				if (FpsRecords.size() > 1000)
					FpsRecords.removeFirst();
				frames = 0;
			}
		}
		stop();
	}

	/**
	 * ticks the game, makes it move forward for example, If i pressed "W" the tick
	 * method will make my player move forward 5 pixels each time the method is
	 * called (about 10000 times a second!)
	 */
	public void tick() {
		if (paused && Menu.SoundOn) {
			if (AudioPlayer.getMusic("Menu").playing()) {
				AudioPlayer.getMusic("Menu").stop();
				AudioPlayer.getMusic("Pause").loop();
			}
		}
		if (!paused && Menu.SoundOn) {
			if (AudioPlayer.getMusic("Pause").playing()) {
				AudioPlayer.getMusic("Pause").stop();
				AudioPlayer.getMusic("Menu").loop();
			}
		}

		if (gameState == State.Game) {

			if (!paused) {
				handler.tick();
				hud.tick();
				spawn.tick();
			}

		} else {
			handler.tick();
			if (gameState == State.Menu) {
				menu.tick();
			} else if (gameState == State.Settings) {
				settings.tick();
			} else if (gameState == State.Help) {
				help.tick();
			} else if (gameState == State.midGame) {
				menu.tick();
			} else if (gameState == State.EndScreen) {
				endScreen.tick();
			} else if (gameState == State.Shop) {
				shop.tick();
			}
		}

	}

	/*
	 * renders the game using the java.awt.Graphics class (basically shows the game,
	 * without it the game will be a blank screen.
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		if (gameState == State.Game) {
			handler.render(g);
			hud.render(g);
			menu.render(g);
		} else if (gameState == State.Menu) {
			handler.render(g);
			menu.render(g);
		} else if (gameState == State.Help) {
			handler.render(g);
			help.render(g);
		} else if (gameState == State.Settings) {
			handler.render(g);
			settings.render(g);
		} else if (gameState == State.EndScreen) {
			handler.render(g);
			hud.render(g);
			endScreen.render(g);

		} else if (gameState == State.Shop) {
			shop.render(g);
			handler.render(g);
		} else {
			handler.render(g);
		}
		if (waiting) {
			if (time < timeToWait) {
				time++;
			} else if (time >= timeToWait) {
				timeToWait = 0;
				time = 0;
				waiting = false;
				gameState = State.midGame;
			}
		}

		FontUIResource font = new FontUIResource("arial", 1, 15);
		g.setFont(font);
		g.setColor(ColorUIResource.white);

		g.setColor(ColorUIResource.white);
		g.fillRect(5, height - 65, 70, 20);

		g.setXORMode(Color.white);
		g.setColor(ColorUIResource.black);
		g.drawString("FPS: " + handler.getActualFPS(), 13, Game.height - 49);
		g.drawString("Avg " + String.format("%.1f", getAvg(FpsRecords)), 13, Game.height - 79);

		g.dispose();

		bs.show();
	}

	/**
	 * Takes a float value, max value, min value and if the value is out of bounds
	 * it will return the closest value in bounds of max and min. for example if
	 * value=6, min=3, max=5, it will return 5 since its the closest in-bound value
	 * inclusive. otherwise it would return the value.
	 * 
	 * @param var the value which will be returned according to the range
	 * @author yisha
	 * @param min the min value of the range
	 * @param max the max value of the range
	 * @return The closest inbound value
	 * @throws IllegalArgumentException In case the Max is larger than the Min.
	 * @see {@link #com.totuiral.main.Game.clampCycle(float, float, float)
	 *      Game.clampCycle}
	 */
	public static float clamp(float var, float min, float max) {
		if (max < min)
			throw new IllegalArgumentException(String.format("Max (%d) cannot be larger than Min (%d).", max, min));
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	/**
	 * just like {@link #clamp(float, float, float) clamp}, but the other way
	 * around. This method takes a float {@code value} {@code max} value,
	 * {@code min} value and if the value is out of bounds it will return the
	 * opposite closest value in bounds of max and min. for example if value=6,
	 * min=3, max=5, it will return 3 since its the closest opposite in-bound value.
	 * otherwise it would return the value.
	 * 
	 * @param var {@code the value which will be returned according to the range}
	 * @param min the min value of the range
	 * @param max the max value of the range
	 * @see {@link #clamp(float, float, float) clamp}
	 * @throws IllegalArgumentException {@code In case the Max is larger than the Min.}
	 * @see State
	 * 
	 */
	public static float clampCycle(float var, float min, float max) {
		if (max < min)
			throw new IllegalArgumentException(String.format("Max (%d) cannot be larger than Min (%d).", max, min));
		if (var > max)
			return var = min;
		else if (var < min)
			return var = max;
		else
			return var;
	}

	/*
	 * waits a given amount of time and while the method is still running
	 * Game.waiting is true and once its finished Game.waiting is false and the game
	 * State will be initialized to State.midGame .
	 */
	public static void waitTime(int time) {
		timeToWait = time;
		waiting = true;
	}

	public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
		double rads = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
		int w = img.getWidth();
		int h = img.getHeight();
		int newWidth = (int) Math.floor(w * cos + h * sin);
		int newHeight = (int) Math.floor(h * cos + w * sin);

		BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotated.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - w) / 2, (newHeight - h) / 2);

		int x = w / 2;
		int y = h / 2;

		at.rotate(rads, x, y);
		g2d.setTransform(at);
		g2d.drawImage(img, 0, 0, this);
		g2d.dispose();

		return rotated;
	}

	public BufferedImage flipImage(BufferedImage img, int direction) {
		int height = img.getHeight();
		int width = img.getWidth();

		BufferedImage rotated = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (direction == 1) {
					rotated.setRGB((width - 1) - x, y, img.getRGB(x, y));
				} else if (direction == -1) {
					rotated.setRGB(x, (height - 1) - y, img.getRGB(x, y));
				}
			}
		}

		return rotated;
	}

	public static double convertNumToDiffRange(double num, double firstMax, double firstMin, double secondMax,
			double secondMin) {

		if (num <= firstMax && num >= firstMin && firstMax >= firstMin && secondMax >= secondMin) {

			double ratio = (num - firstMin) / (firstMax - firstMin);

			double result = ((ratio * (secondMax - secondMin)) + secondMin);

			return result;
		}
		return -1;

	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();

		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	static {
		File PATH = new File("Libs/lwjgl/native/windows");

		System.setProperty("org.lwjgl.librarypath", PATH.getAbsolutePath()); // This is the property not the regular
																				// Djava.library.path
	}
	LinkedList<Integer> FpsRecords = new LinkedList<Integer>();

	// main method
	public double getAvg(LinkedList<Integer> l) {
		double sum = 0;
		for (int i = 0; i < l.size(); i++) {
			sum += l.get(i);
		}
		return sum / l.size();
	}

	public static void main(String[] args) throws IOException {
		new Game();
	}

}
