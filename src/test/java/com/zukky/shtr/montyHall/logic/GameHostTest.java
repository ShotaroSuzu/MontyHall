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
	private List<Door> doorsForSelectFirst = Lists.newArrayList();
	private List<Door> doorsForSelectSecond = Lists.newArrayList();
	private List<Door> doorsForSelectLast = Lists.newArrayList();

	@Before
	public void setup() {
		doorsActual = gameHost.createDoors();
		doorsForSelectFirst = gameHost.createDoors();
		doorsForSelectSecond = gameHost.createDoors();
		doorsForSelectLast = gameHost.createDoors();
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

	@Test
	public void testCreateDoorsContainingOnePrise() {
		assertThat(isContainOnlyOnePrise(doorsActual)).describedAs("生成された扉の内一つに当たりが含まれていることのテスト").isEqualTo(true);
	}

	@Test
	public void testSelectDoor() {
		int selectedFirstIndex = 1;
		int selectedSecondIndex = 2;
		int selectedLastIndex = doorsForSelectFirst.size();
		List<Door> selectedFirstDoor = gameHost.selectDoors(doorsForSelectFirst, selectedFirstIndex);
		List<Door> selectedSecondDoor = gameHost.selectDoors(doorsForSelectSecond, selectedSecondIndex);
		List<Door> selectedLastDoor = gameHost.selectDoors(doorsForSelectLast, selectedLastIndex);
		assertThat(selectedFirstDoor.get(selectedFirstIndex - 1).isSelected()).describedAs("指定したインデックスの扉が選択状態になっていることを確認する").isEqualTo(true);
		assertThat(selectedSecondDoor.get(selectedSecondIndex - 1).isSelected()).describedAs("指定したインデックスの扉が選択状態になっていることを確認する").isEqualTo(true);
		assertThat(selectedLastDoor.get(selectedLastIndex - 1).isSelected()).describedAs("指定したインデックスの扉が選択状態になっていることを確認する").isEqualTo(true);
	}

	@Test
	public void testIllegalArgumentForSelectDoor() {
		int illegalPlusIndex = doorsForSelectFirst.size() + 2;
		int illegalMinusIndex = -1;
		int illegalZeroIndex = 0;
		assertThatIllegalArgumentException().isThrownBy(() -> gameHost.selectDoors(doorsForSelectFirst, illegalPlusIndex))
											.describedAs("範囲から正に外れたインデックスを指定した場合はIllegalArgumentExceptionを投げる")
											.withMessage(illegalPlusIndex + "が指定されました。" +"1から" + (doorsForSelectFirst.size()) + "までの数字を選択してください。");
		assertThatIllegalArgumentException().isThrownBy(() -> gameHost.selectDoors(doorsForSelectFirst, illegalMinusIndex))
											.describedAs("範囲から負に外れたインデックスを指定した場合はIllegalArgumentExceptionを投げる")
											.withMessage(illegalMinusIndex + "が指定されました。" +"1から" + (doorsForSelectFirst.size()) + "までの数字を選択してください。");
		assertThatIllegalArgumentException().isThrownBy(() -> gameHost.selectDoors(doorsForSelectFirst, illegalZeroIndex))
											.describedAs("0を指定した場合はIllegalArgumentExceptionを投げる")
											.withMessage(illegalZeroIndex + "が指定されました。" +"1から" + (doorsForSelectFirst.size()) + "までの数字を選択してください。");
	}

	private boolean isContainOnlyOnePrise(List<Door> doors) {
		return doors.stream().filter(door -> door.isPrise()).count() == 1L;
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
