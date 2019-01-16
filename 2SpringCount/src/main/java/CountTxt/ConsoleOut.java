package CountTxt;


public class ConsoleOut implements OutDummy {
	public void out(int in) {
		System.out.println("\r\n//단어수:"+in);		
	}
}
