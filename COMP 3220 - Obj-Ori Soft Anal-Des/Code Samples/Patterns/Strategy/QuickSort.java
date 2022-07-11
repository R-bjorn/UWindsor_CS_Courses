package StrategyPattern;

public class QuickSort implements SortStrategy{

	private void quickSort(double[] a, int left, int right) {
		if (right <= left) return;
		int i = partition(a, left, right);
		quickSort(a, left, i-1);
		quickSort(a, i+1, right);
	} 
	
	private int partition(double[] a, int left, int right) {
		int i = left;
		int j = right;
		while(true) {
			while(a[i] < a[right]) i++;
			while(less(a[right], a[--j])) if (j==left) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, i, right);
		return i;
	}

	private boolean less(double x, double y) {
		return (x < y);
	}
	
	private void exch(double[] a, int i, int j) {
		double swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	@Override
	public void sort(double[] a) {
		quickSort(a, 0, a.length - 1);
	}
}
