package com.betelgeuse.Screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.betelgeuse.GameHelpers.InputHandler;
import com.betelgeuse.GameWorld.GameWorldRenderer;
import com.betelgeuse.GameWorld.GameWorldUpdate;

/**
 *
 * 
 */
public class GameScreen implements Screen {

	private GameWorldUpdate world;
	private GameWorldRenderer renderer;
	private float runTime;
	public static float gameWidth = 288.0f;
	public static float gameHeight = 512.0f;

	/**
	 * 
	 */
	public GameScreen() {

		/* setting the width in pixels of the display surface */
		float screenWidth = Gdx.graphics.getWidth();
		/* setting the height in pixels of the display surface */
		float screenHeight = Gdx.graphics.getHeight();

		/**
		 * setting the midPoint of the screenHeight in pixels of the display
		 * surface
		 */
		int midPointY = (int) (gameHeight / 2);

		/**
		 * setting the GameWordUpdate which is also creating the main GameWorld.
		 */
		world = new GameWorldUpdate(midPointY);

		Gdx.input.setInputProcessor(new InputHandler(world));

		/**
		 * setting the GameWorldRenderer which is render the main GameWorld
		 * objects.
		 */
		renderer = new GameWorldRenderer(world, (int) screenWidth,
				(int) screenHeight, midPointY, new Random().nextInt(2),
				new Random().nextInt(3), new Random().nextInt(2));
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
		runTime += delta;
		Gdx.gl.glClearColor(1 / 255.0f, 1 / 255.0f, 60 / 255.0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		world.update(delta);
		renderer.render(delta, runTime);
	}

	@Override
	public void resize(int width, int height) {
		gameHeight = height * 1.0f;
		gameWidth = width * 1.0f;
	}

	/**
	 * Called when this screen becomes the current screen for a subclass of
	 * Game.
	 */
	@Override
	public void show() {
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
	}

}
