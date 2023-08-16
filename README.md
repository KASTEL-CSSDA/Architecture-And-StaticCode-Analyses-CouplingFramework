# Architecture-And-StaticCode-Analyses-CouplingFramework

Provides a framework for executing a static analysis in 5 Steps: ALIGNMENT, COMPLETION, ANALYSIS, RESOLUTION, and INTEGRATION in this prescribed order.

![Framework_overview](https://github.com/KASTEL-CSSDA/Architecture-And-StaticCode-Analyses-CouplingFramework/assets/77493291/1e098b66-ce96-4076-8b77-bcc1f9517267)

## Project Structure

- [src/edu/kit/kastel/sdq/analysiscouplingframework](https://github.com/KASTEL-CSSDA/Architecture-And-StaticCode-Analyses-CouplingFramework/tree/main/src/edu/kit/kastel/sdq/analysiscouplingframework): Contains the abstract framework.
- [Framework Instances](https://github.com/KASTEL-CSSDA/Architecture-And-StaticCode-Analyses-CouplingFramework/tree/main/Framework%20Instances): Directory for Instances of the framework. Each for different analyses, developed at specific branches.

## Installation

The Project uses Java Compiler compliance level 17.
Import the Main Project and all Framework Instance Projects into your Eclipse Workspace.
For each instance implementation take a look at their READMEs to further install all necessary projects they depend on, to run that specific instance.

## Implementing the Framework

As shown in the bottom half of the grafic, you have to implement several classes of the abstract framework.

- Runner: Necessary to run your Instance by injecting your ConcreteConfiguration into the Framework.
- Configuration: Override createStrategy() to return your own strategy implementation.
- Processingstrategy: Pass your own implemented 5 ProcessingSteps to super() in the constructor.
- 5 ProcessingSteps: For each PS override the execute() method with a scriptcall which triggers the execution of the external project belonging to this Step. Furthermore getWorkflow() has to return a Workflow e.g. return new DefaultWorkflow(this);. Lastly you have to override getFilesForImport(), getFilesForExecution(), and getFilesForExport(), so they return the IDs of files needed in this PS.
- CONFIG.xml: This file contains ID → Path mappings for the necessary files, directories or scripts. It also contains an entrypoint and a UUID.

For further information take a look at the above mentioned elements in the linked [ExampleImplementation](https://github.com/KASTEL-CSSDA/Architecture-And-StaticCode-Analyses-CouplingFramework/tree/solidityexample/Framework%20Instances/SolidityExample/src/edu/kit/kastel/sdq/analysiscouplingframework/solidityexample).

## Using the Framework

To run a specific Framework instance that is already implemented, just rightclick the instances' Runner and select 'Run As > Java Application'.
If one of the 5 ProcessingSteps detects an exception it returns a NotOKResult and the execution of the framework is stopped. The last successful Entrypoint is written back to the file CONFIG.xml. During next execution the framework starts directly at this point instead of the first ProcessingStep.
