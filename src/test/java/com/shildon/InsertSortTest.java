package com.shildon;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InsertSortTest {
	
	private InsertSort insertSort;
	
	@Before
	public void init() {
		insertSort = new InsertSort();
	}

	@Test
	public void test() {
		Integer[] datas = { 123, 11, 43, 54, 3, 111};
		Integer[] expecteds = { 123, 111, 54, 43, 11, 3 };
		insertSort.sort(datas, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}

		});
		Assert.assertArrayEquals(expecteds, datas);
	}
}
