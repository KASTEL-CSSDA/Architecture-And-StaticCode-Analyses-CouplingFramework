package edu.kit.kastel.sdq.analysiscouplingframework;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Configuration;

public class AnalysisCouplingFramework {
	
	Logger logger = new Logger();

	public boolean run(Configuration config) {
		boolean successful = false;
		try {
			Session session = config.createSession(logger);
			
			successful = session.execute();
			
			config.saveEntrypointForNextExecution(session.getEntrypointForNextExecution());
			
		} catch (MissingPathIdentifierException e) {
			e.printStackTrace();
		}
		Logger.saveLogFile();
		return successful;
	}
}
