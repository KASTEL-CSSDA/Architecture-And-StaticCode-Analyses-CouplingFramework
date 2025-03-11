package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample;

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
import edu.kit.kastel.sdq.coupling.backprojection.joana2accessanalysis.iterative.Joana2AccessAnalysisAdapter;

public class JoanaIntegrationPS extends IntegrationPS {

	protected static final String USER_SPECIFIC_PATH = "USER_SPECIFIC_PATH";
	protected static final String[] ARG_IDS = { "JAVA_MODEL_PATH", "JOANA_MODEL_PATH", "CORRESPONDENCE_MODEL_PATH",
			"JOANA_RESULT_FILE_PATH", "REPOSITORY_PATH", "CONFIDENTIALITY_MODEL_PATH",
			"ORIGIN_BACKUP_LOCATION", "POLICY_STYLE",  "SCAR_LOCATION", "RESULTINGVALUES_LOCATION","ACCESSANALYSIS_JOANA_CORRESPONDENCE_PATH", "SCAR_CORRESPONDENCES_LOCATION", "RESULTINGVALUES_CORRESPONDENCES_LOCATION"};

	public JoanaIntegrationPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		// return new DummyAdapter("JoanaIntegrationPS");
		return new Joana2AccessAnalysisAdapter();
	}
	
	
	@Override
	protected String[] getNecessaryIDs() {
		// TODO Auto-generated method stub
		return ARG_IDS;
	}

	@Override
	protected String[] getArgsForExecution() {
		String pathPrefix = super.registry.getFileForID(USER_SPECIFIC_PATH).getPath();

		// Create absolut and relative paths
		List<String> relPaths1 = Arrays.stream(Arrays.copyOfRange(ARG_IDS, 0, 7))
				.map(e -> pathPrefix + super.registry.getFileForID(e).getPath()).collect(Collectors.toList());
		String absPath1 = super.registry.getFileForID(ARG_IDS[7]).getPath();
		List<String> relPaths2 = Arrays.stream(Arrays.copyOfRange(ARG_IDS, 8, 13))
				.map(e -> pathPrefix + super.registry.getFileForID(e).getPath()).collect(Collectors.toList());

		relPaths1.add(absPath1);
		relPaths1.addAll(relPaths2);

		// args[0] = success message, args[1] = failure message
		// all other ordered args are the paths of the IDs taken from the registry
		return Stream.concat(Arrays.stream(new String[] { "JoanaIntegrationPS: execution successful.",
				"JoanaIntegrationPS: execution not successful: " }), relPaths1.stream()).toArray(String[]::new);
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
