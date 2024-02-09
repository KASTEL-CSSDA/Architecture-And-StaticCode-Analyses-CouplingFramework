package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.iterative.AbstractIterativeRunner;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.IterativeConfiguration;

public class IterativeRunner extends AbstractIterativeRunner {

	static final String CONFIG_FILE_PATH = "src/edu/kit/kastel/sdq/analysiscouplingframework/joanaexample/iterative/CONFIG.xml";
	static final String CONFIG_FILE_PATH_OF_JSON = "src/edu/kit/kastel/sdq/analysiscouplingframework/joanaexample/iterative/ITERATION_CONFIG.json";

	private long startTime = 0;
	private long stopTime = 0;

	public static void main(String[] args) {
		new IterativeRunner().start(CONFIG_FILE_PATH, CONFIG_FILE_PATH_OF_JSON);
	}

	@Override
	public void start(String configFilePath, String partitionerJSONFilePath) {
		this.startTimeMeasurement();
		super.start(configFilePath, partitionerJSONFilePath);
		this.stopTimeMeasurement();
		this.printTimeElapsedInSeconds();
	}

	@Override
	protected IterativeConfiguration createConfiguration(String configfilePath, String partitionerJSONFilePath) {
		return new IterativeAccessanalysisJoanaConfiguration(configfilePath, partitionerJSONFilePath);
	}

	private void startTimeMeasurement() {
		this.startTime = System.currentTimeMillis();
	}

	private void stopTimeMeasurement() {
		this.stopTime = System.currentTimeMillis();
	}
	
	private void printTimeElapsedInSeconds() {
		System.out.println("Duration of framework execution: " + ((stopTime - startTime) / 1000.0) + " Seconds");
	}
}
