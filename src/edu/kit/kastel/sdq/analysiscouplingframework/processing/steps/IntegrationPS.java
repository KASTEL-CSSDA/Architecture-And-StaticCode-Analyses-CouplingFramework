package edu.kit.kastel.sdq.analysiscouplingframework.processing.steps;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.NecessaryFile;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.Entrypoint;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

public abstract class IntegrationPS extends ProcessingStep {

	public IntegrationPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	public Entrypoint getEntrypoint() {
		return Entrypoint.INTEGRATION;
	}
	
	@Override
	protected Result exportFileToSharedFolder(NecessaryFile file) {
		file.copyToFolder(this.registry.getFileForID(Registry.RESULT_SHARED_FOLDER));
		return new OKResult(this.getPSName() + ": Export file: " + file.toString());
	}
}