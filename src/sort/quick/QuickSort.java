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
		
		// Necessarry because of high chance of stack overflow.
		final int center = (right+left) / 2;
		final T lp = input[left];
		final T rp = input[right];
		final T cp = input[center];
		if(rp.compareTo(cp) < 0) {
			if(rp.compareTo(lp) < 0) {
				if(lp.compareTo(cp) < 0)
					swap(input, right, left);
				else
					swap(input, right, center);
			}
		} else if (lp.compareTo(rp) < 0) {
			if(lp.compareTo(cp) < 0)
				swap(input, right, center);
			else
				swap(input, right, left);
		}
		
		// Partitioning.
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
		
		if(pivot.compareTo(input[r]) < 0)
			r--;
		
		swap(input, right, r);
		sort(input, right, r-1);
		sort(input, r, left);
	}
	
	@Override
	public void sort(final T[] input) {
		if (input == null || input.length < 2)
			return;
		
		sort(input, 0, input.length -1);
	}
}
