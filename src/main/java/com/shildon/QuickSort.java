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

	private <T> void quickSort(T[] datas, int s, int t, 
			Comparator<T> comparator) {
		int pivotloc;
		if (s < t) {
			pivotloc = partition(datas, s, t, comparator);
			quickSort(datas, s, pivotloc - 1, comparator);
			quickSort(datas, pivotloc + 1, t, comparator);
		}
	}
	
	private <T> int partition(T[] datas, int low, int high,
			Comparator<T> comparator) {
		T pivotkey = datas[low];
		while (low < high) {
			while (low < high && (-1 == comparator.compare(datas[high], pivotkey) || 
					0 == comparator.compare(datas[high], pivotkey))) {
				high--;
			}
			if (low < high) {
				datas[low++] = datas[high];
			}
			while (low < high && (1 == comparator.compare(datas[high], pivotkey))) {
				low++;
			}
			if (low < high) {
				datas[high--] = datas[low];
			}
		}
		datas[low] = pivotkey;
		return low;
	}
	
}
