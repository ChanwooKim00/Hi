package List;

public class SelectionSort {
	public static int[] sort(int[] data) {
		int temp;
		for(int i=0; i<data.length-1;i++) {
			for(int j=i+1; j<data.length;j++) {
				if(data[i]>data[j]) {
					temp=data[i];
					data[i]=data[j];
					data[j]=temp;
				}
			}
		}
		return data;
	}
	public static void main(String[] args) {
		int[] testData= {24,12,66,1,4};
		sort(testData);
		for(int i=0;i<testData.length;i++) {
			System.out.print(testData[i]+", ");
		}
	}

}
