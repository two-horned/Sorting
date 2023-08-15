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
	Merger<T> merger = new Merger<>();
	private T[] buffer1;
	private T[] buffer2;
	private Queue<Integer> queue;

	private final void swapBuffers() {
		T[] temp = buffer1;
		buffer1 = buffer2;
		buffer2 = temp;
	}

	private final void prepare() {
		int mode;
		int l, c, r;
		l = 0;
		c = 0;
		while (++c < buffer1.length) {
			mode = 0;
			if (buffer1[c].compareTo(buffer1[c - 1]) < 0) {
				c = merger.modeB(buffer1, c);
				++mode;
				if (buffer1.length - 2 < c) {
					merger.reverse(buffer1, buffer2, l, c);
					return;
				}
			} else {
				c = merger.modeS(buffer1, c);
				if (buffer1.length - 2 < c)
					return;
			}

			r = c + 1;
			if (r < buffer1.length - 1) {
				if (buffer1[r + 1].compareTo(buffer1[r]) < 0) {
					r = merger.modeB(buffer1, r);
					mode += 2;
				} else
					r = merger.modeS(buffer1, r);
			}
			if (r < buffer1.length - 1)
				queue.uncheckedAdd(r);
			merger.dynMerge(buffer1, buffer2, mode, l, c, r);
			l = r + 1;
			c = l;
		}
	}

	private final void sort() {
		prepare();
		int l, c, r;
		while (!queue.isEmpty()) {
			swapBuffers();
			l = 0;
			while (!queue.isEmpty() && l < queue.head()) {
				c = queue.uncheckedRemove();
				while (!queue.isEmpty() && c < queue.head()) {
					if (buffer1[c + 1].compareTo(buffer1[c]) < 0)
						break;
					c = queue.uncheckedRemove();
				}

				if (!queue.isEmpty() && c < queue.head()) {
					r = queue.uncheckedRemove();
					queue.uncheckedAdd(r);
				} else
					r = buffer1.length - 1;
				
				merger.mergeSS(buffer1, buffer2, l, c, r);
				l = r + 1;
			}
			while(l < buffer1.length)
				buffer2[l] = buffer1[l++];

		}
	}

	@Override
	public final void sort(final T[] input) {
		if (input == null)
			return;
		//System.out.println(java.util.Arrays.toString(input));
		buffer1 = input.clone();
		buffer2 = input;

		queue = new Queue<>(buffer1.length / 4 + 1);

		sort();
		
		if(buffer2 != input)
			for(int i=0; i<input.length;++i)
					input[i] = buffer2[i];
	}
}
