package CountTxt;

public class ConsoleOut implements OutDummy {
	public void out(String date, int in) {
		System.out.println("\r\n"+date+"//단어수:"+in);		
	}
}
