package edu.kit.kastel.sdq.analysiscouplingframework.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerBlackboard;

public abstract class IterativeExecutableProcessingStepAdapter implements ExecutableProcessingStepAdapter {

	protected PartitionerBlackboard blackboard;

	/**
	 * In case of an iterative framework execution, for some ProsseingSteps it might
	 * be necessary to transfer the partitioners to the Adapter where they should
	 * be used.<br>
	 * <br>
	 * 
	 * In this case the adapter should be an
	 * IterativeExecutableProcessingStepAdapter instead of just implementing
	 * ExecutableProcessingStepAdapter to force the transmission of the blackboard
	 * 
	 * @param blackboard the PartitionerBlackboard, which contains all necessary partitioners
	 */
	public IterativeExecutableProcessingStepAdapter(PartitionerBlackboard blackboard) {
		this.blackboard = blackboard;
	}

}
