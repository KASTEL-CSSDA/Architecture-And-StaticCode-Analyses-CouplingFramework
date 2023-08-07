package edu.kit.kastel.sdq.analysiscouplingframework.parser;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;

public class IDVerifier {
	String[] necessaryIDs;
	public IDVerifier(String [] necessaryIDs) {
		this.necessaryIDs = necessaryIDs;
	}
	
	public void verifyPresenceOfAllNecessaryIDs(Registry registry) throws MissingPathIdentifierException {
		for(String id : this.necessaryIDs) {
			if (!registry.containsID(id)) {
				throw new MissingPathIdentifierException(id);
			}
		}
	}
}
