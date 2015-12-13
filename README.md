# 用Java实现各种基本排序及简单优化
根据`compare(T t0, T t1)`返回的结果进行排序，当结果为-1时排序。

## 冒泡排序
优化思路：因为后面的元素在每一趟冒泡过程都至少会有一个被排好序，所以我们可以设置一个标志域来标志每一趟排完序后的坐标，利用这个标志域推出循环。
```java
public <T> void sort(T[] datas, Comparator<T> comparator) {
	if (null == datas || null == comparator) {
		return;
	}
	int len = datas.length;
	T t;
	while (len > 0) {
		int curlen = len;
		len = 0;
		for (int i = 1; i < curlen; i++) {
			if (-1 == comparator.compare(datas[i - 1], datas[i])) {
				t = datas[i - 1];
				datas[i - 1] = datas[i];
				datas[i] = t;
				len = i;
			}
		}
	}
	
}
```
另一种方法，同样设置一个标志域来判断是否需要继续冒泡，但这种做法会多出1趟没必要的冒泡过程。
```java
public <T> void sort(T[] datas, Comparator<T> comparator) {
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
```

## 选择排序
优化思路：采用双向选择排序的思路，在一次选择比较中同时选择出大值和最小值。注意这样子的话最外层循环只需要遍历数组的长度/2次，在做交换值操作的时候也要注意判断max是否已经交换过。
```java
public <T> void sort(T[] datas, Comparator<T> comparator) {
	if (null == datas || null == comparator) {
		return;
	}
	int len = datas.length;
	T t;
	for (int i = 0; i < len / 2; i++) {
		int max = i;
		int min = len - i - 1;
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
```