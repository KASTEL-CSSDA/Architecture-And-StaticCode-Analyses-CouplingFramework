package edu.kit.kastel.sdq.analysiscouplingframework.solidityexample.iterative;

import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.iterative.IterationBlackboard;
import edu.kit.kastel.sdq.analysiscouplingframework.parser.Registry;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;
import edu.kit.kastel.sdq.analysiscouplingframework.solidityexample.ManualCodeCompletionPS;
import edu.kit.kastel.sdq.analysiscouplingframework.solidityexample.MissingRoleRelationAdderPS;
import edu.kit.kastel.sdq.analysiscouplingframework.solidityexample.SolcVerifyAndSlitherPS;
import edu.kit.kastel.sdq.analysiscouplingframework.solidityexample.SolidityRoleAdapterPS;

public class IterativeSolidityACEProcessingStrategy extends ProcessingStrategy {

	public IterativeSolidityACEProcessingStrategy(Registry registry, IterationBlackboard blackboard)
			throws MissingPathIdentifierException {
		super(new IterativeAccessControlGeneratorPS(registry, blackboard), new ManualCodeCompletionPS(registry),
				new SolcVerifyAndSlitherPS(registry), new SolidityRoleAdapterPS(registry),
				new MissingRoleRelationAdderPS(registry));
	}

}
