package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.ResolutionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.analysiscouplingframework.results.NotOKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;
import edu.kit.kastel.sdq.solidityroleadapter.SolidityRoleAdapter;

public class SolidityRoleAdapterPS extends ResolutionPS {

	static final String[] FILES_FOR_IMPORT = { "RESOLUTION_import_URI_SOLC_VERIFY", "RESOLUTION_import_URI_SLITHER",
			"RESOLUTION_import_URI_ROLE_ANNOTATIONS" };
	static final String[] FILES_FOR_EXECUTION = {};
	static final String[] FILES_FOR_EXPORT = { "RESOLUTION_export_URI_OF_RESULT_TXT_FILE",
			"RESOLUTION_export_URI_OF_RESULT_JSON_FILE" };

	public SolidityRoleAdapterPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	public Result execute() {
		Result result = new OKResult("SolidityRoleAdapterPS: Start execute()");
		String[] args = new String[5];
		for (int i = 0; i < FILES_FOR_IMPORT.length + FILES_FOR_EXPORT.length; i++) {
			args[i] = super.registry.getFileForID(super.getNecessaryIDs()[i]).getPath();
		}

		try {
			SolidityRoleAdapter.main(args);
		} catch (Exception e) {
			return result.concatWithSuccessor(
					new NotOKResult("SolidityRoleAdapterPS: Execution failed:" + "\n" + e.getMessage()));
		}
		return result.concatWithSuccessor(new OKResult("SolidityRoleAdapterPS: Execution successful"));
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
