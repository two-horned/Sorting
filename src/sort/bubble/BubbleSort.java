package sort.bubble;

import sort.Sort;

/*
 * BubbleSort is a stable sorting algorithm.
 * It't time complexity is O(n) in best case and O(n^2) on average and worst case. 
 */

public final class BubbleSort<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(final T[] input) {
		if(input==null)
			return;
		
		boolean flag;
		for(int i=0; i<input.length;i++) {
			flag = true;
			for(int j=0; j<input.length-i-1;j++) {
				if(input[j+1].compareTo(input[j]) < 0) {
					swap(input, j, j+1);
					flag = false;
				}
			}
			if (flag)
				return;
		}	
	}
}
