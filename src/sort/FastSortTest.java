package sort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sort.hybrid.*;
import sort.said.*;
import sort.heap.*;
import sort.merge.*;
import sort.quick.*;

import java.util.Arrays;
import java.util.Random;

final class FastSortTest {
	private static final int TEST_SIZE = 10_000_000;
	private static final Integer[] input1 = newInput(TEST_SIZE);
	private static final Integer[] input2 = newInput(TEST_SIZE);
	private static final Integer[] sorted = getSorted(input1);
	private static final Integer[] reverse = getReversed(sorted);


	private static Integer[] getSorted(final Integer[] input) {
		final Integer[] sorted = input.clone();
		Arrays.sort(sorted);
		return sorted;
	}

	private static Integer[] getReversed(final Integer[] input) {
		final Integer[] reversed = new Integer[input.length];
		int i = 0;
		int j = input.length -1;
		while(i<input.length) {
			reversed[i++] = input[j--];
 		}
		return reversed;
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
		final Integer[] reversed = FastSortTest.reverse.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		Arrays.sort(reversed);
		assertEquals(true, s.isSorted(reversed));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
/*

	*/
	@Test
	void testStaticSaidSort() {
		final StaticSaidSort<Integer> s = new StaticSaidSort<>();
		final Integer[] input1 = FastSortTest.input1.clone();
		final Integer[] input2 = FastSortTest.input2.clone();
		final Integer[] reversed = FastSortTest.reverse.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		Arrays.sort(reversed);
		assertEquals(true, s.isSorted(reversed));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}

	// /*
	@Test
	void testProtoDynamicSaidSort() {
		final ProtoDynamicSaidSort<Integer> s = new ProtoDynamicSaidSort<>();
		final Integer[] input1 = FastSortTest.input1.clone();
		final Integer[] input2 = FastSortTest.input2.clone();
		final Integer[] reversed = FastSortTest.reverse.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		Arrays.sort(reversed);
		assertEquals(true, s.isSorted(reversed));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	// */
	
	// /*
	@Test
	void testDynamicSaidSort() {
		final DynamicSaidSort<Integer> s = new DynamicSaidSort<>();
		final Integer[] input1 = FastSortTest.input1.clone();
		final Integer[] input2 = FastSortTest.input2.clone();
		final Integer[] reversed = FastSortTest.reverse.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		Arrays.sort(reversed);
		assertEquals(true, s.isSorted(reversed));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	} // */
	
	/*
	@Test
	void testUltraSort() {
		final UltraSort<Integer> s = new UltraSort<>();
		final Integer[] input1 = FastSortTest.input1.clone();
		final Integer[] input2 = FastSortTest.input2.clone();
		final Integer[] reversed = FastSortTest.reverse.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		Arrays.sort(reversed);
		assertEquals(true, s.isSorted(reversed));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	*/

	@Test
	void testJavaTimSort() {
		final MergeSort<Integer> s = new MergeSort<>();
		final Integer[] input1 = FastSortTest.input1.clone();
		final Integer[] input2 = FastSortTest.input2.clone();
		final Integer[] reversed = FastSortTest.reverse.clone();
		Arrays.sort(input1);
		assertEquals(true, s.isSorted(input1));
		Arrays.sort(input2);
		assertEquals(true, s.isSorted(input2));
		Arrays.sort(reversed);
		assertEquals(true, s.isSorted(reversed));
		Arrays.sort(sorted);
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
