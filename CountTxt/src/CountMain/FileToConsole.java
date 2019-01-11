package CountMain;

import CountTxt.ConsoleInOut;
import CountTxt.ConsoleOut;
import CountTxt.Count;
import CountTxt.FileIn;
import CountTxt.FileInOut;
import CountTxt.FileOut;
import CountTxt.GetDate;
import CountTxt.KeyboardIn;

public class FileToConsole {
	
	public static void main(String[] args) {
		Count cnt=new Count();
		GetDate getDate=new GetDate();
		FileInOut fileInOut=new FileInOut();
		ConsoleInOut consoleInOut= new ConsoleInOut();
		
			// 파일입력 -> 콘솔출력
		consoleInOut.consoleOut().out(getDate.getDate(), cnt.txtCount(fileInOut.getFileIn().in()));
	}
}
