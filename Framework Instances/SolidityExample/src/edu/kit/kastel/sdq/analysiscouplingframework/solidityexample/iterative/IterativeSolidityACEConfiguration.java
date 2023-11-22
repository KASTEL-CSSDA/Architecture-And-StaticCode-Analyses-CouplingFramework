package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample.iterative;

import java.util.Arrays;
import java.util.List;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.IterativeConfiguration;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;
import edu.kit.kastel.sdq.partitioner.LinearSingleElementPartitioner;
import edu.kit.kastel.sdq.partitioner.Partitioner;
import edu.kit.kastel.sdq.partitioner.TrivialPartitioner;

public class IterativeSolidityACEConfiguration extends IterativeConfiguration {

	public IterativeSolidityACEConfiguration(String configfilePath, String partitionerJSONFilePath) {
		super(configfilePath, partitionerJSONFilePath);
	}

	@Override
	protected List<Partitioner> getDefaultPartitionersOrdered() {
		
		Partitioner pEntry = new LinearSingleElementPartitioner("partitioner_entry");
		Partitioner pSrcs = new TrivialPartitioner("partitioner_srcs");
		Partitioner pSinks = new LinearSingleElementPartitioner("partitioner_sinks");
		
		return Arrays.asList(pSinks, pSrcs, pEntry);
	}

	@Override
	protected ProcessingStrategy createStrategy() throws MissingPathIdentifierException {
		return new IterativeSolidityACEProcessingStrategy(super.registry, super.blackboard);
	}

}
