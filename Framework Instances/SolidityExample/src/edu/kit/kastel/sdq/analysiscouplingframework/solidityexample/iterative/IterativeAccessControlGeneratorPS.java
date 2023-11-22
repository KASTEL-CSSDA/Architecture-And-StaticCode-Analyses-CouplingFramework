package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample.iterative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.IterationBlackboard;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;
import edu.kit.kastel.sdq.analysiscouplingframework.solidityexample.AccessControlGeneratorPS;
import edu.kit.kastel.sdq.partitioner.Partitioner;

public class IterativeAccessControlGeneratorPS extends AccessControlGeneratorPS {

	static final String[] IDS_OF_NECCESSARY_PARTITIONERS = { "partitioner_entry", "partitioner_srcs",
			"partitioner_sinks" };
	private IterationBlackboard blackboard;

	public IterativeAccessControlGeneratorPS(Registry registry, IterationBlackboard blackboard)
			throws MissingPathIdentifierException {
		super(registry);
		this.blackboard = blackboard;
	}

	@Override
	public Result execute() {

		Result result = new OKResult("IterativeAccessControlGeneratorPS: Start execute()");

		Partitioner pEntry = this.blackboard.getPartitionerByID(IDS_OF_NECCESSARY_PARTITIONERS[0]);
		Partitioner pSrcs = this.blackboard.getPartitionerByID(IDS_OF_NECCESSARY_PARTITIONERS[1]);
		Partitioner pSinks = this.blackboard.getPartitionerByID(IDS_OF_NECCESSARY_PARTITIONERS[2]);

		if (!pEntry.isInitialized()) {
			pEntry.init(new ArrayList<String>(Arrays.asList("e1", "e2", "e3")));
		}
		if (!pSrcs.isInitialized()) {
			pSrcs.init(new ArrayList<String>(Arrays.asList("src1", "src2", "src3")));
		}
		if (!pSinks.isInitialized()) {
			pSinks.init(new ArrayList<String>(Arrays.asList("sink1", "sink2", "sink3")));
		}

		result = result.concatWithSuccessor(
				new OKResult("IterativeAccessControlGeneratorPS:                                                  "
						+ pEntry.currentPartition().toString() + ", " + pSrcs.currentPartition().toString() + ", "
						+ pSinks.currentPartition().toString()));

		return result.concatWithSuccessor(new OKResult("IterativeAccessControlGeneratorPS: Execution successful"));
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
