package edu.kit.kastel.sdq.analysiscouplingframework.iterative.terminationcondition;

public class ModelChangeObserver {
	boolean modelHasChanged;

	public ModelChangeObserver() {
		this.modelHasChanged = false;
	}

	public void update(boolean modelHasChanged) {
		this.modelHasChanged = modelHasChanged;
	}
	
	public boolean hasModelChanged() {
		return modelHasChanged;
	}

	public void reset() {
		this.modelHasChanged = false;
	}
}
