package com.zukky.shtr.montyHall;

import com.zukky.shtr.montyHall.logic.Game;
import com.zukky.shtr.montyHall.logic.impl.GameImpl;

public class App {
	public static void main( String[] args ) {
		Game game = new GameImpl();
		game.startGame();
	}
}
