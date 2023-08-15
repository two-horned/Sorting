/*
 * LICENSE: Said Kadrioski <said@kadrioski.de>
 */

package sort.said;

import sort.Sort;
import sort.helper.Merger;

/*
 * A variation of SaidSort making use of dynamically
 * increasing window sizes.
 */

public final class ProtoDynamicSaidSort<T extends Comparable<T>> extends Sort<T> {
	Merger<T> merger = new Merger<>();
	private T[] buffer1;
	private T[] buffer2;

	private void swapBuffers() {
		T[] temp = buffer1;
		buffer1 = buffer2;
		buffer2 = temp;
	}

	private final int prepare() {
		int skip = 0;
		int mode;
		int l, c, r;
		l = 0;
		c = 0;
		skip = -1;
		while (++c < buffer1.length) {
			mode = 0;
			if (buffer1[c].compareTo(buffer1[c - 1]) < 0) {
				c = merger.modeB(buffer1, c);
				++mode;

				if (c == buffer1.length - 1) {
					merger.reverse(buffer1, buffer2, l, c);
					break;
				}
			} else {
				c = merger.modeS(buffer1, c);
				if (c == buffer1.length - 1)
					break;
			}

			r = c + 1;
			if (r < buffer1.length - 1) {
				if (buffer1[++r].compareTo(buffer1[r - 1]) < 0) {
					r = merger.modeB(buffer1, r);
					mode += 2;
				} else
					r = merger.modeS(buffer1, r);
			}

			merger.dynMerge(buffer1, buffer2, mode, l, c, r);

			if (skip < 0)
				skip = r - 1;
			l = r + 1;
			c = l;
		}
		return skip;
	}

	private final void sort() {
		int skip = prepare();
		int l, c, r;
		while (-1 < skip) {
			swapBuffers();
			if (skip == buffer1.length - 2)
				return;
			l = 0;
			c = skip;
			skip = -1;
			while (++c < buffer1.length) {
				c = merger.modeS(buffer1,c);
				if (c == buffer1.length - 1) {
					if (l < 1)
						return;
					break;
				}

				r = merger.modeS(buffer1, c+1);
				merger.mergeSS(buffer1, buffer2, l,c,r);

				if (skip < 0)
					skip = r - 1;
				l = r + 1;
				c = l;
			}
		}
	}

	@Override
	public final void sort(final T[] input) {
		if (input == null)
			return;
		buffer1 = input;
		buffer2 = input.clone();

		sort();
		if (buffer1 != input) {
			for (int i = 0; i < input.length; ++i)
				input[i] = buffer1[i];
		}
	}
}
