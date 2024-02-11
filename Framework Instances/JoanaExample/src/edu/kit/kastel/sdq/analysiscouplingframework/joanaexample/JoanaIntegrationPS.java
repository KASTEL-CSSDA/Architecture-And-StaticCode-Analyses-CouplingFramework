package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample;

import java.util.Arrays;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.IntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.coupling.backprojection.joanaresult2accessanalysis.adapter.JoanaResult2AccessAnalysisAdapter;

public class JoanaIntegrationPS extends IntegrationPS {

	protected static final String[] ARG_IDS = { "JAVA_MODEL_PATH", "JOANA_MODEL_PATH",
			"PCMJAVACORRESPONDENCE_MODEL_PATH", "JOANA_RESULT_FILE_PATH", "REPOSITORY_MODEL_PATH",
			"CONFIDENTIALITY_SPECIFICATION_MODEL_PATH" };

	public JoanaIntegrationPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		//return new DummyAdapter("JoanaIntegrationPS");
		return new JoanaResult2AccessAnalysisAdapter();
	}

	@Override
	protected String[] getArgsForExecution() {
		// args[0] = success message, args[1] = failure message
		// all other ordered args are the paths of the IDs taken from the registry
		return Stream.concat(
				Arrays.stream(new String[] { "JoanaIntegrationPS: execution successful.",
						"JoanaIntegrationPS: execution not successful: " }),
				Arrays.stream(ARG_IDS).map(e -> super.registry.getFileForID(e).getPath())).toArray(String[]::new);
	}

	@Override
	public Workflow getWorkflow() {
		return new DefaultWorkflow(this);
	}

	@Override
	protected String[] getFilesForImport() {
		return new String[] {};
	}

	@Override
	protected String[] getFilesForExecution() {
		return new String[] {};
	}

	@Override
	protected String[] getFilesForExport() {
		return new String[] {};
	}

}
