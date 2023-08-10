package sort.merge;

import sort.Sort;

/*
 * Merge sort is a stable sorting algorithm.
 * It's time complexity is O(n log n).
 */

@SuppressWarnings("unchecked")
public final class SharedBufferMergeSort<T extends Comparable<T>> extends Sort<T> {
	private Object[] buffer;
	
	private void merge(
			final T[] input,
			final int right,
			final int left
			) {
		final int center = (right + left) / 2;
		int r = right;
		int l = center + 1;
		int i = right;
		while(i<left+1) {
			if(input[l].compareTo(input[r]) < 0)
				buffer[i] = input[l++];
			else
				buffer[i] = input[r++];
			i++;
			
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
		i = right;
		while(i<left+1)
			input[i] = (T) buffer[i++];
	}
	
	private void sort(
			final T[] input,
			final int right,
			final int left
			) {
		final int center = (right + left) / 2;
		
		if(center==left) {
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
		if(input == null)
			return;
		buffer = new Object[input.length];
		sort(input, 0, input.length-1);
	}
}
