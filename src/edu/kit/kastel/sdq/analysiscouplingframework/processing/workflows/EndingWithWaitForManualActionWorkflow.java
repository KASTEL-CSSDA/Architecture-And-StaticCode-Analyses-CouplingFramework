package edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows;

import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.ProcessingStep;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

public class EndingWithWaitForManualActionWorkflow extends Workflow{

	String messageForPerformed;
	String manualActionInstructions;
	String messageForNotPerformed;
	
	public EndingWithWaitForManualActionWorkflow(ProcessingStep ps, String manualActionInstructions, String messageForPerformed,
			String messageForNotPerformed) {
		super(ps);
		this.manualActionInstructions = manualActionInstructions;
		this.messageForPerformed = messageForPerformed;
		this.messageForNotPerformed = messageForNotPerformed;
	}

	@Override
	public Result start() {
		Result result = new DefaultWorkflow(super.ps).start();
		
		if(!result.isOK()) {
			return result;
		}
		
		return new WaitForManualActionWorkflow(ps, manualActionInstructions, messageForPerformed, messageForNotPerformed).start();
	}

}
