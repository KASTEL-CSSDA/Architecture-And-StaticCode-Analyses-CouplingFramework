package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerBlackboard;

public class IterativeCodeQLProcessingStrategy extends ProcessingStrategy {

	public IterativeCodeQLProcessingStrategy(Registry registry, PartitionerBlackboard blackboard)
			throws MissingPathIdentifierException {
		super(new IterativeCodeQLAlignmentPS(registry, blackboard), new CodeQLCompletionPS(registry),
				new CodeQLAnalysisPS(registry), new CodeQLResolutionPS(registry),
				new CodeQLIntegrationPS(registry));
	}

}
