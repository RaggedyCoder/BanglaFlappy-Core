package com.betelgeuse.GameObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.betelgeuse.GameHelpers.AssetLoader;

public class Cat {
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;

	private float rotation; // For handling bird rotation
	private int width;
	private int height;
	private Circle catBodyBound;
	private boolean isAlive;
	protected int angle;
	protected float originalY;
	protected Sound wing;

	public Cat(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		this.originalY = y;
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 500 * 2);
		catBodyBound = new Circle();
		setAlive(true);
		wing = AssetLoader.wing;
	}

	public void update(float delta) {

		velocity.add(acceleration.cpy().scl(delta));

		if (velocity.y > 250 * 2) {
			velocity.y = 250 * 2;
		}
		if (position.y < -height * 2) {
			velocity.y = 250;
		}
		if (velocity.y < 0) {
			rotation -= 250 * 2 * delta;

			if (rotation < -20) {
				rotation = -20;
			}
		}

		if (isFalling() || !isAlive()) {
			rotation += 500 * delta;
			if (rotation > 90) {
				rotation = 90;
			}

		}
		position.add(velocity.cpy().scl(delta));
		if (rotation == -20) {
			catBodyBound.set(position.x + 25, position.y + 16, 16);
		}
		if (rotation == 90) {
			catBodyBound.set(position.x + 20, position.y + 22, 16);
		} else
			catBodyBound.set(position.x + 27, position.y + 16, 16);
	}

	public void updateReady(float runTime) {
		position.y = 10 * (float) Math.sin(5 * runTime) + originalY;
	}

	public boolean isFalling() {
		return velocity.y > 130 * 2;
	}

	public boolean shouldntFlap() {
		return velocity.y > 65 * 2 || !isAlive();
	}

	public void onClick() {
		if (isAlive()) {
			wing.play();
			velocity.y = -145 * 2;
		}
	}

	public void die() {
		setAlive(false);
	}

	public void decelerate() {
		velocity.y = 0;
		acceleration.y = 0;
	}

	public void onRestart(int x, int y) {
		rotation = 0;
		position.x = x;
		position.y = y;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = 500 * 2;
		isAlive = true;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getRotation() {
		return rotation;
	}

	/**
	 * @return the catBodyBound
	 */
	public Circle getCatBodyBound() {
		return catBodyBound;
	}

	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @param isAlive
	 *            the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void updateMenu(float runTime) {
		// TODO Auto-generated method stub
		position.y = 10 * (float) Math.sin(5 * runTime) + originalY;
	}
}
