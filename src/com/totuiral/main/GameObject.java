package com.totuiral.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * An <code>abstract</code> class which <b>all</b> of the Game objects inherit.
 * <p> -For Example the {@linkplain Player} class or the {@linkplain BasicEnemy} class, etc.
 * <br> -Every GameObject instance has the following instance variables and methods:</br>
 * <br><br><b>Protected</b> Instance Variabels:
 * <ul>
 * <li> {@linkplain #x} - The X coordinantion of the object on the Game frame 
 * <li> {@linkplain #y} - The Y coordinantion of the object on the Game frame 
 * <li> {@linkplain #id} - (?Nullable) The <b>{@code ID}</b> of the current object which is often used
 * <br> to determine the object Type easily. Each class that inherits <b>{@code GameObject}</b>
 * <br>should have its own ID.
 * <li> {@linkplain #velX} - The X velocity of this object. It is usually used to increment the 
 * <br> X coordinate each time {@linkplain Game#tick()} calls.
 * <li> {@linkplain #velY} - The Y velocity of this object. It is usually used to increment the 
 * <br> Y coordinate each time {@linkplain Game#tick()} calls.
 * </ul>
 * <br> Every Game Object Has by Default <u><code><b>Public Abstract</b></code></u><code>getters</code> & <code>setters</code>
 * <br> for his instance variables. For example see {@linkplain #getID()} & {@linkplain #setID(ID)}
 * <br><br> Each Game Object also inherits the methods
 * <ul>
 * <br><b>{@link #tick()}</b>
 * <ul>which is used to update the technical attributes related to the object.
 * <br> this method is called hundrends of time a second by the {@linkplain #Handler} class.
 * </ul>
 * <b>{@link #render(Graphics)}</b>
 * <ul> this is used to render, show the object on the screen.
 * <br> And it is called by the {@linkplain #Handler} as many times as the 
 * <br>computer can handle a second allowing more performance for <Br>stornger PCs.
 * </ul>
 * <b> {@link #getBounds()}</b>
 * <ul> Returns a rectangle representing the bounds of an object,
 * <br> and it is typically used to determine if two or more object are touching each other. 
 * <br> For example Determine if an enemy hits you.
 * </ul>
 * </ul>
 * </p>
 * @author yisha
 *
 */
public abstract class GameObject {
	
	 protected float x, y;
	 protected ID id; 
	 protected float velX, velY;
	 /**
	  * Constructs a new game object give the following parametres.
	  * @param x The x coordinate
	  * @param y The x coordinate
	  * @param id ? The object's id 
	  */
	 public GameObject(float x, float y, ID id)
	 {
		 this.x=x; this.y=y; this.id=id;
	 }
	 /**
	  * ticks the game, same as {@link Game#tick}
	  */
	 public abstract void tick();
	 /**
	  * render the current obj to the screen - makes it visible in other words
	  * @param g The graphics that will be used to draw the obj.
	  */
	 public abstract void render(Graphics g);
	 /**
	  * returns a rectangle represnting the bounds of the object 
	  * @return nullable ?Rectangle of the bounds 
	  */
	 public abstract Rectangle getBounds();
	 public void setX(float x) {this.x=x;}
	 public void setY(float y) {this.y=y;}
	 public float getX() { return x;}
	 public float getY() { return y;}
	 public void setID(ID id) {this.id =id;}
	 public ID getID() {return id;}
	 public void setVelX(float velX) {this.velX=velX;}
	 public void setVelY(float velY) {this.velY=velY;}
	 public float getVelX() {return velX;}
	 public float getVelY() {return velY;}
}
	









