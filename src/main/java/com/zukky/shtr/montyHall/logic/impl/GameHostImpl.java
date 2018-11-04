package com.zukky.shtr.montyHall.logic.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;

import com.zukky.shtr.montyHall.entity.Door;
import com.zukky.shtr.montyHall.logic.GameHost;

public class GameHostImpl implements GameHost{

	public List<Door> createDoors() {
		List<Door> doors = Lists.newArrayList(new Door(1), new Door(2), new Door(3));
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

	@Override
	public List<Door> openUnselectedDoor(List<Door> doors) {
		long countsOfSelected = doors.stream().filter(door -> door.isSelected()).count();
		if(countsOfSelected > 1L) {
			throw new IllegalArgumentException("選択されている扉が一つ以上あります。");
		} else if(countsOfSelected == 0) {
			throw new IllegalArgumentException("扉が一つも選択されていません。");
		}
		if(!doors.stream().filter(door -> door.isPrise()).findFirst().isPresent()) {
			throw new IllegalArgumentException("当たりの扉が一つもありません。");
		}
		
		List<Door> unselectedDoors = doors.stream().filter(door -> !door.isSelected() || !door.isPrise()).collect(Collectors.toList());
		Door openedDoor = unselectedDoors.get(new Random().nextInt(unselectedDoors.size()));
		doors.stream().forEach(door -> {
			if(door.getId() == openedDoor.getId()) {
				door.setOpened(true);
			}
		});
		return doors;
	}

	@Override
	public List<Door> changeSelectedDoor(List<Door> doors, boolean isChange) {
		// TODO Auto-generated method stub
		return null;
	}
}
