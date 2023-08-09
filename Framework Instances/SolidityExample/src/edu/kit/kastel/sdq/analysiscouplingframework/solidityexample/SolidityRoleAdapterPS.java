package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.ResolutionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

public class SolidityRoleAdapterPS extends ResolutionPS {
	
	static final String[] FILES_FOR_IMPORT = {"RESOLUTION_IMPORT_FILE_B"};
	static final String[] FILES_FOR_EXECUTION = {"RESOLUTION_EXECUTE_FILE_C"};
	static final String[] FILES_FOR_EXPORT = {"RESOLUTION_EXPORT_FILE_D"};

	public SolidityRoleAdapterPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	public Result execute() {
		return new OKResult("SolidityRoleAdapterPS: Start execute()");
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
