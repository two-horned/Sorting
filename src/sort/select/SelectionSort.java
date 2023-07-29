package sort.select;

import sort.Sort;

/*
 * Selection Sort is an unstable sorting algorithm.
 * It's time complexity is O(n^2).
 */

public final class SelectionSort<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(final T[] input) {
		if (input == null || input.length < 2)
			return;
		
		int current;
		
		for (int i=0; i<input.length; i++) {
			current = i;
			
			for(int j=i+1; j<input.length; j++)
				if(input[j].compareTo(input[current]) < 0)
					current = j;
			
			if (current != i) 
				swap(input, i, current);
		}
	}
}
