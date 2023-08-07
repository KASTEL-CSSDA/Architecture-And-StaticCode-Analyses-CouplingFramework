package edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows;

import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.ProcessingStep;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

/**
 * A simple Workflow which triggers following methods on its ProcessingStep in
 * the described order and concats the Results until a NotOKResult is
 * received:<br>
 * <br>
 * 
 * 1) ps.importInputData()<br>
 * 2) ps.verifyPresenceOfExecutionData()<br>
 * 3) ps.execute()<br>
 * 4) ps.exportOutputData()<br>
 * 
 * @author Jonas Lehmann
 */
public class DefaultWorkflow extends Workflow {

	public DefaultWorkflow(ProcessingStep ps) {
		super(ps);
	}

	@Override
	public Result start() {
		Result result = super.ps.importInputData();
		result = (result.isOK()) ? result.concatWithSuccessor(super.ps.verifyPresenceOfExecutionData()) : result;
		result = (result.isOK()) ? result.concatWithSuccessor(super.ps.execute()) : result;
		return (result.isOK()) ? result.concatWithSuccessor(super.ps.exportOutputData()) : result;
	}

}
