package edu.kit.kastel.sdq.analysiscouplingframework.results;

import java.util.Scanner;

public class WaitForManualActionResult extends NotOKResult {
	
	String manualActionInstructions;
	String messageForPerformed;
	String messageForNotPerformed;

	public WaitForManualActionResult(String manualActionInstructions,String messageForPerformed, String messageForNotPerformed) {
		super(messageForNotPerformed);
		this.manualActionInstructions = manualActionInstructions;
		this.messageForPerformed = messageForPerformed;
		this.messageForNotPerformed = messageForNotPerformed;
	}

	@Override
	public Result tryToHandle() {
		System.out.println(">>> " + this.manualActionInstructions);
		System.out.println(">>> Manual action ready? y/n");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(!scanner.hasNext());
		String input = scanner.nextLine();

		return (input.equals("y")) ? new ManualActionPerformedResult(this.messageForPerformed)
				: new ManualActionNotPerformedResult(this.messageForNotPerformed);
	}
}
