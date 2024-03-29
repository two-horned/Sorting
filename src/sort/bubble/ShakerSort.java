package sort.bubble;

import sort.Sort;

/*
 * Shaker sort is a variation of BubbleSort with slightly
 * improved performance due to the order in which the swaps occure. 
 */

public final class ShakerSort<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(final T[] input) {
		if(input==null)
			return;
		
		boolean flag;
		int i;
		for(int j =0;j<input.length / 2;j++) {
			flag = true;
			i=j;
			while(i<input.length-j-1) {
				if(input[i++].compareTo(input[i]) > 0)
					swap(input, i, i-1);
				flag = false;
			}
			
			if (flag)
				return;
			flag = true;	
			i--;
			while(j<i)
				if(input[i--].compareTo(input[i]) < 0) {
					swap(input, i, i+1);		
					flag = false;
				}
			
			if (flag)
				return;			
		}
	}
}
