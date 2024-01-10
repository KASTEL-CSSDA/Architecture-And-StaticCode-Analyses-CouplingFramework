package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.DummyAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.AnalysisPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;

public class CodeQLAnalysisPS extends AnalysisPS {
	
	static final String[] FILES_FOR_IMPORT = {};
	static final String[] FILES_FOR_EXECUTION = {};
	static final String[] FILES_FOR_EXPORT = {};
	
	public CodeQLAnalysisPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}
	
	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		return new DummyAdapter("CodeQLAnalysisPS");
	}

	@Override
	protected String[] getArgsForExecution() {
		return null;
	}

	@Override
	public Workflow getWorkflow() {
		return new DefaultWorkflow(this);
	}
	
	@Override
	protected String[] getFilesForImport() {
		return FILES_FOR_IMPORT;
	}

	@Override
	protected String[] getFilesForExecution() {
		return FILES_FOR_EXECUTION;
	}

	@Override
	protected String[] getFilesForExport() {
		return FILES_FOR_EXPORT;
	}
}
