package com.zukky.shtr.montyHall.logic;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.zukky.shtr.montyHall.entity.Door;
import com.zukky.shtr.montyHall.logic.impl.GameHostImpl;

public class GameHostTest {
	public GameHost gameHost = new GameHostImpl();
	private List<Door> doorsActual = Lists.newArrayList();

	@Before
	public void setup() {
		doorsActual = gameHost.createDoors();
	}
	
	@Test
	public void testCreateTreeDoors() {
		assertThat(doorsActual.size()).describedAs("３つの扉が生成されていることのテスト").isEqualTo(3);
	}
	
	@Test
	public void testCreateUnopenedDoors() {
		assertThat(isAllClosed(doorsActual)).describedAs("生成された扉が全て閉じていることのテスト").isEqualTo(true);
	}
	
	@Test
	public void testCreateUnselectedDoors() {
		assertThat(isAllUnSelected(doorsActual)).describedAs("生成された扉が全て未選択であることのテスト").isEqualTo(true);
	}

	
	private boolean isAllClosed(List<Door> doors) {
		for(Door door : doors) {
			if(door.isOpened()) return false;
		}
		return true;
	}
	
	private boolean isAllUnSelected(List<Door> doors) {
		for(Door door : doors) {
			if(door.isSelected()) return false;
		}
		return true;
	}
}
