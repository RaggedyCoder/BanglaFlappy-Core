package com.betelgeuse.TweenAccessors;

import aurelienribon.tweenengine.TweenAccessor;

public class ValueAccessor implements TweenAccessor<Value> {

	public ValueAccessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getValues(Value target, int tweenType, float[] returnValues) {
		// TODO Auto-generated method stub
		 returnValues[0] = target.getValue();
		return 1;
	}

	@Override
	public void setValues(Value target, int tweenType, float[] newValues) {
		// TODO Auto-generated method stub
		target.setValue(newValues[0]);
	}

}
