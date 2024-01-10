package edu.kit.kastel.sdq.analysiscouplingframework.adapter;

import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

public class DummyAdapter implements ExecutableProcessingStepAdapter {

	String callingPSName;
	
	/**
	 * Creates an ExecutableProcessingStepAdapter which can be explicitly called with String [ ] args = null.
	 * Its purpose is to put it in ProcessingSteps which need no implementation.
	 * @param callingPSName
	 */
	public DummyAdapter (String callingPSName) {
		this.callingPSName = callingPSName;
	}
	
	@Override
	public Result executeAdapter(String[] args) {
		return new OKResult(callingPSName + ": execution skipped by DummyAdapter (no Adapter was implemented)");
	}

}
