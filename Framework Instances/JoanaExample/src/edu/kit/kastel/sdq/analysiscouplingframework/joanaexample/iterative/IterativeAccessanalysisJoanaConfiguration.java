package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.iterative;

import java.util.Arrays;
import java.util.List;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.IterativeConfiguration;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;
import edu.kit.kastel.sdq.partitioner.LinearSingleElementPartitioner;
import edu.kit.kastel.sdq.partitioner.Partitioner;
import edu.kit.kastel.sdq.partitioner.TrivialPartitioner;

public class IterativeAccessanalysisJoanaConfiguration extends IterativeConfiguration {

	public IterativeAccessanalysisJoanaConfiguration(String configFilePath, String partitionerJSONFilePath) {
		super(configFilePath, partitionerJSONFilePath);
	}

	@Override
	protected List<Partitioner> getDefaultPartitionersOrdered() {
		Partitioner pEntry = new TrivialPartitioner("partitioner_entrypoints");
		//Partitioner pEntry = new TrivialPartitioner("partitioner_entrypoints");
		Partitioner pSrcs = new TrivialPartitioner("partitioner_sources");
		Partitioner pSinks = new LinearSingleElementPartitioner("partitioner_sinks");
		
		//return Arrays.asList(pSinks, pSrcs, pEntry);
		return Arrays.asList(pSinks, pEntry);
	}

	@Override
	protected ProcessingStrategy createStrategy() throws MissingPathIdentifierException {
		return new IterativeAccessanalysisJoanaProcessingStrategy(super.registry, super.blackboard);
	}

}
