package com.zukky.shtr.montyHall.entity;

public class Door {
	public Door() {
		super();
	}
	public Door(int id) {
		super();
		this.id = id;
	}
	private int id;
	private boolean opened;
	private boolean selected;
	private boolean prise;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isOpened() {
		return opened;
	}
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isPrise() {
		return prise;
	}
	public void setPrise(boolean prise) {
		this.prise = prise;
	}
}
