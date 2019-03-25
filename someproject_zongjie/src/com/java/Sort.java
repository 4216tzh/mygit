package com.java;

/**
 * һЩ�����㷨
 * @author tzh
 *
 */
public class Sort {
	
	public static void main(String[] args) {
		int[] x= {99,88,100,66,101,3,111};
		//buddleSort(x);
		selectSort(x);
		//directInsertSort(x);
	}
	
	 /**  
      ���αȽ����ڵ�����������С������ǰ�棬�������ں���  
 * ð�����򣬾����ȶ���  
 * ʱ�临�Ӷ�ΪO��n^2��  
 * ���������򣬿�������O��nlogn������Ϊ2��
     * @param sort  
     */  
    public static void buddleSort(int[] sort){   
        for(int i=1;i<sort.length;i++){   
            for(int j=0;j<sort.length-i;j++){   
                if(sort[j]>sort[j+1]){   
                    int temp = sort[j+1];   
                    sort[j+1] = sort[j];   
                    sort[j] = temp;   
                }
                for(int x=0;x<sort.length;x++) {
                	System.out.print(sort[x]+",");
                }
                System.out.println("end"+i);
            }
            
            System.out.println("ends");
        }   
    }  
    
    /**  
     * ѡ������  
 * ÿһ�˴Ӵ����������Ԫ����ѡ����С������󣩵�һ��Ԫ�أ�  
 * ˳��������ź�������е����ֱ��ȫ�������������Ԫ�����ꡣ   
 * ѡ�������ǲ��ȶ������򷽷���    
     * @param sort  
     */  
    public static void selectSort(int[] sort){   
        for(int i =0;i<sort.length-1;i++){   
            for(int j = i+1;j<sort.length;j++){   
                if(sort[j]<sort[i]){   
                    int temp = sort[j];   
                    sort[j] = sort[i];   
                    sort[i] = temp;   
                }
                for(int x=0;x<sort.length;x++) {
                	System.out.print(sort[x]+",");
                }
                System.out.println("end"+i);
            }
            System.out.println("ends"+i);
        }   
    } 
    
    /**  
     * �������� ͨ��һ������Ҫ��������ݷָ�ɶ����������֣� ����һ���ֵ��������ݶ�������һ���ֵ��������ݶ�ҪС��  
 * Ȼ���ٰ��˷����������������ݷֱ���п������� ����������̿��Եݹ���У��Դ˴ﵽ�������ݱ���������С�
     * @param sort Ҫ���������  
     * @param start ����Ŀ�ʼ����  
     * @param end ����Ľ�������  
     */  
    public static void quickSort(int[] sort, int start, int end) {   
        // ���ùؼ�����keyΪҪ��������ĵ�һ��Ԫ�أ�   
        // ����һ�������key�ұߵ���ȫ����key��key��ߵ���ȫ����keyС   
        int key = sort[start];   
        // ����������ߵ������������ƶ��жϱ�key�����   
        int i = start;   
        // ���������ұߵ������������ƶ��жϱ�keyС����   
        int j = end;   
        // �������������ұ�����С����������û������   
        while (i < j) {   
            while (sort[j] > key && j > start) {   
                j--;   
            }   
            while (sort[i] < key && i < end) {   
                i++;   
            }   
            if (i < j) {   
                int temp = sort[i];   
                sort[i] = sort[j];   
                sort[j] = temp;   
            }   
        }   
        // �������������ұ�����Ҫ��˵����һ��������ɣ���sort[j]��key�Ի���   
        // ��������key��ߵ�����keyС��key�ұߵ�����key��   
        if (i > j) {   
            int temp = sort[j];   
            sort[j] = sort[start];   
            sort[start] = temp;   
        }   
        //�ݹ����   
        if (j > start && j < end) {   
            quickSort(sort, start, j -1);   
            quickSort(sort, j + 1, end);   
        }   
    }
    
    /**
     * ֱ�Ӳ�������  
     * ��һ�����ݲ��뵽�Ѿ��ź��������������,�Ӷ��õ�һ���µġ�������һ����������  
     * �㷨�������������ݵ�����ʱ�临�Ӷ�ΪO(n^2)�����ȶ������򷽷���
     * @param sort
     */
    public static void directInsertSort(int[] sort) {   
        for (int i = 1; i < sort.length; i++) {   
            int index = i - 1;   
            int temp = sort[i];   
            while (index >= 0 && sort[index] > temp) {   
                sort[index + 1] = sort[index];   
                System.out.println(index);
                index--;
               
            }   
            sort[index + 1] = temp;   
            for(int m=0;m<sort.length;m++) {
            	System.out.print(sort[m]+",");
            }
            System.out.println("end");
        }   
    }
    
    /**  
     * �������������������꣬�����ڷ���-1  
     * @param sort  
     * @return  
     */  
    public static int binarySearch(int[] sort,int data){   
        if(data<sort[0] || data>sort[sort.length-1]){   
            return -1;   
        }   
        int begin = 0;   
        int end = sort.length;   
        int mid = (begin+end)/2;   
        while(begin <= end){   
            mid = (begin+end)/2;   
            if(data > sort[mid]){   
                begin = mid + 1;   
            }else if(data < sort[mid]){   
                end = mid - 1;   
            }else{   
                return mid;   
            }   
        }   
        return -1;   
           
    }  

}
