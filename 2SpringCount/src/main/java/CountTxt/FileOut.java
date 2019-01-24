package CountTxt;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOut extends FileData implements OutInterface{
	//File
	public void out(int in) {
		String filePath=getFilePathOut().concat(getFileNameOut());
		FileWriter fileWriter = null;
		try {
			File file=new File(filePath);
			fileWriter=new FileWriter(file, true);
			fileWriter.write("\r\n//단어수:"+String.valueOf(in));
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fileWriter!=null) {
				try {
					fileWriter.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(filePath+" 에 저장 완료.");
	}

}
