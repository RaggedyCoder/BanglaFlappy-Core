package com.betelgeuse.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOverMessage extends ShowMessage {

	protected int changeY = -3;

	public GameOverMessage(float x, float y, float width, float height,
			TextureRegion message) {
		super(x, y, width, height, message);
		i = 0;
	}

	public void update(float delta) {
		if (i < 5) {
			y += changeY;
		}
		i++;
		if (i == 2) {
			changeY = 2;
		} else if (i == 5) {
			setDestination(true);
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		super.reset();
		changeY = -3;
	}

	public void setX(float x) {
		this.x = x;
	}
}
