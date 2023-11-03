package telran.printers.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import telran.printers.tasks.Printer;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

public class PrinterAppl {
	private static final int MIN_NUMBER_OF_Printers = 3;
	private static final int MAX_NUMBER_OF_Printers = 10;
	private static final int MIN_Portion = 3;
	private static final int MAX_Portion = 10;
	private static final int MIN_Numbers = 50;
	private static final int MAX_Numbers = 1000;
	public static void main(String[] args) throws InterruptedException, IOException {
		Item[] items = getItems();
		Menu menu = new Menu("Printers", items);
		menu.perform(new SystemInputOutput());		
	}

	private static Item[] getItems() {		
		return new Item[] {
				Item.of("Start", t -> {
					try {
						start(t);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}),
				Item.exit()
		};
	}
	static void start(InputOutput io) throws InterruptedException {
		int[] operands = getOperands(io);
		ArrayList<Printer> printers = new ArrayList<>();
		Printer.nPrinters = operands[0];
		Printer.portion = operands[1];
		Printer.distance = operands[2];
		
		for (int i = 0; i < operands[0]; i++) {
			Printer printer = new Printer();
			printer.setName(i+ 1 + "");
			printer.setDaemon(true);
			printers.add(printer);
		}	
		for(Printer p : printers) {
			int nextIndex = (Integer.parseInt(p.getName()))%operands[0];
			p.setNextThread(printers.get(nextIndex));
			p.start();
		}
		printers.get(0).interrupt();	
		Thread.sleep(1000);
	}
	
	private static int[] getOperands(InputOutput io) {
		int[] oper = {0,0,0};
		while (oper[0]<MIN_NUMBER_OF_Printers || oper[0]>MAX_NUMBER_OF_Printers) {
			oper[0] = io.readInt("Enter number of threads between " + MIN_NUMBER_OF_Printers + " and " + MAX_NUMBER_OF_Printers , "Wrong number");
		}
		while (oper[1] < MIN_Portion || oper[1] > MAX_Portion) {	
			oper[1] = io.readInt("Enter portion  between " + MIN_Portion + " and " + MAX_Portion , "Wrong number");
		}
		while (oper[2] < MIN_Numbers || oper[2] > MAX_Numbers) {	
			oper[2] = io.readInt("Enter total numbers  between " + MIN_Numbers + " and " + MAX_Numbers , "Wrong number");
		}
		return oper;
	}
}
