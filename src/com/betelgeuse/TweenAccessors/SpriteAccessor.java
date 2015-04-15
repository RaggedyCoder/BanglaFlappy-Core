package com.betelgeuse.TweenAccessors;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

public class SpriteAccessor implements TweenAccessor<Sprite> {
	public static final int ALPHA = 1;
	public static final int POSITION = 2;

	public SpriteAccessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case ALPHA:
			System.out.println(returnValues[0]+" ret");
			returnValues[0] = target.getColor().a;
			return ALPHA;
		case POSITION:
			returnValues[0]=target.getX();
			returnValues[1]=target.getY();
			return POSITION;
		default:
			return 0;
		}
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case ALPHA:
			System.out.println(newValues[0]+" set");
			target.setColor(1, 1, 1, newValues[0]);
			break;
		case POSITION:
			System.out.println(newValues[0]+" "+newValues[1]);
			target.setPosition(newValues[0],- newValues[1]);
			break;
		}
	}

}
