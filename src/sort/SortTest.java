package sort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sort.heap.*;
import sort.merge.*;
import sort.quick.*;
import sort.select.SelectionSort;
import sort.bubble.*;
import sort.insert.InsertionSort;

import java.util.Random;

final class SortTest {
	private static final int TEST_SIZE = 40000;
	
	private static Integer[] newInput(final int size) {
		final Random rand = new Random();
		Integer[] r = new Integer[size];
		for(int i=0; i<size; i++) {
			r[i] = rand.nextInt();
		}
		return r;
	}
	
	@Test
	void testSelectionSort() {
		final Integer[] input = newInput(TEST_SIZE);
		final SelectionSort<Integer> s = new SelectionSort<>();
		s.sort(input);
		assertEquals(true, s.isSorted(input));
	}
	
	@Test
	void testShakerSort() {
		final Integer[] input = newInput(TEST_SIZE);
		final ShakerSort<Integer> s = new ShakerSort<>();
		s.sort(input);
		assertEquals(true, s.isSorted(input));
	}
	
	@Test
	void testBubbleSort() {
		final Integer[] input = newInput(TEST_SIZE);
		final BubbleSort<Integer> s = new BubbleSort<>();
		s.sort(input);
		assertEquals(true, s.isSorted(input));
	}
	
	@Test
	void testHeapSort() {
		final Integer[] input = newInput(TEST_SIZE);
		final HeapSort<Integer> s = new HeapSort<>();
		s.sort(input);
		assertEquals(true, s.isSorted(input));
	}
	
	@Test
	void testQuickSort() {
		final Integer[] input = newInput(TEST_SIZE);
		final QuickSort<Integer> s = new QuickSort<>();
		s.sort(input);
		assertEquals(true, s.isSorted(input));
	}
	
	@Test
	void testMergeSort() {
		final Integer[] input = newInput(TEST_SIZE);
		final MergeSort<Integer> s = new MergeSort<>();
		s.sort(input);
		assertEquals(true, s.isSorted(input));
	}
	
	@Test
	void testMergeSortNoBuffer() {
		final Integer[] input = newInput(TEST_SIZE);
		final MergeSortNoBuffer<Integer> s = new MergeSortNoBuffer<>();
		s.sort(input);
		assertEquals(true, s.isSorted(input));
	}
	
	
	@Test
	void testInsertionSort() {
		final Integer[] input = newInput(TEST_SIZE);
		final InsertionSort<Integer> s = new InsertionSort<>();
		s.sort(input);
		assertEquals(true, s.isSorted(input));
	}
	
	@Test
	void testIsSorted() {
		final SelectionSort<Integer> s = new SelectionSort<>();
		final Integer[] input1 = {1,2,3,4};
		final Integer[] input2 = {1,2,4,3};
		final Integer[] input3 = {1,2,4,4};
		assertEquals(true, s.isSorted(input1));
		assertEquals(false, s.isSorted(input2));
		assertEquals(true, s.isSorted(input3));
	}

}
