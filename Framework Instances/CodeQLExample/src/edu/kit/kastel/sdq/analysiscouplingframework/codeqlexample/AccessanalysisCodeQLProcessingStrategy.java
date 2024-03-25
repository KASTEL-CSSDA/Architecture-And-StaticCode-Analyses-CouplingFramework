package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;

public class AccessanalysisCodeQLProcessingStrategy extends ProcessingStrategy {

	public AccessanalysisCodeQLProcessingStrategy(Registry registry) throws MissingPathIdentifierException {
		super(new CodeQLAlignmentPS(registry), new CodeQLCompletionPS(registry), new CodeQLAnalysisPS(registry),
				new CodeQLResolutionPS(registry), new CodeQLIntegrationPS(registry));
	}

}
