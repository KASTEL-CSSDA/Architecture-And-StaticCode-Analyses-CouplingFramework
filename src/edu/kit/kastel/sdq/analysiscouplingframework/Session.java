package edu.kit.kastel.sdq.analysiscouplingframework;

import java.util.UUID;

import edu.kit.kastel.sdq.analysiscouplingframework.processing.Entrypoint;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;
import edu.kit.kastel.sdq.analysiscouplingframework.results.NotOKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

/**
 * 
 * @author Jonas Lehmann
 */
public class Session {
	ProcessingStrategy strategy;
	Entrypoint entrypoint;
	Logger logger;
	UUID uuid;

	public Session(ProcessingStrategy strategy, Entrypoint entrypoint, Logger logger, UUID uuid) {
		this.strategy = strategy;
		this.entrypoint = entrypoint;
		this.logger = logger;
		this.uuid = uuid;
	}

	public void execute() {

		boolean running = true;

		while (running) {

			Result result = this.strategy.continueWith(this.entrypoint);

			if (!result.isOK()) {
				result = result.tryToHandle();
			}

			this.logger.print(result);

			if (!result.isOK()) {
				this.logger.print(new NotOKResult("Session " + this.uuid + " was terminated."));
				running = false;
			} else if (!this.entrypoint.hasNext()) {
				this.logger.print(new OKResult("Session " + this.uuid + " was executed successfully."));
				this.resetEntrypoint();
				running = false;
			} else {
				this.entrypoint = this.entrypoint.getNext();
			}
		}
	}
	
	private void resetEntrypoint() {
		this.entrypoint = Entrypoint.getFirst();
	}
	
	public Entrypoint getEntrypointForNextExecution() {
		return this.entrypoint;
	}
}
