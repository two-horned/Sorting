package sort.insert;

import sort.Sort;

/*
 * Insertion Sort is a stable sorting algorithm.
 * It's time complexity is O(n^2).
 */

public final class InsertionSort<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(final T[] input) {
		if (input == null || input.length < 2)
			return;
		
		T current;
		int border;
		for(int i=1; i<input.length; i++) {
			current = input[i];
			border = i;
			
			while(0<border) {
				if(input[border-1].compareTo(current) <= 0)
					break;
				input[border] = input[--border];
			}
			
			input[border] = current;
		}
	}
}
