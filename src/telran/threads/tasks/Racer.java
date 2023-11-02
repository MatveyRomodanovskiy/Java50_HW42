package telran.threads.tasks;
import telran.threads.controller.Race;


public class Racer implements Runnable {
	private String name;
	private int distance;

	public Racer(String name, int distance) {
		this.name = name;
		this.distance = distance;
	}

	@Override
	public void run() {
		System.out.println(name + " start...");
		for (int i = 0; i < distance; i++) {
			if (i<distance-1) {
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
		Race.prizePlaces.add(name);
	}

	

}
