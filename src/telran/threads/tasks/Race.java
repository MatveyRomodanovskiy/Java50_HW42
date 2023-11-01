package telran.threads.tasks;

import java.util.ArrayList;


public class Race implements Runnable {
	private String name;
	private int max;
	private static int prizePlace = 0;
	public static ArrayList<String> prizePlaces = new ArrayList<>();
	
	public Race(String name, int max) {
		this.name = name;
		this.max = max;
	}

	@Override
	public void run() {
		System.out.println(name + " start...");
		for (int i = 0; i < max; i++) {
			if (i<max-1) {
				System.out.println(name + ", finished " + (i + 1) + " loop ");
			}
			try {
				int delay = 2 + (int) (Math.random() * 4);
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		prizePlaces.add(prizePlace++, name);
	}

	

}
