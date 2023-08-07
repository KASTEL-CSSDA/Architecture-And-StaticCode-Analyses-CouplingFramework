package edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows;

import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.ProcessingStep;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

/**
 * 
 * @author Jonas Lehmann
 */
public abstract class Workflow {
	ProcessingStep ps;

	public Workflow(ProcessingStep ps) {
		this.ps = ps;
	}

	public abstract Result start();
}
