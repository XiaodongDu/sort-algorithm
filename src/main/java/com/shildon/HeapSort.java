package com.shildon;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * 堆排序,因为堆顶节点肯定是优先级最高的节点，所以可以不断地删除顶点，再筛选来得到排序。
 * @author shildon<shildondu@gmail.com>
 * @date Dec 14, 2015 11:29:12 PM
 *
 */
public class HeapSort extends Sort {
	
	// 堆的长度标志，避免在删除头节点时需要去复制一个新堆
	private int length;

	@SuppressWarnings("unchecked")
	@Override
	public <T> void sort(T[] datas, Comparator<T> comparator) {
		if (null == datas || null == comparator) {
			return;
		}
		// 构造0节点不使用的数组，方便堆的子树下标计算
		T[] heapDatas = (T[]) Array.newInstance(datas[0].getClass(), datas.length + 1);
		length = datas.length;

		for (int i = 0; i < datas.length; i++) {
			heapDatas[i + 1] = datas[i];
		}
		makeHeap(heapDatas, comparator);
		for (int i = 0; i < datas.length; i++) {
			datas[i] = removeFirstHeap(heapDatas, comparator);
		}
	}
	
	// 筛选
	private <T> void shiftDown(T[] datas,int pos, Comparator<T> comparator) {
		int lc, rc;
		while (pos <= length / 2) {	//当pos指向非叶子节点
			lc = pos * 2;
			rc = lc + 1;
			if (rc <= length && -1 == comparator.compare(datas[lc], datas[rc])) {
				lc = rc;
			}
			if (-1 == comparator.compare(datas[pos], datas[lc])) {
				swap(datas, pos, lc);
				pos = lc;
			} else {
				return;
			}
		}
	}
	
	// 交换节点
	private <T> void swap(T[] datas, int i, int j) {
		T t;
		t = datas[i];
		datas[i] = datas[j];
		datas[j] = t;
	}
	
	// 建堆操作
	private <T> void makeHeap(T[] datas, Comparator<T> comparator) {
		for (int i = length / 2; i > 0; i--) {
			shiftDown(datas, i, comparator);
		}
	}
	
	// 删除头节点
	private <T> T removeFirstHeap(T[] datas, Comparator<T> comparator) {
		T first = datas[1];
		swap(datas, 1, length);
		length--;
		if (length > 1) {
			shiftDown(datas, 1, comparator);
		}
		return first;
	}

}
