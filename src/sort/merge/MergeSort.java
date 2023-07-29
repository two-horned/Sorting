package sort.merge;

import sort.Sort;

/*
 * Merge sort is a stable sorting algorithm.
 * It's time complexity is O(n log n).
 */
public final class MergeSort<T extends Comparable<T>> extends Sort<T> {
	@SuppressWarnings("unchecked")
	private void merge(
			final T[] input,
			final int right,
			final int left
			) {

		Object[] buffer = new Object[left-right+1];
		final int center = (right + left) / 2;
		int r = right;
		int l = center + 1;
		int i = 0;
		while(i<buffer.length) {
			if(input[l].compareTo(input[r]) < 0)
				buffer[i++] = input[l++];
			else
				buffer[i++] = input[r++];
			
			if(left<l) {
				while(r<=center)
					buffer[i++] = input[r++];
				break;
			}
			if(center<r) {
				while(l<=left)
					buffer[i++] = input[l++];
				break;
			}
		}
		
		i = 0;
		while(i<buffer.length)
			input[right+i] = (T) buffer[i++];
	}
	
	private void sort(
			final T[] input,
			final int right,
			final int left
			) {
		final int center = (right + left) / 2;
		
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
