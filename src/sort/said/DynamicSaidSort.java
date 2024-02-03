/*
 * LICENSE: Said Kadrioski <said@kadrioski.de>
 */

package sort.said;

/*
 * A variation of SaidSort making use of dynamically
 * increasing window sizes while keeping track of their borders
 * via a queue to jump quickly between them.
 */

import sort.helper.Merger;
import sort.helper.Queue;
import sort.Sort;

public final class DynamicSaidSort<T extends Comparable<T>> extends Sort<T> {
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

	private final void prepare() {
		buffer2[size - 1] = buffer1[size - 1];
		added = 0;
		int mode;
		int l, c, r;
		l = 0;
		c = 1;
		while (c < size) {
			mode = 0;
			if (buffer1[c].compareTo(buffer1[c - 1]) < 0) {
				c = m.modeB(buffer1, c);
				if (c == size - 1) {
					m.reverse(buffer1, buffer2, l, c);
					break;
				}
				++mode;
			} else {
				c = m.modeS(buffer1, c);
				if (c == size - 1) {
					System.arraycopy(buffer1, l, buffer2, l, size - l);
					break;
				}
			}

			r = c + 1;
			if (r < size - 1) {
				if (buffer1[r + 1].compareTo(buffer1[r]) < 0) {
					mode += 2;
					r = m.modeB(buffer1, r);
				} else
					r = m.modeS(buffer1, r);
				if (r < size - 1) {
					queue.uncheckedAdd(r);
					++added;
				}
			}
			m.dynMerge(buffer1, buffer2, mode, l, c, r);
			l = r + 1;
			c = l + 1;
		}
		swapBuffers();
		return;
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

		queue = new Queue<>(size / 4 + 3);

		sort();
		if (buffer1 != input) {
			System.arraycopy(buffer1, 0, buffer2, 0, size);
		}
	}
}