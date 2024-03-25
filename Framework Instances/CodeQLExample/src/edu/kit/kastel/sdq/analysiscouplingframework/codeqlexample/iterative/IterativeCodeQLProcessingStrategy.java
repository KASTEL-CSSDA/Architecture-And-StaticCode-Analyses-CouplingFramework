package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample.CodeQLAnalysisPS;
import edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample.CodeQLCompletionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample.CodeQLIntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample.CodeQLResolutionPS;
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
