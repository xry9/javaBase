package dataStructure;

import java.util.ArrayList;
import java.util.Collections;

public class SortDemo {

	public static void main(String[] args) {
//		https://www.cnblogs.com/onepixel/articles/7674659.html
		int arr[] = init(10_0000);
		long l = System.currentTimeMillis();
//		 int sortArr[] = bubbleSort(arr);//15.5
//		int sortArr[] = selectionSort(arr);//4.3
//		int sortArr[] = shellSort(arr);//0.02
//		 int sortArr[] = insertionSort(arr);//4.3
//		int sortArr[] = mergeSort(arr,0,10_0000-1);//0.025
		quickSort(arr,0,10_0000-1);//0.02
//		int sortArr[] = heapSort(arr);//0.016
//		int sortArr[] = countSort(arr,10_0000);//0.003
//		bucketSort(arr);//0.08
//		radixSort(arr,100_0000);//0.025
		System.out.println(System.currentTimeMillis() - l);
//		for (int i = 0; i < arr.length; i++) {
//			 System.out.println(arr[i]);
//		}
	}


	public static int[] init(int n) {
		int arr[] = new int[n];
		// System.out.println(Math.random()*n +1);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * n + 1);
		}
		return arr;
	}

	// 最佳情况：T(n) = O(n) 最差情况：T(n) = O(n2) 平均情况：T(n) = O(n2)
	public static int[] bubbleSort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			boolean flag = true;
			for (int j = 0; j < len - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) { // 相邻元素两两对比
					int temp = arr[j + 1]; // 元素交换
					arr[j + 1] = arr[j];
					arr[j] = temp;
					flag=false;
				}
			}
			if(flag){
				break;
			}
		}
		return arr;
	}

	public static void bubbleSort1(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			boolean flag = true;// 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成。
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					// swap(arr,j,j+1);
					flag = false;
				}
			}
			if (flag) {
				break;
			}
		}
	}

	// 最佳情况：T(n) = O(n2) 最差情况：T(n) = O(n2) 平均情况：T(n) = O(n2)
	public static int[] selectionSort(int[] arr) {
		int len = arr.length;
		int minIndex, temp;
		for (int i = 0; i < len - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (arr[j] < arr[minIndex]) { // 寻找最小的数
					minIndex = j; // 将最小数的索引保存
				}
			}
			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
		return arr;
	}

	// 最佳情况：T(n) = O(n) 最坏情况：T(n) = O(n2) 平均情况：T(n) = O(n2)
	public static int[] insertionSort(int[] arr) {
		int preIndex, current;
		for (int i = 1; i < arr.length; i++) {
			preIndex = i - 1;
			current = arr[i];
			while (preIndex >= 0 && arr[preIndex] > current) {
				arr[preIndex + 1] = arr[preIndex];
				preIndex--;
			}
			arr[preIndex + 1] = current;
		}
		return arr;
	}

	// 最佳情况：T(n) = O(nlog2 n) 最坏情况：T(n) = O(nlog2 n) 平均情况：T(n) =O(nlog n)
	public static int[] shellSort(int[] arr) {
		int len = arr.length, temp, gap = 1;
		while (gap < len / 3) { // 动态定义间隔序列
			gap = gap * 3 + 1;
		}
		for (; gap > 0; gap = (int) Math.floor(gap / 3)) {
			for (int i = gap; i < len; i++) {
				temp = arr[i];
				int j = 0;
				for (j = i - gap; j > 0 && arr[j] > temp; j -= gap) {
					arr[j + gap] = arr[j];
				}
				arr[j + gap] = temp;
			}
		}
		return arr;
	}

	// 最佳情况：T(n) = O(n) 最差情况：T(n) = O(nlogn) 平均情况：T(n) = O(nlogn)
	public static int[] mergeSort(int[] a, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			mergeSort(a, low, mid);
			mergeSort(a, mid + 1, high);
			// 左右归并
			merge(a, low, mid, high);
		}
		return a;
	}

	public static void merge(int[] a, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		// 把较小的数先移到新数组中
		while (i <= mid && j <= high) {
			if (a[i] < a[j]) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}
		// 把左边剩余的数移入数组
		while (i <= mid) {
			temp[k++] = a[i++];
		}
		// 把右边边剩余的数移入数组
		while (j <= high) {
			temp[k++] = a[j++];
		}
		// 把新数组中的数覆盖nums数组
		for (int x = 0; x < temp.length; x++) {
			a[x + low] = temp[x];
		}
	}

	// 最佳情况：T(n) = O(nlogn) 最差情况：T(n) = O(n2) 平均情况：T(n) = O(nlogn)　
	public static void quickSort(int[] array, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int index = partition(array, lo, hi);
		quickSort(array, lo, index - 1);
		quickSort(array, index + 1, hi);
	}

	public static int partition(int[] array, int low, int high) {
		// 固定的切分方式
		int key = array[low];
		while (low < high) {
			while (array[high] >= key && high > low) {// 从后半部分向前扫描
				high--;
			}
			array[low] = array[high];
			while (array[low] <= key && high > low) {// 从前半部分向后扫描
				low++;
			}
			array[high] = array[low];
		}
		array[high] = key;
		return high;
	}

	// 最佳情况：T(n) = O(nlogn) 最差情况：T(n) = O(nlogn) 平均情况：T(n) = O(nlogn)
	public static int[] heapSort(int[] a) {
		int i;
		for (i = a.length / 2 - 1; i >= 0; i--) {// 构建一个大顶堆
			adjustHeap(a, i, a.length - 1);
		}
		for (i = a.length - 1; i >= 0; i--) {// 将堆顶记录和当前未经排序子序列的最后一个记录交换
			int temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			adjustHeap(a, 0, i - 1);// 将a中前i-1个记录重新调整为大顶堆
		}
		return a;
	}

	public static void adjustHeap(int[] a, int i, int len) {
		int temp, j;
		temp = a[i];
		for (j = 2 * i; j < len; j *= 2) {// 沿关键字较大的孩子结点向下筛选
			if (j < len && a[j] < a[j + 1])
				++j; // j为关键字中较大记录的下标
			if (temp >= a[j])
				break;
			a[i] = a[j];
			i = j;
		}
		a[i] = temp;
	}

	//	计数排序适用于小范围数
	// 最佳情况：T(n) = O(n+k) 最差情况：T(n) = O(n+k) 平均情况：T(n) = O(n+k)
	private static int[] countSort(int[] array, int k) {
		int[] C = new int[k + 1];// 构造C数组
		int length = array.length, sum = 0;// 获取A数组大小用于构造B数组
		int[] B = new int[length];// 构造B数组
		for (int i = 0; i < length; i++) {
			C[array[i]] += 1;// 统计A中各元素个数，存入C数组
		}
		for (int i = 0; i < k + 1; i++)// 修改C数组
		{
			sum += C[i];
			C[i] = sum;
		}
		for (int i = length - 1; i >= 0; i--)// 遍历A数组，构造B数组
		{
			B[C[array[i]] - 1] = array[i];// 将A中该元素放到排序后数组B中指定的位置
			C[array[i]]--;// 将C中该元素-1，方便存放下一个同样大小的元素

		}
		return B;// 将排序好的数组返回，完成排序
	}

	// 最佳情况：T(n) = O(n+k) 最差情况：T(n) = O(n+k) 平均情况：T(n) = O(n2)
	public static void bucketSort(int[] arr) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}
		// 桶数
		int bucketNum = (max - min) / arr.length + 1;
		ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
		for (int i = 0; i < bucketNum; i++) {
			bucketArr.add(new ArrayList<Integer>());
		}

		// 将每个元素放入桶
		for (int i = 0; i < arr.length; i++) {
			int num = (arr[i] - min) / (arr.length);
			bucketArr.get(num).add(arr[i]);
		}

		// 对每个桶进行排序
		for (int i = 0; i < bucketArr.size(); i++) {
			Collections.sort(bucketArr.get(i));
		}
	}

	// 最佳情况：T(n) = O(n * k) 最差情况：T(n) = O(n * k) 平均情况：T(n) = O(n * k)
	private static void radixSort(int[] array, int d) {
		int n = 1;// 代表位数对应的数：1,10,100...
		int k = 0;// 保存每一位排序后的结果用于下一位的排序输入
		int length = array.length;
		int[][] bucket = new int[10][length];// 排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
		int[] order = new int[length];// 用于保存每个桶里有多少个数字
		while (n < d) {
			for (int num : array) // 将数组array里的每个数字放在相应的桶里
			{
				int digit = (num / n) % 10;
				bucket[digit][order[digit]] = num;
				order[digit]++;
			}
			for (int i = 0; i < length; i++)// 将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
			{
				if (order[i] != 0)// 这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
				{
					for (int j = 0; j < order[i]; j++) {
						array[k] = bucket[i][j];
						k++;
					}
				}
				order[i] = 0;// 将桶里计数器置0，用于下一次位排序
			}
			n *= 10;
			k = 0;// 将k置0，用于下一轮保存位排序结果
		}
	}
}
