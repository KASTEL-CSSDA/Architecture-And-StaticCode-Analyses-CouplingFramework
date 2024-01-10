package edu.kit.kastel.sdq.analysiscouplingframework.adapter;

import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

/**
 * This interface has to be implemented by external projects, which are called during a ProcessingStep.
 * 
 * @author Jonas Lehmann
 */
public interface ExecutableProcessingStepAdapter {

	public Result executeAdapter(String[] args);
}
