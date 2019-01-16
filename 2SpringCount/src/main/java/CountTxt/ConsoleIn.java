package CountTxt;


import java.util.Scanner;

public class ConsoleIn implements InDummy {
	//Keyboard
	public String in() {
		Scanner sc = new Scanner(System.in);
		System.out.print("키보드 입력 :");
		String data=sc.nextLine();
		sc.close();
		return data;
	}
}
