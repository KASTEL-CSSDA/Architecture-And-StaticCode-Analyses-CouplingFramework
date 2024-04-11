package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.JoanaIntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.coupling.backprojection.joana2accessanalysis.iterative.IterativeJoana2AccessAnalysisAdapter;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerBlackboard;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerStateRepresentation;

public class IterativeJoanaIntegrationPS extends JoanaIntegrationPS {

	protected static final String RESULT_FILE_ID = "JOANA_RESULT_FILE_PATH";

	PartitionerBlackboard blackboard;

	public IterativeJoanaIntegrationPS(Registry registry, PartitionerBlackboard blackboard)
			throws MissingPathIdentifierException {
		super(registry);
		this.blackboard = blackboard;
	}
	
	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		return new IterativeJoana2AccessAnalysisAdapter();
	}

	@Override
	protected String[] getArgsForExecution() {

		String[] args = super.getArgsForExecution();

		// E.g.: Change "Result.txt" to "Result_iteration1.2.5.txt"
		PartitionerStateRepresentation pStateRepr = new PartitionerStateRepresentation();

		String oldOutputFileName = super.registry.getFileForID(RESULT_FILE_ID).getPath();
		String newOutputFileName = oldOutputFileName.substring(0, oldOutputFileName.lastIndexOf('.')) + "_iteration"
				+ pStateRepr.getCurrentPartitionerRepresentation(this.blackboard)
				+ oldOutputFileName.substring(oldOutputFileName.lastIndexOf('.'));

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(oldOutputFileName)) {
				args[i] = newOutputFileName;
				break;
			}
		}
		return args;
	}
}
