package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;

public class SolidityACEProcessingStrategy extends ProcessingStrategy {

	public SolidityACEProcessingStrategy(Registry registry) throws MissingPathIdentifierException {
		super(new AccessControlGeneratorPS(registry), new ManualCodeCompletionPS(registry),
				new SolcVerifyAndSlitherPS(registry), new SolidityRoleAdapterPS(registry),
				new MissingRoleRelationAdderPS(registry));
	}

}
