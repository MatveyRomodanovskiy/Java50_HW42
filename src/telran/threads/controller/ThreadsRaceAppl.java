package telran.threads.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import telran.threads.tasks.Race;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;
import telran.view.test.ArithmeticCalculatorAppl;

public class ThreadsRaceAppl {
	private static final int MIN_NUMBER_OF_THREADS = 3;
	private static final int MAX_NUMBER_OF_THREADS = 10;
	private static final int MIN_DISTANCE = 100;
	private static final int MAX_DISTANCE = 3500;
	public static void main(String[] args) throws InterruptedException, IOException {
		Item[] items = getItems();
		Menu menu = new Menu("ThreadsRaces", items);
		menu.perform(new SystemInputOutput());		
	}

	private static Item[] getItems() {		
		return new Item[] {
				Item.of("Start new game", ThreadsRaceAppl::start),
				Item.exit()
		};
	}
	static void start(InputOutput io) {
		int[] operands = getOperands(io);
	//	io.writeObjectLine(operands[0] + operands[1]);
		Race[] tasks = new Race[operands[0]];
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new Race("thread #" + (i + 1), operands[1]);
			
		}
		Thread[] threads = new Thread[tasks.length];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(tasks[i]);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}


		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("******************");
		System.out.println("Congratulations to " + Race.prizePlaces.get(0) + ", the winner of the race\n**********************");
		System.out.println("List of winners");
		Race.prizePlaces.forEach(p -> System.out.println(p));
		
	}
	private static int[] getOperands(InputOutput io) {
		int[] oper = {0,0};
		while (oper[0]<MIN_NUMBER_OF_THREADS || oper[0]>MAX_NUMBER_OF_THREADS) {
			oper[0] = io.readInt("Enter number of threads between " + MIN_NUMBER_OF_THREADS + " and " + MAX_NUMBER_OF_THREADS , "Wrong number");
		}
		while (oper[1] < MIN_DISTANCE || oper[1] > MAX_DISTANCE) {	
			oper[1] = io.readInt("Enter distance  between " + MIN_DISTANCE + " and " + MAX_DISTANCE , "Wrong number");
		}
		return oper;
	}
}
