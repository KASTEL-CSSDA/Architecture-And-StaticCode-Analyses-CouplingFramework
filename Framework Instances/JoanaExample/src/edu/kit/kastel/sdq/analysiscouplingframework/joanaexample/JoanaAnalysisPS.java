package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.AnalysisPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.coupling.sourcecodeanalysis.joanaexecution.adapter.JoanaExecutionAdapter;

public class JoanaAnalysisPS extends AnalysisPS {

	protected static final String USER_SPECIFIC_PATH = "USER_SPECIFIC_PATH";
	protected static final String[] ARG_IDS = { "ANALYSIS_PROJECT_PATH", "JOANA_SOURCECODE_BASE_PACKAGE_PATH",
			"ENTRYPOINTS_FILE_PATH", "OUTPUT_FILE_PATH", "JAVA8_COMPILER_LOCATION", "JAVA8_RUNTIME_LOCATION",
			"JOANA_CLI_LOCATION" };

	public JoanaAnalysisPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		// return new DummyAdapter("JoanaAnalysisPS");
		return new JoanaExecutionAdapter();
	}

	@Override
	protected String[] getArgsForExecution() {
		String pathPrefix = super.registry.getFileForID(USER_SPECIFIC_PATH).getPath();

		String[] args = Stream.concat(
				Arrays.stream(Arrays.copyOfRange(ARG_IDS, 0, 4))
						.map(e -> pathPrefix + super.registry.getFileForID(e).getPath()),
				Arrays.stream(Arrays.copyOfRange(ARG_IDS, 4, 7)).map(e -> super.registry.getFileForID(e).getPath()))
				.map(p -> (System.getProperty("os.name").toLowerCase().contains("win"))
						? p.replaceAll(Pattern.quote("/"), Matcher.quoteReplacement("\\\\"))
						: p)
				.toArray(String[]::new);

		// args[0] = success message, args[1] = failure message
		// all other ordered args are the paths of the IDs taken from the registry
		return Stream.concat(Arrays.stream(new String[] { "JoanaAnalysisPS: execution successful.",
				"JoanaAnalysisPS: execution not successful: " }), Arrays.stream(args)).toArray(String[]::new);
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
