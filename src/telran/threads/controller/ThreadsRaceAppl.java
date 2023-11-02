package telran.threads.controller;

import java.io.IOException;
import java.util.Arrays;

import telran.threads.tasks.Racer;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

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
		Race race = new Race(operands[0], operands[1]);
		
		for (int i = 0; i < race.getRacers().length; i++) {
			race.getRacers()[i].start();
		}


		for (int i = 0; i < race.getRacers().length; i++) {
			try {
				race.getRacers()[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		io.writeObjectLine("******************");
		io.writeObjectLine("Congratulations to " + race.getPrizePlaces()[0].getThreadName() + ", the winner of the race\n**********************");
		io.writeObjectLine("List of winners");
		for(Racer racer :race.getPrizePlaces()) {
			io.writeObjectLine(racer.getThreadName());
		}
		
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
