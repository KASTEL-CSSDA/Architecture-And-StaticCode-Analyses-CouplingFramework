package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Configuration;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;

public class AccessanalysisJoanaConfiguration extends Configuration {

	public AccessanalysisJoanaConfiguration(String configFile) {
		super(configFile);
	}

	@Override
	protected ProcessingStrategy createStrategy() throws MissingPathIdentifierException {
		return new AccessanalysisJoanaProcessingStrategy(super.registry);
	}

}
