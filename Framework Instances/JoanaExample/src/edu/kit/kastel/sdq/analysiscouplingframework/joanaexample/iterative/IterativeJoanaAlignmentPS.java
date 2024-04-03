package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.JoanaAlignmentPS;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.coupling.alignment.accessanalysis2joana.iterative.IterativeAccessAnalysis2JoanaAdapter;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerBlackboard;

public class IterativeJoanaAlignmentPS extends JoanaAlignmentPS {

	PartitionerBlackboard blackboard;

	/**
	 * Extends the normal JoanaAlignmentPS with the blackboard transfer to the
	 * adapter.
	 * 
	 * @param registry   Registry needed for finding filepaths
	 * @param blackboard The PartitionerBlackboard containing the partitioners
	 * @throws MissingPathIdentifierException in case of an incorrect or missing
	 *                                        filepath in the registry
	 */
	public IterativeJoanaAlignmentPS(Registry registry, PartitionerBlackboard blackboard)
			throws MissingPathIdentifierException {
		super(registry);
		this.blackboard = blackboard;
	}

	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		return new IterativeAccessAnalysis2JoanaAdapter(this.blackboard);
	}

}
