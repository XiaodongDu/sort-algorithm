package com.shildon;

import java.util.Comparator;

/**
 * The abstract sort class.
 * @author shildon<shildondu@gmail.com>
 * @date Aug 15, 2015 8:59:02 PM
 *
 */
public abstract class Sort {
	
	public abstract <T> void sort(T[] datas, Comparator<T> comparator);

}
