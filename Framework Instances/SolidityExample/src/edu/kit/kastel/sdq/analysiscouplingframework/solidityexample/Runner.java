package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample;

import edu.kit.kastel.sdq.analysiscouplingframework.AbstractRunner;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Configuration;

public class Runner extends AbstractRunner {
	static final String CONFIG_FILE_PATH = "src/edu/kit/kastel/sdq/analysiscouplingframework/solidityexample/CONFIG.xml";
	static final String PATH_TEST = "C:/Users/legua/Documents/HIWI/Eclipse/Workspace/Architecture-And-StaticCode-Analyses-CouplingFramework/Framework Instances/SolidityExample/src/edu/kit/kastel/sdq/analysiscouplingframework/solidityexample/CONFIG.xml";

	public static void main(String[] args) {
		new Runner().start(PATH_TEST);
	}

	@Override
	protected Configuration createConfiguration(String configfilePath) {
		return new SolidityACEConfiguration(configfilePath);
	}

}
