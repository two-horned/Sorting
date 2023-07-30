package sort.heap;

import sort.Sort;

/*
 * Heap sort is a stable sorting algorithm
 * that has always the time complexity of O(n log n).
 */
public final class HeapSort<T extends Comparable<T>> extends Sort<T> {
	private void heapify(final T[] input, final int i, final int e) {
		int l = i * 2 + 1;
		int r = l + 1;
		int m = i;
		
		if(l < e && input[m].compareTo(input[l]) < 0)
			m = l;
		if(r < e && input[m].compareTo(input[r]) < 0)
			m = r;
		
		if(m != i) {
			swap(input, i, m);
			heapify(input, m, e);
		}
	}
	
	private void heapify(final T[] input, final int i) {
		heapify(input, i, input.length);
	}
	
	private void build(final T[] input) {
		int i = input.length / 2 - 1;
		while(-1 < i)
			heapify(input, i--);
	}
	
	@Override
	public void sort(final T[] input) {
		if(input == null || input.length < 2)
			return;
		build(input);
		int e = input.length - 1;
		while(0 < e) {
			swap(input, 0, e);
			heapify(input, 0, e--);
		}
	}
}
