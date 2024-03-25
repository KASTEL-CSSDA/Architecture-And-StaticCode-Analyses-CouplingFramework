package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Configuration;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;

public class AccessanalysisCodeQLConfiguration extends Configuration {

	public AccessanalysisCodeQLConfiguration(String configFile) {
		super(configFile);
	}

	@Override
	protected ProcessingStrategy createStrategy() throws MissingPathIdentifierException {
		return new AccessanalysisCodeQLProcessingStrategy(super.registry);
	}

}
