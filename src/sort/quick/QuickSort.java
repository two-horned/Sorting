package sort.quick;

import sort.Sort;
import java.util.Random;

/*
 * QuickSort is an unstable sorting algorithm.
 * It's time complexity is O(n log n) on average and in best case and O(n^2) in the worst case.
 */

public final class QuickSort<T extends Comparable<T>> extends Sort<T> {	
	private Random random;
	
	private void sort(final T[] input, final int left, final int right) {
		final int size = right - left + 1;
		if(size < 2) {
			return;
		}
		if (size < 3) {
			if (input[right].compareTo(input[left]) < 0)
				swap(input, left, right);
			return;
		}
		
		
		// Choose random pivot.
		swap(input, left, random.nextInt(size) + left);
		//swap(input, left, (right+left)/2);
		
		// Partitioning.
		final T pivot = input[left];
		int l = left+1;
		int r = right;
		while(l < r + 1) {
			if (input[l].compareTo(pivot) <= 0)
				l++;
			else if (input[r].compareTo(pivot) > 0)
				r--;
			else
				swap(input, l++, r);
		}
		swap(input, left, r);
		sort(input, left, r-1);
		sort(input, l, right);
	}
	
	@Override
	public void sort(final T[] input) {
		if (input == null)
			return;
		random = new Random();
		sort(input, 0, input.length -1);
	}
}
