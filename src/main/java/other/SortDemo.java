package other;

import java.util.ArrayList;
import java.util.Collections;

//public class SortDemo {
//
//	public static void main(String[] args) {
////		https://www.cnblogs.com/onepixel/articles/7674659.html
//		int arr[] = init(10_0000);
//		long l = System.currentTimeMillis();
////		 int sortArr[] = bubbleSort(arr);//15.5
////		int sortArr[] = selectionSort(arr);//4.3
////		int sortArr[] = shellSort(arr);//0.02
////		 int sortArr[] = insertionSort(arr);//4.3
////		int sortArr[] = mergeSort(arr,0,10_0000-1);//0.025
//		quickSort(arr,0,10_0000-1);//0.02
////		int sortArr[] = heapSort(arr);//0.016
////		int sortArr[] = countSort(arr,10_0000);//0.003
////		bucketSort(arr);//0.08
////		radixSort(arr,100_0000);//0.025
//		System.out.println(System.currentTimeMillis() - l);
////		for (int i = 0; i < arr.length; i++) {
////			 System.out.println(arr[i]);
////		}
//	}
//
//
//	public static int[] init(int n) {
//		int arr[] = new int[n];
//		// System.out.println(Math.random()*n +1);
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = (int) (Math.random() * n + 1);
//		}
//		return arr;
//	}
//
//	// ��������T(n) = O(n) ��������T(n) = O(n2) ƽ�������T(n) = O(n2)
//	public static int[] bubbleSort(int[] arr) {
//		int len = arr.length;
//		for (int i = 0; i < len; i++) {
//			boolean flag = true;
//			for (int j = 0; j < len - 1 - i; j++) {
//				if (arr[j] > arr[j + 1]) { // ����Ԫ�������Ա�
//					int temp = arr[j + 1]; // Ԫ�ؽ���
//					arr[j + 1] = arr[j];
//					arr[j] = temp;
//					flag=false;
//				}
//			}
//			if(flag){
//				break;
//			}
//		}
//		return arr;
//	}
//
//	public static void bubbleSort1(int[] arr) {
//		for (int i = 0; i < arr.length - 1; i++) {
//			boolean flag = true;// �趨һ����ǣ���Ϊtrue�����ʾ�˴�ѭ��û�н��н�����Ҳ���Ǵ��������Ѿ�����������Ȼ��ɡ�
//			for (int j = 0; j < arr.length - 1 - i; j++) {
//				if (arr[j] > arr[j + 1]) {
//					// swap(arr,j,j+1);
//					flag = false;
//				}
//			}
//			if (flag) {
//				break;
//			}
//		}
//	}
//
//	// ��������T(n) = O(n2) ��������T(n) = O(n2) ƽ�������T(n) = O(n2)
//	public static int[] selectionSort(int[] arr) {
//		int len = arr.length;
//		int minIndex, temp;
//		for (int i = 0; i < len - 1; i++) {
//			minIndex = i;
//			for (int j = i + 1; j < len; j++) {
//				if (arr[j] < arr[minIndex]) { // Ѱ����С����
//					minIndex = j; // ����С������������
//				}
//			}
//			temp = arr[i];
//			arr[i] = arr[minIndex];
//			arr[minIndex] = temp;
//		}
//		return arr;
//	}
//
//	// ��������T(n) = O(n) ������T(n) = O(n2) ƽ�������T(n) = O(n2)
//	public static int[] insertionSort(int[] arr) {
//		int preIndex, current;
//		for (int i = 1; i < arr.length; i++) {
//			preIndex = i - 1;
//			current = arr[i];
//			while (preIndex >= 0 && arr[preIndex] > current) {
//				arr[preIndex + 1] = arr[preIndex];
//				preIndex--;
//			}
//			arr[preIndex + 1] = current;
//		}
//		return arr;
//	}
//
//	// ��������T(n) = O(nlog2 n) ������T(n) = O(nlog2 n) ƽ�������T(n) =O(nlog n)
//	public static int[] shellSort(int[] arr) {
//		int len = arr.length, temp, gap = 1;
//		while (gap < len / 3) { // ��̬����������
//			gap = gap * 3 + 1;
//		}
//		for (; gap > 0; gap = (int) Math.floor(gap / 3)) {
//			for (int i = gap; i < len; i++) {
//				temp = arr[i];
//				int j = 0;
//				for (j = i - gap; j > 0 && arr[j] > temp; j -= gap) {
//					arr[j + gap] = arr[j];
//				}
//				arr[j + gap] = temp;
//			}
//		}
//		return arr;
//	}
//
//	// ��������T(n) = O(n) ��������T(n) = O(nlogn) ƽ�������T(n) = O(nlogn)
//	public static int[] mergeSort(int[] a, int low, int high) {
//		int mid = (low + high) / 2;
//		if (low < high) {
//			mergeSort(a, low, mid);
//			mergeSort(a, mid + 1, high);
//			// ���ҹ鲢
//			merge(a, low, mid, high);
//		}
//		return a;
//	}
//
//	public static void merge(int[] a, int low, int mid, int high) {
//		int[] temp = new int[high - low + 1];
//		int i = low;
//		int j = mid + 1;
//		int k = 0;
//		// �ѽ�С�������Ƶ���������
//		while (i <= mid && j <= high) {
//			if (a[i] < a[j]) {
//				temp[k++] = a[i++];
//			} else {
//				temp[k++] = a[j++];
//			}
//		}
//		// �����ʣ�������������
//		while (i <= mid) {
//			temp[k++] = a[i++];
//		}
//		// ���ұ߱�ʣ�������������
//		while (j <= high) {
//			temp[k++] = a[j++];
//		}
//		// ���������е�������nums����
//		for (int x = 0; x < temp.length; x++) {
//			a[x + low] = temp[x];
//		}
//	}
//
//	// ��������T(n) = O(nlogn) ��������T(n) = O(n2) ƽ�������T(n) = O(nlogn)��
//	public static void quickSort(int[] array, int lo, int hi) {
//		if (lo >= hi) {
//			return;
//		}
//		int index = partition(array, lo, hi);
//		quickSort(array, lo, index - 1);
//		quickSort(array, index + 1, hi);
//	}
//
//	public static int partition(int[] array, int low, int high) {
//		// �̶����зַ�ʽ
//		int key = array[low];
//		while (low < high) {
//			while (array[high] >= key && high > low) {// �Ӻ�벿����ǰɨ��
//				high--;
//			}
//			array[low] = array[high];
//			while (array[low] <= key && high > low) {// ��ǰ�벿�����ɨ��
//				low++;
//			}
//			array[high] = array[low];
//		}
//		array[high] = key;
//		return high;
//	}
//
//	// ��������T(n) = O(nlogn) ��������T(n) = O(nlogn) ƽ�������T(n) = O(nlogn)
//	public static int[] heapSort(int[] a) {
//		int i;
//		for (i = a.length / 2 - 1; i >= 0; i--) {// ����һ���󶥶�
//			adjustHeap(a, i, a.length - 1);
//		}
//		for (i = a.length - 1; i >= 0; i--) {// ���Ѷ���¼�͵�ǰδ�����������е����һ����¼����
//			int temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			adjustHeap(a, 0, i - 1);// ��a��ǰi-1����¼���µ���Ϊ�󶥶�
//		}
//		return a;
//	}
//
//	public static void adjustHeap(int[] a, int i, int len) {
//		int temp, j;
//		temp = a[i];
//		for (j = 2 * i; j < len; j *= 2) {// �عؼ��ֽϴ�ĺ��ӽ������ɸѡ
//			if (j < len && a[j] < a[j + 1])
//				++j; // jΪ�ؼ����нϴ��¼���±�
//			if (temp >= a[j])
//				break;
//			a[i] = a[j];
//			i = j;
//		}
//		a[i] = temp;
//	}
//
////	��������������С��Χ��
//	// ��������T(n) = O(n+k) ��������T(n) = O(n+k) ƽ�������T(n) = O(n+k)
//	private static int[] countSort(int[] array, int k) {
//		int[] C = new int[k + 1];// ����C����
//		int length = array.length, sum = 0;// ��ȡA�����С���ڹ���B����
//		int[] B = new int[length];// ����B����
//		for (int i = 0; i < length; i++) {
//			C[array[i]] += 1;// ͳ��A�и�Ԫ�ظ���������C����
//		}
//		for (int i = 0; i < k + 1; i++)// �޸�C����
//		{
//			sum += C[i];
//			C[i] = sum;
//		}
//		for (int i = length - 1; i >= 0; i--)// ����A���飬����B����
//		{
//			B[C[array[i]] - 1] = array[i];// ��A�и�Ԫ�طŵ����������B��ָ����λ��
//			C[array[i]]--;// ��C�и�Ԫ��-1����������һ��ͬ����С��Ԫ��
//
//		}
//		return B;// ������õ����鷵�أ��������
//	}
//
//	// ��������T(n) = O(n+k) ��������T(n) = O(n+k) ƽ�������T(n) = O(n2)
//	public static void bucketSort(int[] arr) {
//		int max = Integer.MIN_VALUE;
//		int min = Integer.MAX_VALUE;
//		for (int i = 0; i < arr.length; i++) {
//			max = Math.max(max, arr[i]);
//			min = Math.min(min, arr[i]);
//		}
//		// Ͱ��
//		int bucketNum = (max - min) / arr.length + 1;
//		ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
//		for (int i = 0; i < bucketNum; i++) {
//			bucketArr.add(new ArrayList<Integer>());
//		}
//
//		// ��ÿ��Ԫ�ط���Ͱ
//		for (int i = 0; i < arr.length; i++) {
//			int num = (arr[i] - min) / (arr.length);
//			bucketArr.get(num).add(arr[i]);
//		}
//
//		// ��ÿ��Ͱ��������
//		for (int i = 0; i < bucketArr.size(); i++) {
//			Collections.sort(bucketArr.get(i));
//		}
//	}
//
//	// ��������T(n) = O(n * k) ��������T(n) = O(n * k) ƽ�������T(n) = O(n * k)
//	private static void radixSort(int[] array, int d) {
//		int n = 1;// ����λ����Ӧ������1,10,100...
//		int k = 0;// ����ÿһλ�����Ľ��������һλ����������
//		int length = array.length;
//		int[][] bucket = new int[10][length];// ����Ͱ���ڱ���ÿ�������Ľ������һλ����������ͬ�����ַ���ͬһ��Ͱ��
//		int[] order = new int[length];// ���ڱ���ÿ��Ͱ���ж��ٸ�����
//		while (n < d) {
//			for (int num : array) // ������array���ÿ�����ַ�����Ӧ��Ͱ��
//			{
//				int digit = (num / n) % 10;
//				bucket[digit][order[digit]] = num;
//				order[digit]++;
//			}
//			for (int i = 0; i < length; i++)// ��ǰһ��ѭ�����ɵ�Ͱ������ݸ��ǵ�ԭ���������ڱ�����һλ��������
//			{
//				if (order[i] != 0)// ���Ͱ�������ݣ����ϵ��±������Ͱ�������ݱ��浽ԭ������
//				{
//					for (int j = 0; j < order[i]; j++) {
//						array[k] = bucket[i][j];
//						k++;
//					}
//				}
//				order[i] = 0;// ��Ͱ���������0��������һ��λ����
//			}
//			n *= 10;
//			k = 0;// ��k��0��������һ�ֱ���λ������
//		}
//	}
//}
