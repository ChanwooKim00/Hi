package CountMain;

import CountTxt.ConsoleInOut;
import CountTxt.ConsoleOut;
import CountTxt.Count;
import CountTxt.FileIn;
import CountTxt.FileInOut;
import CountTxt.FileOut;
import CountTxt.GetDate;
import CountTxt.KeyboardIn;

public class KeyToFile {
	
	public static void main(String[] args) {
		Count cnt=new Count();
		GetDate getDate=new GetDate();
		FileInOut fileInOut=new FileInOut();
		ConsoleInOut consoleInOut= new ConsoleInOut();
		

			// 키보드입력 -> 파일 출력
		fileInOut.getFileOut().out(getDate.getDate(), cnt.txtCount(consoleInOut.keyboardIn().in()));
	}
}
