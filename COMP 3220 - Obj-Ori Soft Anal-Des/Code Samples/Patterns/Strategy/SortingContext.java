package StrategyPattern;

public class SortingContext {
	private SortStrategy sorter = null; 
	// This will point to the
	// object that includes
	// the sorting algorithm
	
	public void sortDouble(double[] list) { 
		// this is what the
		// client uses
		sorter.sort(list);
	}
	
	public SortStrategy getSorter() {
		return sorter;
	}
	
	// Before handling a client call this method will be used to define
	// which sorting algorithm to use
	public void setSorter(SortStrategy sorter) {
		this.sorter = sorter;
	}
}
