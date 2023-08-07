package edu.kit.kastel.sdq.analysiscouplingframework.processing.steps;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.Entrypoint;

public abstract class AlignmentPS extends ProcessingStep {

	public AlignmentPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	public Entrypoint getEntrypoint() {
		return Entrypoint.ALIGNMENT;
	}
}