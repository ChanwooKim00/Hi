package CountTxt;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public class Count implements CountInterface, InitializingBean {
	Character charStr;

	public int txtCount(String txt) {
		int count = 0;
		for (int i = 0; i < txt.length(); i++) {
			if (txt.charAt(i) == charStr) {
				count = count + 1;
				if (txt.charAt(i + 1) == charStr) {
					i = i + 1;
				}
			}
		}
		count = count + 1;
		return count;
	}

	public Character getCharStr() {
		return charStr;
	}

	public void setCharStr(Character charStr) {
		this.charStr = charStr;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Split Char='"+charStr+"'");
		
	}
}
