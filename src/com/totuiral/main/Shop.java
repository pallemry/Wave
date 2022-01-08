package com.totuiral.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import java.awt.*;

import com.totuiral.main.Game.State;

public class Shop extends MouseAdapter {
	static int HealthRegPotions = 0, MaxHpAdditon = 0, SpeedBoost = 0, Shields = 0;
	private Handler handler;
	private Game game;
	private SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
	private BufferedImage img = Game.resize(ss.grapImage(4, 3, 20, 21), 45, 47);

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == State.Shop) {
			/** Back button */
			if (mouseOver(mx, my, 4, 7, 42, 32)) {
				Random r = new Random();
				game.gameState = State.Menu;
				for (int i = 0; i < 10; i++)
					handler.addObject(new MenuParticle(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50),
							ID.BasicEnemy, handler));
			} else if (mouseOver(mx, my, 76, 107, 490, 67)) {
				if (Handler.getNumCoins() >= 50 && handler.canSubtruct(50)) {
					if (HealthRegPotions <= 5) {
						if (Menu.SoundOn)
							AudioPlayer.getSound("money").play();
						handler.susbstructCoins(50);
						HealthRegPotions++;
						handler.addObject(new CoinAnimation(mx, my, null, handler));
					} else if (HealthRegPotions > 5)
						maxedStrings[0] = "Maxed!";
				}

			}
			// Max Hp++
			else if (mouseOver(mx, my, 76, 190, 490, 68)) {
				if (Handler.getNumCoins() >= 175 && handler.canSubtruct(175)) {
					if (MaxHpAdditon <= 20) {
						if (Menu.SoundOn)
							AudioPlayer.getSound("money").play();
						handler.susbstructCoins(175);
						MaxHpAdditon += 5;
						handler.addObject(new CoinAnimation(mx, my, null, handler));
					} else if (MaxHpAdditon > 20)
						maxedStrings[1] = "Maxed!";
				}
			}
			// Shield Button
			else if (mouseOver(mx, my, 75, 273, 490, 68)) {
				if (Handler.getNumCoins() >= 25 && handler.canSubtruct(25)) {
					if (Shields <= 5) {
						if (Menu.SoundOn)
							AudioPlayer.getSound("money").play();
						handler.susbstructCoins(25);
						Shields++;
						handler.addObject(new CoinAnimation(mx, my, null, handler));
					} else if (Shields > 5)
						maxedStrings[2] = "Maxed!";
				}

			}
			// Speed Button
			else if (mouseOver(mx, my, 74, 357, 490, 68)) {
				if (Handler.getNumCoins() >= 75 && handler.canSubtruct(75)) {
					if (SpeedBoost <= 25) {
						if (Menu.SoundOn)
							AudioPlayer.getSound("money").play();
						handler.susbstructCoins(75);
						SpeedBoost += 5;
						handler.addObject(new CoinAnimation(mx, my, null, handler));
					} else if (SpeedBoost > 25)
						maxedStrings[3] = "Maxed!";
				}
			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}
		}
		return false;
	}

	public Shop(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		Handler.setNumCoins(25);
	}

	String[] maxedStrings = { "", "", "", "" };

	public void render(java.awt.Graphics g) {

		g.drawImage(Game.shop_Image, 0, 0, null);

		g.drawImage(img, 416, 20, null);
		g.setColor(ColorUIResource.white);
		FontUIResource font = new FontUIResource("Broadway", 1, 40);
		g.setFont(font);

		g.drawString(Integer.toString(Handler.getNumCoins()), 470, 60);

		FontUIResource font2 = new FontUIResource("Broadway", 1, 16);
		g.setFont(font2);
		g.setColor(new Color(255, 255, 255, 235));
		g.drawString(Integer.toString(HealthRegPotions) + "  " + maxedStrings[0], 145, 106);
		g.drawString(Integer.toString(MaxHpAdditon + 100) + "  " + maxedStrings[1], 155, 190);
		g.drawString(Integer.toString(Shields) + "  " + maxedStrings[2], 142, 272);
		g.drawString("%" + Integer.toString(SpeedBoost) + "  " + maxedStrings[3], 162, 356);

	}

	public void tick() {

	}

}
