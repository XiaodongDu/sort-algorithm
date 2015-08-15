package com.shildon;

import java.util.Comparator;

/**
 * 
 * @author shildon<shildondu@gmail.com>
 * @date Aug 15, 2015 9:12:06 PM
 *
 */
public class BubbleSort extends Sort {

	@Override
	public <T> void sort(T[] datas, Comparator<T> comparator) {
		if (null == datas) {
			return;
		}
		boolean change = true;
		T t;
		for (int i = datas.length - 1; i > 1 && change; i--) {
			change = false;
			for (int j = 0; j < i; j++) {
				if (-1 == comparator.compare(datas[j], datas[j + 1])) {
					t = datas[j + 1];
					datas[j + 1] = datas[j];
					datas[j] = t;
					change = true;
				}
			}
		}
	}

}
