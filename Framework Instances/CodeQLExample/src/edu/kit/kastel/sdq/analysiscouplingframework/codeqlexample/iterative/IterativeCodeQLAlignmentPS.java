package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample.iterative;

import java.util.Arrays;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.DummyAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.AlignmentPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerBlackboard;

public class IterativeCodeQLAlignmentPS extends AlignmentPS {
	static final String[] FILES_FOR_IMPORT = {};
	static final String[] FILES_FOR_EXECUTION = {};
	static final String[] FILES_FOR_EXPORT = {};
	static final String[] IDS_OF_NECCESSARY_PARTITIONERS = { "partitioner" };
	private PartitionerBlackboard blackboard;

	public IterativeCodeQLAlignmentPS(Registry registry, PartitionerBlackboard blackboard)
			throws MissingPathIdentifierException {
		super(registry);
		this.blackboard = blackboard;
	}

	@Override
	public Workflow getWorkflow() {
		return new DefaultWorkflow(this);
	}

	@Override
	protected String[] getFilesForImport() {
		return FILES_FOR_IMPORT;
	}

	@Override
	protected String[] getFilesForExecution() {
		return FILES_FOR_EXECUTION;
	}

	@Override
	protected String[] getFilesForExport() {
		return FILES_FOR_EXPORT;
	}

	//TODO put the logic into the iterative adapter
//	@Override
//	public Result execute() {
//
//		Result result = new OKResult("IterativeAccessControlGeneratorPS: Start execute()");
//
//		Partitioner partitioner = this.blackboard.getPartitionerByID(IDS_OF_NECCESSARY_PARTITIONERS[0]);
//
//		if (!partitioner.isInitialized()) {
//			partitioner.init(new ArrayList<String>(Arrays.asList("e1", "e2", "e3", "e4")));
//		}
//
//		result = result.concatWithSuccessor(
//				new OKResult("IterativeAccessControlGeneratorPS:                                                  "
//						+ partitioner.currentPartition().toString()));
//
//		return result.concatWithSuccessor(new OKResult("IterativeAccessControlGeneratorPS: Execution successful"));
//	}
	
	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		return new DummyAdapter("IterativeAccessControlGeneratorPS");
	}

	@Override
	protected String[] getArgsForExecution() {
		return null;
	}

	@Override
	protected String[] getNecessaryIDs() {
		// Verifies that the partitioners are registered
		// (No specific path must be registered for their IDs).
		// Later they can be taken directly from the blackboard.
		return Stream.concat(Arrays.stream(super.getNecessaryIDs()), Arrays.stream(IDS_OF_NECCESSARY_PARTITIONERS))
				.toArray(String[]::new);
	}
}
