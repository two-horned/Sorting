package sort;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import sort.hybrid.*;
import sort.said.*;
import sort.quick.*;

import java.util.Arrays;
import java.util.Random;

@TestMethodOrder(OrderAnnotation.class)
final class FastSortTest {
	private static final int TEST_SIZE = 100_000;
	private static final int WARMUP   = 100;
	private static final int MAX_TEST = 10;
	
	private static Integer[] newInput(final int size) {
		final Random rand = new Random();
		Integer[] r = new Integer[size];
		for(int i=0; i<size; i++) {
			r[i] = rand.nextInt(2*size);
		}
		return r;
	}
	
	@Test
	@Order(1)
	void warmup() {
		Sort<Integer> s = new QuickSort<Integer>();
		Integer[] input;
		for(int i = 0; i < WARMUP; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
		s = new UltraSort<Integer>();
		for(int i = 0; i < WARMUP; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
		s = new HyperSort<Integer>();
		for(int i = 0; i < WARMUP; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
		s = new StaticSaidSort<Integer>();
		for(int i = 0; i < WARMUP; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
		s = new DynamicSaidSort<Integer>();
		for(int i = 0; i < WARMUP; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
	}
	
	@Test
	void testQuickSort() {
		final var s = new QuickSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
		input = newInput(TEST_SIZE);
		s.sort(input);
		assert(s.isSorted(input));
	}

	@Test
	void testStaticSaidSort() {
		final var s = new StaticSaidSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
		input = newInput(TEST_SIZE);
		s.sort(input);
		assert(s.isSorted(input));
	}

	@Test
	void testDynamicSaidSort() {
		final var s = new DynamicSaidSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
		input = newInput(TEST_SIZE);
		s.sort(input);
		assert(s.isSorted(input));
	}
	
	@Test
	void testUltraSort() {
		final var s = new UltraSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
		input = newInput(TEST_SIZE);
		s.sort(input);
		assert(s.isSorted(input));
	}
	
	@Test
	void testHyperSort() {
		final var s = new HyperSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			s.sort(input);
		}
		input = newInput(TEST_SIZE);
		s.sort(input);
		assert(s.isSorted(input));
	}
	

	@Test
	void testJavaTimSort() {
		final var s = new UltraSort<Integer>();
		Integer[] input;
		for (int i = 0; i < MAX_TEST; i++) {
			input = newInput(TEST_SIZE);
			Arrays.sort(input);
		}
		input = newInput(TEST_SIZE);
		Arrays.sort(input);
		assert(s.isSorted(input));
	}
}
