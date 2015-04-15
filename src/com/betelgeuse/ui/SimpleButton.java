package com.betelgeuse.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SimpleButton {
	private float x, y, width, height;

	private TextureRegion button;
	private Rectangle bounds;
	private boolean isPressed = false;
	private boolean wasPressed = false;

	public SimpleButton(float x, float y, float width, float height,
			TextureRegion button) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.button = button;
		bounds = new Rectangle(x, y, width, height);

	}

	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}

	public void draw(SpriteBatch batcher) {
		batcher.draw(button, x, y, width, height);
	}

	public boolean isTouchDown(int screenX, int screenY) {

		if (bounds.contains(screenX, screenY)) {
			isPressed = true;
			y += 5;
			wasPressed = true;
			return true;
		}

		return false;
	}

	public boolean isTouchUp(int screenX, int screenY) {

		// It only counts as a touchUp if the button is in a pressed state.
		if (bounds.contains(screenX, screenY) && isPressed) {
			isPressed = false;
			y -= 5;
			return true;
		}

		// Whenever a finger is released, we will cancel any presses.
		if (isPressed) {
			y -= 5;
			isPressed = false;

		}
		wasPressed = false;
		return false;
	}

	public void isTouchDragged(int screenX, int screenY) {
		if (wasPressed) {
			if (!bounds.contains(screenX, screenY) && isPressed) {
				isPressed = false;
				y -= 5;
			} else if (bounds.contains(screenX, screenY) && !isPressed) {
				isPressed = true;
				y += 5;
			}
		}
	}

}
