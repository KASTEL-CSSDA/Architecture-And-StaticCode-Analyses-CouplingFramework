package edu.kit.kastel.sdq.analysiscouplingframework;

import edu.kit.kastel.sdq.analysiscouplingframework.parser.Configuration;

/**
 * In your implementing package, just override the method createConfiguration() in your ConcreteRunner to return your specific subclass of Configuration.
 * <br><br>
 * To run this subclass after that, put following main() in the ConcreteRunner:<br>
 * <pre>
 * public static void main(String[] args) {
 * 	new ConcreteRunner().start(CONFIG_FILE_PATH);
 * }
 * </pre>
 * 
 * @author Jonas Lehmann
 */
public abstract class AbstractRunner {
	
	public void start(String configfilePath) {

		Configuration config = createConfiguration(configfilePath);

		AnalysisCouplingFramework framework = new AnalysisCouplingFramework();

		framework.run(config);
	}

	protected abstract Configuration createConfiguration(String configfilePath);
}
