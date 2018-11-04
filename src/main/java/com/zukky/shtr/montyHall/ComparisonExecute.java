package com.zukky.shtr.montyHall;

import com.zukky.shtr.montyHall.logic.GameMultiExaminator;
import com.zukky.shtr.montyHall.logic.impl.GameMultiExaminatorImpl;

public class ComparisonExecute {

	public static void main(String[] args) {
		GameMultiExaminator executor = new GameMultiExaminatorImpl();
		long times = 10000;
		System.out.println("扉の変更ありのときの当たりの回数:" + executor.examinGameChange(times));
		System.out.println("扉の変更なしのときの当たりの回数:"+ executor.examinGameNotChange(times));
	}
}
