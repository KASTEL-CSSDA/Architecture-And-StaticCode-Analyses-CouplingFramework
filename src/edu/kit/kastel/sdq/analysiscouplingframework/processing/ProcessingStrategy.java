package edu.kit.kastel.sdq.analysiscouplingframework.processing;

import java.util.HashMap;

import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.AlignmentPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.AnalysisPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.CompletionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.IntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.ProcessingStep;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.ResolutionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

/**
 * 
 * @author Jonas Lehmann
 */
public abstract class ProcessingStrategy {
	private HashMap<Entrypoint, ProcessingStep> processingSteps;

	public ProcessingStrategy(AlignmentPS alignment, CompletionPS completion, AnalysisPS analysis,
			ResolutionPS resolution, IntegrationPS integration) {

		this.processingSteps = new HashMap<Entrypoint, ProcessingStep>();
		ProcessingStep[] input = { alignment, completion, analysis, resolution, integration };

		for (ProcessingStep ps : input) {
			this.processingSteps.put(ps.getEntrypoint(), ps);
		}
	}

	public final Result continueWith(Entrypoint entry) {
		ProcessingStep ps = this.processingSteps.get(entry);
		return ps.getWorkflow().start();
	}
}
