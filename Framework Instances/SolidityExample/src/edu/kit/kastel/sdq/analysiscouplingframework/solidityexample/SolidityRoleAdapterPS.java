package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.steps.ResolutionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;
import edu.kit.kastel.sdq.solidityroleadapter.SolidityRoleAdapter;

public class SolidityRoleAdapterPS extends ResolutionPS {
	
	static final String[] FILES_FOR_IMPORT = {"RESOLUTION_IMPORT_FILE_B"};
	static final String[] FILES_FOR_EXECUTION = {"RESOLUTION_EXECUTE_FILE_C"};
	static final String[] FILES_FOR_EXPORT = {"RESOLUTION_EXPORT_FILE_D"};

	public SolidityRoleAdapterPS(Registry registry) throws MissingPathIdentifierException {
		super(registry);
	}

	@Override
	public Result execute() {
		String[]args = {};
		SolidityRoleAdapter.main(args);
		
//		try {
//            System.out.println("**********");
//            //runProcess("java -cp src ../../../TestExternalExec/src/edu/kit/kastel/sdq/test/Test.java");
//            //runProcess("java -cp src ../../../SolidityAccessControlEnforcement/Projects/SolidityRoleAdapter/src/edu/kit/kastel/sdq/solidityroleadapter/SolidityRoleAdapter.java");
//            runProcess("java -cp src ../../../SolidityRoleAdapter/src/edu/kit/kastel/sdq/solidityroleadapter/SolidityRoleAdapter.java");
//            System.out.println("**********");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		
		return new OKResult("SolidityRoleAdapterPS: Start execute()");
	}
	
	private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
      }

      private static void runProcess(String command) throws Exception {
    	Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
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
