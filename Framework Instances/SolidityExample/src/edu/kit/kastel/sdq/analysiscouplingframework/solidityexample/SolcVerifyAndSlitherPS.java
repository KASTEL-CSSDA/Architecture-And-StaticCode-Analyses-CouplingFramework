package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.AnalysisPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

public class SolcVerifyAndSlitherPS extends AnalysisPS {
	
	static final String[] FILES_FOR_IMPORT = {"ANALYSIS_IMPORT_FILE_A"};
	static final String[] FILES_FOR_EXECUTION = {};
	static final String[] FILES_FOR_EXPORT = {"ANALYSIS_EXPORT_FILE_B"};
	
	public SolcVerifyAndSlitherPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	public Result execute() {
		return new OKResult("SolcVerifyAndSlitherPS: Start execute()");
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
