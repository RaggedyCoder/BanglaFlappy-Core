package com.betelgeuse.GameHelpers;

import java.util.Random;

import com.betelgeuse.GameObjects.Bar;
import com.betelgeuse.GameObjects.Cat;
import com.betelgeuse.GameObjects.Grass;
import com.betelgeuse.GameWorld.GameWorldUpdate;

public class ScrollHandler {

	// ScrollHandler will create all five objects that we need.
	private Grass frontGrass, backGrass;
	private Bar bar1, bar2, bar3;

	// ScrollHandler will use the constants below to determine
	// how fast we need to scroll and also determine
	// the size of the gap between each pair of bars.

	public static final int SCROLL_SPEED = -70 * 2;
	public static final int PIPE_GAP = 100;
	protected GameWorldUpdate gameWorldUpdate;

	// Constructor receives a float that tells us where we need to create our
	// Grass and Bar objects.
	public ScrollHandler(GameWorldUpdate gameWorldUpdate, float yPos) {
		this.gameWorldUpdate = gameWorldUpdate;
		frontGrass = new Grass(0, yPos, 336, 112, SCROLL_SPEED);
		backGrass = new Grass(frontGrass.getTailX(), yPos, 336, 112,
				SCROLL_SPEED);

		bar1 = new Bar(512, -new Random().nextInt(220) - 50, 54, 320,
				SCROLL_SPEED);
		bar2 = new Bar(bar1.getTailX() + PIPE_GAP,
				-new Random().nextInt(220) - 50, 54, 320, SCROLL_SPEED);
		bar3 = new Bar(bar2.getTailX() + PIPE_GAP,
				-new Random().nextInt(220) - 50, 54, 320, SCROLL_SPEED);
	}

	public void update(float delta) {

		frontGrass.update(delta);
		backGrass.update(delta);
		bar1.update(delta);
		bar2.update(delta);
		bar3.update(delta);
		// Check if any of the bars are scrolled left,
		// and reset accordingly
		if (bar1.isScrolledLeft()) {
			bar1.reset(bar3.getTailX() + PIPE_GAP);
		} else if (bar2.isScrolledLeft()) {
			bar2.reset(bar1.getTailX() + PIPE_GAP);

		} else if (bar3.isScrolledLeft()) {
			bar3.reset(bar2.getTailX() + PIPE_GAP);
		}

		// Same with grass
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());
		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());
		}
	}

	public void stop() {
		frontGrass.stop();
		backGrass.stop();
		bar1.stop();
		bar2.stop();
		bar3.stop();

	}

	public boolean collides(Cat nyanCat) {
		if (!bar1.isScored()
				&& bar1.getX() + (bar1.getWidth() / 2) < nyanCat.getX()
						+ nyanCat.getWidth()) {
			addScore(1);
			bar1.setScored(true);
		} else if (!bar2.isScored()
				&& bar2.getX() + (bar2.getWidth() / 2) < nyanCat.getX()
						+ nyanCat.getWidth()) {
			addScore(1);
			bar2.setScored(true);

		} else if (!bar3.isScored()
				&& bar3.getX() + (bar3.getWidth() / 2) < nyanCat.getX()
						+ nyanCat.getWidth()) {
			addScore(1);
			bar3.setScored(true);
		}
		return bar1.collides(nyanCat) || bar2.collides(nyanCat)
				|| bar3.collides(nyanCat);

	}

	private void addScore(int increment) {
		// TODO Auto-generated method stub
		gameWorldUpdate.addScore(increment);

	}

	// The getters for our five instance variables
	public Grass getFrontGrass() {
		return frontGrass;
	}

	public Grass getBackGrass() {
		return backGrass;
	}

	public Bar getBar1() {
		return bar1;
	}

	public Bar getBar2() {
		return bar2;
	}

	public Bar getBar3() {
		return bar3;
	}

	public void onRestart() {
		// TODO Auto-generated method stub
		frontGrass.onRestart(0, SCROLL_SPEED);
		backGrass.onRestart(frontGrass.getTailX(), SCROLL_SPEED);
		bar1.onRestart(356, SCROLL_SPEED);
		bar2.onRestart(bar1.getTailX() + PIPE_GAP, SCROLL_SPEED);
		bar3.onRestart(bar2.getTailX() + PIPE_GAP, SCROLL_SPEED);
	}

	public void updateReady(float delta) {
		frontGrass.update(delta);
		backGrass.update(delta);
		
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());
		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());
		}
	}

}
