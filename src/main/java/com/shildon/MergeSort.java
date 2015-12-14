package com.shildon;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * 合并排序
 * @author shildon<shildondu@gmail.com>
 * @date Aug 15, 2015 10:31:24 PM
 *
 */
public class MergeSort extends Sort {

	@SuppressWarnings("unchecked")
	@Override
	public <T> void sort(T[] datas, Comparator<T> comparator) {
		if (null == datas || null == comparator) {
			return;
		}
		T[] sortedData = (T[])Array.newInstance(datas[0].getClass(), datas.length);
		int high = datas.length - 1;
		mSort(datas, sortedData, comparator, 0, 0, high);
	}
	
	private <T> void mSort(T[] oldData, T[] sortedData, Comparator<T> comparator,
			int i, int low, int high) {
		if (low == high) {
			if (i % 2 == 1) {
				sortedData[low] = oldData[low];
			}
		} else {
			int mid = (low + high) / 2;
			i++;
			mSort(oldData, sortedData, comparator, i, low, mid);
			mSort(oldData, sortedData, comparator, i, mid + 1, high);
			if (i % 2 == 0) {
				mergeSort(oldData, sortedData, comparator, low, mid, high);
			} else {
				mergeSort(sortedData, oldData, comparator, low, mid, high);
			}
		}
	}
	
	private <T> void mergeSort(T[] oldData, T[] sortedData, Comparator<T> comparator,
			int low, int mid, int high) {
		int i, j, k;
		//i是对归并后的迭代索引,low和j是另外两个.
		for (i = low, j = mid + 1; low <= mid && j <= high; i++) {
			if (1 == comparator.compare(oldData[low], oldData[j])) {
				sortedData[i] = oldData[low++];
			} else {
				sortedData[i] = oldData[j++];
			}
		}
		if (low <= mid) {
			for (k = 0; k <= mid - low; k++) {
				sortedData[i + k] = oldData[low + k];
			}
		}
		if (j <= high) {
			for (k = 0; k <= high - j; k++) {
				sortedData[i + k] = oldData[j + k];
			}
		}
	}

}
