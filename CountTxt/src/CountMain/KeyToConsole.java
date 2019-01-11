package CountMain;

import CountTxt.ConsoleInOut;
import CountTxt.ConsoleOut;
import CountTxt.Count;
import CountTxt.FileIn;
import CountTxt.FileInOut;
import CountTxt.FileOut;
import CountTxt.GetDate;
import CountTxt.KeyboardIn;

public class KeyToConsole {
	
	public static void main(String[] args) {
		Count cnt=new Count();
		GetDate getDate=new GetDate();
		ConsoleInOut consoleInOut= new ConsoleInOut();
		
			// 키보드입력 -> 콘솔 출력
		consoleInOut.consoleOut().out(getDate.getDate(), cnt.txtCount(consoleInOut.keyboardIn().in()));
	}
}
