package com.shildon;

import java.util.Comparator;

/**
 * 
 * @author shildon<shildondu@gmail.com>
 * @date Aug 15, 2015 10:16:44 PM
 *
 */
public class InsertSort extends Sort {

	@Override
	public <T> void sort(T[] datas, Comparator<T> comparator) {
		T temp;
		int j;
		for (int i = 1; i < datas.length - 1; i++) {
			if (-1 == comparator.compare(datas[i], datas[i + 1])) {
				temp = datas[i + 1];
				j = i + 1;
				do {
					j--;
					datas[j + 1] = datas[j];
				}
				while (-1 == comparator.compare(datas[j - 1], temp));
				datas[j] = temp;
			}
		}
	}

}
