package resultLogAnalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util4Result {
	/**
	 * 파일경로(path)와 파일 이름(name) 을 입력받아 BufferedReader 에 넣어 반환
	 * 
	 * @param path (파일경로 + /)
	 * @param name (파일이름)
	 * @return BufferedReader
	 */
	BufferedReader makeBufReader(String path, String name) {
		FileReader fileReader = null;
		BufferedReader bufReader = null;
		try {
			File file = new File(path + name);
			fileReader = new FileReader(file);
			bufReader = new BufferedReader(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufReader;
	}

	/**
	 * 정규표현식 테스트 용
	 *
	 * @param regx       (정규표현식)
	 * @param filePathIn (파일경로 + /)
	 * @param fileNameIn (파일이름)
	 * @return system.out.println
	 */
	public void TEST(String regx, String path, String name, int where) {
		BufferedReader bufReader = makeBufReader(path, name);
		String line = null;
		ArrayList<String> threadArr = new ArrayList<>();

		try {
			while ((line = bufReader.readLine()) != null) {
				String test = searchPattern(regx, line, where);
				if (test == null || test == "" || test == " ") {
					continue;
				} else {
					threadArr.add(test);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		consoleCheck(threadArr);
	}

	public void consoleCheck(ArrayList<String> threadArr) {
		for (int a = 0; a < threadArr.size(); a++) {
			System.out.println(a + "번 쨰        " + threadArr.get(a));
		}
	}

	/**
	 * 조건(Pattern) 이 들어 있는지 확인 들어있다면 string 반환 / 없으면 "" 반환
	 * 
	 * @param patternIn (조건)
	 * @param line      (분석할 String)
	 * @return String
	 */
	private String searchPattern(String patternIn, String line, int index) {
		String result = "";

		Pattern pattern = Pattern.compile(patternIn);
		Matcher matcher = pattern.matcher(line);// String 에 분석 할 문자열 들어가야 되는듯.
		if (matcher.find()) {
			result = matcher.group(index);
		}
		return result;
	}

	/**
	 * ArrayList 중복 제거
	 *
	 * @param dataList
	 * @return ArrayList
	 */
	public ArrayList<String> removeDup(ArrayList<String> arr) {
		HashSet<String> arr2 = new HashSet<String>(arr);
		ArrayList<String> result = new ArrayList<String>(arr2);
		return result;
	}

	/**
	 * arrayList 에 담아서 파일 쓰기 <빠름 but 메모리 많음>
	 * 
	 * @param path
	 * @param logList
	 * @param dup     (true = 겹쳐쓰기 / false = 새로쓰기)
	 */
	public void makeLogFast(String path, ArrayList<String> logList, boolean dup) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		File file = new File(path);
		try {
			fileWriter = new FileWriter(file, dup);
			bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < logList.size(); i++) {
				bufferedWriter.write("\r\n" + logList.get(i));
			}
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
