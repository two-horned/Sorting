/*
 * LICENSE: Said Kadrioski <said@kadrioski.de>
 */

package sort.hybrid;

/*
 * A hybrid form of Dynamic Said sort using binary
 * insertion sort to create first set of runs and
 * Static Said sort to create second set of runs.
 */

import sort.helper.Merger;
import sort.helper.Queue;

import sort.Sort;

public final class UltraSort<T extends Comparable<T>> extends Sort<T> {
	private final int MIN_MIN_RUN = 32;
	private final int MIN_RUN = 2048;
	
	private final Merger<T> m = new Merger<>();
	private T[] buffer1;
	private T[] buffer2;
	private Queue<Integer> queue;
	private int added;
	private int size;

	private final void swapBuffers() {
		T[] temp = buffer1;
		buffer1 = buffer2;
		buffer2 = temp;
	}

	private final int advance(int from) {
		while (0 < added) {
			if (buffer1[from + 1].compareTo(buffer1[from]) < 0)
				break;
			from = queue.uncheckedRemove();
			--added;
		}
		return from;
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
	
	private final void preprepare() {
		T current;
		int border;
		
		int j = 0;
		int k;
		
		for (int i = MIN_MIN_RUN; i <= size; i += MIN_MIN_RUN) {

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
	
	private final void prepare() {
		preprepare();
		int l, c, r;

		for (int jump = MIN_MIN_RUN; jump <= MIN_RUN; jump = jump << 1) {
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
		
		for (int i = MIN_RUN - 1; i < size - 1; i += MIN_RUN) {
			queue.uncheckedAdd(i);
			++added;
		}
	}

	private final void sort() {
		prepare();
		int nadd;
		int l, c, r;
		while (0 < added) {
			nadd = 0;
			l = 0;
			while (0 < added) {
				--added;
				c = queue.uncheckedRemove();
				c = advance(c);

				if (0 < added) {
					r = queue.uncheckedRemove();
					--added;
					r = advance(r);
					queue.uncheckedAdd(r);
					++nadd;
				} else
					r = size - 1;

				m.mergeSS(buffer1, buffer2, l, c, r);
				l = r + 1;
			}
			added += nadd;
			if (l < size)
				System.arraycopy(buffer1, l, buffer2, l, size - l);
			swapBuffers();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void sort(final T[] input) {
		if (input == null || input.length == 0)
			return;
		size = input.length;
		
		buffer1 = input;
		buffer2 = (T[]) new Comparable[size];

		queue = new Queue<>(size / MIN_RUN + MIN_RUN-1);
		sort();

		if (buffer1 != input) {
			System.arraycopy(buffer1, 0, buffer2, 0, size);
		}
	}
}