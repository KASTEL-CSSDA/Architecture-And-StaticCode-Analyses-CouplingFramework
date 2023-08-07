package edu.kit.kastel.sdq.analysiscouplingframework.exceptions;

public class MissingPathIdentifierException extends Exception {
	String missingPathIdentifier;
	
	public MissingPathIdentifierException(String missingPathIdentifier) {
		this.missingPathIdentifier = missingPathIdentifier;
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("The following Path-ID was not specified: " + this.missingPathIdentifier);
		super.printStackTrace();
	}

	private static final long serialVersionUID = 1L;

}
