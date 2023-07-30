package sort;

public abstract class Sort<T extends Comparable<T>> {
	public abstract void sort(T[] input);
	
	public final boolean isSorted(final T[] input) {
		if (input == null)
			return true;
		
		for(int i=1; i<input.length; i++)
			if(input[i].compareTo(input[i-1]) < 0)
				return false;
		return true;
	}
	
	protected static final void swap(final Object[] input, final int a, final int b) {
		final Object temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}
}
