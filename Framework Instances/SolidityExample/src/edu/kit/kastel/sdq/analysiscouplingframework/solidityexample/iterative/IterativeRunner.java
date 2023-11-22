package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.iterative.AbstractIterativeRunner;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.IterativeConfiguration;

public class IterativeRunner extends AbstractIterativeRunner {

	static final String CONFIG_FILE_PATH = "src/edu/kit/kastel/sdq/analysiscouplingframework/solidityexample/iterative/CONFIG.xml";
	static final String CONFIG_FILE_PATH_OF_JSON = "src/edu/kit/kastel/sdq/analysiscouplingframework/solidityexample/iterative/ITERATION_CONFIG.json";
	
	public static void main(String[] args) {
		new IterativeRunner().start(CONFIG_FILE_PATH, CONFIG_FILE_PATH_OF_JSON);
	}
	
	@Override
	protected IterativeConfiguration createConfiguration(String configfilePath, String partitionerJSONFilePath) {
		return new IterativeSolidityACEConfiguration(configfilePath, partitionerJSONFilePath);
	}
}
