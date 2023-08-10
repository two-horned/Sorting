package sort.bubble;

import sort.Sort;

/*
 * GnomeSort is a variation of BubbleSort without using nested loops.
 * It't time complexity is O(n) in best case and O(n^2) on average and worst case. 
 */

public final class GnomeSort<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(final T[] input) {
		if(input==null)
			return;
		
		int p = 0;
		while(p < input.length) {
			if (p == 0)
				p++;
			else if (input[p].compareTo(input[p-1]) < 0)
				swap(input, p, --p);
			else
				p++;
		}
	}
}