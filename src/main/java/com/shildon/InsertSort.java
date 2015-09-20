package com.shildon;

import java.util.Comparator;

/**
 * 
 * @author shildon<shildondu@gmail.com>
 * @date Aug 15, 2015 10:16:44 PM
 *
 */
public class InsertSort extends Sort {

	/**
	 * 实现优化：既然直接插入排序中要将有序区中的部分元素后移一位，那么
	 * 我们就可以用二分查找算法找到这个“部分元素”。
	 */
	@Override
	public <T> void sort(T[] datas, Comparator<T> comparator) {
		T t;
		int j;
		for (int i = 0; i < datas.length - 1; i++) {
			if (-1 == comparator.compare(datas[i], datas[i + 1])) {
				t = datas[i + 1];
				int boun = binaryFind(t, datas, 0, i, comparator);
				for (j = i; j >= boun; j--) {
					datas[j + 1] = datas[j];
				}
				datas[j + 1] = t;
			}
		}
	}
	
	/**
	 * 利用二分查找算法找到边界值。
	 */
	private <T> int binaryFind(T t, T[] datas, int low, int high, Comparator<T> comparator) {
		if (low >= high) {
			return low;
		}
		int mid = (low + high) / 2;
		if (1 == comparator.compare(datas[mid], t)) {
			return binaryFind(t, datas, mid + 1, high, comparator);
		} else if (-1 == comparator.compare(datas[mid], t)) {
			return binaryFind(t, datas, low, mid, comparator);	//注意这里不用-1
		} else {
			return mid + 1;	//为了保证稳定性
		}
	}

}
