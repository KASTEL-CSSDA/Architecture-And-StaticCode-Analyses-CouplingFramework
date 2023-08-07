package edu.kit.kastel.sdq.analysiscouplingframework.results;

/**
 * 
 * @author Jonas Lehmann
 */
public abstract class Result {
	String message;

	public Result(String message) {
		this.message = message;
	}

	public Result(String message, Result predecessor) {
		this(predecessor.getMessage() + "\n" + message);
	}

	public String getMessage() {
		return this.message;
	}

	public abstract boolean isOK();

	public abstract Result concatWithPredecessor(Result other);

	public abstract Result concatWithSuccessor(Result other);

	public Result tryToHandle() {
		return this;
	}
}
