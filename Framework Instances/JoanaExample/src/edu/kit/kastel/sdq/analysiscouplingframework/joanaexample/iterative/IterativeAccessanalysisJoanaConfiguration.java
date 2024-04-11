package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.iterative;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.IterativeConfiguration;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;
import edu.kit.kastel.sdq.partitioner.LinearSingleElementPartitioner;
import edu.kit.kastel.sdq.partitioner.Partitioner;
import edu.kit.kastel.sdq.partitioner.TrivialPartitioner;

public class IterativeAccessanalysisJoanaConfiguration extends IterativeConfiguration {

	protected static final String[] PARTITIONER_IDS = { "partitioner_levels", "partitioner_sources",
			"partitioner_sinks", "partitioner_entrypoints" };

	public IterativeAccessanalysisJoanaConfiguration(String configFilePath, String partitionerJSONFilePath) {
		super(configFilePath, partitionerJSONFilePath);
	}

	@Override
	protected List<Partitioner> getDefaultPartitionersOrdered() {
		return Arrays.stream(PARTITIONER_IDS)
				.map(e -> (super.registry.getFileForID(e).getPath().equals("TrivialPartitioner"))
						? new TrivialPartitioner(e)
						: new LinearSingleElementPartitioner(e))
				.collect(Collectors.toList());
		
//		String[] partitioners = Arrays.stream(PARTITIONER_IDS).map(e -> super.registry.getFileForID(e).getPath())
//				.toArray(String[]::new);
//
//		String pEntry2 = this.registry.getFileForID("partitioner_entrypoints").getPath();
//
//		Partitioner pEntry = new LinearSingleElementPartitioner("partitioner_entrypoints");
//		Partitioner pSrcs = new TrivialPartitioner("partitioner_sources");
//		Partitioner pSinks = new TrivialPartitioner("partitioner_sinks");
//		Partitioner pLevels = new LinearSingleElementPartitioner("partitioner_levels");
//
//		return Arrays.asList(pLevels, pSrcs, pSinks, pEntry);
		// return Arrays.asList(pEntry);
	}

	@Override
	protected ProcessingStrategy createStrategy() throws MissingPathIdentifierException {
		return new IterativeAccessanalysisJoanaProcessingStrategy(super.registry, super.blackboard);
	}

}
