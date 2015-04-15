package com.betelgeuse.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.betelgeuse.GameHelpers.AssetLoader;

public class LocalScoreBoard extends ShowMessage {
	// 149,40,637
	// 149,139,736
	// 149,240,837

	// 65,76,673
	// 65,176,773
	// 65,276,873

	protected static final int BRONGE_CUP = 0;
	protected static final int SILVER_CUP = 1;
	protected static final int GOLD_CUP = 2;
	protected static final int PLATINUM_CUP = 3;

	private int trophy_1_Color;
	private boolean trophy_1;
	private int trophy_1X = 149;
	private int trophy_1Y = 637;

	private int trophy_2_Color;
	private boolean trophy_2;
	private int trophy_2X = 149;
	private int trophy_2Y = 736;

	private int trophy_3_Color;
	private boolean trophy_3;
	private int trophy_3X = 149;
	private int trophy_3Y = 837;

	private Integer score1;
	private Integer score2;
	private Integer score3;

	private int score1X = 65;
	private int score1Y = 673;

	private int score2X = 65;
	private int score2Y = 773;

	private int score3X = 65;
	private int score3Y = 873;
	private boolean destination;
	private boolean on;
	private boolean off;

	public LocalScoreBoard(float x, float y, float width, float height,
			TextureRegion message) {
		super(x, y, width, height, message);
		i = 0;
		scoreUpdate();
		on = false;
		off = true;
		destination = true;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		if (on) {
			y -= 50 * Math.cos(Math.toRadians(i));
			trophy_1Y -= 50 * Math.cos(Math.toRadians(i));
			trophy_2Y -= 50 * Math.cos(Math.toRadians(i));
			trophy_3Y -= 50 * Math.cos(Math.toRadians(i));
			score1Y -= 50 * Math.cos(Math.toRadians(i));
			score2Y -= 50 * Math.cos(Math.toRadians(i));
			score3Y -= 50 * Math.cos(Math.toRadians(i));
			if (i < 90) {
				i += 5;
			}
			if (i >= 90) {
				y = 10;
				trophy_1Y = 40;

				trophy_2Y = 139;

				trophy_3Y = 240;
				score1Y = 76;
				score2Y = 176;
				score3Y = 276;
				i = 90;
				destination = true;
			}

		}
		if (off) {
			y += 50 * Math.sin(Math.toRadians(i));
			trophy_1Y += 50 * Math.cos(Math.toRadians(i));
			trophy_2Y += 50 * Math.cos(Math.toRadians(i));
			trophy_3Y += 50 * Math.cos(Math.toRadians(i));
			score1Y += 50 * Math.cos(Math.toRadians(i));
			score2Y += 50 * Math.cos(Math.toRadians(i));
			score3Y += 50 * Math.cos(Math.toRadians(i));
			if (i > 0) {
				i -= 5;
			}
			if (i <= 0) {
				reset();
				i = 0;
			}
		}

	}

	public void scoreUpdate() {
		score1 = AssetLoader.getHighScore("highscore1");
		score2 = AssetLoader.getHighScore("highscore2");
		score3 = AssetLoader.getHighScore("highscore3");
		if (score1 > 9) {
			trophy_1 = true;
		}
		if (score2 > 9) {
			trophy_2 = true;
		}
		if (score3 > 9) {
			trophy_3 = true;
		}
		trophyUpdate();
	}

	private void trophyUpdate() {
		if (score1 >= 10 && score1 <= 19) {
			trophy_1_Color = BRONGE_CUP;
		} else if (score1 >= 20 && score1 <= 29) {
			trophy_1_Color = SILVER_CUP;
		} else if (score1 >= 30 && score1 <= 39) {
			trophy_1_Color = GOLD_CUP;
		} else if (score1 >= 40) {
			trophy_1_Color = PLATINUM_CUP;
		}
		if (score2 >= 10 && score2 <= 19) {
			trophy_2_Color = BRONGE_CUP;
		} else if (score2 >= 20 && score2 <= 29) {
			trophy_2_Color = SILVER_CUP;
		} else if (score2 >= 30 && score2 <= 39) {
			trophy_2_Color = GOLD_CUP;
		} else if (score2 >= 40) {
			trophy_2_Color = PLATINUM_CUP;
		}
		if (score3 >= 10 && score3 <= 19) {
			trophy_3_Color = BRONGE_CUP;
		} else if (score3 >= 20 && score3 <= 29) {
			trophy_3_Color = SILVER_CUP;
		} else if (score3 >= 30 && score3 <= 39) {
			trophy_3_Color = GOLD_CUP;
		} else if (score3 >= 40) {
			trophy_3_Color = PLATINUM_CUP;
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		super.reset();
		trophy_1X = 149;
		trophy_1Y = 637;

		trophy_2X = 149;
		trophy_2Y = 736;

		trophy_3X = 149;
		trophy_3Y = 837;

		score1Y = 673;
		score2Y = 773;
		score3Y = 873;

		on = false;
		off = true;

		destination = true;

	}

	/**
	 * @return the trophy_1X
	 */
	public int getCup_1X() {
		return trophy_1X;
	}

	/**
	 * @return the trophy_1Y
	 */
	public int getCup_1Y() {
		return trophy_1Y;
	}

	/**
	 * @return the trophy_2X
	 */
	public int getCup_2X() {
		return trophy_2X;
	}

	/**
	 * @return the trophy_2Y
	 */
	public int getCup_2Y() {
		return trophy_2Y;
	}

	/**
	 * @return the trophy_3X
	 */
	public int getCup_3X() {
		return trophy_3X;
	}

	/**
	 * @return the trophy_3Y
	 */
	public int getCup_3Y() {
		return trophy_3Y;
	}

	/**
	 * @return the score1
	 */
	public Integer getScore1() {
		return score1;
	}

	/**
	 * @return the score2
	 */
	public Integer getScore2() {
		return score2;
	}

	/**
	 * @return the score3
	 */
	public Integer getScore3() {
		return score3;
	}

	/**
	 * @return the destination1
	 */
	public boolean isDestination() {
		return destination;
	}

	/**
	 * @param on
	 *            the on to set
	 */
	public void setOn(boolean on) {
		this.on = on;
	}

	/**
	 * @param off
	 *            the off to set
	 */
	public void setOff(boolean off) {
		this.off = off;
	}

	/**
	 * @return the on
	 */
	public boolean isOn() {
		return on;
	}

	/**
	 * @return the off
	 */
	public boolean isOff() {
		return off;
	}

	/**
	 * @return the trophy_1
	 */
	public boolean isTrophy_1() {
		return trophy_1;
	}

	/**
	 * @return the trophy_2
	 */
	public boolean isTrophy_2() {
		return trophy_2;
	}

	/**
	 * @return the trophy_3
	 */
	public boolean isTrophy_3() {
		return trophy_3;
	}

	/**
	 * @return the trophy_1_Color
	 */
	public int getTrophy_1_Color() {
		return trophy_1_Color;
	}

	/**
	 * @return the trophy_2_Color
	 */
	public int getTrophy_2_Color() {
		return trophy_2_Color;
	}

	/**
	 * @return the trophy_3_Color
	 */
	public int getTrophy_3_Color() {
		return trophy_3_Color;
	}

	/**
	 * @return the score3Y
	 */
	public int getScore3Y() {
		return score3Y;
	}

	/**
	 * @param score3y
	 *            the score3Y to set
	 */
	public void setScore3Y(int score3y) {
		score3Y = score3y;
	}

	/**
	 * @return the score1X
	 */
	public int getScore1X() {
		return score1X;
	}

	/**
	 * @return the score1Y
	 */
	public int getScore1Y() {
		return score1Y;
	}

	/**
	 * @return the score2X
	 */
	public int getScore2X() {
		return score2X;
	}

	/**
	 * @return the score2Y
	 */
	public int getScore2Y() {
		return score2Y;
	}

	/**
	 * @return the score3X
	 */
	public int getScore3X() {
		return score3X;
	}

}
