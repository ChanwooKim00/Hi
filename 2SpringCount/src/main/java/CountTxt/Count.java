package CountTxt;


import java.util.List;

public class Count implements CountInterface{
	public int txtCount(String txt) {	
		int count=0;
		for(int i=0; i<txt.length();i++) {
			if(txt.charAt(i)==' ') {		
				count=count+1;
				if(txt.charAt(i+1)==' ') {
					i=i+1;
				}
			}
		}
		count=count+1;
		return count;
	}
}
