/*
 * LICENSE: Said Kadrioski <said@kadrioski.de>
 */

package sort.said;

/*
 * This stable sorting algorithm has been developed by myself.
 * It's ultra fast and runs in O(n log n) time and O(n) best time
 * and with a much smaller amount of needed swaps compared to all other known 
 * non-hybrid stable sorting algorithms.
 * 
 * When compared to quicksort the performance is slightly worse for
 * true randomized data and better when it's already slightly ordered.
 */

import sort.Sort;
import sort.helper.Merger;

public final class StaticSaidSort<T extends Comparable<T>> extends Sort<T> {
	Merger<T> m = new Merger<>();
	private T[] buffer1;
	private T[] buffer2;

	int size;

	private final void swapBuffers() {
		T[] temp = buffer1;
		buffer1 = buffer2;
		buffer2 = temp;
	}

	private final void prepare() {
		for (int i = 1; i < size; i += 2)
			if (buffer1[i].compareTo(buffer1[i - 1]) < 0)
				swap(buffer1, i, i - 1);
	}

	private final void sort() {
		prepare();
		final int max = size - 1;
		int l, c, r;

		for (int jump = 2; jump < max; jump = jump << 1) {
			l = 0;
			c = l + jump - 1;
			r = c + jump;
			while (r + jump + 1 < size) {
				m.mergeSS(buffer1, buffer2, l, c, r);
				l = r + 1;
				c = r + jump;
				r = c + jump;
			}
			
			if (size - 1 <= r)
				m.mergeSS(buffer1, buffer2, l, c, size - 1);
			else
				m.mergeSSS(buffer1, buffer2, l, c, r, size - 1);
			
			swapBuffers();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void sort(final T[] input) {
		if (input == null)
			return;

		size = input.length;

		buffer1 = input;
		buffer2 = (T[]) new Comparable[size];
		sort();

		if (buffer1 != input) {
			System.arraycopy(buffer1, 0, buffer2, 0, size);
		}
	}
}