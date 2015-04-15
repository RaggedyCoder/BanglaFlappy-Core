package com.betelgeuse.GameHelpers;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.betelgeuse.GameObjects.Cat;
import com.betelgeuse.GameWorld.GameWorldRenderer;
import com.betelgeuse.GameWorld.GameWorldUpdate;
import com.betelgeuse.Screens.GameScreen;
import com.betelgeuse.ui.SimpleButton;

public class InputHandler implements InputProcessor {

	protected Cat nyanCat;
	protected GameWorldUpdate myWorld;
	private ArrayList<SimpleButton> menuButtons;
	private SimpleButton playButton;
	private SimpleButton statButton;
	private float scaleFactorX = GameScreen.gameWidth / 288.0f;
	private float scaleFactorY = GameScreen.gameHeight / 512.0f;
	protected Sound whoop;

	public InputHandler(GameWorldUpdate myWorld) {
		this.myWorld = myWorld;
		nyanCat = myWorld.getNyanCat();
		menuButtons = new ArrayList<SimpleButton>();
		playButton = new SimpleButton(65, 350,
				AssetLoader.buttonPlay.getRegionWidth(),
				AssetLoader.buttonPlay.getRegionHeight(),
				AssetLoader.buttonPlay);
		statButton = new SimpleButton(165, 350,
				AssetLoader.buttonStat.getRegionWidth(),
				AssetLoader.buttonStat.getRegionHeight(),
				AssetLoader.buttonStat);
		menuButtons.add(playButton);
		menuButtons.add(statButton);
		whoop = AssetLoader.whoop;
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Keys.BACK) {
			// Do your optional back button handling (show pause menu?)
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		if (myWorld.isMenu()) {
			playButton.isTouchDown(screenX, screenY);
			statButton.isTouchDown(screenX, screenY);
		} else if (myWorld.isReady()
				&& GameWorldRenderer.getAlpha().getValue() == 0) {
			myWorld.start();
		}
		if (myWorld.isPlaying()) {
			nyanCat.onClick();
		}

		if (myWorld.isGameOver()) {
			// Reset all variables, go to GameState.READY
			playButton.isTouchDown(screenX, screenY);
			statButton.isTouchDown(screenX, screenY);
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		if (myWorld.isMenu()) {
			if (playButton.isTouchUp(screenX, screenY)) {
				myWorld.setToReady(true);
				whoop.play();
				myWorld.ready();
				return true;
			}
			if (statButton.isTouchUp(screenX, screenY)) {
				whoop.play();
				if (!myWorld.getLocalScoreBoard().isOn()
						&& myWorld.getLocalScoreBoard().isDestination()) {
					myWorld.getLocalScoreBoard().setDestination(false);
					myWorld.getLocalScoreBoard().setOn(true);
					myWorld.getLocalScoreBoard().setOff(false);
					myWorld.getLocalScoreBoard().scoreUpdate();
				} else if (!myWorld.getLocalScoreBoard().isOff()
						&& myWorld.getLocalScoreBoard().isDestination()) {
					myWorld.getLocalScoreBoard().setDestination(false);
					myWorld.getLocalScoreBoard().setOff(true);
					myWorld.getLocalScoreBoard().setOn(false);
				}

			}
		} else if (myWorld.isGameOver()) {
			if (playButton.isTouchUp(screenX, screenY)) {
				whoop.play();
				myWorld.setToReady(true);
				myWorld.ready();
				return true;
			}
			if (statButton.isTouchUp(screenX, screenY)) {
				whoop.play();
				if (!myWorld.getLocalScoreBoard().isOn()
						&& myWorld.getLocalScoreBoard().isDestination()) {
					myWorld.getLocalScoreBoard().setDestination(false);
					myWorld.getLocalScoreBoard().setOn(true);
					myWorld.getLocalScoreBoard().setOff(false);
					myWorld.getLocalScoreBoard().scoreUpdate();
				} else if (!myWorld.getLocalScoreBoard().isOff()
						&& myWorld.getLocalScoreBoard().isDestination()) {
					myWorld.getLocalScoreBoard().setDestination(false);
					myWorld.getLocalScoreBoard().setOff(true);
					myWorld.getLocalScoreBoard().setOn(false);
				}
			}

		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		if (myWorld.isMenu() || myWorld.isGameOver()) {
			playButton.isTouchDragged(screenX, screenY);
			statButton.isTouchDragged(screenX, screenY);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	/**
	 * @return the menuButtons
	 */
	public ArrayList<SimpleButton> getMenuButtons() {
		return menuButtons;
	}

	private int scaleX(int screenX) {
		scaleFactorX = GameScreen.gameWidth / 288.0f;
		System.out.println(scaleFactorX);
		return (int) (screenX * 1.0 / scaleFactorX * 1.0);
	}

	private int scaleY(int screenY) {
		scaleFactorY = GameScreen.gameHeight / 512.0f;
		System.out.println(scaleFactorY);
		return (int) (screenY * 1.0 / scaleFactorY * 1.0);
	}
}
