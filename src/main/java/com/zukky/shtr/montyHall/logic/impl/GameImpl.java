package com.zukky.shtr.montyHall.logic.impl;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.zukky.shtr.montyHall.entity.Door;
import com.zukky.shtr.montyHall.logic.Game;
import com.zukky.shtr.montyHall.logic.GameHost;

public class GameImpl implements Game{

	@Override
	public void startGame() {
		Scanner console = new Scanner(System.in);
		while(true) {
			startGameSingle(console);
			if(!judgeContinue(console)) break;
		}
		System.out.println("これにてゲームは終了です。ありがとうございました。");
		console.close();
	}

	private boolean judgeContinue(Scanner console) {
		System.out.println("もう一度ゲームをやりますか？　y/n");
		return loadBoolean(console);
	}

	private void startGameSingle(Scanner console) {
		GameHost host = new GameHostImpl();
		System.out.println("Monty Hallゲームを始めます。");
		
		List<Door> doors = host.createDoors();
		
		int doorNumber = loadDoorNumber(console);
		
		doors = host.selectDoors(doors, doorNumber);
		
		doors = host.openUnselectedDoor(doors);
		
		System.out.println("選択されていない扉を開けました。");
		System.out.println("残った扉は、" + showUnOpenedDoor(doors) + "です。");
		System.out.println("さて、これを見て選択する扉を変えますか？変える場合はy、変えない場合はnを入力してください。");
		
		boolean isChange = loadBoolean(console);
		
		doors = host.changeSelectedDoor(doors, isChange);
		boolean isPrise = host.judgePrise(doors);
		
		System.out.println(host.getResultMessage(isPrise));
	}

	private String showUnOpenedDoor(List<Door> doors) {
		return doors.stream()
					.filter(door -> !door.isOpened())
					.map(door -> String.valueOf(door.getId()))
					.collect(Collectors.joining(" , "));
	}

	private int loadDoorNumber(Scanner console) {
		System.out.println("１～３の番号のついた扉があります。１～３の数字を一つ入力してください。");
		while(true) {
			try {
				int doorNumber = Integer.parseInt(console.next());
				if(doorNumber >= 1 && doorNumber <= 3 ) {
					console.reset();
					return doorNumber;
				}
				System.out.println("入力は１～３の数字にしてください。");
			} catch(NumberFormatException e) {
				System.out.println("入力は１～３の数字にしてください。");
			}
		}
	}

	private boolean loadBoolean(Scanner console) {
		while(true) {
			String isChange = console.next().toLowerCase();
			if(isChange.equals("y")) {
				return true;
			} else if(isChange.equals("n")) {
				return false;
			} else {
				System.out.println("入力は　y または n　にしてください。");
			}
		}
	}
}
