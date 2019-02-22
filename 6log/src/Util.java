

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

public class Util {
	/**
	 * 파일경로(path)와 파일 이름(name) 을 입력받아 BufferedReader 에 넣어 반환
	 * 
	 * @param path
	 * @param name
	 * @return BufferedReader
	 */
	public BufferedReader makeBufReader(String path, String name) {
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
	 * arrayList 에 담아서 파일 쓰기 <빠름 but 메모리 많음>
	 * 
	 * @param path
	 * @param logList
	 */
	public void makeLogFast(String path, ArrayList<String> logList, boolean dup) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		File file = new File(path);
		try {
			fileWriter = new FileWriter(file, dup);
			bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < logList.size(); i++) {
				bufferedWriter.write(logList.get(i) + "\r\n");
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

	/**
	 * 로그 파일 만드는법 <느림 but 메모리 적음>
	 * 
	 * @param path
	 * @param line
	 */
	public void makeLogSlow(String path, String line) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		File file = new File(path);
		try {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(line + "\r\n");
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

	/**
	 * 정규표현식 테스트 용
	 *
	 * @param regx
	 * @param filePathIn
	 * @param fileNameIn
	 */
	public void TEST(String regx, String path, String name) {
		BufferedReader bufReader4All = makeBufReader(path, name);
		String line = null;
		ArrayList<String> threadArr = new ArrayList<>();

		try {
			while ((line = bufReader4All.readLine()) != null) {
				String test = searchPattern(regx, line, 0);
				if (test == null || test == "" || test == " ") {
					continue;
				} else {
					threadArr.add(test);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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
	 * 시작 시간 구해서 반환
	 * 
	 * @return
	 */
	public long startTime() {
		Runtime.getRuntime().gc();
		return System.currentTimeMillis();
	}

	/**
	 * 시작 메모리 사용량 구해서 반환
	 * 
	 * @return
	 */
	public long startUseMemory() {
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	/**
	 * 시작에서 끝날때 시간과 메모리 사용량 구해서 사용량 구해 systemout 으로 출력
	 * 
	 * @param startTime
	 * @param preUseMemory
	 */
	public void end(long startTime, long preUseMemory) {
		long afterUseMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long useMemory = (afterUseMemory - preUseMemory) / 1000;
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println(new Date() + " | Elapsed Time : " + elapsedTime + " | Use Memory : " + useMemory);
	}
	public void closeBuf(BufferedReader bufReader) {
		try {
			bufReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
