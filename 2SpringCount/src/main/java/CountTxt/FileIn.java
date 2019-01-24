package CountTxt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIn extends FileData implements InInterface {
	// File
	public String in() {
		String path=getFilePathIn();
		String name=getFileNameIn();
		String line="";	
		String line2="";
		String blank=" ";
		FileReader fileReader=null;
		BufferedReader bufReader=null;
		
		System.out.println("파일 이름:"+getFileNameIn()+"에 문자수 입력.");
		try {
			File file=new File(path+name);
			fileReader=new FileReader(file);
			bufReader=new BufferedReader(fileReader);
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
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(bufReader!=null) {
				try {
					bufReader.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(fileReader!=null) {
				try {
					fileReader.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}return line2;
}}
