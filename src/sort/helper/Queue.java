package sort.helper;

import java.util.Arrays;

public final class Queue<T> {
	private final Object[] me;
	private int start;
	private int end;

	public Queue(final int size) {
		me = new Object[size];
		start = 0;
		end = 0;
	}

	public final boolean add(final T input) {
		if ((end + 1) % me.length != start) {
			me[end] = input;
			++end;
			end %= me.length;
			return true;
		}
		return false;
	}

	public final void uncheckedAdd(final T input) {
		me[end] = input;
		++end;
		end %= me.length;
	}

	@SuppressWarnings("unchecked")
	public final T remove() {
		if (start == end)
			return null;
		T temp = (T) me[start];
		++start;
		start %= me.length;
		return temp;
	}

	@SuppressWarnings("unchecked")
	public final T uncheckedRemove() {
		T temp = (T) me[start];
		++start;
		start %= me.length;
		return temp;
	}

	@SuppressWarnings("unchecked")
	public final T head() {
		if (isEmpty())
			return null;
		return (T) me[start];
	}

	public final boolean isEmpty() {
		return start == end;
	}

	public void print() {
		System.out.println(Arrays.toString(me));
	}
}