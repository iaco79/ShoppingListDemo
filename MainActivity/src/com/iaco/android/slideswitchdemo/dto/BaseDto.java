package com.iaco.android.slideswitchdemo.dto;

public abstract class BaseDto {

	private boolean isDirty = false;

	public boolean isDirty() {
		return isDirty;
	}

	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	
}
