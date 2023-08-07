package edu.kit.kastel.sdq.analysiscouplingframework.processing;

public enum Entrypoint {
	ALIGNMENT {
		@Override
		public Entrypoint getNext() {
			return COMPLETION;
		}

		@Override
		public boolean hasNext() {
			return true;
		}
	},
	COMPLETION {
		@Override
		public Entrypoint getNext() {
			return ANALYSIS;
		}

		@Override
		public boolean hasNext() {
			return true;
		}
	},
	ANALYSIS {
		@Override
		public Entrypoint getNext() {
			return RESOLUTION;
		}

		@Override
		public boolean hasNext() {
			return true;
		}
	},
	RESOLUTION {
		@Override
		public Entrypoint getNext() {
			return INTEGRATION;
		}

		@Override
		public boolean hasNext() {
			return true;
		}
	},
	INTEGRATION {
		@Override
		public Entrypoint getNext() {
			return null;
		}

		@Override
		public boolean hasNext() {
			return false;
		}
	};
	
	public abstract Entrypoint getNext();
	
	public abstract boolean hasNext();
	
	public static Entrypoint getFirst() {
		return ALIGNMENT;
	}
}
