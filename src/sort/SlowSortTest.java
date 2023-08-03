package sort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sort.merge.*;
import sort.select.*;
import sort.bubble.*;
import sort.insert.*;

import java.util.Random;

final class SlowSortTest {
	private static final int TEST_SIZE = 32000;
	private static final Integer[] input1 = newInput(TEST_SIZE);
	private static final Integer[] input2 = newInput(TEST_SIZE);
	private static final Integer[] sorted = getSorted(input1);

	private static Integer[] getSorted(final Integer[] input) {
		final Integer[] sorted = input.clone();
		new InsertionSort<Integer>().sort(sorted);
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
	void testSelectionSort() {
		final SelectionSort<Integer> s = new SelectionSort<>();
		final Integer[] input1 = SlowSortTest.input1.clone();
		final Integer[] input2 = SlowSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	
	@Test
	void testShakerSort() {
		final ShakerSort<Integer> s = new ShakerSort<>();
		final Integer[] input1 = SlowSortTest.input1.clone();
		final Integer[] input2 = SlowSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	
	@Test
	void testGnomeSort() {
		final GnomeSort<Integer> s = new GnomeSort<>();
		final Integer[] input1 = SlowSortTest.input1.clone();
		final Integer[] input2 = SlowSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	
	@Test
	void testBubbleSort() {
		final BubbleSort<Integer> s = new BubbleSort<>();
		final Integer[] input1 = SlowSortTest.input1.clone();
		final Integer[] input2 = SlowSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	
	@Test
	void testInsertionSort() {
		final InsertionSort<Integer> s = new InsertionSort<>();
		final Integer[] input1 = SlowSortTest.input1.clone();
		final Integer[] input2 = SlowSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}
	
	@Test
	void testMergeSortNoBuffer() {
		final MergeSortNoBuffer<Integer> s = new MergeSortNoBuffer<>();
		final Integer[] input1 = SlowSortTest.input1.clone();
		final Integer[] input2 = SlowSortTest.input2.clone();
		s.sort(input1);
		assertEquals(true, s.isSorted(input1));
		s.sort(input2);
		assertEquals(true, s.isSorted(input2));
		s.sort(sorted);
		assertEquals(true, s.isSorted(sorted));
	}

}