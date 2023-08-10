package sort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sort.heap.*;

import java.util.Arrays;
import java.util.Random;

final class MediumSortTest {
	private static final int TEST_SIZE = 100_000;
	private static final Integer[] input1 = newInput(TEST_SIZE);
	private static final Integer[] input2 = newInput(TEST_SIZE);
	private static final Integer[] sorted = getSorted(input1);

	private static Integer[] getSorted(final Integer[] input) {
		final Integer[] sorted = input.clone();
		Arrays.sort(sorted);
		return sorted;
	}
	private static Integer[] newInput(final int size) {
		final Random rand = new Random();
		Integer[] r = new Integer[size];
		for(int i=0; i<size; i++) {
			r[i] = rand.nextInt(2*size);
		}
		return r;
	}
	
	@Test
	void testHeapSort() {
		final HeapSort<Integer> s = new HeapSort<>();
		final Integer[] input1 = MediumSortTest.input1.clone();
		final Integer[] input2 = MediumSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	
	@Test
	void testWeakHeapSort() {
		final WeakHeapSort<Integer> s = new WeakHeapSort<>();
		final Integer[] input1 = MediumSortTest.input1.clone();
		final Integer[] input2 = MediumSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
}
