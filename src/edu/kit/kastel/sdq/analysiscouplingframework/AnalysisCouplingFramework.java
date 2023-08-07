package edu.kit.kastel.sdq.analysiscouplingframework;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Configuration;

public class AnalysisCouplingFramework {
	
	Logger logger = new Logger();

	public void run(Configuration config) {
		try {
			Session session = config.createSession(logger);
			
			session.execute();
			
			config.saveEntrypointForNextExecution(session.getEntrypointForNextExecution());
			
		} catch (MissingPathIdentifierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logger.saveLogFile();
	}
}
