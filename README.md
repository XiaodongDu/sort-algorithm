# 用Java实现各种基本排序及简单优化
根据`compare(T t0, T t1)`返回的结果进行排序，当结果为-1时排序。

## 冒泡排序
优化思路：因为后面的元素在每一趟冒泡过程都**至少**会有一个被排好序，所以我们可以设置一个标志域len来标志每一趟那些排好序后的坐标，利用这个标志域退出循环。
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
另一种方法，同样设置一个标志域change来判断是否需要继续冒泡，但这种做法会多出1趟没必要的冒泡过程。
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
优化思路：采用双向选择排序的思路，在一次选择比较中同时选择出大值和最小值。注意这样子的话最外层循环只需要遍历数组的长度/2次，需要注意的是如果最小值索引min刚好等于i，那么就要判断i是否在max交换时被交换到max了。
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
		// 注意
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

## 插入排序
优化思路：既然直接插入排序中要将有序区中的部分元素后移一位，那么我们就可以用二分查找算法找到这个“部分元素”的临界值。
```java
public <T> void sort(T[] datas, Comparator<T> comparator) {
	if (null == comparator || null == comparator) {
		return;
	}
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
```

## 快速排序
```java
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
```

## 合并排序
```java
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
```

## 堆排序
因为堆顶节点肯定是优先级最高的节点，所以可以不断地删除顶点，再筛选来得到排序。
```java
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
```

## 比较
经不专业测试，上述算法的效率比较结果如下：
**heap sort** > **quick sort** >= **merge sort** > **insert sort** > **select sort** > **bubble sort**