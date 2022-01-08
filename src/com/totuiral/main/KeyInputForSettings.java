package com.totuiral.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class KeyInputForSettings extends KeyAdapter {
	public KeyInputForSettings(Settings settings) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String keyString = KeyEvent.getKeyText(keyCode);
		char keyChar = e.getKeyChar();

		if (Settings.page == 1) {
			for (int i = 0; i < Settings.isWaitingForInput.length; i++) {
				if (Settings.isWaitingForInput[i] == true && i >= 0 && i <= 3) {
					if (!checkForError(keyString.toUpperCase())) {
						if (Menu.SoundOn) {
							AudioPlayer.getSound("Done").play();
						}
						Settings.isWaitingForInput[i] = false;
						Settings.KeyCodes[i] = keyCode;
						Settings.keyTextString[i] = keyString;
						Settings.free = false;
					}
				}
				else if (Settings.isWaitingForInput[i] && Settings.amountOfPlayers == 2 && i >= 4 && i <= 7) {
					if (!checkForError(keyString.toUpperCase())) {
						if (Menu.SoundOn) {
							AudioPlayer.getSound("Done").play();
						}
						Settings.isWaitingForInput[i] = false;
						Settings.KeyCodes[i] = keyCode;
						Settings.keyTextString[i] = keyString;
						Settings.free = false;
					}
				}
			}
		} else if (Settings.page == 2) {
			if (keyString.equals("Space"))
				keyString = "_";
			if ((Settings.waitingForName || Settings.waitingForSecondName) && !keyString.equalsIgnoreCase("backspace")
					&& (Settings.Player1name.length() < 12 || Settings.Player2name.length() < 12)
					&& checkForInputValidity(keyString)) {
				if (Settings.waitingForName && !Settings.isPlayer1Namefull) {
					if (!keyString.equals("_"))
						Settings.Player1name += Character.toString(keyChar);
					else if (keyString.equals("_"))
						Settings.Player1name += keyString;
				} else if (Settings.waitingForSecondName && !Settings.isPlayer2Namefull) {
					if (!keyString.equals("_"))
						Settings.Player2name += Character.toString(keyChar);
					else if (keyString.equals("_"))
						Settings.Player2name += keyString;
				}
			} else if ((Settings.waitingForName || Settings.waitingForSecondName)
					&& keyString.equalsIgnoreCase("backspace")) {
				if (Settings.Player1name.length() > 0 && Settings.waitingForName) {
					Settings.Player1name = Settings.Player1name.substring(0, Settings.Player1name.length() - 1);
				} else if (Settings.Player2name.length() > 0 && Settings.waitingForSecondName) {
					Settings.Player2name = Settings.Player2name.substring(0, Settings.Player2name.length() - 1);
				}
			}
			//
			if (Settings.Player1name.length() >= 12) {
				Settings.isPlayer1Namefull = true;
			} else
				Settings.isPlayer1Namefull = false;
			if (Settings.Player2name.length() >= 12) {
				Settings.isPlayer2Namefull = true;
			} else
				Settings.isPlayer2Namefull = false;
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	String[] validities = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "l", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z", "_", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };

	public boolean checkForInputValidity(String keyString) {
		for (int i = 0; i < validities.length; i++) {
			if (keyString.equalsIgnoreCase(validities[i]))
				return true;
		}
		return false;
	}

	public boolean checkForError(String letter) {
		String usage = ".";
		switch (letter) {
		case "R": {
			usage = "refilling your health.";
			break;
		}
		case "F": {
			usage = "activating a shield.";
			break;
		}
		case "P": {
			usage = "pausing the game.";
			break;
		}
		default:
			return false;
		}
		JOptionPane.showMessageDialog(Window.frame,
				String.format("%s is an Invalid character => (Used for %s)"
						+ "\nPlease choose a valid charcter\nInvalid Charcters are : (F, R, P)", letter, usage),
				"Invalid Input", JOptionPane.YES_NO_CANCEL_OPTION);
		return true;
	}

}
