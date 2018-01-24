package main;

import java.util.Arrays;
import java.util.Random;

public class Sorter {
	
	private int[] arr;
	
	public Sorter(int[] values) {
		arr = new int[values.length];
		for (int i=0; i<values.length; i++) arr[i] = values[i];
	}
	
	public boolean validateSorted() {
		
		for (int i=1; i<arr.length; i++) {
			if (arr[i] < arr[i-1]) return false;
		}
		
		
		return true;
	}
	
	public void arraySort() {
		Arrays.parallelSort(arr);
	}
	
	public void bubblesort() {
		boolean notSorted = true;
		
		while (notSorted) {
			notSorted = false;
			for (int i=1; i<arr.length; i++) {
				if (arr[i] < arr[i-1]) {
					int tmp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = tmp;
					
					notSorted = true;
				}
			}
		}
	}
	
	public void mergesort() {
		
		int[] scratch = mergesort(0, arr.length-1);
		
		for (int i=0; i<arr.length; i++) {
			arr[i] = scratch[i];
		}
	}
	
	public int[] mergesort(int start, int end) {
		int[] merged = new int[end-start+1];
		
		if (start == end) {
			merged[0] = arr[start];
			return merged;
		}
		
		int midpoint = (start + end) / 2;
		
		int[] mergeL = mergesort(start, midpoint);
		int[] mergeR = mergesort(midpoint+1, end);
		
		int leftIndex = 0;
		int rightIndex = 0;
		int mergedIndex = 0;
		
		while (leftIndex < mergeL.length && rightIndex < mergeR.length) {
			if (mergeL[leftIndex] <= mergeR[rightIndex]) {
				merged[mergedIndex] = mergeL[leftIndex];
				leftIndex ++;
			} else {
				merged[mergedIndex] = mergeR[rightIndex];
				rightIndex ++;
			}
			
			mergedIndex ++;
		}
		
		for (int i=leftIndex; i<mergeL.length; i++){
			merged[mergedIndex] = mergeL[i];
			mergedIndex ++;
		}
		
		for (int i=rightIndex; i<mergeR.length; i++){
			merged[mergedIndex] = mergeR[i];
			mergedIndex ++;
		}
		
		return merged;
	}
	
	
	public void quicksort() {
		quicksort(0, arr.length-1);
	}
	
	private void quicksort(int start, int end) {
		int lo = start;
		int hi = end;
		
		int divider = medianOfThree(arr, start, end); //arr[start];
		//int divider = arr[start];
		
		while (true) {
			while (arr[hi] > divider) {
				hi --;
				if (hi <= lo) break;
			}
			
			if (hi <= lo) {
				arr[lo] = divider;	
				break;
			}
			
			arr[lo] = arr[hi];
			
			lo ++;
			while (arr[lo] < divider) {
				lo ++;
				if (lo >= hi ) break;
			}
			
			if (lo >= hi) {
				lo = hi;
				arr[hi] = divider;	
				break;
			}
			
			arr[hi] = arr[lo];
		}
		
		if (lo > start) quicksort(start, lo-1);
		if (lo < end) quicksort(lo + 1, end);
	}
	
	private static int medianOfThree( int[] array, int start, int end) {
		
		int mid = (end+start)/2;
		if (array[start] > array[end]) array = swap(array, start, end);
		if (array[start] > array[mid]) array = swap(array, start, mid);		
		if (array[mid] > array[end]) array = swap(array, mid, end);
		
		return array[mid];
	}
	
	private static int[] swap (int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
		
		return array;
	}
	
	public void print() {
		for (int value: arr) {
			System.out.print(value + " ");
			
		}
		System.out.println("");
		System.out.println("");
	}
	
	public static void main(String args[]) {
		//final int[] values = {39, 5, 14, 25, 8, 10, 44, 23, 82, 66, 45, 22, 33, 71, 18, 105, 2, 3, 0, 99, 34, 43, 81, 17};
		int size = 10000000;
		int maxValue = size * 10;
		int[] values = new int[size];
		
		Random r = new Random(1234567891);
		
		for (int i = 0; i<size; i++) values[i] = r.nextInt(maxValue);
			
		{
			Sorter qs = new Sorter(values);
			long now = System.currentTimeMillis();
			qs.arraySort();
			long elapsed = System.currentTimeMillis() - now;
			System.out.println("Array parallel sort " + qs.validateSorted() + " " + elapsed + "ms");
		}
		
		{
			Sorter qs = new Sorter(values);
			long now = System.currentTimeMillis();
			qs.quicksort();
			long elapsed = System.currentTimeMillis() - now;
			System.out.println("Quick sort " + qs.validateSorted() + " " + elapsed + "ms");
		}
		
		{
			Sorter qs = new Sorter(values);
			long now = System.currentTimeMillis();
			qs.mergesort();
			long elapsed = System.currentTimeMillis() - now;
			
			System.out.println("Merge sort " + qs.validateSorted() + " " + elapsed + "ms");	
		}
		
		{
			Sorter qs = new Sorter(values);
			long now = System.currentTimeMillis();
			//qs.bubblesort();
			long elapsed = System.currentTimeMillis() - now;
			System.out.println("Bubble sort " + qs.validateSorted() + " " + elapsed + "ms");
		}
	}
	
}
