package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.iterative;

import java.util.Arrays;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.DummyAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.JoanaIntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.coupling.backprojection.joana2accessanalysis.iterative.IterativeJoana2AccessAnalysisAdapter;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerBlackboard;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerStateRepresentation;

public class IterativeJoanaIntegrationPS extends JoanaIntegrationPS {

	protected static final String RESULT_FILE_ID = "JOANA_RESULT_FILE_PATH";
	protected static final String CONFIDENTIALITY_SPECIFICATION_MODEL_PATH = "CONFIDENTIALITY_SPECIFICATION_MODEL_PATH";

	PartitionerBlackboard blackboard;

	public IterativeJoanaIntegrationPS(Registry registry, PartitionerBlackboard blackboard)
			throws MissingPathIdentifierException {
		super(registry);
		this.blackboard = blackboard;
	}
	
	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
//		return new DummyAdapter("IterativeJoanaIntegrationPS");
		return new IterativeJoana2AccessAnalysisAdapter();
	}

	@Override
	protected String[] getArgsForExecution() {
		String[] args = super.getArgsForExecution();

		// E.g.: Change "Result.txt" to e.g. "Result_iteration1.2.5.txt"
		PartitionerStateRepresentation pStateRepr = new PartitionerStateRepresentation();

		String oldOutputFileName = super.registry.getFileForID(USER_SPECIFIC_PATH).getPath() + super.registry.getFileForID(RESULT_FILE_ID).getPath();
		
		String newOutputFileName = oldOutputFileName.substring(0, oldOutputFileName.lastIndexOf('.')) + "_iteration"
				+ pStateRepr.getCurrentPartitionerRepresentation(this.blackboard)
				+ oldOutputFileName.substring(oldOutputFileName.lastIndexOf('.'));

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(oldOutputFileName)) {
				args[i] = newOutputFileName;
				break;
			}
		}
		
		System.out.println("------------------------> Partitioner repr: " + pStateRepr.getCurrentPartitionerRepresentation(this.blackboard));
		System.out.println("------------------------> Result file name: " + args[5]);
		
		
		// TODO ziehe diese fallunterscheidung in die Konfigerstellung und weise dann ParallelJIntegrationPS oder SequentialPS zur strategy hinzu...
		if (super.registry.getFileForID("iteration_type").getPath().equals("Parallel")) {
			String confModelFileName = super.registry.getFileForID(USER_SPECIFIC_PATH).getPath() + super.registry.getFileForID(CONFIDENTIALITY_SPECIFICATION_MODEL_PATH).getPath();
			String iterationDependentConfModelFileName = confModelFileName.substring(0, confModelFileName.lastIndexOf('.')) + "_iteration"
					+ pStateRepr.getCurrentPartitionerRepresentation(this.blackboard)
					+ confModelFileName.substring(confModelFileName.lastIndexOf('.'));
			// For the parallel iterative approach Append RESULT_CONFIDENTIALITY_MODEL_PATH at the end of args
			
			args = Stream.concat(Arrays.stream(args), Arrays.stream(new String[] { iterationDependentConfModelFileName })).toArray(String[]::new);
		}
		//System.out.println("------------------------> Result model name: " + args[10]);
		
		return args;
	}
}
