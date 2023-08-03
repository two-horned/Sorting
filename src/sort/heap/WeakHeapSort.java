package sort.heap;

import sort.Sort;

/*
 * WeakHeap Sort is a vartiation of Heap Sort,
 * where we use Weak Heaps instead of Heaps.
 */
public final class WeakHeapSort<T extends Comparable<T>> extends Sort<T> {
	
	private void weakHeapify(final T[] input, final int i, final int e) {
		int m = i;
		
		for(int r =i*2+1;r<e;r*=2)
			if(input[m].compareTo(input[r]) <= 0)
				m = r;
		
		if(m != i) {
			swap(input, i, m);
			weakHeapify(input, m, e);
		}
	}
	
	private void weakHeapify(final T[] input, final int i) {
		weakHeapify(input, i, input.length);
	}
	
	private void build(final T[] input) {
		int i = input.length / 2;
		while(-1 < i)
			weakHeapify(input, i--);
	}
	
	@Override
	public void sort(final T[] input) {
		if(input == null || input.length < 2)
			return;
		build(input);
		int e = input.length - 1;
		while(0 < e) {
			swap(input, 0, e);
			weakHeapify(input, 0, e--);
		}
	}
}