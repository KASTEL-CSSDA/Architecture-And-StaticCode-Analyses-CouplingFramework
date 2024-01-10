package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.IterationBlackboard;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;

public class IterativeCodeQLProcessingStrategy extends ProcessingStrategy {

	public IterativeCodeQLProcessingStrategy(Registry registry, IterationBlackboard blackboard)
			throws MissingPathIdentifierException {
		super(new IterativeCodeQLAlignmentPS(registry, blackboard), new CodeQLCompletionPS(registry),
				new CodeQLAnalysisPS(registry), new CodeQLResolutionPS(registry),
				new CodeQLIntegrationPS(registry));
	}

}
