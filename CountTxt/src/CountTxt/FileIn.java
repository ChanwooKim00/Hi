package CountTxt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIn extends FileData {
	//File 
	public List<String> in() {
		String path=getFilePathIn();
		String name=getFileNameIn();
		String line="";		
		List<String> wordList = new ArrayList<String>();
		
		System.out.println("파일 이름:"+getFileNameIn()+"에 문자수 입력.");
		try {
			File file=new File(path+name);
			FileReader fileReader=new FileReader(file);
			BufferedReader bufReader=new BufferedReader(fileReader);
			while((line=bufReader.readLine()) != null) {
				wordList.addAll( Arrays.asList(line.split(" ")));
			}
			bufReader.close();
			fileReader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return wordList;
	}
}
