package other;

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
//		quickSort(arr,0,10_0000-1);//0.02
		int sortArr[] = heapSort(arr);//0.016
//		int sortArr[] = countSort(arr,10_0000);//0.003
//		bucketSort(arr);//0.08
//		radixSort(arr,100_0000);//0.025
		System.out.println(System.currentTimeMillis() - l);
		for (int i = 0; i < arr.length; i++) {
			 System.out.println(arr[i]);
		}
	}


	public static int[] init(int n) {
		int arr[] = new int[n];
		// System.out.println(Math.random()*n +1);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * n + 1);
		}
		return arr;
	}

	public static int[] bubbleSort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			boolean flag = true;
			for (int j = 0; j < len - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j + 1];
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
			boolean flag = true;
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

	public static int[] selectionSort(int[] arr) {
		int len = arr.length;
		int minIndex, temp;
		for (int i = 0; i < len - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
		return arr;
	}

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

	public static int[] shellSort(int[] arr) {
		int len = arr.length, temp, gap = 1;
		while (gap < len / 3) {
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

	public static int[] mergeSort(int[] a, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			mergeSort(a, low, mid);
			mergeSort(a, mid + 1, high);
			merge(a, low, mid, high);
		}
		return a;
	}

	public static void merge(int[] a, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= high) {
			if (a[i] < a[j]) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}
		while (i <= mid) {
			temp[k++] = a[i++];
		}
		while (j <= high) {
			temp[k++] = a[j++];
		}
		for (int x = 0; x < temp.length; x++) {
			a[x + low] = temp[x];
		}
	}


	public static void quickSort(int[] array, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int index = partition(array, lo, hi);
		quickSort(array, lo, index - 1);
		quickSort(array, index + 1, hi);
	}

    public static int partition(int[] arr, int left ,int right) {
        int index = left + 1;
        for(int i = index; i <= right; i++) {
            if(arr[i] < arr[left]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, left, index - 1);
        return index-1;
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int[] heapSort(int[] a) {
		int i;
		for (i = a.length / 2 - 1; i >= 0; i--) {
			adjustHeap(a, i, a.length - 1);
		}
		for (i = a.length - 1; i >= 0; i--) {
			int temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			adjustHeap(a, 0, i - 1);
		}
		return a;
	}

	public static void adjustHeap(int []arr,int i,int length){
		int temp = arr[i];
		for(int k=i*2+1;k<length;k=k*2+1){
			if(k+1<length && arr[k]<arr[k+1]){
				k++;
			}
			if(arr[k] >temp){
				arr[i] = arr[k];
				i = k;
			}else{
				break;
			}
		}
		arr[i] = temp;
	}

	private static int[] countSort(int[] array, int k) {
		int[] C = new int[k + 1];
		int length = array.length, sum = 0;
		int[] B = new int[length];
		for (int i = 0; i < length; i++) {
			C[array[i]] += 1;
		}
		for (int i = 0; i < k + 1; i++) {
			sum += C[i];
			C[i] = sum;
		}
		for (int i = length - 1; i >= 0; i--) {
			B[C[array[i]] - 1] = array[i];
			C[array[i]]--;
		}
		return B;
	}

	public static void bucketSort(int[] arr) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}
		int bucketNum = (max - min) / arr.length + 1;
		ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
		for (int i = 0; i < bucketNum; i++) {
			bucketArr.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < arr.length; i++) {
			int num = (arr[i] - min) / (arr.length);
			bucketArr.get(num).add(arr[i]);
		}

		for (int i = 0; i < bucketArr.size(); i++) {
			Collections.sort(bucketArr.get(i));
		}
	}

	private static void radixSort(int[] array, int d) {
		int n = 1;
		int k = 0;
		int length = array.length;
		int[][] bucket = new int[10][length];
		int[] order = new int[length];
		while (n < d) {
			for (int num : array)
			{
				int digit = (num / n) % 10;
				bucket[digit][order[digit]] = num;
				order[digit]++;
			}
			for (int i = 0; i < length; i++)
			{
				if (order[i] != 0)
				{
					for (int j = 0; j < order[i]; j++) {
						array[k] = bucket[i][j];
						k++;
					}
				}
				order[i] = 0;
			}
			n *= 10;
			k = 0;
		}
	}
}
