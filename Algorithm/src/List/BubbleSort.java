package List;

public class BubbleSort {
	public static int[] sort(int[] data) {
		int temp=0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < (data.length-1)-i; j++) {
				if (data[j] > data[j + 1]) {
					temp = data[j + 1];
					data[j + 1] = data[j];
					data[j] = temp;
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
