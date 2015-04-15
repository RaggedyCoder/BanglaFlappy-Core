package com.betelgeuse.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	/** For locating the OPENGL texture for the game */
	public static Texture texture;
	/**
	 * For storing the specific part of the Texture.This will store the
	 * background for the game.
	 */
	public static TextureRegion[] bg;
	/**
	 * For storing the specific part of the Texture.This will store the grass
	 * for the game.
	 */
	public static TextureRegion grass;

	/**
	 * For creating the animation of the cat.
	 */
	public static Animation[] catAnimation;
	/**
	 * For creating the sparking of the trophy.
	 */
	public static Animation sparklingAnimation;
	/**
	 * For storing the specific part of the Texture.This will store the bar for
	 * the game.
	 */
	public static TextureRegion barUp[], barDown[];
	/**
	 * For storing the specific part of the Texture.This will store the cat for
	 * the game.
	 */
	public static TextureRegion[] cat1, cat2, cat3;
	/**
	 * For storing the specific part of the Texture.This will store the number
	 * for the game.This number will be used to show the score during the game.
	 */
	public static TextureRegion[] numberGame;

	public static TextureRegion tap;
	/**
	 * For storing the specific part of the Texture.This will store the play
	 * button for the game.
	 */
	public static TextureRegion buttonPlay;
	/**
	 * For storing the specific part of the Texture.This will store the topscore
	 * for the game.
	 */
	public static TextureRegion buttonStat;
	/**
	 * For storing the specific part of the Texture.This will store the splash
	 * for the game.
	 */
	public static TextureRegion splashSceen;
	/**
	 * For storing the specific part of the Texture.This will store the
	 * "প্রস্তুত!" image for the game.
	 */
	public static TextureRegion prepareNotice;
	/**
	 * For storing the specific part of the Texture.This will store the score
	 * board for the game.
	 */
	public static TextureRegion scoreBoard;
	/**
	 * For storing the specific part of the Texture.This will store the
	 * "খেলাশেষ" image for the game.
	 */
	public static TextureRegion gameOverNotice;
	/**
	 * For storing the specific part of the Texture.This will store the
	 * copyright image for the game.
	 */
	public static TextureRegion copyRight;
	/**
	 * For storing the specific part of the Texture.This will store the topscore
	 * for the game.
	 */
	public static TextureRegion logo;
	public static TextureRegion hit;
	/**
	 * For storing the specific part of the Texture.This will store the number
	 * for the game.This number will be used to show the score after the game is
	 * over.
	 */
	public static TextureRegion[] numberScoreBoard;
	/**
	 * For storing the specific part of the Texture.This will store the number
	 * for the game.This number will be used to show the score during the game
	 * in the scoreboard.
	 */
	public static TextureRegion[] numberLocalScoreBoard;
	/**
	 * For storing the specific part of the Texture.This will store the trophy
	 * for the game.
	 */
	public static TextureRegion[] Trophy;
	/**
	 * For storing the specific part of the Texture.This will store the trophy
	 * sparklings for the game.
	 */
	public static TextureRegion[] sparkling;
	/**
	 * For storing the specific part of the Texture.This will store the
	 * leaderboard for the game.
	 */
	public static TextureRegion LocalScoreBoard;

	/** preference is used for small savings.It is used to save the highscores. */
	public static Preferences highScore;

	/** A sound class for playing the cat flapping sound */
	public static Sound wing;
	/** A sound class for playing the sound when the cat hit the bar */
	public static Sound bar_hit;
	/** A sound class for playing the sound when the cat is dead */
	public static Sound death;
	/** A sound class for playing the sound when the cat cross the bar. */
	public static Sound coin;
	/** A sound class for playing the sound when the cat hit the grass */
	public static Sound whoop;

	public AssetLoader() {

	}

	/**
	 * A public method for loading all the animations,sounds,textures and
	 * textures region
	 */
	public static void load() {
		texture = new Texture(Gdx.files.internal("data/atlas.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		bg = new TextureRegion[2];
		bg[0] = new TextureRegion(texture, 0, 0, 288, 512);
		bg[0].flip(false, true);
		bg[1] = new TextureRegion(texture, 292, 0, 288, 512);
		bg[1].flip(false, true);

		grass = new TextureRegion(texture, 586, 0, 336, 112);
		grass.flip(false, true);

		cat1 = new TextureRegion[3];
		cat2 = new TextureRegion[3];
		cat3 = new TextureRegion[3];

		cat1[0] = new TextureRegion(texture, 0, 979, 44, 30);
		cat1[0].flip(false, true);
		cat1[1] = new TextureRegion(texture, 52, 981, 44, 30);
		cat1[1].flip(false, true);
		cat1[2] = new TextureRegion(texture, 108, 981, 44, 30);
		cat1[2].flip(false, true);

		cat2[0] = new TextureRegion(texture, 168, 979, 44, 30);
		cat2[0].flip(false, true);
		cat2[1] = new TextureRegion(texture, 224, 655, 44, 30);
		cat2[1].flip(false, true);
		cat2[2] = new TextureRegion(texture, 224, 708, 44, 30);
		cat2[2].flip(false, true);

		cat3[0] = new TextureRegion(texture, 224, 759, 44, 30);
		cat3[0].flip(false, true);
		cat3[1] = new TextureRegion(texture, 224, 811, 44, 30);
		cat3[1].flip(false, true);
		cat3[2] = new TextureRegion(texture, 224, 864, 44, 30);
		cat3[2].flip(false, true);
		catAnimation = new Animation[3];

		catAnimation[0] = new Animation(0.06f, cat1);
		catAnimation[0].setPlayMode(Animation.LOOP_PINGPONG);
		catAnimation[1] = new Animation(0.06f, cat2);
		catAnimation[1].setPlayMode(Animation.LOOP_PINGPONG);
		catAnimation[2] = new Animation(0.06f, cat3);
		catAnimation[2].setPlayMode(Animation.LOOP_PINGPONG);
		barDown = new TextureRegion[2];
		barUp = new TextureRegion[2];
		barDown[0] = new TextureRegion(texture, 167, 646, 54, 320);
		barUp[0] = new TextureRegion(barDown[0]);
		barUp[0].flip(false, true);
		barDown[1] = new TextureRegion(texture, 55, 646, 54, 320);
		barUp[1] = new TextureRegion(barDown[1]);
		barUp[1].flip(false, true);

		numberGame = new TextureRegion[10];
		numberGame[0] = new TextureRegion(texture, 992, 120, 24, 36);
		numberGame[0].flip(false, true);
		numberGame[1] = new TextureRegion(texture, 270, 910, 24, 36);
		numberGame[1].flip(false, true);
		numberGame[2] = new TextureRegion(texture, 584, 320, 24, 36);
		numberGame[2].flip(false, true);
		numberGame[3] = new TextureRegion(texture, 612, 320, 24, 36);
		numberGame[3].flip(false, true);
		numberGame[4] = new TextureRegion(texture, 640, 320, 24, 36);
		numberGame[4].flip(false, true);
		numberGame[5] = new TextureRegion(texture, 669, 320, 24, 36);
		numberGame[5].flip(false, true);
		numberGame[6] = new TextureRegion(texture, 584, 368, 24, 36);
		numberGame[6].flip(false, true);
		numberGame[7] = new TextureRegion(texture, 612, 368, 24, 36);
		numberGame[7].flip(false, true);
		numberGame[8] = new TextureRegion(texture, 640, 368, 24, 36);
		numberGame[8].flip(false, true);
		numberGame[9] = new TextureRegion(texture, 668, 368, 24, 36);
		numberGame[9].flip(false, true);
		tap = new TextureRegion(texture, 584, 180, 114, 100);
		tap.flip(false, true);
		buttonPlay = new TextureRegion(texture, 552, 815, 48, 54);
		buttonPlay.flip(false, true);
		buttonStat = new TextureRegion(texture, 602, 815, 48, 54);
		buttonStat.flip(false, true);
		splashSceen = new TextureRegion(texture, 736, 518, 288, 512);
		prepareNotice = new TextureRegion(texture, 542, 920, 102, 67);
		prepareNotice.flip(false, true);
		copyRight = new TextureRegion(texture, 496, 888, 203, 24);
		copyRight.flip(false, true);
		gameOverNotice = new TextureRegion(texture, 516, 728, 204, 63);
		gameOverNotice.flip(false, true);
		logo = new TextureRegion(texture, 446, 548, 224, 50);
		logo.flip(false, true);
		scoreBoard = new TextureRegion(texture, 292, 645, 204, 205);
		scoreBoard.flip(false, true);
		hit = new TextureRegion(texture, 584, 451, 32, 32);
		numberScoreBoard = new TextureRegion[10];
		numberScoreBoard = new TextureRegion[10];
		numberScoreBoard[0] = new TextureRegion(texture, 276, 646, 12, 14);
		numberScoreBoard[0].flip(false, true);
		numberScoreBoard[1] = new TextureRegion(texture, 276, 664, 12, 14);
		numberScoreBoard[1].flip(false, true);
		numberScoreBoard[2] = new TextureRegion(texture, 276, 698, 12, 14);
		numberScoreBoard[2].flip(false, true);
		numberScoreBoard[3] = new TextureRegion(texture, 276, 716, 12, 14);
		numberScoreBoard[3].flip(false, true);
		numberScoreBoard[4] = new TextureRegion(texture, 276, 750, 12, 14);
		numberScoreBoard[4].flip(false, true);
		numberScoreBoard[5] = new TextureRegion(texture, 276, 768, 12, 14);
		numberScoreBoard[5].flip(false, true);
		numberScoreBoard[6] = new TextureRegion(texture, 276, 802, 12, 14);
		numberScoreBoard[6].flip(false, true);
		numberScoreBoard[7] = new TextureRegion(texture, 276, 820, 12, 14);
		numberScoreBoard[7].flip(false, true);
		numberScoreBoard[8] = new TextureRegion(texture, 276, 854, 12, 14);
		numberScoreBoard[8].flip(false, true);
		numberScoreBoard[9] = new TextureRegion(texture, 276, 872, 12, 14);
		numberScoreBoard[9].flip(false, true);

		Trophy = new TextureRegion[4];
		Trophy[0] = new TextureRegion(texture, 191, 521, 94, 77);
		Trophy[0].flip(false, true);
		Trophy[1] = new TextureRegion(texture, 286, 521, 94, 77);
		Trophy[1].flip(false, true);
		Trophy[2] = new TextureRegion(texture, 1, 521, 94, 77);
		Trophy[2].flip(false, true);
		Trophy[3] = new TextureRegion(texture, 96, 521, 94, 77);
		Trophy[3].flip(false, true);

		sparkling = new TextureRegion[8];
		sparkling[0] = new TextureRegion(texture, 270, 785, 2, 2);
		sparkling[1] = new TextureRegion(texture, 280, 686, 4, 4);
		sparkling[2] = new TextureRegion(texture, 278, 736, 6, 6);
		sparkling[3] = new TextureRegion(texture, 276, 786, 10, 10);
		sparkling[4] = sparkling[3];
		sparkling[5] = sparkling[2];
		sparkling[6] = sparkling[1];
		sparkling[7] = sparkling[0];

		sparklingAnimation = new Animation(.08f, sparkling);
		sparklingAnimation.setPlayMode(Animation.LOOP);

		LocalScoreBoard = new TextureRegion(texture, 709, 131, 248, 340);
		LocalScoreBoard.flip(false, true);
		numberLocalScoreBoard = new TextureRegion[10];
		numberLocalScoreBoard = new TextureRegion[10];
		numberLocalScoreBoard[0] = new TextureRegion(texture, 274, 612, 14, 20);
		numberLocalScoreBoard[0].flip(false, true);
		numberLocalScoreBoard[1] = new TextureRegion(texture, 274, 954, 14, 20);
		numberLocalScoreBoard[1].flip(false, true);
		numberLocalScoreBoard[2] = new TextureRegion(texture, 274, 978, 14, 20);
		numberLocalScoreBoard[2].flip(false, true);
		numberLocalScoreBoard[3] = new TextureRegion(texture, 274, 1002, 14, 20);
		numberLocalScoreBoard[3].flip(false, true);
		numberLocalScoreBoard[4] = new TextureRegion(texture, 1004, 0, 14, 20);
		numberLocalScoreBoard[4].flip(false, true);
		numberLocalScoreBoard[5] = new TextureRegion(texture, 1004, 24, 14, 20);
		numberLocalScoreBoard[5].flip(false, true);
		numberLocalScoreBoard[6] = new TextureRegion(texture, 1005, 52, 14, 20);
		numberLocalScoreBoard[6].flip(false, true);
		numberLocalScoreBoard[7] = new TextureRegion(texture, 1005, 84, 14, 20);
		numberLocalScoreBoard[7].flip(false, true);
		numberLocalScoreBoard[8] = new TextureRegion(texture, 584, 492, 14, 20);
		numberLocalScoreBoard[8].flip(false, true);
		numberLocalScoreBoard[9] = new TextureRegion(texture, 622, 412, 14, 20);
		numberLocalScoreBoard[9].flip(false, true);

		/*** Create (or retrieve existing) preferences file */
		highScore = Gdx.app.getPreferences("BanglaFlappy");
		if (!highScore.contains("highscore1")) {
			highScore.putInteger("highscore1", 0);
		}
		if (!highScore.contains("highscore2")) {
			highScore.putInteger("highscore2", 0);
		}
		if (!highScore.contains("highscore3")) {
			highScore.putInteger("highscore3", 0);
		}
		highScore.flush();

		wing = Gdx.audio.newSound(Gdx.files.internal("sfx/wing.ogg"));
		whoop = Gdx.audio.newSound(Gdx.files.internal("sfx/button.ogg"));
		coin = Gdx.audio.newSound(Gdx.files.internal("sfx/coin.ogg"));
		death = Gdx.audio.newSound(Gdx.files.internal("sfx/death.ogg"));
		bar_hit = Gdx.audio.newSound(Gdx.files.internal("sfx/hit.ogg"));
	}

	/**
	 * A public method for disposing all the animations,sounds,textures and
	 * textures region
	 */

	public static void dispose() {
		texture.dispose();
	}

	/** To set the high score */
	public static void setHighScore(String tag, int val) {
		highScore.putInteger(tag, val);
		highScore.flush();
	}

	/** To get the high score */
	public static int getHighScore(String tag) {
		return highScore.getInteger(tag);
	}
}
