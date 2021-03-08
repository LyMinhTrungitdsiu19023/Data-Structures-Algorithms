package DSA;

class BinarySearch { 
    int binarySearch(int arr[], int a, int b, int c) 
    { 
        if (b >= a) { 
            int mid = a + (b - a) / 2; 
            if (arr[mid] == c) 
                return mid; 
            if (arr[mid] > c) 
                return binarySearch(arr, a, mid - 1, c); 
            return binarySearch(arr, mid + 1, b, c); 
        } 
        return -1; 
    } 
  
    public static void main(String args[]) 
    { 
        BinarySearch bs = new BinarySearch(); 
        int arr[] = { 2, 5, 6, 10, 40 , 50}; 
        int n = arr.length; 
        int x = 6; 
        int result = bs.binarySearch(arr, 0, n - 1, x); 
        if (result <= 0) 
            System.out.println("no Element"); 
        else
            System.out.println("Element at " + result); 
    } 
} 
