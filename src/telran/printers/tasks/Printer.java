package telran.printers.tasks;

public class Printer extends Thread {
	private static final long SLEEP_TIME = 1_000_000_000;
	public static int portion;
	public static int distance;
	private int current_pos = 0;
	public static int nPrinters;
	Thread nexThread;
	
	public Printer() {}

	public void setNextThread(Thread nexThread) {
		this.nexThread = nexThread;
	}

	@Override
	public void run() {
		while (current_pos < distance) {
		try {
			sleep(SLEEP_TIME);
		}	
		 catch (InterruptedException e) {
			 		calculateIfTheLastPortion();
			 		printOnePortion();
					nexThread.interrupt();
				}
		}
	}

	private void calculateIfTheLastPortion() {
		if (current_pos + portion < distance) {
				current_pos += portion;		
			} else {
				portion = distance - current_pos;
				current_pos = distance;
			}	
	}

	private void printOnePortion() {
		for (int i = 0; i < portion; i++) {
			System.out.print(this.getName() + " ");
		}
		System.out.println();
	}
	}




