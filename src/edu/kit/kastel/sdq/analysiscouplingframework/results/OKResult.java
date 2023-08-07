package edu.kit.kastel.sdq.analysiscouplingframework.results;

public class OKResult extends Result {

	public OKResult(String message) {
		super(message);
	}

	protected OKResult(String message, OKResult predecessor) {
		super(message, predecessor);
	}

	@Override
	public boolean isOK() {
		return true;
	}

	@Override
	public Result concatWithPredecessor(Result other) {
		return (other.isOK()) ? new OKResult(this.getMessage(), (OKResult) other)
				: new NotOKResult(this.getMessage(), other);
	}

	@Override
	public Result concatWithSuccessor(Result other) {
		return (other.isOK()) ? new OKResult(other.getMessage(), this) : new NotOKResult(other.getMessage(), this);
	}

}
