package com.zukky.shtr.montyHall.logic.impl;

import java.util.List;
import java.util.Random;

import com.zukky.shtr.montyHall.entity.Door;
import com.zukky.shtr.montyHall.logic.GameHost;
import com.zukky.shtr.montyHall.logic.GameMultiExaminator;

public class GameMultiExaminatorImpl implements GameMultiExaminator {

	@Override
	public long examinGameNotChange(long times) {
		long priseTimes = executeGame(times, false);
		return priseTimes;
	}

	@Override
	public long examinGameChange(long times) {
		long priseTimes = executeGame(times, true);
		return priseTimes;
	}

	private long executeGame(long times, boolean isChange) {
		long priseTimes = 0L;
		GameHost host = new GameHostImpl();
		for(int i = 0 ; i < times; i++) {
			List<Door> doors = host.createDoors();
			doors = host.selectDoors(doors, new Random().nextInt(doors.size()) + 1);
			doors = host.openUnselectedDoor(doors);
			doors = host.changeSelectedDoor(doors, isChange);
			if(host.judgePrise(doors)) {
				priseTimes++;
			}
		}
		return priseTimes;
	}
}
