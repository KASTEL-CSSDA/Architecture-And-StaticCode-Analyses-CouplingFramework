package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.IntegrationPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.analysiscouplingframework.results.NotOKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;
import edu.kit.kastel.sdq.missingrolerelationadder.MissingRoleRelationAdder;

public class MissingRoleRelationAdderPS extends IntegrationPS {

	static final String[] FILES_FOR_IMPORT = {"INTEGRATION_import_FILE_URI_ACS", "INTEGRATION_import_FILE_URI_SCM", "INTEGRATION_import_JSON_FILE_URI"};
	static final String[] FILES_FOR_EXECUTION = {};
	static final String[] FILES_FOR_EXPORT = {"INTEGRATION_export_FILE_URI_UPDATED_ACS"};

	public MissingRoleRelationAdderPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	public Result execute() {
		Result result = new OKResult("SolidityRoleAdapterPS: Start execute()");
		int argsLength = FILES_FOR_IMPORT.length + FILES_FOR_EXPORT.length;
		String[] args = new String[argsLength];
		for (int i = 0; i < argsLength; i++) {
			args[i] = super.registry.getFileForID(super.getNecessaryIDs()[i]).getPath();
		}

		try {
			MissingRoleRelationAdder.main(args);
		} catch (Exception e) {
			return result.concatWithSuccessor(
					new NotOKResult("MissingRoleRelationAdderPS: Execution failed:" + "\n" + e.getMessage()));
		}
		return result.concatWithSuccessor(new OKResult("MissingRoleRelationAdderPS: Execution successful"));
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
