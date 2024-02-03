package sort.helper;

public final class Queue<T> {
	private final T[] me;
	private int start;
	private int end;

	@SuppressWarnings("unchecked")
	public Queue(final int size) {
		me = (T[]) new Object[size];
		start = 0;
		end = 0;
	}

	public final boolean add(final T input) {
		if ((end + 1) % me.length != start) {
			uncheckedAdd(input);
			return true;
		}
		return false;
	}

	public final void uncheckedAdd(final T input) {
		me[end] = input;
		++end;
		end %= me.length;
	}

	public final T remove() {
		if (start == end)
			return null;
		return uncheckedRemove();
	}

	public final T uncheckedRemove() {
		T temp = me[start];
		++start;
		start %= me.length;
		return temp;
	}

	public final T head() {
		if (isEmpty())
			return null;
		return me[start];
	}

	public final boolean isEmpty() {
		return start == end;
	}
}