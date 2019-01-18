package CountTxt;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public class Count implements CountInterface, InitializingBean {
	Character charSplit;
	public Count(Character charSplit) {
		this.charSplit=charSplit;
	}
	public int txtCount(String txt) {
		int count = 0;
		for (int i = 0; i < txt.length(); i++) {
			if (txt.charAt(i) == charSplit) {
				count = count + 1;
				if (txt.charAt(i + 1) == charSplit) {
					i = i + 1;
				}
			}
		}
		count = count + 1;
		return count;
	}

	public Character getCharStr() {
		return charSplit;
	}

	public void setCharStr(Character charStr) {
		this.charSplit = charStr;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Split Char='"+charSplit+"'");
		
	}
}

