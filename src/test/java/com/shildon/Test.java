package com.shildon;

import java.util.Comparator;
import java.util.Random;

import org.junit.Before;

public class Test {
	
	private BubbleSort bubbleSort;
	private HeapSort heapSort;
	private InsertSort insertSort;
	private MergeSort mergeSort;
	private QuickSort quickSort;
	private SelectSort selectSort;
	
	@Before
	public void init() {
		bubbleSort = new BubbleSort();
		heapSort = new HeapSort();
		insertSort = new InsertSort();
		mergeSort = new MergeSort();
		quickSort = new QuickSort();
		selectSort = new SelectSort();
	}
	
	@org.junit.Test
	public void test0() {
		Random random = new Random();
		Integer[] datas = new Integer[20000];
		for (int i = 0; i < 20000; i++) {
			datas[i] = random.nextInt(1000000);
		}
		long t0 = System.currentTimeMillis();
		bubbleSort.sort(datas, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		long t1 = System.currentTimeMillis() - t0;
		System.out.println("bubble:" + t1);
	}

	@org.junit.Test
	public void test1() {
		Random random = new Random();
		Integer[] datas = new Integer[20000];
		for (int i = 0; i < 20000; i++) {
			datas[i] = random.nextInt(1000000);
		}
		long t0 = System.currentTimeMillis();
		heapSort.sort(datas, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		long t1 = System.currentTimeMillis() - t0;
		System.out.println("heap:" + t1);
	}

	@org.junit.Test
	public void test2() {
		Random random = new Random();
		Integer[] datas = new Integer[20000];
		for (int i = 0; i < 20000; i++) {
			datas[i] = random.nextInt(1000000);
		}
		long t0 = System.currentTimeMillis();
		insertSort.sort(datas, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		long t1 = System.currentTimeMillis() - t0;
		System.out.println("insert:" + t1);
	}
	
	@org.junit.Test
	public void test3() {
		Random random = new Random();
		Integer[] datas = new Integer[20000];
		for (int i = 0; i < 20000; i++) {
			datas[i] = random.nextInt(1000000);
		}
		long t0 = System.currentTimeMillis();
		mergeSort.sort(datas, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		long t1 = System.currentTimeMillis() - t0;
		System.out.println("merge:" + t1);
	}

	@org.junit.Test
	public void test4() {
		Random random = new Random();
		Integer[] datas = new Integer[20000];
		for (int i = 0; i < 20000; i++) {
			datas[i] = random.nextInt(1000000);
		}
		long t0 = System.currentTimeMillis();
		quickSort.sort(datas, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		long t1 = System.currentTimeMillis() - t0;
		System.out.println("quick:" + t1);
	}

	@org.junit.Test
	public void test5() {
		Random random = new Random();
		Integer[] datas = new Integer[20000];
		for (int i = 0; i < 20000; i++) {
			datas[i] = random.nextInt(1000000);
		}
		long t0 = System.currentTimeMillis();
		selectSort.sort(datas, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		long t1 = System.currentTimeMillis() - t0;
		System.out.println("select:" + t1);
	}

}
