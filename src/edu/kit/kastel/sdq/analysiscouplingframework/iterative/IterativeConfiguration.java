package edu.kit.kastel.sdq.analysiscouplingframework.iterative;

import java.util.ArrayList;
import java.util.List;

import edu.kit.kastel.sdq.analysiscouplingframework.parser.Configuration;
import edu.kit.kastel.sdq.partitioner.Partitioner;
import edu.kit.kastel.sdq.partitioner.json.PartitionerParsingFactory;

public abstract class IterativeConfiguration extends Configuration {

	protected final String partitionerJSONFilePath;
	protected IterationBlackboard blackboard;
	protected PartitionerParsingFactory ppFactory;

	public IterativeConfiguration(String configFilePath, String partitionerJSONFilePath) {
		super(configFilePath);
		this.partitionerJSONFilePath = partitionerJSONFilePath;
		this.blackboard = new IterationBlackboard();
		this.ppFactory = new PartitionerParsingFactory(partitionerJSONFilePath);
		this.loadPartitionersOrInitializeWithDefault();
	}

	/**
	 * Loads the Partitioners from partitionerJSONFilePath. If there are no
	 * Partitioners the Defaults are created. The partitioners are put on the
	 * blackboard and their IDs are registered in the global Registry.
	 * 
	 * @return true if initWithDefault, false if loaded from file
	 */
	private boolean loadPartitionersOrInitializeWithDefault() {

		List<Partitioner> partitioners = loadPartitioners();
		boolean initWithDefault = (partitioners.size() == 0);

		if (initWithDefault) {
			partitioners = this.getDefaultPartitionersOrdered();
		}

		this.blackboard.initWithOrderedPartitioners(partitioners);
		partitioners.forEach(p -> this.registry.put(p.getId(), ""));

		return initWithDefault;
	}

	public void saveCurrentStateOfPartitioners() {
		this.savePartitioners(this.blackboard.getOrderedPartitioners());
	}

	public void cleanCurrentStateOfPartitioners() {
		this.savePartitioners(new ArrayList<Partitioner>());
	}

	public IterationBlackboard getIterationBlackboard() {
		return this.blackboard;
	}

	/**
	 * Implementations must override this method to specify the needed Partitioners.
	 * The first Element in the List should be the one which is incremented until
	 * all partitions of it are visited once. Only after one full circle, the second
	 * Partitioner will be incremented and so on. <br>
	 * <br>
	 * So the first element should be the most inner loop.
	 * 
	 * @return a list of Partioner
	 */
	protected abstract List<Partitioner> getDefaultPartitionersOrdered();

	private List<Partitioner> loadPartitioners() {
		return this.ppFactory.loadPartitioners();
	}

	private void savePartitioners(List<Partitioner> partitioners) {
		this.ppFactory.savePartitioners(partitioners);
	}
}
