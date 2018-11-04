package com.zukky.shtr.montyHall.logic;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

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

	@Test
	public void testOpenUnselectedAndNoPriseDoor() {
		List<Door> selectedIsPriseDoor = Lists.newArrayList(new Door(1), new Door(2), new Door(3));
		selectedIsPriseDoor.get(1).setPrise(true);
		selectedIsPriseDoor.get(1).setSelected(true);
		gameHost.openUnselectedDoor(selectedIsPriseDoor);
		
		List<Door> selectedIsNotPriseDoor = createNoPriseDoors();
		selectedIsNotPriseDoor.get(0).setPrise(true);
		selectedIsNotPriseDoor.get(1).setSelected(true);
		gameHost.openUnselectedDoor(selectedIsNotPriseDoor);
		
		assertThat(isOnlyOneDoorOpened(selectedIsPriseDoor)).describedAs("扉が一つだけ空いていることを確認する").isEqualTo(true);
		assertThat(checkOpenedDoorIsNotPriseOrSelected(selectedIsPriseDoor)).describedAs("開けた扉が当たりではないことを確認する。選択した扉が当たりのケース").isEqualTo(true);
		assertThat(isOnlyOneDoorOpened(selectedIsNotPriseDoor)).describedAs("扉が一つだけ空いていることを確認する").isEqualTo(true);
		assertThat(checkOpenedDoorIsNotPriseOrSelected(selectedIsNotPriseDoor)).describedAs("開けた扉が当たりではないことを確認する。選択した扉が当たりではないケース").isEqualTo(true);
	}

	private List<Door> createNoPriseDoors() {
		List<Door> selectedIsNotPriseDoor = Lists.newArrayList(new Door(1), new Door(2), new Door(3));
		return selectedIsNotPriseDoor;
	}

	@Test
	public void testOpenUnselectedAndNoPriseDoorWhenNoDoorChoosen() {
		List<Door> doorsNoDoorSelected = gameHost.createDoors();
		assertThatIllegalArgumentException().isThrownBy(() -> gameHost.openUnselectedDoor(doorsNoDoorSelected))
											.describedAs("一つも選択されていない扉のリストを渡されたらIllegalArgumentExceptionを投げる")
											.withMessage("扉が一つも選択されていません。");
	}

	@Test
	public void testOpenUnselectedAndNoPriseDoorWhenNoPrise() {
		List<Door> doorsNoPrise = Lists.newArrayList(new Door(1), new Door(2), new Door(3));
		doorsNoPrise.get(0).setSelected(true);
		assertThatIllegalArgumentException().isThrownBy(() -> gameHost.openUnselectedDoor(doorsNoPrise))
											.describedAs("当たりがない扉のリストを渡されたらIllegalArgumentExceptionを投げる")
											.withMessage("当たりの扉が一つもありません。");
	}

	@Test
	public void testChangeSelectedDoorWhenChanged() {
		List<Door> doors = Lists.newArrayList(new Door(1), new Door(2, true), new Door(3));
		int selectedId = 0;
		int notSelectedAndnotOpened = 1;
		int openedeId = 2;
		doors.get(selectedId).setSelected(true);
		doors.get(openedeId).setOpened(true);
		List<Door> changedDoors = gameHost.changeSelectedDoor(doors, true);
		
		assertThat(changedDoors.get(selectedId).isSelected())
				.describedAs("変更する場合はもともと選択していた扉は閉じている")
				.isEqualTo(false);
		assertThat(changedDoors.get(notSelectedAndnotOpened).isSelected())
				.describedAs("変更する場合は選択されず、開かれなかった扉が開いた状態になる")
				.isEqualTo(true);
	}

	@Test
	public void testChangeSelectedDoorWhenNotChanged() {
		List<Door> doors = Lists.newArrayList(new Door(1), new Door(2, true), new Door(3));
		int selectedId = 0;
		int notSelectedAndnotOpened = 1;
		int openedeId = 2;
		doors.get(selectedId).setSelected(true);
		doors.get(openedeId).setOpened(true);
		List<Door> notChangedDoors = gameHost.changeSelectedDoor(doors, false);
		
		assertThat(notChangedDoors.get(selectedId).isSelected())
				.describedAs("変更する場合はもともと選択していた扉は開いたまま")
				.isEqualTo(true);
		assertThat(notChangedDoors.get(notSelectedAndnotOpened).isSelected())
				.describedAs("変更する場合は選択されず、開かれなかった扉は閉じたまま")
				.isEqualTo(false);
	}

	private boolean isOnlyOneDoorOpened(List<Door> doors) {
		return doors.stream().filter(door -> door.isOpened()).count() == 1L;
	}

	private boolean checkOpenedDoorIsNotPriseOrSelected(List<Door> doors) {
		Optional<Door> illegallyOpenedDoor = doors.stream().filter(door -> door.isOpened() && door.isPrise()).findFirst();
		return !illegallyOpenedDoor.isPresent();
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
