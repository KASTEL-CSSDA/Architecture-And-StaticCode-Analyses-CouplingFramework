package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample;

import edu.kit.kastel.sdq.analysiscouplingframework.AbstractRunner;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Configuration;

public class Runner extends AbstractRunner {
	static final String CONFIG_FILE_PATH = "src/edu/kit/kastel/sdq/analysiscouplingframework/solidityexample/CONFIG.xml";
	
	public static void main(String[] args) {
		new Runner().start(CONFIG_FILE_PATH);
	}

	@Override
	protected Configuration createConfiguration(String configfilePath) {
		return new AccessanalysisJoanaConfiguration(configfilePath);
	}

}
