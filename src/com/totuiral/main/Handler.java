package com.totuiral.main;
import java.awt.*;
import java.util.ArrayList;

import com.totuiral.main.Game.State;
public class Handler {
	private int amountToSubtract=0;
	private int actualFPS=0;
	public static int numCoins=0;
	boolean shouldPrintLists=false;
	ArrayList<GameObject> object = new ArrayList<GameObject>();
    private float subtructionspeed=1;
	private Game game;
	public Handler(Game game) {this.game=game;}
	/**
	 * Renders all of the game objects visible and is called by {@linkplain Game#tick()} <br>
	 * using the {@link Graphics}
	 * class and calling each GameObject's in {@linkplain #object} <br>(ArrayList containing all GameObjects)  
	 * {@linkplain GameObject#tick()}
	 * @see Game#tick 
	 * @see GameObject
	 */
	public void tick() {
		//checks whether the game is focused or running in the bg(BackGround). case true: pauses the game if active
		if (!Window.frame.isFocused() && game.gameState == State.Game) Game.paused=true;

		for (int i=0; i<object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.tick();	
		}
		for (int i =0; i<(int)subtructionspeed; i++) {
			if (amountToSubtract>0  && numCoins>0) {
				numCoins--;
				amountToSubtract--;
			}
		}
		if (amountToSubtract>0  && numCoins>0) subtructionspeed+=(numCoins-amountToSubtract)/100;
		else subtructionspeed=1;
	}
	public void render(Graphics g)
	{
		if (object.size()>0)
		{
			ArrayList<GameObject> nonTrailObjects = new ArrayList<GameObject>();
			for (int i=0; i<object.size(); i++)
			{
				
				if (object.get(i)!=null && object.get(i).getID()!=null && (object.get(i).getID()==ID.Coin ||  object.get(i).getID()==ID.CoinInstance)) {
					GameObject tempObject = object.get(i);
					tempObject.render(g);
				}
				
			}
			
			for (int i=0; i<object.size(); i++)
			{
				
				GameObject tempObject = (GameObject) object.get(i);
				if (tempObject!=null) {
					if (tempObject.getID() == ID.Trail || tempObject.getID() == ID.PlayerTrail)
						tempObject.render(g);
					else if (tempObject.getID()!=ID.Coin) nonTrailObjects.add(tempObject);
				}
				
			}
			for (int i=0; i<nonTrailObjects.size(); i++)
			{
				
				if (nonTrailObjects.get(i)!=null )
				{
					GameObject tempObject = nonTrailObjects.get(i);
					tempObject.render(g);
				}
				
			}
			
		}
		
	}
	public void addObject(GameObject obj)
	{
		this.object.add(obj);
	}
	
	

	
	
	public void removeObject(GameObject obj)
	{
		this.object.remove(obj);
	}
	public void clearEnemies()
	{
		for (int i=0; i<object.size(); i++) {
			//GameObject object = handler.object.get(i);
			if (object.get(i).getID()!=ID.player && object.get(i).getID()!=ID.Player2 && object.get(i).getID()!=ID.CoinInstance)
			{
				removeObject(object.get(i));
				i--;
			}
		}
	}
	
	public boolean isIn(ID id) {
		for (int j=0; j<object.size(); j++) {
			GameObject tempobj = object.get(j);
			if (tempobj.getID()==id) {
				return true;
			}
		}
		return false;
	}
	public void setVelTo0() {
		for (int i = 0; i<object.size(); i++) {
			object.get(i).setVelX(0);
			object.get(i).setVelY(0);
			
		}
		
	}
	public void setVelTo0withID(ID id) {
		for (int i = 0; i<object.size(); i++) {
			if (object.get(i).getID()==id)
			{
			object.get(i).setVelX(0);
			object.get(i).setVelY(0);
			}
			
		}
		
	}
	public void printFrames() {
		// TODO Auto-generated method stub
		actualFPS = Game.frames;
	}
	public void clearEnemiesAndPlayer() {
		for (int i=object.size()-1; i>=0; i--) {
     		if (object.get(i).getID()!=ID.CoinInstance )
				removeObject(object.get(i));
		}		
	}
	public int getActualFPS() {
		return actualFPS;
	}
	public void setActualFPS(int actualFPS) {
		this.actualFPS = actualFPS;
	}
	public static int getNumCoins() {
		return numCoins;
	}
	public static void setNumCoins(int numCoins) {
		Handler.numCoins = numCoins;
	}
	public void susbstructCoins(int i) {
		if (numCoins-i-amountToSubtract>=0) {
			amountToSubtract+=i;
			subtructionspeed=1;
		}
	}
	public boolean canSubtruct(int i) {
		if (numCoins-i-amountToSubtract>=0) {
			return true;
		}
		return false;
	}
	public int getAmountToSubtruct() {
		return amountToSubtract;
	}
}




























