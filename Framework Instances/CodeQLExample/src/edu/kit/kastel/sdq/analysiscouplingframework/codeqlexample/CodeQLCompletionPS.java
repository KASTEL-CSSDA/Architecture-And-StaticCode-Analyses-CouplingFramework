package edu.kit.kastel.sdq.analysiscouplingframework.codeqlexample;

import edu.kit.kastel.sdq.analysiscouplingframework.adapter.DummyAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.adapter.ExecutableProcessingStepAdapter;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.CompletionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.WaitForManualActionWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;

public class CodeQLCompletionPS extends CompletionPS {

	static final String[] FILES_FOR_IMPORT = {};
	static final String[] FILES_FOR_EXECUTION = {};
	static final String[] FILES_FOR_EXPORT = {};

	public CodeQLCompletionPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	protected ExecutableProcessingStepAdapter getDefinedExecutableProcessingStepAdapter() {
		return new DummyAdapter("ManualCodeCompletionPS");
	}

	@Override
	protected String[] getArgsForExecution() {
		return null;
	}

	@Override
	public Workflow getWorkflow() {
		return new WaitForManualActionWorkflow(this,
				"ManualCodeCompletionPS: It is necessary to manually perform following action to continue: Put edited files into folder X",
				"ManualCodeCompletionPS: manual action performed", "ManualCodeCompletionPS: manual action not performed");
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
