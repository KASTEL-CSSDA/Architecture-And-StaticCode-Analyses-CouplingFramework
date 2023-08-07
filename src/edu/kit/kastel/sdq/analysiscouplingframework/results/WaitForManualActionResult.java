package edu.kit.kastel.sdq.analysiscouplingframework.results;

import java.io.IOException;

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

		char input = 0;
		try {
			input = (char) System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (input == 'y') ? new ManualActionPerformedResult(this.messageForPerformed)
				: new ManualActionNotPerformedResult(this.messageForNotPerformed);
	}
}
