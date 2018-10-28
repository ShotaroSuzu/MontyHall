package com.zukky.shtr.montyHall.logic.impl;

import java.util.List;

import org.assertj.core.util.Lists;

import com.zukky.shtr.montyHall.entity.Door;
import com.zukky.shtr.montyHall.logic.GameHost;

public class GameHostImpl implements GameHost{

	public List<Door> createDoors() {
		List<Door> doors = Lists.newArrayList(new Door(), new Door(), new Door());
		return doors;
	}
}
