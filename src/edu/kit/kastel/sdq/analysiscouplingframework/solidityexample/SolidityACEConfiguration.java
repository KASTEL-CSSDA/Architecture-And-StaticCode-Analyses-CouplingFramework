package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Configuration;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;

public class SolidityACEConfiguration extends Configuration {

	public SolidityACEConfiguration(String configFile) {
		super(configFile);
	}

	@Override
	protected ProcessingStrategy createStrategy() throws MissingPathIdentifierException {
		return new SolidityACEProcessingStrategy(super.registry);
	}

}
