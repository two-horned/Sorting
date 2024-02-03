package sort;

import sort.insert.*;
import sort.said.StaticSaidSort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

class SortTest {
	private static final int TEST_SIZE = 16;
	private static final int MAX_TEST = 100_000_000 / TEST_SIZE;
	
	private static Integer[] newInput(final int size) {
		final Random rand = new Random();
		Integer[] r = new Integer[size];
		for(int i=0; i<size; i++) {
			r[i] = rand.nextInt(2*size);
		}
		return r;
	}

	@Test
	void testSaid() {
		final var s = new StaticSaidSort<Integer>();
		Integer[] in;
		
		for (int i = 0; i < MAX_TEST; i++) {
			in = newInput(TEST_SIZE);
			s.sort(in);
		}
	}
	
	@Test
	void testOther() {
		final var s = new BinaryInsertionSort<Integer>();
		Integer[] in;
		
		for (int i = 0; i < MAX_TEST; i++) {
			in = newInput(TEST_SIZE);
			s.sort(in);
		}
	}
	
	
	@Test
	void testIsSorted() {
		final var s = new BinaryInsertionSort<Integer>();
		final Integer[] input1 = {1,2,3,4};
		final Integer[] input2 = {1,2,4,3};
		final Integer[] input3 = {1,2,4,4};
		assertEquals(true, s.isSorted(input1));
		assertEquals(false, s.isSorted(input2));
		assertEquals(true, s.isSorted(input3));
	}
}
