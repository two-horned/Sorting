package sort.insert;

import sort.Sort;

/*
 * Variation of Insertion Sort which uses Binary Search,
 * to find where the next element needs to be inserted.
 * It's time complexity is O(n^2).
 */

public final class BinaryInsertionSort<T extends Comparable<T>> extends Sort<T> {
	private final int find(
			final T[] input,
			int upper,
			final T find
			) {
		int lower = 0;
		int center;
		while (lower < upper) {
			center = (upper - lower) >> 1;
			center += lower;
			if (find.compareTo(input[center]) < 0)
				upper = center;
			else
				lower = center + 1;
		}
		if (find.compareTo(input[upper]) < 0)
			return upper;
		return upper + 1;
	}
	
	@Override
	public void sort(final T[] input) {
		if (input == null)
			return;
		
		T current;
		int border;
		
		for(int i = 1; i < input.length; i++) {
			current = input[i];
			border = find(input, i - 1, current);			
			System.arraycopy(input, border, input, border + 1, i - border);
			input[border] = current;
		}
	}
}