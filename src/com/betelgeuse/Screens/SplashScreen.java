package com.betelgeuse.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.betelgeuse.BanglaFlappy.BFGame;
import com.betelgeuse.GameHelpers.AssetLoader;
import com.betelgeuse.TweenAccessors.SpriteAccessor;

/**
 * Splash Screen class is used for calculating the tween property of the splash
 * screen and also drawing it on the game screen.
 */
public class SplashScreen implements Screen, InputProcessor {

	/*
	 * A TweenManager to update the tween and timeline of the splash screen at
	 * once.
	 */
	private TweenManager manager;
	/* A SpriteBatch to draw the splash screen. */
	private SpriteBatch batcher;
	/* A Sprite to hold the image of the splash screen. */
	private Sprite sprite;
	private BFGame game;

	/**
	 * Splash Screen constractor
	 * 
	 * @param game
	 *            gets the main game object.
	 * */
	public SplashScreen(BFGame game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);

	}

	/**
	 * Called when the screen should render itself.
	 * 
	 * @param delta
	 *            The time in seconds since the last render.
	 */
	@Override
	public void render(float delta) {
		manager.update(delta);
		System.out.println(1/delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		sprite.draw(batcher);
		batcher.end();

	}

	/**
	 * Called when the Application is resized. This can happen at any point
	 * during a non-paused state but will never happen before a call to
	 * create().
	 * 
	 * @param width
	 *            the new width in pixels
	 * @param height
	 *            the new height in pixels
	 */

	@Override
	public void resize(int width, int height) {

	}

	/**
	 * Called when this screen becomes the current screen for a subclass of
	 * Game.
	 */
	@Override
	public void show() {
		/* setting the width in pixels of the display surface */
		float width = Gdx.graphics.getWidth();

		/* setting the height in pixels of the display surface */
		float height = Gdx.graphics.getHeight();

		/* setting the Sprite image */
		sprite = new Sprite(AssetLoader.splashSceen);

		/* setting the Sprite image color */
		sprite.setColor(0, 0, 0, 0);
		/* setting the Sprite image position at 0,0 co-ordinate */
		sprite.setPosition(0, 0);

		/*
		 * setting the Sprite image size according to the display surface height
		 * and widht
		 */
		sprite.setSize(width, height);
		setupTween();

		/** setting the SpriteBatch for drawing on the display */
		batcher = new SpriteBatch();

	}

	/**
	 * sets up the tween accessor.Which will monitor the SplashScreen of the
	 * Game.
	 * */
	private void setupTween() {
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		manager = new TweenManager();

		/**
		 * TweenCallbacks are used to trigger actions at some specific times.
		 * They are used in both Tweens and Timelines. The moment when the
		 * callback is triggered depends on its registered triggers:
		 */
		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(new GameScreen());

			}
		};
		/**
		 * Factory creating a new standard interpolation. This is the most
		 * common type of interpolation. The starting values are retrieved
		 * automatically after the delay (if any).
		 */
		/*
		 * Tween.to(sprite, SpriteAccessor.POSITION, 1.4f).target(0, 500)
		 * .ease(TweenEquations.easeOutSine).repeatYoyo(1, 1.4f)
		 * .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
		 * .start(manager);
		 */
		Tween.to(sprite, SpriteAccessor.ALPHA, 1.4f).target(1)
				.ease(TweenEquations.easeInOutQuad).repeatYoyo(1, 1.4f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(manager);
	}

	/** Called when this screen is no longer the current screen for a Game */
	@Override
	public void hide() {
	}

	/**
	 * Called when the Application is paused. An Application is paused before it
	 * is destroyed, when a user pressed the Home button on Android or an
	 * incoming call happend. On the desktop this will only be called
	 * immediately before dispose() is called.
	 */
	@Override
	public void pause() {
	}

	/**
	 * Called when the Application is resumed from a paused state. On Android
	 * this happens when the activity gets focus again. On the desktop this
	 * method will never be called.
	 */
	@Override
	public void resume() {
	}

	/**
	 * Called when the Application is destroyed. Preceded by a call to pause().
	 */
	@Override
	public void dispose() {

		batcher.dispose();
	}

	/**
	 * Called when a key was pressed
	 * 
	 * @param keycode
	 *            one of the constants in Input.Keys
	 * @return whether the input was processed
	 */

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			return true;
		}
		return false;
	}

	/**
	 * Called when a key was released
	 * 
	 * @param keycode
	 *            one of the constants in Input.Keys
	 * @return whether the input was processed
	 */
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * Called when a key was typed
	 * 
	 * @param character
	 *            The character
	 * @return whether the input was processed
	 */

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * Called when the screen was touched or a mouse button was pressed. The
	 * button parameter will be on Android.
	 * 
	 * @param screenX
	 *            The x coordinate, origin is in the upper left corner
	 * @param screenY
	 *            The y coordinate, origin is in the upper left corner
	 * @param pointer
	 *            the pointer for the event.
	 * @param button
	 *            the button
	 * @return whether the input was processed
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Called when a finger was lifted or a mouse button was released. The
	 * button parameter will be on Android.
	 * 
	 * @param pointer
	 *            the pointer for the event.
	 * @param button
	 *            the button
	 * @return whether the input was processed
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Called when a finger or the mouse was dragged.
	 * 
	 * @param pointer
	 *            the pointer for the event.
	 * @return whether the input was processed
	 */

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * Called when the mouse was moved without any buttons being pressed. Will
	 * not be called on Android.
	 * 
	 * @return whether the input was processed
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * Called when the mouse wheel was scrolled. Will not be called on Android.
	 * 
	 * @param amount
	 *            the scroll amount, -1 or 1 depending on the direction the
	 *            wheel was scrolled.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
