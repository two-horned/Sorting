package sort.quick;

import sort.Sort;
import java.util.Random;

/*
 * QuickSort is an unstable sorting algorithm.
 * It's time complexity is O(n log n) on average and in best case and O(n^2) in the worst case.
 */

public final class QuickSort<T extends Comparable<T>> extends Sort<T> {	
	private Random random;
	
	private void sort(final T[] input, final int right, final int left) {
		final int size = left - right + 1;
		if(size < 2) {
			return;
		}
		if (size < 3) {
			if (input[left].compareTo(input[right]) < 0)
				swap(input, right, left);
			return;
		}
		
		
		// Choose random pivot.
		swap(input, right, random.nextInt(size) + right);
		
		// Partitioning.
		final T pivot = input[right];
		int r = right+1;
		int l = left;
		while(r < l + 1) {
			if (input[r].compareTo(pivot) <= 0)
				r++;
			else if (input[l].compareTo(pivot) > 0)
				l--;
			else
				swap(input, r++, l);
		}
		swap(input, right, l);
		sort(input, right, l-1);
		sort(input, r, left);
	}
	
	@Override
	public void sort(final T[] input) {
		if (input == null)
			return;
		random = new Random();
		sort(input, 0, input.length -1);
	}
}
