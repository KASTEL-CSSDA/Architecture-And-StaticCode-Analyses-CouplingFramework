package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample;


import java.util.Arrays;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.CompletionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.EndingWithWaitForManualActionWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.coupling.completion.specificationtransfer.adapter.SpecificationTransferAdapter;

public class JoanaCompletionPS extends CompletionPS {

	protected static final String USER_SPECIFIC_PATH = "USER_SPECIFIC_PATH";
	protected static final String[] ARG_IDS = { "SPECIFICATION_SRC_DIR", "IMPLEMENTATION_SRC_DIR", "JAVA_CODE_BASE_PATH" };

	public JoanaCompletionPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}
	
	
	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		// return new DummyAdapter("JoanaIntegrationPS");
		return new SpecificationTransferAdapter();
	}

	@Override
	protected String[] getArgsForExecution() {
		String pathPrefix = super.registry.getFileForID(USER_SPECIFIC_PATH).getPath();
		// args[0] = success message, args[1] = failure message
		// all other ordered args are the paths of the IDs taken from the registry
		return Stream.concat(
				Arrays.stream(new String[] { "JoanaCompletionPS: execution successful.",
						"JoanaCompletionPS: execution not successful: " }),
				Arrays.stream(ARG_IDS).map(e -> pathPrefix + super.registry.getFileForID(e).getPath())).toArray(String[]::new);
	}

	@Override
	public Workflow getWorkflow() {
		return new EndingWithWaitForManualActionWorkflow(this, "It may be neccesary to perform manual completions, e.g., adding JOANA Specifications to fields" , "manual action performed", "manual action not performed");
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
