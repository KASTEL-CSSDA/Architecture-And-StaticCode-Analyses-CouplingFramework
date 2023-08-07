package edu.kit.kastel.sdq.analysiscouplingframework.results;

public class NotOKResult extends Result {

	public NotOKResult(String message) {
		super(message);
	}
	
	protected NotOKResult(String message, Result predecessor) {
		super(message, predecessor);
	}

	@Override
	public boolean isOK() {
		return false;
	}

	@Override
	public Result concatWithPredecessor(Result other) {
		return new NotOKResult(this.getMessage(), other);
	}

	@Override
	public Result concatWithSuccessor(Result other) {
		return new NotOKResult(other.getMessage(), this);
	}

}
