package CountTxt;


public class ConsoleOut implements OutInterface {
	public void out(int in) {
		System.out.println("\r\n//단어수:"+in);		
	}
}
