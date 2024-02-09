package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.JoanaAnalysisPS;
import edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.JoanaCompletionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.JoanaIntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.JoanaResolutionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerBlackboard;

public class IterativeAccessanalysisJoanaProcessingStrategy extends ProcessingStrategy {

	public IterativeAccessanalysisJoanaProcessingStrategy(Registry registry, PartitionerBlackboard blackboard) throws MissingPathIdentifierException {
		super(new IterativeJoanaAlignmentPS(registry, blackboard), new JoanaCompletionPS(registry),
				new JoanaAnalysisPS(registry), new JoanaResolutionPS(registry),
				new JoanaIntegrationPS(registry));
	}

}
