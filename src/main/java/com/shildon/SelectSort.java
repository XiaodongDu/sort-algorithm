package com.shildon;

import java.util.Comparator;

/**
 * 
 * @author shildon<shildondu@gmail.com>
 * @date Aug 15, 2015 11:03:42 PM
 *
 */
public class SelectSort extends Sort {

	@Override
	public <T> void sort(T[] datas, Comparator<T> comparator) {
		if (null == datas) {
			return;
		}
		T temp;
		for (int i = 0; i < datas.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < datas.length; j++) {
				if (-1 == comparator.compare(datas[min], datas[j])) {
					min = j;
				}
			}
			if (min != i) {
				temp = datas[i];
				datas[i] = datas[min];
				datas[min] = temp;
			}
		}
	}

}
