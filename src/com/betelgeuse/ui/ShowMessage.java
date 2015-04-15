package com.betelgeuse.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ShowMessage {

	protected float x, y, width, height;
	protected float originX, originY, originWidth, originHeight;
	protected TextureRegion message;
	private boolean destination;
	protected float i;

	public ShowMessage(float x, float y, float width, float height,
			TextureRegion message) {
		this.originX = this.x = x;
		this.originY = this.y = y;
		this.originHeight = this.height = height;
		this.originWidth = this.width = width;
		this.message = message;
		setDestination(false);
	}

	public void draw(SpriteBatch batcher) {
		batcher.draw(message, x, y, width, height);
	}

	public void reset() {
		x = originX;
		y = originY;
		height = originHeight;
		width = originWidth;
		destination = false;
		i = 0;
	}

	/**
	 * @return the destination
	 */
	public boolean isDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(boolean destination) {
		this.destination = destination;
	}

}
