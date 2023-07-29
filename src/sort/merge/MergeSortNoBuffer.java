package sort.merge;
/*
 * Without using a buffer Merge Sort sucks.
 * The time complexity is O(n^2 log n)
 * due to swapping alot of elements in the merging process.
 */

import sort.Sort;

public final class MergeSortNoBuffer<T extends Comparable<T>> extends Sort<T> {
	private void merge(
			final T[] input,
			final int right,
			final int left
			) {

		T current;
		int r = right;
		int c = (right+left) / 2 + 1;
		int i;
		while(r<c & c<=left) {
			if(input[c].compareTo(input[r]) < 0) {
				current = input[c];
				i = c++;
				while(r<i)
					input[i--] = input[i];
				input[r++] = current;
			} else
				r++;
		}

	}
	
	private void sort(
			final T[] input,
			final int right,
			final int left
			) {
		final int center = (right + left) / 2;
		if(center==left)
			return;
		
		if(center==right) {
			if(input[left].compareTo(input[right]) < 0)
				swap(input, left, right);
			return;
		}
		
		sort(input, right, center);
		sort(input, center+1, left);
		merge(input, right, left);
	}
	
	@Override
	public void sort(final T[] input) {
		if(input == null || input.length < 2)
			return;
		
		sort(input, 0, input.length-1);
	}
}
