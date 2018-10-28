package com.zukky.shtr.montyHall.logic.impl;

import java.util.List;
import java.util.Random;

import org.assertj.core.util.Lists;

import com.zukky.shtr.montyHall.entity.Door;
import com.zukky.shtr.montyHall.logic.GameHost;

public class GameHostImpl implements GameHost{

	public List<Door> createDoors() {
		List<Door> doors = Lists.newArrayList(new Door(), new Door(), new Door());
		return arrangePrise(doors);
	}

	private List<Door> arrangePrise(List<Door> doors) {
		doors.get(new Random().nextInt(doors.size())).setPrise(true);
		return doors;
	}

	@Override
	public List<Door> selectDoors(List<Door> doors, int doorId) {
		// TODO Auto-generated method stub
		return null;
	}
}
