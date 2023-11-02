package telran.threads.tasks;

import java.time.Instant;
import java.util.*;

public class Racer extends Thread implements Comparable<Racer> {
	private String nameThread;
	private int distance;
	static int minSleepTime = 2;
	static int maxSleepTime = 4;
	static Random random = new Random();
	Instant finishTime;
	
	public Racer(String name, int distance) {
		this.nameThread = name;
		this.distance = distance;
		
	}

	/**
	 * @return the timeStamp
	 */
	public Instant getFinishTime() {
		return finishTime;
	}

	@Override
	public void run() {
		System.out.println(nameThread + " start...");
		for (int i = 0; i < distance; i++) {
			if (i<distance-1) {
				System.out.println(nameThread + ", finished " + (i + 1) + " loop ");
			}
			try {
				int sleepTime = minSleepTime + random.nextInt(maxSleepTime + 1 - minSleepTime);
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		this.finishTime = Instant.now();
	}


	/**
	 * @return the name
	 */
	public String getThreadName() {
		return nameThread;
	}

	@Override
	public int compareTo(Racer o) {	
		return this.finishTime.compareTo(o.finishTime);
	}

	

}
