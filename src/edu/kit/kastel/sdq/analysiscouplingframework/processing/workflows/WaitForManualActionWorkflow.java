package edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows;

import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.ProcessingStep;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;
import edu.kit.kastel.sdq.analysiscouplingframework.results.WaitForManualActionResult;

/**
 * This Workflow doesn't call interface methods of its ProcessingStep, but
 * directly returns a WaitForManualActionResult (subclass of NotOKResult) with a
 * specified instruction. <br>
 * <br>
 * For example "It is necessary to manually perform following action to
 * continue:\n" + "Put edited files into folder X"
 * 
 * @author Jonas Lehmann
 */
public class WaitForManualActionWorkflow extends Workflow {
	String messageForPerformed;
	String manualActionInstructions;
	String messageForNotPerformed;

	/**
	 * Creates a new Workflow of type {@link WaitForManualActionWorkflow} which
	 * waits for manual user action, by sending an WaitForManualActionResult. The
	 * message is for logging, the instruction string should contain user
	 * info/instructions.
	 * 
	 * @param ps                       the ProcessingStep which should be manually
	 *                                 completed
	 * @param manualActionInstructions the description to be printed to the user
	 *                                 before waiting for the manual action
	 * @param messageForPerformed      the Result message for logging the
	 *                                 ProcessingStep if successfull
	 * @param messageForNotPerformed   the Result message for the Logger if the user
	 *                                 decides to not perform the action and aborts
	 */
	public WaitForManualActionWorkflow(ProcessingStep ps, String manualActionInstructions, String messageForPerformed,
			String messageForNotPerformed) {
		super(ps);
		this.manualActionInstructions = manualActionInstructions;
		this.messageForPerformed = messageForPerformed;
		this.messageForNotPerformed = messageForNotPerformed;
	}

	@Override
	public Result start() {
		return new WaitForManualActionResult(manualActionInstructions, messageForPerformed, messageForNotPerformed);
	}

}
