package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample.iterative;

import java.util.Arrays;
import java.util.List;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.IterativeConfiguration;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;
import edu.kit.kastel.sdq.partitioner.LinearSingleElementPartitioner;
import edu.kit.kastel.sdq.partitioner.Partitioner;

public class IterativeCodeQLConfiguration extends IterativeConfiguration {

	public IterativeCodeQLConfiguration(String configfilePath, String partitionerJSONFilePath) {
		super(configfilePath, partitionerJSONFilePath);
	}

	@Override
	protected List<Partitioner> getDefaultPartitionersOrdered() {

		Partitioner partitioner = new LinearSingleElementPartitioner("partitioner");

		return Arrays.asList(partitioner);
	}

	@Override
	protected ProcessingStrategy createStrategy() throws MissingPathIdentifierException {
		return new IterativeCodeQLProcessingStrategy(super.registry, super.blackboard);
	}

}
