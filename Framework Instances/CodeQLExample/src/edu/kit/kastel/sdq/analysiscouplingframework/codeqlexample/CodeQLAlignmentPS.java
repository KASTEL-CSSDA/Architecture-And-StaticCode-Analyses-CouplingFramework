package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.AlignmentPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.coupling.alignment.accessanalysis2codeql.iterative.AccessAnalysis2CodeQLAdapter;

public class CodeQLAlignmentPS extends AlignmentPS {

	protected static final String USER_SPECIFIC_PATH = "USER_SPECIFIC_PATH";
	protected static final String[] ARG_IDS = { "REPOSITORY_PATH", "CONFIDENTIALITY_MODEL_PATH",
			"CODE_BASE_PACKAGE_NAME", "JAVA_MODEL_PATH", "CODEQL_MODEL_PATH", "CORRESPONDENCE_MODEL_PATH",
			"CODEQL_FOLDER_PATH", "POLICY_STYLE", "ACCESSANALYSIS_CODEQL_CORRESPONDENCE_PATH" };

	public CodeQLAlignmentPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		return new AccessAnalysis2CodeQLAdapter();
	}

	@Override
	protected String[] getArgsForExecution() {
		String pathPrefix = super.registry.getFileForID(USER_SPECIFIC_PATH).getPath();

		// Create absolut and relative paths
		List<String> relPaths1 = Arrays.stream(Arrays.copyOfRange(ARG_IDS, 0, 2))
				.map(e -> pathPrefix + super.registry.getFileForID(e).getPath()).collect(Collectors.toList());
		String absPath1 = super.registry.getFileForID(ARG_IDS[2]).getPath();
		List<String> relPaths2 = Arrays.stream(Arrays.copyOfRange(ARG_IDS, 3, 7))
				.map(e -> pathPrefix + super.registry.getFileForID(e).getPath()).collect(Collectors.toList());
		String absPath2 = super.registry.getFileForID(ARG_IDS[7]).getPath();
		String relPath3 = pathPrefix + super.registry.getFileForID("ACCESSANALYSIS_CODEQL_CORRESPONDENCE_PATH").getPath();
		
		
		relPaths1.add(absPath1);
		relPaths1.addAll(relPaths2);
		relPaths1.add(absPath2);
		relPaths1.add(relPath3);

		// args[0] = success message, args[1] = failure message
		// all other ordered args are the paths of the IDs taken from the registry
		return Stream.concat(Arrays.stream(new String[] { "CodeQLAlignmentPS: execution successful.",
				"CodeQLAlignmentPS: execution not successful: " }), relPaths1.stream()).toArray(String[]::new);
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
