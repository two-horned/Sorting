/*
 * LICENSE: Said Kadrioski <said@kadrioski.de>
 */

package sort.hybrid;

/*
 * A hybrid form of Static Said sort using binary
 * insertion sort to create first set of runs.
 */

import sort.Sort;
import sort.helper.Merger;

public final class HyperSort<T extends Comparable<T>> extends Sort<T> {
	private final int MIN_RUN = 16;
	private final Merger<T> m = new Merger<>();
	private T[] buffer1;
	private T[] buffer2;

	int size;

	private final void swapBuffers() {
		T[] temp = buffer1;
		buffer1 = buffer2;
		buffer2 = temp;
	}
	
	private final int find(
			final T[] input,
			int lower,
			int upper,
			final T find
			) {
		int center;
		while (lower < upper) {
			center = (upper - lower) >> 1;
			center += lower;
			if (find.compareTo(input[center]) < 0)
				upper = center;
			else
				lower = center + 1;
		}
		if (find.compareTo(input[upper]) < 0)
			return upper;
		return upper + 1;
	}

	private final void prepare() {
		T current;
		int border;
		
		int j = 0;
		int k;
		
		for (int i = MIN_RUN; i <= size; i += MIN_RUN) {

			k = j++;
			while(j < i) {
				current = buffer1[j];
				border = find(buffer1, k, j - 1, current);
				System.arraycopy(buffer1, border, buffer1, border + 1, j - border);
				buffer1[border] = current;
				++j;
			}
		}
		if (j < size) {

			k = j++;
			while(j < size) {
				current = buffer1[j];
				border = find(buffer1, k, j - 1, current);			
				System.arraycopy(buffer1, border, buffer1, border + 1, j - border);
				buffer1[border] = current;
				++j;
			}
		}
	}

	private final void sort() {
		prepare();
		final int max = size - 1;
		int l, c, r;

		for (int jump = MIN_RUN; jump < max; jump = jump << 1) {
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