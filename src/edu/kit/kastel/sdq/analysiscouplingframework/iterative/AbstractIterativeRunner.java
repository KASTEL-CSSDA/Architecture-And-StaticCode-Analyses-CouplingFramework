package edu.kit.kastel.sdq.analysiscouplingframework.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.AnalysisCouplingFramework;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.terminationcondition.AbstractTerminationCondition;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerBlackboard;
import edu.kit.kastel.sdq.partitioner.blackboard.PartitionerIncrementer;

/**
 * In your implementing package, just override the method createConfiguration()
 * in your ConcreteRunner to return your specific subclass of Configuration.
 * <br>
 * <br>
 * To run this subclass after that, put following main() in the
 * ConcreteRunner:<br>
 * 
 * <pre>
 * public static void main(String[] args) {
 * 	new ConcreteRunner().start(CONFIG_FILE_PATH, PARTITIONER_JSON_FILE_PATH);
 * }
 * </pre>
 * 
 * @author Jonas Lehmann
 */

public abstract class AbstractIterativeRunner {

	public void start(String configFilePath, String partitionerJSONFilePath) {

		AnalysisCouplingFramework framework = new AnalysisCouplingFramework();

		IterativeConfiguration config = createConfiguration(configFilePath, partitionerJSONFilePath);

		PartitionerBlackboard blackboard = config.getPartitionerBlackboard();
		PartitionerIncrementer incrementer = new PartitionerIncrementer();
		
		AbstractTerminationCondition terminationCondition = config.getTerminationCondition();
		// TODO use terminationConditions --> while(condition.isFulfilled())

		while (true) {
			// run framework
			boolean exitedSuccessfully = framework.run(config);
			if (!exitedSuccessfully) {
				config.saveCurrentStateOfPartitioners();
				break;
			}
			// increment partitioners
			boolean stillSomeIterationsLeft = incrementer.incrementPartitionersOfBlackboard(blackboard);
			if (!stillSomeIterationsLeft) {
				config.cleanCurrentStateOfPartitioners();
				break;
			}
		}
	}

	protected abstract IterativeConfiguration createConfiguration(String configfilePath,
			String partitionerJSONFilePath);

}
