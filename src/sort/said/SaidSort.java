/*
 * LICENSE: Said Kadrioski <said@kadrioski.de>
 */

package sort.said;
import sort.Sort;
/*
 * This stable sorting algorithm has been developed by myself.
 * It's ultra fast and runs in O(n log n) time and O(n) best time
 * and with a much smaller amount of needed swaps compared to all other known 
 * non-hybrid sorting algorithms with similar amount of time.
 */

public final class SaidSort<T extends Comparable<T>> extends Sort<T> {
	@SuppressWarnings("rawtypes")
	private Comparable[] buffer1;
	@SuppressWarnings("rawtypes")
	private Comparable[] buffer2;
	boolean flag;
	
	@SuppressWarnings("unchecked")
	private void merge(
			final int left,
			final int center,
			final int right
			) {
		int l = left;
		int r = center + 1;
		int i = left;
		while(i<right+1) {
			if(buffer1[r].compareTo(buffer1[l]) < 0) {
				buffer2[i] = buffer1[r++];
				flag = false;
			}
			else
				buffer2[i] = buffer1[l++];
			i++;
			
			if(right<r) {
				while(l<center+1)
					buffer2[i++] = buffer1[l++];
				break;
			}
			if(center<l) {
				while(r<right+1)
					buffer2[i++] = buffer1[r++];
				break;
			}
		}
	}
	
	private void swapBuffers() {
		@SuppressWarnings("rawtypes")
		Comparable[] temp = buffer1;
		buffer1 = buffer2;
		buffer2 = temp;
	}
	
	private void sort() {
		final int max = buffer1.length*2-1;
		flag = true;
		int l,c,r;
		for(int i=2; i<max; i*=2) {
			l = 0;
			r = i-1;
			c = r / 2;
			while(r < buffer1.length) {
				merge(l, c, r);
				l += i;
				r += i;
				c += i;
			}
			if (c < buffer1.length-1) {
				r = buffer1.length-1;
				merge(l, c, r);
			} else
				while(l < buffer1.length)
					buffer2[l] = buffer1[l++];
			if(flag)
				return;
			swapBuffers();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T[] loudSort(final T[] input) {
		if(input == null)
			return input;
		buffer1 = input;
		buffer2 = new Comparable[input.length];
		sort();
		return (T[]) buffer1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void sort(final T[] input) {
		if(input == null)
			return;
		buffer1 = input;
		buffer2 = new Comparable[input.length];
		sort();
		if (buffer1 != input)
			for(int i=0;i<input.length;++i)
				input[i] = (T) buffer1[i];
	}
}
