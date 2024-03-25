package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample;

import java.util.Arrays;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.AnalysisPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.coupling.analysis.codeqlexecution.adapter.CodeQLExecutionAdapter;

public class CodeQLAnalysisPS extends AnalysisPS {

	// codeQLCLIPath
	// dBLocation =
	// CaseStudies\Systems\TravelPlanner\Code\edu.kit.kastel.sdq.coupling.casestudy.travelplanner.code\TravelPlannerCodeQLDB
	// sourceCodePath =
	// Code/edu.kit.kastel.sdq.coupling.casestudy.travelplanner.code
	// codeQLQuery = ./CodeQLQuery/AccessAnalysisLabeledTaintTracking.ql
	// outputFileName = SCAR/CodeQL/codeQLAccessAnalysisOutput.sarif
	protected static final String[] ARG_IDS = { "CODEQL_CLI_PATH", "DB_LOCATION", "SOURCECODE_PATH", "CODEQL_QUERY",
			"OUTPUT_FILENAME" };

	public CodeQLAnalysisPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		// return new DummyAdapter("CodeQLAnalysisPS");
		return new CodeQLExecutionAdapter();
	}

	@Override
	protected String[] getArgsForExecution() {
		// args[0] = success message, args[1] = failure message
		// all other ordered args are the paths of the IDs taken from the registry
		return Stream.concat(
				Arrays.stream(new String[] { "CodeQLAnalysisPS: execution successful.",
						"CodeQLAnalysisPS: execution not successful: " }),
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
