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
		if(doorId > doors.size() + 1 || doorId <= 0) {
			throw new IllegalArgumentException(doorId + "が指定されました。" +"1から" + (doors.size()) + "までの数字を選択してください。");
		}
		doors.get(doorId -1).setSelected(true);
		return doors;
	}
}
