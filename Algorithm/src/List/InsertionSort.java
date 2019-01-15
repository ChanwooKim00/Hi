package List;

public class InsertionSort {
	public static int[] sort(int[] data) {
		int temp;
		int j;
		for(int i=1;i<data.length;i++) {
			for(j=i-1;j>=0;j--) {
				if(data[j]>data[j+1]) {
					temp=data[j];
					data[j]=data[j+1];
					data[j+1]=temp;
				}
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int[] testData= {24,12,66,2,4};
		sort(testData);
		for(int i=0;i<testData.length;i++) {
			System.out.print(testData[i]+", ");
		}
	}

}
