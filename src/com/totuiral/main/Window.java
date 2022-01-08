package com.totuiral.main;

import java.awt.Canvas;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;

public class Window extends Canvas {
	static JFrame frame;
	private static final long serialVersionUID = -7726295299007848788L;

	public Window(int w, int h, String title, Game game) {
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(w, h));
		frame.setMaximumSize(new Dimension(w, h));
		frame.setMinimumSize(new Dimension(w, h));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JButton button = new JButton("submit");
		button.setPreferredSize(new DimensionUIResource(100, 100));
		button.setVisible(true);

		frame.add(button);

		frame.add(game);

		frame.setVisible(true);

//		game.start();
	}
}
