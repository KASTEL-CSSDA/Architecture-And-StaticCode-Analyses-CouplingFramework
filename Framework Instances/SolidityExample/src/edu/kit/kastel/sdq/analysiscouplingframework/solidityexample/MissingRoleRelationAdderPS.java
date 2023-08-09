package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.IntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

public class MissingRoleRelationAdderPS extends IntegrationPS {

	static final String[] FILES_FOR_IMPORT = {"INTEGRATION_IMPORT_FILE_D"};
	static final String[] FILES_FOR_EXECUTION = {};
	static final String[] FILES_FOR_EXPORT = {"INTEGRATION_EXPORT_FILE_E"};

	public MissingRoleRelationAdderPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	public Result execute() {
		return new OKResult("MissingRoleRelationAdderPS: Start execute()");
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
