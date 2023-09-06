package edu.kit.kastel.sdq.analysiscouplingframework.processing.steps;

import java.util.Arrays;
import java.util.stream.Stream;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.IDVerifier;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.NecessaryFile;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.Entrypoint;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.DefaultWorkflow;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.workflows.Workflow;
import edu.kit.kastel.sdq.analysiscouplingframework.results.NotOKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.OKResult;
import edu.kit.kastel.sdq.analysiscouplingframework.results.Result;

/**
 * When executing the framework, the 5 work packages are divided into
 * ProcessingSteps. In each ProcessingStep its tasks are executed via a Workflow
 * e.g. DefaultWorkflow. First in importInputData() all necessary files from
 * previous ProcessingSteps are loaded from the TempSharedFolder. In
 * verifyPresenceOfExecutionData() the presence of all relevant scripts and
 * files is checked. The execute() function must be implemented by all
 * subclasses according to their needs. In the last step exportOutputData() the
 * generated results are written back from the external working directories to
 * the TempSharedFolder of the framework. <br>
 * <br>
 * 
 * Each subclass must also specify exactly which files it needs to import,
 * execute and export so that they can be found in the registry. For this, the
 * abstract methods getFilesForImport(), getFilesForExecution(), and
 * getFilesForExport() must be overridden and return all IDs to which you then
 * want to assign paths in the CONFIG.xml file.
 * 
 * @author Jonas Lehmann
 */
public abstract class ProcessingStep {

	protected Registry registry;

	/**
	 * Creates a new {@link ProcessingStep}. A Registry must be passed to let this
	 * ProcessingStep access the paths of its necessary files and folders, which are
	 * specified by their IDs in getFilesForImport(), getFilesForExecution(), and
	 * getFilesForExport().<br>
	 * <br>
	 * 
	 * The constructor uses an IDVerifier to compare the needed IDs with all entries
	 * of the registry. If any ID has no entry a MissingPathIdentifierException is
	 * thrown.
	 * 
	 * @param registry The Registry
	 * @throws MissingPathIdentifierException if any ID has no entry.
	 */
	public ProcessingStep(Registry registry) throws MissingPathIdentifierException {
		this.registry = registry;
		IDVerifier verifier = new IDVerifier(this.getNecessaryIDs());
		verifier.verifyPresenceOfAllNecessaryIDs(registry);
	}

	protected abstract String[] getFilesForImport();

	protected abstract String[] getFilesForExecution();

	protected abstract String[] getFilesForExport();

	/**
	 * The 5 ProcessingStep subclasses AlignmentPS, CompletionPS, AnalysisPS,
	 * ResolutionPS, and IntegrationPS should return their corresponding Entrypoint.
	 * 
	 * @return an Entrypoint
	 */
	public abstract Entrypoint getEntrypoint();

	/**
	 * An implementation should return a Workflow which triggers its import,
	 * execution and export methods. In most cases you can use a
	 * {@link DefaultWorkflow}.
	 * 
	 * @return a Workflow that can be started by workflow.start()
	 */
	public abstract Workflow getWorkflow();

	/**
	 * This is the main part an implementation must complete. In this method the
	 * corresponding projects which are connected by this framework should be
	 * executed by scriptcalls or other running mechanisms.
	 * 
	 * @return OKResult or NotOKResult
	 */
	public abstract Result execute();

	/**
	 * For each NecessaryFile of getFilesForExport() copy it to the TempSharedFolder
	 * of the framework and verify the success of the copy operation.
	 * 
	 * @return OKResult or NotOKResult
	 */
	public Result importInputData() {
		Result result = new OKResult(this.getPSName() + ": Start importInputData()");
		for (String id : this.getFilesForImport()) {
			NecessaryFile file = this.registry.getFileForID(id);
			result = (result.isOK()) ? result.concatWithSuccessor(this.importFileFromSharedFolder(file)) : result;
			result = (result.isOK()) ? result.concatWithSuccessor(doesFileExist(file)) : result;
		}
		return result;
	}

	/**
	 * For each NecessaryFile of getFilesForExecution() check if it exists.
	 * 
	 * @return OKResult or NotOKResult
	 */
	public Result verifyPresenceOfExecutionData() {
		Result result = new OKResult(this.getPSName() + ": Start verifyPresenceOfExecutionData()");
		for (String id : this.getFilesForExecution()) {
			NecessaryFile file = this.registry.getFileForID(id);
			result = (result.isOK()) ? result.concatWithSuccessor(doesFileExist(file)) : result;
		}
		return result;
	}

	/**
	 * For each NecessaryFile of getFilesForExport() check if it exists and after
	 * that copy it to the TempSharedFolder of the framework.
	 * 
	 * @return OKResult or NotOKResult
	 */
	public Result exportOutputData() {
		Result result = new OKResult(this.getPSName() + ": Start exportOutputData()");
		for (String id : this.getFilesForExport()) {
			NecessaryFile file = this.registry.getFileForID(id);
			result = (result.isOK()) ? result.concatWithSuccessor(doesFileExist(file)) : result;
			result = (result.isOK()) ? result.concatWithSuccessor(this.exportFileToSharedFolder(file)) : result;
		}
		return result;
	}

	/**
	 * Copies the file specified by the filename of the param NecessaryFile from the
	 * TempSharedFolder of the framework to the path of the param NecessaryFile.
	 * 
	 * @param file the NecessaryFile
	 * @return an OKResult
	 */
	protected Result importFileFromSharedFolder(NecessaryFile file) {
		file.copyFromFolder(this.registry.getFileForID(Registry.TEMP_SHARED_FOLDER));
		return new OKResult(this.getPSName() + ": Import file: " + file.toString());
	}

	/**
	 * Copies the specified NecessaryFile from its path to the TempSharedFolder of
	 * the framework.
	 * 
	 * @param file the NecessaryFile
	 * @return an OKResult
	 */
	protected Result exportFileToSharedFolder(NecessaryFile file) {
		file.copyToFolder(this.registry.getFileForID(Registry.TEMP_SHARED_FOLDER));
		return new OKResult(this.getPSName() + ": Export file: " + file.toString());
	}

	/**
	 * Returns the official Name of the ProcessingStep for Logging.
	 * 
	 * @return the class name
	 */
	protected String getPSName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * Checks whether a file exists or not and creates a message according to this
	 * check for the logging result.
	 * 
	 * @param file The NecessaryFile obtained by the registry.
	 * @return OKResult if exists or NotOKResult if not.
	 */
	private Result doesFileExist(NecessaryFile file) {
		try {
			if (!file.doesExist()) {
				return new NotOKResult(this.getPSName() + ": File not found: " + file.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new NotOKResult(this.getPSName() + ": File not found: " + file.toString() + "\n" + e.getMessage());
		}
		return new OKResult(this.getPSName() + ": Check file: " + file.toString());
	}

	/**
	 * Concats all necessaryIDs of this ProcessingStep. getFilesForImport() +
	 * getFilesForExecution() + getFilesForExport() + Registry.TEMP_SHARED_FOLDER
	 * + Registry.RESULT_SHARED_FOLDER
	 * 
	 * @return an ordered Array with all IDs named above
	 */
	protected String[] getNecessaryIDs() {
		String[] sharedFolders = { Registry.TEMP_SHARED_FOLDER, Registry.RESULT_SHARED_FOLDER };
		return Stream.concat(
				Stream.concat(Stream.concat(Arrays.stream(getFilesForImport()), Arrays.stream(getFilesForExecution())),
						Arrays.stream(getFilesForExport())),
				Arrays.stream(sharedFolders)).toArray(String[]::new);
	}
}
