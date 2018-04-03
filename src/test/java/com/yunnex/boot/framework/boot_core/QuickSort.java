package com.yunnex.boot.framework.boot_core;

import java.util.Arrays;

public class QuickSort {
	
	public static void main(String[] args) {
		int[] a = {23, 2, 21, 14, 25, 7, 8, 1, 2, 3, 6};
		
		long start = System.currentTimeMillis();
		quickSort(a, 0, a.length-1);
		
		System.out.println("耗时："+ (System.currentTimeMillis() - start));
		
		System.out.println("排序结果：" + Arrays.toString(a));
	}
	
	
	public static void quickSort(int s[], int left, int right){
		if(left < right){
			//获取枢纽值，并将其放在当前待处理序列末尾
            getPivot(s, left, right);
            //枢纽值被放在序列末尾
            int pivot = right - 1;
            
			int i = left; //左指针
			int j = right-1;//右指针
			
			while(i < j){
				while (s[++i] < s[pivot]) {
				}
	            while (j > left && s[--j] > s[pivot]) {
	            }
	            
                if (i < j) {
                    swap(s, i, j);
                } else {
                    break;
                }
			}
			
	        if (i < right) {
	        	swap(s, i, right - 1);
            }
			
			//递归分而治之，分割出的2个数组依次同样处理
			quickSort(s, left, i-left);
			quickSort(s, i+1, right);
		}
		
	}
	
	/**
	 * 处理中枢值
	 * @param arr
	 * @param left
	 * @param right
	 */
	private static void getPivot(int arr[], int left, int right){
		int mid = (left + right) / 2;
		
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        
        if (arr[right] < arr[mid]) {
            swap(arr, right, mid);
        }
        
        swap(arr, right - 1, mid);
	}
	
	/**
	 * 元素交换
	 * <p>Title: swap<／p>
	 * <p>Description: <／p>
	 */
	private static void swap(int i[], int a, int b){
		int temp = i[a];
		i[a] = i[b];
		i[b] = temp;
	}
}
