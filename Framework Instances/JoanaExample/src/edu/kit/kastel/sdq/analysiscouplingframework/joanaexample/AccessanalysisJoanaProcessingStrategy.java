package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;

public class AccessanalysisJoanaProcessingStrategy extends ProcessingStrategy {

	public AccessanalysisJoanaProcessingStrategy(Registry registry) throws MissingPathIdentifierException {
		super(new JoanaAlignmentPS(registry), new JoanaCompletionPS(registry),
				new JoanaAnalysisPS(registry), new JoanaResolutionPS(registry),
				new JoanaIntegrationPS(registry));
	}

}
