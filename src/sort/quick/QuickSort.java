package sort.quick;

import sort.Sort;

/*
 * QuickSort is an unstable sorting algorithm.
 * It's time complexity is O(n log n) on average and in best case and O(n^2) in the worst case.
 */

public final class QuickSort<T extends Comparable<T>> extends Sort<T> {

	private void sort(final T[] input, final int right, final int left) {
		if (left-right < 2) {
			if (input[left].compareTo(input[right]) < 0)
				swap(input, right, left);
			return;
		}
		
		final T pivot = input[right];
		int r = right+1;
		int l = left;
		
		while(r<l) {
			if (input[r].compareTo(pivot) <= 0)
				r++;
			else if (input[l].compareTo(pivot) > 0)
				l--;
			else
				swap(input, l, r);
		}
		
		if(input[r].compareTo(pivot) > 0)
			r--;
		
		swap(input, right, r);
		sort(input, right, r);
		sort(input, l, left);
	}
	
	@Override
	public void sort(final T[] input) {
		if (input == null || input.length < 2)
			return;
		
		sort(input, 0, input.length -1);
	}
}
