package edu.kit.kastel.sdq.analysiscouplingframework.processing.steps;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.Entrypoint;

public abstract class AnalysisPS extends ProcessingStep {

	public AnalysisPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	public Entrypoint getEntrypoint() {
		return Entrypoint.ANALYSIS;
	}
}