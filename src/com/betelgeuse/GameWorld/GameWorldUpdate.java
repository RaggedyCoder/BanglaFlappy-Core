package com.betelgeuse.GameWorld;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.betelgeuse.GameHelpers.AssetLoader;
import com.betelgeuse.GameHelpers.ScrollHandler;
import com.betelgeuse.GameObjects.Cat;
import com.betelgeuse.ui.GameOverMessage;
import com.betelgeuse.ui.LocalScoreBoard;
import com.betelgeuse.ui.ShowScoreBoard;

public class GameWorldUpdate {

	private Cat nyanCat;
	private ScrollHandler scroller;
	protected final Rectangle bound;
	private Integer score;
	private GameState currentState;
	private int midPointY;
	private float runTime;
	private boolean toReady;
	protected double timecountStart;
	protected double timecountEnd;
	private GameOverMessage gameOver;
	private boolean showTrophy;
	private boolean hit;
	private ShowScoreBoard scoreBoard;
	private LocalScoreBoard localScoreBoard;
	protected Sound coin;
	protected Sound death;
	protected Sound whoop;
	protected Sound bar_hit;
	protected boolean bar_hitted;
	protected boolean SB_play;

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, SHOWGO, SHOWSB, DEAD
	}

	public GameWorldUpdate(int midPointY) {
		currentState = GameState.MENU;
		this.midPointY = midPointY;
		nyanCat = new Cat(122, midPointY - 50, 44, 30);
		scroller = new ScrollHandler(this, 400);

		// System.out.println(midPointY);
		bound = new Rectangle(0, 400, 336, 112);
		showTrophy = false;
		score = 0;
		runTime = 0;
		setToReady(false);
		hit = false;
		gameOver = new GameOverMessage(542, 80,
				AssetLoader.gameOverNotice.getRegionWidth(),
				AssetLoader.gameOverNotice.getRegionHeight(),
				AssetLoader.gameOverNotice);
		scoreBoard = new ShowScoreBoard(41, 725,
				AssetLoader.scoreBoard.getRegionHeight(),
				AssetLoader.scoreBoard.getRegionWidth(), AssetLoader.scoreBoard);
		localScoreBoard = new LocalScoreBoard(19, 607,
				AssetLoader.LocalScoreBoard.getRegionWidth(),
				AssetLoader.LocalScoreBoard.getRegionHeight(),
				AssetLoader.LocalScoreBoard);
		coin = AssetLoader.coin;
		bar_hit = AssetLoader.bar_hit;
		death = AssetLoader.death;
		whoop = AssetLoader.whoop;
		SB_play = true;
		bar_hitted = false;
	}

	public void update(float delta) {
		runTime += delta;
		switch (currentState) {
		case MENU:

			updateMenu(delta);
			localScoreBoard.update();
			break;
		case READY:
			updateReady(delta);
			localScoreBoard.update();
			break;
		case RUNNING:
			updateGamePlay(delta);
			break;
		case GAMEOVER:
			localScoreBoard.update();
			break;
		case SHOWGO:
			updateGameOver(delta);
			break;
		case SHOWSB:
			updateScoreBoard();
			break;
		case DEAD:
			updateDead(delta);
			break;
		default:
			break;
		}
	}

	private void updateScoreBoard() {
		scoreBoard.update();
		// timecountEnd = System.currentTimeMillis();
		if (SB_play) {
			whoop.play();
			SB_play = false;
		}
		if (scoreBoard.isDestination()) {
			currentState = GameState.GAMEOVER;
			if (AssetLoader.getHighScore("highscore1") < score) {
				if (AssetLoader.getHighScore("highscore1") > AssetLoader
						.getHighScore("highscore2")) {
					if (AssetLoader.getHighScore("highscore2") > AssetLoader
							.getHighScore("highscore3")) {
						AssetLoader.setHighScore("highscore3",
								AssetLoader.getHighScore("highscore2"));
					}
					AssetLoader.setHighScore("highscore2",
							AssetLoader.getHighScore("highscore1"));
				}
				AssetLoader.setHighScore("highscore1", score);
				scoreBoard
						.setHighScorePositionX(138 + (Integer.toString(
								AssetLoader.getHighScore("highscore1"))
								.length() - 1) * 6);

			} else if ((AssetLoader.getHighScore("highscore2") < score)
					&& (AssetLoader.getHighScore("highscore1") != score)) {
				if (AssetLoader.getHighScore("highscore2") > AssetLoader
						.getHighScore("highscore3")) {
					AssetLoader.setHighScore("highscore3",
							AssetLoader.getHighScore("highscore2"));
				}
				AssetLoader.setHighScore("highscore2", score);
			} else if ((AssetLoader.getHighScore("highscore3") < score)
					&& (AssetLoader.getHighScore("highscore2") != score)) {
				AssetLoader.setHighScore("highscore3", score);
			}
			// System.out.println((timecountEnd - timecountStart) / 1000);
			if (score > 9) {
				showTrophy = true;
			}
		}

	}

	private void updateGameOver(float delta) {
		// TODO Auto-generated method stub
		gameOver.update(delta);
		timecountEnd = System.currentTimeMillis();
		if (gameOver.isDestination()
				&& (timecountEnd - timecountStart) / 1000 >= .4) {
			currentState = GameState.SHOWSB;
			// timecountStart = System.currentTimeMillis();
		}
	}

	private void updateDead(float delta) {
		// TODO Auto-generated method stub
		timecountEnd = System.currentTimeMillis();
		if ((timecountEnd - timecountStart) / 1000 >= .4) {
			currentState = GameState.SHOWGO;
			timecountStart = System.currentTimeMillis();
			gameOver.setX(42);
			whoop.play();
		}

	}

	private void updateMenu(float delta) {
		// TODO Auto-generated method stub
		nyanCat.updateMenu(runTime);
		scroller.updateReady(delta);

	}

	public void updateReady(float delta) {
		nyanCat.updateReady(runTime);
		scroller.updateReady(delta);
	}

	public void updateGamePlay(float delta) {
		// System.out.println("GameWorld - update");
		if (delta > .15f) {
			delta = .15f;
		}
		nyanCat.update(delta);
		scroller.update(delta);
		if (scroller.collides(nyanCat) && nyanCat.isAlive()) {
			scroller.stop();
			nyanCat.die();
			bar_hit.play();
			bar_hitted = true;
			death.play();
			hit = true;
		}
		if (Intersector.overlaps(nyanCat.getCatBodyBound(), bound)) {
			if (!hit) {
				hit = true;
			}
			if (!bar_hitted) {
				bar_hit.play();
			}
			scroller.stop();
			nyanCat.die();
			nyanCat.decelerate();
			scoreBoard.setFinalScore(score);
			scoreBoard.setHighScorePositionX(138 + (Integer.toString(
					AssetLoader.getHighScore("highscore1")).length() - 1) * 6);
			if (nyanCat.getRotation() == 90) {
				currentState = GameState.DEAD;
				timecountStart = System.currentTimeMillis();
			}
		}
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isPlaying() {
		return currentState == GameState.RUNNING;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void restart() {
		score = 0;
		showTrophy = false;
		nyanCat.onRestart(62, midPointY - 50);
		scroller.onRestart();
		gameOver.reset();
		scoreBoard.reset();
		hit = false;
		localScoreBoard.reset();
		bar_hitted = false;
		SB_play = true;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	/**
	 * @return the nyanCat
	 */
	public Cat getNyanCat() {
		return nyanCat;
	}

	/**
	 * @return the scroller
	 */
	public ScrollHandler getScroller() {
		return scroller;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param increment
	 *            to increment the score
	 */
	public void addScore(int increment) {
		coin.play();
		score += increment;
	}

	public void ready() {
		// TODO Auto-generated method stub
		currentState = GameState.READY;

	}

	/**
	 * @return the toReady
	 */
	public boolean isToReady() {
		return toReady;
	}

	public boolean isDead() {
		return currentState == GameState.DEAD;
	}

	public boolean isShowGO() {
		return currentState == GameState.SHOWGO;
	}

	public boolean isShowSB() {
		return currentState == GameState.SHOWSB;
	}

	/**
	 * @param toReady
	 *            the toReady to set
	 */
	public void setToReady(boolean toReady) {
		this.toReady = toReady;
	}

	/**
	 * @return the gameOver
	 */
	public GameOverMessage getGameOver() {
		return gameOver;
	}

	/**
	 * @return the scoreBoard
	 */
	public ShowScoreBoard getScoreBoard() {
		return scoreBoard;
	}

	/**
	 * @return the hit
	 */
	public boolean isHit() {
		return hit;
	}

	/**
	 * @param hit
	 *            the hit to set
	 */
	public void setHit(boolean hit) {
		this.hit = hit;
	}

	/**
	 * @return the showTrophy
	 */
	public boolean isShowTrophy() {
		return showTrophy;
	}

	/**
	 * @param showTrophy
	 *            the showTrophy to set
	 */
	public void setShowTrophy(boolean showTrophy) {
		this.showTrophy = showTrophy;
	}

	/**
	 * @return the localScoreBoard
	 */
	public LocalScoreBoard getLocalScoreBoard() {
		return localScoreBoard;
	}

	/**
	 * @param localScoreBoard
	 *            the localScoreBoard to set
	 */
	public void setLocalScoreBoard(LocalScoreBoard localScoreBoard) {
		this.localScoreBoard = localScoreBoard;
	}

}
