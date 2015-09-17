package com.shildon;

import java.util.Comparator;

/**
 * 
 * @author shildon<shildondu@gmail.com>
 * @date Aug 15, 2015 11:03:42 PM
 *
 */
public class SelectSort extends Sort {

	/**
	 * 实现优化：采用双向选择排序的思路，在一次选择比较中同时选择出
	 * 大值和最小值。注意这样子的话最外曾循环只需要遍历数组的长度/2次，
	 * 在做交换值操作的时候也要注意判断max是否已经交换过。
	 */
	@Override
	public <T> void sort(T[] datas, Comparator<T> comparator) {
		if (null == datas) {
			return;
		}
		int len = datas.length;
		T t;
		for (int i = 0; i < len / 2; i++) {
			int max = i;
			int min = i;
			for (int j = i + 1; j < len - i; j++) {
				if (-1 == comparator.compare(datas[max], datas[j])) {
					max = j;
				}
				if (1 == comparator.compare(datas[min], datas[j])) {
					min = j;
				}
			}
			if (max != i) {
				t = datas[i];
				datas[i] = datas[max];
				datas[max] = t;
			}
			if (min == i) {
				min = max;
			}
			if (min != (len - i - 1)) {
				t = datas[len - i - 1];
				datas[len - i - 1] = datas[min];
				datas[min] = t;
			}
		}
	}

}
