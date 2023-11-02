package telran.threads.tasks;

import java.time.Instant;
import java.util.*;

public class Racer implements Runnable, Comparable<Racer> {
	private String name;
	private int distance;
	static int minSleepTime = 2;
	static int maxSleepTime = 4;
	static Random random = new Random();
	Instant finishTime;
	
	public Racer(String name, int distance) {
		this.name = name;
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
		System.out.println(name + " start...");
		for (int i = 0; i < distance; i++) {
			if (i<distance-1) {
				System.out.println(name + ", finished " + (i + 1) + " loop ");
			}
			try {
				int sleepTime = minSleepTime + random.nextInt(maxSleepTime + 1 - minSleepTime);
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		this.finishTime = Instant.now();
//		Race.prizePlaces.add(name);
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Racer o) {	
		return this.finishTime.compareTo(o.finishTime);
	}

	

}
