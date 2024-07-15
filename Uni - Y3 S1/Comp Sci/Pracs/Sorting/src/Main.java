
public class Main
{

	//This is a Quicksort algorithm coded in Java
	public static void quickSort(int[] array, int left, int right) {
	    if (left < right) {
	        int pivotIndex = partition(array, left, right); 
	        quickSort(array, left, pivotIndex - 1); 
	        quickSort(array, pivotIndex + 1, right);
	    }
	    
	}
	 
	private static int partition(int[] array, int left, int right) {
	    int pivotValue = array[right];  
	    int pivotIndex = left;   
	    for (int i = left; i < right; i++) {
	        if (array[i] < pivotValue) {
	            swap(array, i, pivotIndex);
	            pivotIndex++;
	        }
	    }
	    swap(array, pivotIndex, right);  
	    return pivotIndex;
	}
	 
	private static void swap(int[] array, int i, int j) {
	    int temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}

	public static void main(String[] args) 
	{
		int[] arr = new int[8];
		
		arr[0] = 2;
		arr[1] = 5;                 //*  1
		arr[2] = 1;                 //** 5 //*
		arr[3] = 9;//3  ///                //**
		arr[4] = 3;//9* /// 6 return 4
		arr[5] = 4;// 
		arr[6] = 7;//
		arr[7] = 6;//** /// 9
		
		quickSort(arr, 0, 7);
		
		
		
		for(int i = 0; i < 8; i++)
		{
			System.out.println((i+1)+": "+arr[i]);
		}
		// TODO Auto-generated method stub
		

		
		

	}
	
	

}
