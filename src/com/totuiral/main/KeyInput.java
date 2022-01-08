package com.totuiral.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Stack;
import java.awt.*;
import java.net.*;

import com.totuiral.main.Game.State;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	private Game game;
	private boolean[] keyDown = new boolean[8];
	
	public KeyInput(Handler handler, Settings settings, Game game) {
		this.handler = handler;
		this.game=game;
		for (int i=0; i<keyDown.length; i++)
		{
			keyDown[i]=false; 
		}
		
	}
	Stack<Character> stack = new Stack<Character>();
	public void  keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		char letterString = e.getKeyChar();
		//String string = KeyEvent.getKeyText(key);
		//System.out.format("code: %d char: %s string: %s\n",key,String.valueOf(letterString), string);
		if (game.gameState== State.Game && !Game.paused) { 
			String[] cheats = {"/x2coins", "/reghealth","/sus","/quit","/givemoney"};
			String createCheat = createCheat(cheats, stack, letterString);
			HUD.renderCheatCode(getStringStack(stack)); 
			switch (createCheat) {
				case "false": break;
				case "/x2coins": Handler.numCoins*=2; break;
				case "/reghealth" : HUD.resetHealth(); break;
				case "/quit": System.exit(1); break;
				case "/givemoney": Handler.setNumCoins(950000); break;
				case "/sus" : Desktop d = Desktop.getDesktop();
				try {
					//d.browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley"));
					d.browse(new URI("https://he.wikipedia.org/wiki/%D7%A2%D7%9E%D7%95%D7%93_%D7%A8%D7%90%D7%A9%D7%99"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  break;
				default: throw new IllegalArgumentException("\""+createCheat +"\" is not a recognizable output ( /<CheatCode> or \"false\").");
			}
			
			if (letterString=='r' && Shop.HealthRegPotions>0) {
				HUD.resetHealth();
				Shop.HealthRegPotions--;
			}
			if (letterString == 'f' && Shop.Shields>0) {
				if (!Player.shieldActive)
				{
					Player.startShield();
					Shop.Shields--;
				}
			}
			for (int i=0; i<handler.object.size(); i++)
			{
				GameObject tempObject = handler.object.get(i);
				if (tempObject.id==ID.player)
				{
					Player player = (Player) tempObject;
					if (key==Settings.KeyCodes[0]) {tempObject.setVelY( (float) ( - 5 + ( Shop.SpeedBoost / 100.0 ) *-5) ); keyDown[0]=true;}
					if (key==Settings.KeyCodes[1]) {tempObject.setVelY((float) (5+ ( Shop.SpeedBoost / 100.0 ) *5)); keyDown[1]=true;}
					if (key==Settings.KeyCodes[2]) 
					{
						tempObject.setVelX(-5+ ( Shop.SpeedBoost / 100.0f ) *-5);
						keyDown[2]=true;
						if (keyDown[2] && !keyDown[3])
							player.playerImage = game.flipImage(player.playerImage, 1);
					}
					if (key==Settings.KeyCodes[3])
					{
						tempObject.setVelX(5+ ( Shop.SpeedBoost / 100.0f ) *5);
						keyDown[3]=true;
						if (keyDown[3] && !keyDown[2])
							player.playerImage = game.flipImage(player.playerImage, 1);
					}
					
				}
				if (tempObject.id==ID.Player2)
				{ 
					Player player = (Player) tempObject;
					if (key==Settings.KeyCodes[4])  {tempObject.setVelY(-5+ ( Shop.SpeedBoost / 100f ) *-5);   keyDown[4]=true;}
					if (key==Settings.KeyCodes[5])  {tempObject.setVelY(5+ ( Shop.SpeedBoost / 100f ) *5); keyDown[5]=true;}
					if (key==Settings.KeyCodes[6])  {tempObject.setVelX(5+ ( Shop.SpeedBoost / 100f ) *5); keyDown[6]=true; player.player2Image = game.flipImage(player.player2Image, 1);}
					if (key==Settings.KeyCodes[7])  {tempObject.setVelX(-5+ ( Shop.SpeedBoost / 100f ) *-5); keyDown[7]=true; player.player2Image = game.flipImage(player.player2Image, 1);}
					
				}
			}
		}
		//else if (game.gameState==State.Settings && settings.waitingForName) {
			
		//}
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		if (letterString=='p' && game.gameState==State.Game ) {
			if (Game.paused) {
				Game.paused=false;
				//synchronized (handler){try {handler.notify();} catch (Exception es){es.printStackTrace();}}
			}
			else if (!Game.paused) {
				
				Game.paused=true;
				//synchronized (handler){try {handler.wait(1000);} catch (Exception es){es.printStackTrace();}}
			}
			
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i=0; i<handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if (tempObject.id==ID.player)
			{
				if (key==Settings.KeyCodes[0])  keyDown[0]=false;
				if (key==Settings.KeyCodes[1])  keyDown[1]=false;
				if (key==Settings.KeyCodes[2])  keyDown[2]=false; 
				if (key==Settings.KeyCodes[3])  keyDown[3]=false;
				
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0); 
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0); 
			}
			if (tempObject.id==ID.Player2)
			{ 
				if (key==Settings.KeyCodes[4])  keyDown[4]=false;
				if (key==Settings.KeyCodes[5]) keyDown[5]=false;
				if (key==Settings.KeyCodes[6])  keyDown[6]=false;
				if (key==Settings.KeyCodes[7])  keyDown[7]=false;
				
				if(!keyDown[4] && !keyDown[5]) tempObject.setVelY(0); 
				if(!keyDown[6] && !keyDown[7]) tempObject.setVelX(0); 
			}
			
			
		}
	}

	public String createCheat(String[] cheatCodes, Stack<Character> stack, char letter)
	{
		stack.push(letter); String currCheat = getStringStack(stack);
		for (String cheatCode : cheatCodes) {
			if (currCheat.equalsIgnoreCase(cheatCode)){
				stack.clear();
				return cheatCode;
			}else if (cheatCode.toLowerCase().startsWith(currCheat.toLowerCase())){
				return "false";
			}
		}
		stack.clear();
		return "false";
	}
	private String getStringStack(Stack<Character> stack2) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < stack2.size(); i++) {
			s.append(String.valueOf(stack2.get(i)));
			
		}
		return s.toString().toLowerCase();
	}

}










