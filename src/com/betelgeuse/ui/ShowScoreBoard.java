package com.betelgeuse.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.betelgeuse.GameHelpers.AssetLoader;

public class ShowScoreBoard extends ShowMessage {

	protected static final int BRONGE_CUP = 0;
	protected static final int SILVER_CUP = 1;
	protected static final int GOLD_CUP = 2;
	protected static final int PLATINUM_CUP = 3;

	protected int finalScore;
	protected int increasingScore;
	private Integer score;
	private boolean scoreDestination;
	protected boolean timeGap;
	private int scorePositionX = 138;
	private int scorePositionY = 749;
	private int highScorePositionX = 138;
	private int highScorePositionY = 789;
	protected double timeStart;
	protected double timeEnd;
	private int Trophy;
	private int TrophyPositionX = 496;
	private int TrophyPositionY = 253;

	public ShowScoreBoard(float x, float y, float width, float height,
			TextureRegion message) {
		super(x, y, width, height, message);
		i = 0;
		score = 0;
		highScorePositionX = 138 + (Integer.toString(
				AssetLoader.getHighScore("highscore1")).length() - 1) * 6;
		scoreDestination = false;
		timeGap = false;
	}

	public void update() {
		y -= 30 * Math.cos(Math.toRadians(i));
		scorePositionY -= 30 * Math.cos(Math.toRadians(i));
		highScorePositionY -= 30 * Math.cos(Math.toRadians(i));
		if (scorePositionY <= 161) {
			scorePositionY = 161;
		}
		if (highScorePositionY <= 201) {
			setHighScorePositionY(201);
		}
		if (y <= 137) {
			y = 137;
		}
		if (i >= 90 && !timeGap) {
			setDestination(true);
			scorePositionY = 161;
			setHighScorePositionY(201);
			timeGap = true;
			timeStart = System.currentTimeMillis();
		}
		if (timeGap == true) {
			timeEnd = System.currentTimeMillis();
		}
		if (i < 90) {
			i += 3;
		}
		if (super.isDestination() && (timeEnd - timeStart) / 1000 > .5) {
			if (finalScore > score) {
				score += increasingScore;
				if (score > finalScore) {
					score = finalScore;
				}

			} else {
				scoreDestination = true;
			}
		}
		scorePositionX = 138 + ((score.toString().length() - 1) * 6);
	}

	public void setFinalScore(int score) {
		finalScore = score;
		if (finalScore >= 0 && finalScore <= 19) {
			increasingScore = 1;
		} else {
			increasingScore = finalScore / 10;
		}
		if (finalScore >= 10 && finalScore <= 19) {
			Trophy = BRONGE_CUP;
		} else if (finalScore >= 20 && finalScore <= 29) {
			Trophy = SILVER_CUP;
		} else if (finalScore >= 30 && finalScore <= 39) {
			Trophy = GOLD_CUP;
		} else if (finalScore >= 40) {
			Trophy = PLATINUM_CUP;
		}
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		super.reset();
		timeGap = false;
		increasingScore = 0;
		finalScore = 0;
		score = 0;
		scorePositionX = 838;
		scorePositionY = 749;
		highScorePositionY = 789;
		TrophyPositionX = 496;
		scoreDestination = false;
	}

	/**
	 * @return the scorePositionX
	 */
	public int getScorePositionX() {
		return scorePositionX;
	}

	/**
	 * @return the scorePositionY
	 */
	public int getScorePositionY() {
		return scorePositionY;
	}

	@Override
	public boolean isDestination() {
		// TODO Auto-generated method stub
		return scoreDestination;
	}

	/**
	 * @return the highScorePositionX
	 */
	public int getHighScorePositionX() {
		return highScorePositionX;
	}

	/**
	 * @param highScorePositionX
	 *            the highScorePositionX to set
	 */
	public void setHighScorePositionX(int highScorePositionX) {
		this.highScorePositionX = highScorePositionX;
	}

	/**
	 * @return the highScorePositionY
	 */
	public int getHighScorePositionY() {
		return highScorePositionY;
	}

	/**
	 * @param highScorePositionY
	 *            the highScorePositionY to set
	 */
	public void setHighScorePositionY(int highScorePositionY) {
		this.highScorePositionY = highScorePositionY;
	}

	/**
	 * @return the trophy
	 */
	public int getTrophy() {
		return Trophy;
	}

	/**
	 * @param trophy
	 *            the trophy to set
	 */
	public void setTrophy(int trophy) {
		Trophy = trophy;
	}

	/**
	 * @return the trophyPositionX
	 */
	public int getTrophyPositionX() {
		return TrophyPositionX;
	}

	/**
	 * @param trophyPositionX
	 *            the trophyPositionX to set
	 */
	public void setTrophyPositionX(int trophyPositionX) {
		TrophyPositionX = trophyPositionX;
	}

	/**
	 * @return the trophyPositionY
	 */
	public int getTrophyPositionY() {
		return TrophyPositionY;
	}

	/**
	 * @param trophyPositionY
	 *            the trophyPositionY to set
	 */
	public void setTrophyPositionY(int trophyPositionY) {
		TrophyPositionY = trophyPositionY;
	}

}
