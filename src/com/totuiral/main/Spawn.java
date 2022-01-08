package com.totuiral.main;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private static int scoreKeep = 0;
	private Random random;
	private int scoreKeep_HPPacks = 0, scoreKeep_Coins = 0;
	private static double levelmultiplier = 100;
	private Game game;
	static int gainKeep_Coins;

	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		random = new Random();
	}

	public void tick() {
		scoreKeep++;
		scoreKeep_HPPacks++;
		scoreKeep_Coins++;

		if (scoreKeep_HPPacks >= random.nextInt(350) + 300) {
			scoreKeep_HPPacks = 0;
			handler.addObject(new HPpack(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50), hud,
					ID.HPpack, handler));
		}

		if (scoreKeep_Coins >= random.nextInt(50) + 100) {
			scoreKeep_Coins = 0;
			handler.addObject(
					new Coin(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50), ID.Coin, handler));
		}

		if (scoreKeep >= levelmultiplier) {
			scoreKeep = 0;

			levelmultiplier *= 1.35;
			hud.setLevel(hud.getLevel() + 1);

			if (hud.getLevel() == 2) {
				handler.addObject(new BasicEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.BasicEnemy, handler, game));
			} else if (hud.getLevel() == 3) {
				handler.addObject(new SlowEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.SlowEnemy, handler));
			} else if (hud.getLevel() == 4) {
				handler.addObject(new SmartEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.SmartEnemy, handler));
			} else if (hud.getLevel() == 5) {
				handler.addObject(new FastEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.FastEnemy, handler));
			} else if (hud.getLevel() % 7 == 0) {
				handler.clearEnemies();

				handler.addObject(new BossEnemy((Game.width / 2) - 48, -120, ID.BossEnemy, handler));
			} else if ((hud.getLevel() % 7) - 2 == 0) {

				handler.clearEnemies();
				handler.addObject(new BasicEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.BasicEnemy, handler, game));
				handler.addObject(new FastEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.FastEnemy, handler));
				handler.addObject(new BasicEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.BasicEnemy, handler, game));
				handler.addObject(new SlowEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.SlowEnemy, handler));
				handler.addObject(new SlowEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.SlowEnemy, handler));
				handler.addObject(new SmartEnemy(random.nextInt(Game.width - 50), random.nextInt(Game.height - 50),
						ID.SmartEnemy, handler));

			}
		}

	}

	public static void reset() {
		scoreKeep = 0;
		levelmultiplier = 100;
		gainKeep_Coins = 0;

	}

}
