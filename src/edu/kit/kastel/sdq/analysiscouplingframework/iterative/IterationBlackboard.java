package edu.kit.kastel.sdq.analysiscouplingframework.iterative;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.kastel.sdq.partitioner.Partitioner;

public class IterationBlackboard {

	private Map<String, Partitioner> partitionersMapped;
	private List<Partitioner> partitionersOrdered;

	public IterationBlackboard() {
	}

	/**
	 * Increments the first Partitioner in the List, which should be the most inner
	 * loop. If this Partitioner.hasNext() it is incremented and no other. Else, it
	 * is reset and the next outer Partitioner is incremented. The third Partitioner
	 * is only incremented if the second resets after a full loop, and so on. If the
	 * most outer Partitioner reaches the point where hasNext() returns false, this
	 * shows that all iterable partitions are visited once and the process would
	 * restart from the beginning.
	 * 
	 * @return true while not all partitions have been visited.
	 */
	public boolean incrementPartitioners() {

		boolean incrementNext = true;

		for (int i = 0; i < this.partitionersOrdered.size(); i++) {
			if (incrementNext) {
				incrementNext = !this.partitionersOrdered.get(i).setToNextPartitionOrElseReset();
			} else {
				break;
			}
		}
		// If the most outer partitioner has reached its last partition
		// and sets incrementNext to true,
		// it is the only way the loop terminates with incrementNext == true.
		// In this case the negated value indicates the end with return false.

		return !incrementNext;
	}

	/**
	 * The first Element in the List should be the one which is incremented until
	 * all partitions of it are visited once. Only after one full circle, the second
	 * Partitioner will be incremented and so on.
	 */
	public void initWithOrderedPartitioners(List<Partitioner> partitioners) {
		this.partitionersOrdered = partitioners;
		this.partitionersMapped = new HashMap<String, Partitioner>();
		this.partitionersOrdered.forEach(p -> this.partitionersMapped.put(p.getId(), p));
	}
	
	public List<Partitioner> getOrderedPartitioners() {
		return this.partitionersOrdered;
	}

	public Partitioner getPartitionerByID(String ID) {
		return this.partitionersMapped.get(ID);
	}
}
