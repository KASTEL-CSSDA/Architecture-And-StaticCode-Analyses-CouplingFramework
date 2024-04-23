package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.IntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.coupling.backprojection.codeql2accessanalysis.iterative.CodeQL2AccessAnalysisAdapter;

public class CodeQLIntegrationPS extends IntegrationPS {
	
	protected static final String USER_SPECIFIC_PATH = "USER_SPECIFIC_PATH";
	protected static final String[] ARG_IDS = { "POLICY_STYLE", "JAVA_MODEL_LOCATION", "CODEQL_MODEL_LOCATION",
			"CORRESPONDENCE_MODEL_LOCATION", "CODEQL_RESULT_FILE_LOCATION", "REPOSITORY_MODEL_LOCATION",
			"CONFIDENTIALITY_SPECIFICATION_LOCATION", "ORIGIN_BACKUP_LOCATION" };

	public CodeQLIntegrationPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		return new CodeQL2AccessAnalysisAdapter();
	}
	
	@Override
	protected String[] getArgsForExecution() {
		String pathPrefix = super.registry.getFileForID(USER_SPECIFIC_PATH).getPath();

		// Create absolut and relative paths
		String absPath1 = super.registry.getFileForID(ARG_IDS[0]).getPath();
		List<String> relPaths1 = Arrays.stream(Arrays.copyOfRange(ARG_IDS, 1, 8))
				.map(e -> pathPrefix + super.registry.getFileForID(e).getPath()).collect(Collectors.toList());

		List<String> paths = new ArrayList<String>();
		paths.add(absPath1);
		paths.addAll(relPaths1);

		// args[0] = success message, args[1] = failure message
		// all other ordered args are the paths of the IDs taken from the registry
		return Stream.concat(Arrays.stream(new String[] { "CodeQLIntegrationPS: execution successful.",
				"CodeQLIntegrationPS: execution not successful: " }), paths.stream()).toArray(String[]::new);
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
