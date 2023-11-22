package edu.kit.kastel.sdq.analysiscouplingframework.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.AnalysisCouplingFramework;

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

		IterationBlackboard blackboard = config.getIterationBlackboard();

		while (true) {
			// run framework
			if (framework.run(config) == false) {
				config.saveCurrentStateOfPartitioners();
				break;
			}
			// increment partitioners
			if (blackboard.incrementPartitioners() == false) {
				config.cleanCurrentStateOfPartitioners();
				break;
			}
		}
	}

	protected abstract IterativeConfiguration createConfiguration(String configfilePath,
			String partitionerJSONFilePath);

}
