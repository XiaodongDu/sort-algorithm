package com.shildon;

import java.util.Comparator;

/**
 * 
 * @author shildon<shildondu@gmail.com>
 * @date Aug 15, 2015 10:52:05 PM
 *
 */
public class QuickSort extends Sort {

	@Override
	public <T> void sort(T[] datas, Comparator<T> comparator) {
		if (null == datas) {
			return;
		}
		quickSort(datas, 0, datas.length - 1, comparator);
	}

	private <T> void quickSort(T[] datas, int low, int high, 
			Comparator<T> comparator) {
		int pivotloc;
		if (low < high) {
			pivotloc = partition(datas, low, high, comparator);
			quickSort(datas, low, pivotloc - 1, comparator);
			quickSort(datas, pivotloc + 1, high, comparator);
		}
	}
	
	private <T> int partition(T[] datas, int low, int high,
			Comparator<T> comparator) {
		T t = datas[low];
		while (low < high) {
			while (low < high && (1 == comparator.compare(t, datas[high]) || 
					0 == comparator.compare(datas[high], t))) {
				high--;
			}
			if (low < high) {
				datas[low++] = datas[high];
			}
			while (low < high && (1 == comparator.compare(datas[low], t) ||
					0 == comparator.compare(datas[low], t))) {
				low++;
			}
			if (low < high) {
				datas[high--] = datas[low];
			}
		}
		datas[low] = t;
		return low;
	}
	
}
