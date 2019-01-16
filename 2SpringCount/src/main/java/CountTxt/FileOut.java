package CountTxt;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOut extends FileData implements OutInterface{
	public void out(int in) {
		String filePath=getFilePathOut().concat(getFileNameOut());
		try {
			File file=new File(filePath);
			FileWriter fileWriter=new FileWriter(file, true);
			fileWriter.write("\r\n//단어수:"+String.valueOf(in));
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
