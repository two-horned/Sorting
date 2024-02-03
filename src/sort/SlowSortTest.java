package sort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sort.merge.*;
import sort.select.*;
import sort.bubble.*;
import sort.insert.*;

import java.util.Random;

final class SlowSortTest {
	private static final int TEST_SIZE = 64;
	private static final int MAX_TEST = 10_000;


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
		final var s = new SelectionSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
			assertEquals(true, s.isSorted(input));
		}
	}
	
	@Test
	void testShakerSort() {
		final var s = new ShakerSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
			assertEquals(true, s.isSorted(input));
		}
	}
	
	@Test
	void testGnomeSort() {
		final var s = new GnomeSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
			assertEquals(true, s.isSorted(input));
		}
	}
	
	@Test
	void testBubbleSort() {
		final var s = new BubbleSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
			assertEquals(true, s.isSorted(input));
		}
	}
	
	@Test
	void testInsertionSort() {
		final var s = new InsertionSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
			assertEquals(true, s.isSorted(input));
		}
	}
	
	@Test
	void testBinaryInsertionSort() {
		final var s = new BinaryInsertionSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
			assertEquals(true, s.isSorted(input));
		}
	}
	
	@Test
	void testMergeSortNoBuffer() {
		final var s = new MergeSortNoBuffer<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
			assertEquals(true, s.isSorted(input));
		}
	}

}