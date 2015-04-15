package com.betelgeuse.BanglaFlappy;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.betelgeuse.GameHelpers.AssetLoader;
import com.betelgeuse.Screens.SplashScreen;

/**
 * This class creates the total game which is the extend of a {@link Game}
 * abstract Class and the implementation of {@link ApplicationListener}
 */
public class BFGame extends Game implements ApplicationListener {
	public BFGame() {
		super();
	}

	@Override
	public void create() {

		AssetLoader.load();
		/**
		 * Sets the current screen. After calling this method if we running any
		 * screen,it will call the Screen#hide(). setScreen is setting the
		 * opening splash screen.
		 */
		setScreen(new SplashScreen(this));
	}

	/**
	 * Called when the Application is destroyed.
	 */
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
