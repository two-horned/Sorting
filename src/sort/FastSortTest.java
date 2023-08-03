package sort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sort.heap.*;
import sort.merge.*;
import sort.quick.*;

import java.util.Random;

final class FastSortTest {
	private static final int TEST_SIZE = 3200000;
	private static final Integer[] input1 = newInput(TEST_SIZE);
	private static final Integer[] input2 = newInput(TEST_SIZE);
	private static final Integer[] sorted = getSorted(input1);

	private static Integer[] getSorted(final Integer[] input) {
		final Integer[] sorted = input.clone();
		new QuickSort<Integer>().sort(sorted);
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
	void testQuickSort() {
		final QuickSort<Integer> s = new QuickSort<>();
		final Integer[] input1 = FastSortTest.input1.clone();
		final Integer[] input2 = FastSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	
	@Test
	void testHeapSort() {
		final HeapSort<Integer> s = new HeapSort<>();
		final Integer[] input1 = FastSortTest.input1.clone();
		final Integer[] input2 = FastSortTest.input2.clone();
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
		final Integer[] input1 = FastSortTest.input1.clone();
		final Integer[] input2 = FastSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	
	@Test
	void testMergeSort() {
		final MergeSort<Integer> s = new MergeSort<>();
		final Integer[] input1 = FastSortTest.input1.clone();
		final Integer[] input2 = FastSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	
	@Test
	void testIsSorted() {
		final HeapSort<Integer> s = new HeapSort<>();
		final Integer[] input1 = {1,2,3,4};
		final Integer[] input2 = {1,2,4,3};
		final Integer[] input3 = {1,2,4,4};
		assertEquals(true, s.isSorted(input1));
		assertEquals(false, s.isSorted(input2));
		assertEquals(true, s.isSorted(input3));
	}
}
