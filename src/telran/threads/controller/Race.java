package telran.threads.controller;

import java.util.Arrays;

import telran.threads.tasks.Racer;

public class Race {
	Racer[] racers;
	int distance;
	Racer[] prizePlaces;
	public Race (int numberOfRacers, int distance) {
		this.racers = new Racer[numberOfRacers];
		for (int i = 0; i < numberOfRacers; i++) {
			racers[i] = new Racer("thread #" + (i + 1), distance);	
		}
	
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
	public Racer[] getPrizePlaces() {
		 Arrays.sort(racers);
		 return racers;
	}
	
}
