package com.betelgeuse.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.betelgeuse.GameHelpers.Scrollable;

public class Bar extends Scrollable {

	private Random r;
	private Rectangle barBoundUp, barBoundDown;
	private Circle barRoundBoundUp1, barRoundBoundUp2, barRoundBoundDown1,
			barRoundBoundDown2;
	
	private boolean isScored = false;

	// When Pipe's constructor is invoked, invoke the super (Scrollable)
	// constructor
	public Bar(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		// Initialize a Random object for Random number generation
		r = new Random();
		barBoundUp = new Rectangle();
		barBoundDown = new Rectangle();
		barRoundBoundUp1 = new Circle();
		barRoundBoundUp2 = new Circle();
		barRoundBoundDown1 = new Circle();
		barRoundBoundDown2 = new Circle();
	}

	@Override
	public void reset(float newX) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newX);
		// Change the height to a random number
		position.y = -r.nextInt(220) - 50;
		isScored=false;
	}
	public void onRestart(float x, float scrollSpeed) {
        velocity.x = scrollSpeed;
        reset(x);
    }
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		super.update(delta);
		barBoundUp.set(position.x + 10, position.y, 34, height);
		barBoundDown
				.set(position.x + 10, position.y + 100 + height, 34, height);
		barRoundBoundUp1
				.set(position.x + 10, position.y + height - 23 + 10, 10);
		barRoundBoundUp2.set(position.x + 34 + 10, position.y + height - 23
				+ 10, 10);
		barRoundBoundDown1.set(position.x + 10, position.y + height + 100 + 10,
				10);
		barRoundBoundDown2.set(position.x + 34 + 10, position.y + height + 100
				+ 10, 10);

	}

	public boolean collides(Cat nyanCat) {
		if (position.x < nyanCat.getX() + nyanCat.getWidth()) {
			return Intersector.overlaps(nyanCat.getCatBodyBound(), barBoundUp)
					|| Intersector.overlaps(nyanCat.getCatBodyBound(),
							barBoundDown)
					|| Intersector.overlaps(barRoundBoundUp1,
							nyanCat.getCatBodyBound())
					|| Intersector.overlaps(barRoundBoundUp2,
							nyanCat.getCatBodyBound())
					|| Intersector.overlaps(barRoundBoundDown1,
							nyanCat.getCatBodyBound())
					|| Intersector.overlaps(barRoundBoundDown2,
							nyanCat.getCatBodyBound());

		}
		return false;
	}

	/**
	 * @return the barBoundUp
	 */
	public Rectangle getBarBoundUp() {
		return barBoundUp;
	}

	/**
	 * @return the barBoundDown
	 */
	public Rectangle getBarBoundDown() {
		return barBoundDown;
	}

	/**
	 * @return the barRoundBoundUp1
	 */
	public Circle getBarRoundBoundUp1() {
		return barRoundBoundUp1;
	}

	/**
	 * @return the barRoundBoundUp2
	 */
	public Circle getBarRoundBoundUp2() {
		return barRoundBoundUp2;
	}

	/**
	 * @return the barRoundBoundDown1
	 */
	public Circle getBarRoundBoundDown1() {
		return barRoundBoundDown1;
	}

	/**
	 * @return the barRoundBoundDown2
	 */
	public Circle getBarRoundBoundDown2() {
		return barRoundBoundDown2;
	}

	/**
	 * @return the isScored
	 */
	public boolean isScored() {
		return isScored;
	}

	/**
	 * @param isScored the isScored to set
	 */
	public void setScored(boolean isScored) {
		this.isScored = isScored;
	}
}
