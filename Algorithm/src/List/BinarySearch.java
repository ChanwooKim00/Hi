package List;

public class BinarySearch {
	public static int search(int[] data, int key) {
		int mid;
		int left=0;
		int right=data.length-1;
		
		while(right >= left) {
			mid=(right+left)/2;
			if(key==data[mid]) {
				System.out.println(key + "가 "+(mid+1)+" 번 째에 있습니다.");
				return mid;
			}
			if(key<data[mid]) {
				right=mid-1;
			}else {
				left=mid+1;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		BubbleSort sort=new BubbleSort();
		int[] testData= {24,12,66,1,4};		
		sort.sort(testData);
		System.out.println(search(testData, 12));
		for(int i=0;i<testData.length;i++) {
			System.out.print(testData[i]+", ");
		}
	}
}
