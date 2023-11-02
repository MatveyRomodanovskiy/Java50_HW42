package telran.threads.controller;

import java.util.ArrayList;

import telran.threads.tasks.Racer;

public class Race {
	Racer[] racers;
	int distance;
	public static ArrayList<String> prizePlaces = new ArrayList<>();
	public Race (int numberOfRacers, int distance) {
		this.racers = new Racer[numberOfRacers];
		for (int i = 0; i < numberOfRacers; i++) {
			racers[i] = new Racer("thread #" + (i + 1), distance);	
		}
		prizePlaces.clear();
	}
	/**
	 * @return the racers
	 */
	public Racer[] getRacers() {
		return racers;
	}
	
	/**
	 * @return the prizePlaces
	 */
	public ArrayList<String> getPrizePlaces() {
		return prizePlaces;
	}
	
}
