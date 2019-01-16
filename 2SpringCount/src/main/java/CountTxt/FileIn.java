package CountTxt;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIn extends FileData implements InInterface{
	//File 
	public String in() {
		String path=getFilePathIn();
		String name=getFileNameIn();
		String line="";	
		String line2="";
		String blank=" ";
//		List<String> wordList = new ArrayList<String>();
		
		System.out.println("파일 이름:"+getFileNameIn()+"에 문자수 입력.");
		try {
			File file=new File(path+name);
			FileReader fileReader=new FileReader(file);
			BufferedReader bufReader=new BufferedReader(fileReader);
//			while((line=bufReader.readLine()) != null) {
//				wordList.addAll( Arrays.asList(line.split(" ")));
//			}
			while((line=bufReader.readLine()) != null) {
				if(!line2.equals("")) {
					line2=line+blank+line2;
				}else {
					line2=line+line2;
				}
			}
			bufReader.close();
			fileReader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return line2;
	}
}
