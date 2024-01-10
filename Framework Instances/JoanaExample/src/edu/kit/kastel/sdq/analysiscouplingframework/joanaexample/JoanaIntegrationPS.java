package edu.kit.kastel.sdq.analysiscouplingframework.joanaexample;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.DummyAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.IntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;

public class JoanaIntegrationPS extends IntegrationPS {

	static final String ARG_0 = "PATH_FOO_BAR0";
	static final String ARG_1 = "PATH_FOO_BAR1";
	static final String ARG_2 = "PATH_FOO_BAR2";

	public JoanaIntegrationPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}
	
	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		return new DummyAdapter("JoanaIntegrationPS");
	}

	@Override
	protected String[] getArgsForExecution() {
		String[] args = new String[3];
		args[0] = super.registry.getFileForID(ARG_0).getPath();
		args[1] = super.registry.getFileForID(ARG_1).getPath();
		args[2] = super.registry.getFileForID(ARG_2).getPath();
		return args;
	}

	@Override
	public Workflow getWorkflow() {
		return new DefaultWorkflow(this);
	}

	@Override
	protected String[] getFilesForImport() {
		return new String [] {};
	}

	@Override
	protected String[] getFilesForExecution() {
		return new String [] {};
	}

	@Override
	protected String[] getFilesForExport() {
		return new String [] {};
	}

}
