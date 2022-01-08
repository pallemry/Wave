package com.totuiral.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import com.totuiral.main.Game.State;

public class Settings extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Random r = new Random();
	static int Difficulty = 1;
	static int amountOfPlayers = 2;
	private String waitingAnimation = "Waiting.";
	static int[] KeyCodes = { KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_UP,
			KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT };
	static String[] keyTextString = { "W", "S", "A", "D", "Up", "Down", "Right", "Left" };
	static boolean[] isWaitingForInput = new boolean[8];
	private String tempString;
	static boolean free;
	static boolean isPlayer1Namefull, isPlayer2Namefull;
	private int NumberOfButton = 0;
	private int fade = 200;
	static boolean edited;
	int timer = 0, timerCount = 27;
	float fademultipler = 1;
	static boolean waitingForName, waitingForSecondName;
	static int page = 1;
	static String Player1name = "null", Player2name = "null";

	public Settings(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == State.Settings) {
			if (page == 1) {
				if (mouseOver(mx, my, 275, 88, 85, 33)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					Difficulty = 1;
				} else if (mouseOver(mx, my, 360, 88, 130, 33)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					Difficulty = 2;
				} else if (mouseOver(mx, my, 490, 88, 80, 33)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					Difficulty = 3;
				}

				else if (mouseOver(mx, my, 220, 210, 130, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					for (int i = 0; i < isWaitingForInput.length; i++)
						isWaitingForInput[i] = false;
					edited = true;
					if (free)
						keyTextString[NumberOfButton] = tempString;
					tempString = keyTextString[0];
					free = true;
					NumberOfButton = 0;
					keyTextString[0] = waitingAnimation;
					isWaitingForInput[0] = true;
				} else if (mouseOver(mx, my, 220, 243, 130, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					for (int i = 0; i < isWaitingForInput.length; i++)
						isWaitingForInput[i] = false;
					edited = true;
					if (free)
						keyTextString[NumberOfButton] = tempString;
					tempString = keyTextString[1];
					free = true;
					NumberOfButton = 1;
					keyTextString[1] = waitingAnimation;
					isWaitingForInput[1] = true;
				} else if (mouseOver(mx, my, 220, 276, 130, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					for (int i = 0; i < isWaitingForInput.length; i++)
						isWaitingForInput[i] = false;
					edited = true;
					if (free)
						keyTextString[NumberOfButton] = tempString;
					tempString = keyTextString[2];
					free = true;
					NumberOfButton = 2;
					keyTextString[2] = waitingAnimation;
					isWaitingForInput[2] = true;
				} else if (mouseOver(mx, my, 220, 309, 130, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					for (int i = 0; i < isWaitingForInput.length; i++)
						isWaitingForInput[i] = false;
					edited = true;
					if (free)
						keyTextString[NumberOfButton] = tempString;
					tempString = keyTextString[3];
					free = true;
					NumberOfButton = 3;
					keyTextString[3] = waitingAnimation;
					isWaitingForInput[3] = true;
				} else if (mouseOver(mx, my, 490, 210, 130, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					if (amountOfPlayers == 2) {
						for (int i = 0; i < isWaitingForInput.length; i++)
							isWaitingForInput[i] = false;
						edited = true;
						if (free)
							keyTextString[NumberOfButton] = tempString;
						tempString = keyTextString[4];
						free = true;
						NumberOfButton = 4;
						keyTextString[4] = waitingAnimation;
						isWaitingForInput[4] = true;
					}
				} else if (mouseOver(mx, my, 490, 243, 130, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					if (amountOfPlayers == 2) {
						for (int i = 0; i < isWaitingForInput.length; i++)
							isWaitingForInput[i] = false;
						edited = true;
						if (free)
							keyTextString[NumberOfButton] = tempString;
						tempString = keyTextString[5];
						free = true;
						NumberOfButton = 5;
						keyTextString[5] = waitingAnimation;
						isWaitingForInput[5] = true;
					}
				} else if (mouseOver(mx, my, 490, 276, 130, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					if (amountOfPlayers == 2) {
						for (int i = 0; i < isWaitingForInput.length; i++)
							isWaitingForInput[i] = false;
						edited = true;
						if (free)
							keyTextString[NumberOfButton] = tempString;
						tempString = keyTextString[6];
						free = true;
						NumberOfButton = 6;
						keyTextString[6] = waitingAnimation;
						isWaitingForInput[6] = true;
					}
				} else if (mouseOver(mx, my, 490, 309, 130, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					if (amountOfPlayers == 2) {
						for (int i = 0; i < isWaitingForInput.length; i++)
							isWaitingForInput[i] = false;
						edited = true;
						if (free)
							keyTextString[NumberOfButton] = tempString;
						tempString = keyTextString[7];
						free = true;
						NumberOfButton = 7;
						keyTextString[7] = waitingAnimation;
						isWaitingForInput[7] = true;
					}
				} else if (mouseOver(mx, my, 484, 367, 30, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					amountOfPlayers++;
					amountOfPlayers = (int) Game.clampCycle(amountOfPlayers, 1, 2);
				} else if (mouseOver(mx, my, 404, 367, 30, 30)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					amountOfPlayers--;
					amountOfPlayers = (int) Game.clampCycle(amountOfPlayers, 1, 2);
				}
			} else if (page == 2) {
				if (mouseOver(mx, my, 100, 180, 220, 50)) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					if (!waitingForName) {
						waitingForName = true;
						Player1name = replaceIfBlank(Player1name, !waitingForName);
					} else if (waitingForName) {
						waitingForName = false;
						Player1name = replaceIfBlank(Player1name, !waitingForName);
					}
					waitingForSecondName = false;
				} else if (mouseOver(mx, my, 340, 180, 220, 50) && amountOfPlayers == 2) {
					if (Menu.SoundOn)
						AudioPlayer.getSound("Click").play();
					if (!waitingForSecondName) {
						waitingForSecondName = true;
						Player2name = replaceIfBlank(Player2name, !waitingForSecondName);
					} else if (waitingForSecondName) {
						waitingForSecondName = false;
						Player2name = replaceIfBlank(Player2name, !waitingForSecondName);
					}
					waitingForName = false;

				}
			}
			if (mouseOver(mx, my, 15, 15, 60, 30)) {
				if (Menu.SoundOn)
					AudioPlayer.getSound("Click").play();
				page = 1;

				Player1name = replaceIfBlank(Player1name, true);
				Player2name = replaceIfBlank(Player2name, true);

				if (edited && (keyTextString[NumberOfButton].equals("Waiting.")
						|| keyTextString[NumberOfButton].equals("Waiting.."))) {
					keyTextString[NumberOfButton] = tempString;
				}
				game.gameState = State.Menu;
				waitingForName = false;
				waitingForSecondName = false;
				for (int i = 0; i < 10; i++) {
					handler.addObject(
							new MenuParticle(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), null, handler));
				}
			} else if (mouseOver(mx, my, 320, 11, 140, 25)) {
				if (Menu.SoundOn)
					AudioPlayer.getSound("Click").play();
				page--;
				page = (int) Game.clampCycle(page, 1, 2);
			} else if (mouseOver(mx, my, 470, 11, 140, 25)) {
				if (Menu.SoundOn)
					AudioPlayer.getSound("Click").play();

				page--;
				page = (int) Game.clampCycle(page, 1, 2);
			}
		}
	}

	private String replaceIfBlank(String player1name2, boolean b) {
		return b ? (player1name2.isBlank() ? "null" : player1name2) : "";
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

	public void render(Graphics g) {
		Font font = new Font("arial", 1, 15);
		Font font2 = new Font("arial", 1, 30);
		Font font3 = new Font("arial", 1, 50);
		// PLayer1
		g.setFont(font2);

		if (page == 1) {
			for (int i = 0; i < keyTextString.length; i++) {
				if (keyTextString[i].equals(waitingAnimation)) {
					// System.out.println(i+" button is waiting");
					g.setColor(new Color(255, 255, 255, (int) fade));
					if (i == 0) {
						Color color = g.getColor();
						g.setColor(ColorUIResource.red);
						g.fillRect(220, 210, 130, 30);
						g.setColor(color);
						g.drawString(keyTextString[i], 224, 235);
					} else if (i == 1) {
						Color color = g.getColor();
						g.setColor(ColorUIResource.red);
						g.fillRect(220, 243, 130, 30);
						g.setColor(color);
						g.drawString(keyTextString[i], 224, 268);
					} else if (i == 2) {
						Color color = g.getColor();
						g.setColor(ColorUIResource.red);
						g.fillRect(220, 276, 130, 30);
						g.setColor(color);
						g.drawString(keyTextString[i], 224, 301);
					}

					else if (i == 3) {
						Color color = g.getColor();
						g.setColor(ColorUIResource.red);
						g.fillRect(220, 309, 130, 30);
						g.setColor(color);
						g.drawString(keyTextString[i], 224, 334);
					} else if (i == 4 && amountOfPlayers == 2) {
						Color color = g.getColor();
						g.setColor(ColorUIResource.red);
						g.fillRect(490, 210, 130, 30);
						g.setColor(color);
						g.drawString(keyTextString[i], 494, 235);
					} else if (i == 5 && amountOfPlayers == 2) {
						Color color = g.getColor();
						g.setColor(ColorUIResource.red);
						g.fillRect(490, 243, 130, 30);
						g.setColor(color);
						g.drawString(keyTextString[i], 494, 268);
					} else if (i == 6 && amountOfPlayers == 2) {
						Color color = g.getColor();
						g.setColor(ColorUIResource.red);
						g.fillRect(490, 276, 130, 30);
						g.setColor(color);
						g.drawString(keyTextString[i], 494, 301);
					} else if (i == 7 && amountOfPlayers == 2) {
						Color color = g.getColor();
						g.setColor(ColorUIResource.red);
						g.fillRect(490, 309, 130, 30);
						g.setColor(color);
						g.drawString(keyTextString[i], 494, 334);
					}

				} else if (!keyTextString[i].equals(waitingAnimation)) {
					if (i == 0) {
						g.setColor(Color.white);
						g.drawString(keyTextString[i], 224, 235);
						g.setColor(new Color(255, 255, 255, (int) fade));
					} else if (i == 1) {
						g.setColor(Color.white);
						g.drawString(keyTextString[i], 224, 268);
						g.setColor(new Color(255, 255, 255, (int) fade));
					} else if (i == 2) {
						g.setColor(Color.white);
						g.drawString(keyTextString[i], 224, 301);
						g.setColor(new Color(255, 255, 255, (int) fade));
					} else if (i == 3) {
						g.setColor(Color.white);
						g.drawString(keyTextString[i], 224, 334);
						g.setColor(new Color(255, 255, 255, (int) fade));
					} else if (i == 4 && amountOfPlayers == 2) {
						g.setColor(Color.white);
						g.drawString(keyTextString[i], 494, 235);
						g.setColor(new Color(255, 255, 255, (int) fade));
					} else if (i == 5 && amountOfPlayers == 2) {
						g.setColor(Color.white);
						g.drawString(keyTextString[i], 494, 268);
						g.setColor(new Color(255, 255, 255, (int) fade));
					} else if (i == 6 && amountOfPlayers == 2) {
						g.setColor(Color.white);
						g.drawString(keyTextString[i], 494, 301);
						g.setColor(new Color(255, 255, 255, (int) fade));
					} else if (i == 7 && amountOfPlayers == 2) {
						g.setColor(Color.white);
						g.drawString(keyTextString[i], 494, 334);
						g.setColor(new Color(255, 255, 255, (int) fade));
					}

				}

			}
			g.setColor(ColorUIResource.white);

			if (Difficulty == 1) {
				g.setColor(new Color(12, 201, 63));

				g.fillRect(275, 88, 85, 33);
				g.setColor(new Color(16, 16, 16));
				g.fillRect(360, 88, 130, 33);
				g.fillRect(490, 88, 80, 33);
				g.setColor(new Color(12, 201, 63));
				g.setFont(font);
				g.drawString("Easier for newer players, ", 350, 60);
				g.drawString("or those who just wanna have fun.", 320, 80);
				g.setFont(font2);
			} else if (Difficulty == 2) {
				g.setColor(new Color(16, 16, 16));

				g.fillRect(275, 88, 85, 33);
				g.setColor(new Color(191, 158, 11));
				g.fillRect(360, 88, 130, 33);
				g.setColor(new Color(16, 16, 16));
				g.fillRect(490, 88, 80, 33);
				g.setColor(new Color(191, 158, 11));
				g.setFont(font);
				g.drawString("Normal mode- everything is set  ", 323, 60);
				g.drawString("to default. Not too hard nor easy.", 320, 80);
				g.setFont(font2);
			} else if (Difficulty == 3) {
				g.setColor(new Color(16, 16, 16));

				g.fillRect(275, 88, 85, 33);
				g.fillRect(360, 88, 130, 33);
				g.setColor(new Color(191, 11, 11));
				g.fillRect(490, 88, 80, 33);
				g.setFont(font);
				g.drawString("For Pros Who know the game ", 335, 60);
				g.drawString("mechanins and master its aspects.", 320, 80);
				g.setFont(font2);
			}
			g.setColor(Color.white);
			g.setFont(font2);
			g.drawString("Player 1", 100, 198);

			g.drawString("Up: ", 100, 233);

			g.drawString("Down: ", 100, 266);

			g.drawString("Right: ", 100, 299);

			g.drawString("Left: ", 100, 332);

			g.drawString("Easy", 282, 115);
			g.drawString("Hard", 497, 115);

			g.setFont(font);
			g.drawString("Back", 28, 35);
			g.drawRect(15, 15, 60, 30);

			g.drawRect(220, 210, 130, 30);
			g.drawRect(220, 243, 130, 30);
			g.drawRect(220, 276, 130, 30);
			g.drawRect(220, 309, 130, 30);

			// Player2
			g.setFont(font2);
			if (amountOfPlayers == 2) {
				g.drawString("Player 2", 380, 198);

				g.drawString("Up: ", 380, 233);
				g.drawRect(490, 210, 130, 30);
				g.drawString("Down: ", 380, 266);
				g.drawRect(490, 243, 130, 30);
				g.drawString("Right: ", 380, 299);
				g.drawRect(490, 276, 130, 30);
				g.drawString("Left: ", 380, 332);
				g.drawRect(490, 309, 130, 30);
			}

			g.setColor(Color.white);
			g.setFont(font2);
			g.drawString("Difficulty ", 100, 113);
			g.drawString("Configure keybinds ", 100, 166);
			g.drawString("Amount of players ", 100, 392);
			g.drawString(Integer.toString(amountOfPlayers), 452, 393);
			g.drawRect(434, 367, 50, 29);
			g.fillRect(404, 367, 30, 30);
			g.fillRect(484, 367, 30, 30);
			g.setColor(new ColorUIResource(ColorUIResource.black));
			g.drawString("<", 412, 393);
			g.drawString(">", 492, 393);

			g.setColor(new ColorUIResource(ColorUIResource.white));
			g.drawString("Easy", 282, 115);
			g.drawString("Normal", 374, 115);
			g.drawString("Hard", 497, 115);
			g.drawLine(360, 90, 360, 120);
			g.drawLine(490, 90, 490, 120);

			g.drawLine(100, 356, Game.width - 50, 356);
			g.drawLine(370, 180, 370, 339);

			g.drawLine(100, 130, Game.width - 50, 130);
		} else if (page == 2) {
			g.setColor(ColorUIResource.white);
			FontUIResource fontUIResource = new FontUIResource("arial", 1, 38);
			g.setFont(fontUIResource);
			g.drawString("Player Names: ", 100, 117);
			g.setFont(font2);
			g.drawString("Player 1 Name: ", 100, 165);

			if (isPlayer1Namefull && waitingForName) {
				g.setColor(new Color(255, 0, 0, fade));
				g.drawString("Name is Full!", 100, 240);
			} else if (isPlayer1Namefull && !waitingForName) {
				g.setColor(new Color(255, 0, 0, fade));
				g.drawString("!", 92, 165);
			}
			if (amountOfPlayers == 2) {
				if (isPlayer2Namefull && waitingForSecondName) {
					g.setColor(new Color(255, 0, 0, fade));
					g.drawString("Name is Full!", 340, 240);
				} else if (isPlayer2Namefull && !waitingForSecondName) {
					g.setColor(new Color(255, 0, 0, fade));
					g.drawString("!", 332, 165);
				}
			}
			g.setColor(ColorUIResource.white);
			if (waitingForName) {
				g.setColor(new Color(255, 255, 255, fade));
				g.drawString(Player1name, 105, 200);
				g.drawRect(100, 170, 220, 40);
			} else {
				g.setColor(ColorUIResource.white);
				g.drawString(Player1name, 105, 200);
				g.drawRect(100, 170, 220, 40);
			}
			if (amountOfPlayers == 2) {
				g.setColor(ColorUIResource.white);
				g.drawString("Player 2 Name: ", 340, 165);
				g.drawLine(330, 140, 330, 210);
				if (waitingForSecondName) {
					g.setColor(new Color(255, 255, 255, fade));
					g.drawString(Player2name, 345, 200);
					g.drawRect(340, 170, 220, 40);
				} else {
					g.setColor(ColorUIResource.white);
					g.drawString(Player2name, 345, 200);
					g.drawRect(340, 170, 220, 40);
				}
			}
			g.setColor(ColorUIResource.white);
			g.drawLine(100, 130, Game.width - 50, 130);

		}

		g.setColor(ColorUIResource.white);
		g.setFont(font3);
		g.drawString("Settings", 100, 75);
		g.setFont(font);
		g.drawString("Back", 28, 35);

		g.drawRect(15, 15, 60, 30);

		g.drawRect(320, 6, 140, 25);
		g.drawRect(470, 6, 140, 25);

		// page
		g.setFont(font2);
		g.setColor(new Color(15, 200, 157));
		g.drawString("Page " + page + " out of " + 2, 100, 28);
		Font fontForPage = new Font("arial", 1, 20);
		g.setFont(fontForPage);
		g.setColor(new Color(150, 200, 15));
		g.drawString("<< Prev page", 325, 25);
		g.drawString("Next page >>", 480, 26);

		g.setColor(ColorUIResource.white);
		g.drawLine(90, 0, 90, Game.height);

		handler.render(g);

	}

	public void tick() {

		timer++;

		if (timer >= timerCount) {
			timer = 0;
			if (waitingAnimation.equals("Waiting."))
				waitingAnimation = "Waiting..";
			else if (waitingAnimation.equals("Waiting.."))
				waitingAnimation = "Waiting.";
		}

		fade += fademultipler;
		if (fade >= 250)
			fademultipler = -3;
		else if (fade <= 100)
			fademultipler = 3;

		for (int i = 0; i < keyTextString.length; i++) {
			if (keyTextString[i].equals("Waiting.") || keyTextString[i].equals("Waiting..")) {
				keyTextString[i] = waitingAnimation;
			}
		}
	}

}
