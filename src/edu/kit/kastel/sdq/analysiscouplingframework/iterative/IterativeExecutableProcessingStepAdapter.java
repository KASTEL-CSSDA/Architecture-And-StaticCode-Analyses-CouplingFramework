package edu.kit.kastel.sdq.analysiscouplingframework.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerBlackboard;

public abstract class IterativeExecutableProcessingStepAdapter implements ExecutableProcessingStepAdapter{

	protected PartitionerBlackboard blackboard;
	
	public IterativeExecutableProcessingStepAdapter(PartitionerBlackboard blackboard) {
		this.blackboard = blackboard;
	}

}
