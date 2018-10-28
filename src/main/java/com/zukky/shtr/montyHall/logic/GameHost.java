package com.zukky.shtr.montyHall.logic;

import java.util.List;

import com.zukky.shtr.montyHall.entity.Door;

public interface GameHost {
	public List<Door> createDoors();
	public List<Door> selectDoors(List<Door> doors,int doorId);
	public List<Door> openUnselectedDoor(List<Door> doors);
}
