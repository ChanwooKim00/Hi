package ResultLogAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool4Result {
	Matcher matcher;

	/**
	 * keyList 를 받아서 로그를 담은 loglist 를 반환
	 * 
	 * @param arr(keyList)
	 * @param map(mapList)
	 * @return
	 */
	public ArrayList<String> makeLogList(ArrayList<String> arr, HashMap<String, ResultLogDto> map) {
		ArrayList<String> logList = new ArrayList<String>();
		for (int a = 0; a < arr.size(); a++) {
			setAveDto(map.get(arr.get(a)));
			logList.add(arr.get(a) + ", " + map.get(arr.get(a)).toString());
		}
		return logList;
	}

	/**
	 * 분석 시작 메소드
	 * 
	 * @param bufReader
	 * @param keyList
	 * @param logList
	 * @param map
	 */
	public void startAnalisysLog(BufferedReader bufReader, ArrayList<String> keyList, ArrayList<String> logList,
			HashMap<String, ResultLogDto> map) {
		String line = "";
		try {
			while ((line = bufReader.readLine()) != null) {
				setDataMap(line, map);
				keyList.add(getKey());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 조건(patternIn) 에 부합 하는 Matcher 를 반환
	 * 
	 * @param patternIn
	 * @param line
	 * @return matcher
	 */
	private boolean searchMatcher(String patternIn, String line) {
		boolean flag = true;
		Pattern pattern = Pattern.compile(patternIn);
		Matcher matcherIn = pattern.matcher(line);// String 에 분석 할 문자열 들어가야 되는듯.
		if (matcherIn.find()) {
			this.matcher = matcherIn;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 시간을 검색해서 시간 차이를 int 로 반환
	 * 0->전체  1->시작 날짜 2->시작 시간 3->시작 분 4->시작 초 5->끝 날짜 
	 *    6->끝 시간 7-> 끝 분 8-> 끝 초 9->contentLength
	 * @param line (검색 String)
	 * @return int (걸린 시간)
	 */
	private int countTime() {
		int res = 0;
		int startHour = Integer.parseInt(matcher.group(2));
		int endHour = Integer.parseInt(matcher.group(6));
		int startMin = Integer.parseInt(matcher.group(3));
		int endMin = Integer.parseInt(matcher.group(7));
		int startSec = Integer.parseInt(matcher.group(4));
		int endSec = Integer.parseInt(matcher.group(8));
		if (startHour == endHour) {
			int resultMin = (endMin - startMin) * 60;
			int resultSec = (endSec - startSec);
			res = resultMin + resultSec;
		}else if(startHour != endHour) {
			int resultHour = (endHour - startHour) * 360;
			int resultMin = (endMin - startMin) * 60;
			int resultSec = (endSec - startSec);
			res = resultHour + resultMin + resultSec;
		}
		return res * 1000;
	}

	/**
	 * line 을 받아서 map 에 넣는 메소드
	 * 
	 * @param line
	 */
	private void setDataMap(String line, HashMap<String, ResultLogDto> map) {
		searchMatcher(
				"([0-9.]{8}) ([0-9]{2}):([0-9]{2}):([0-9]{2}), ([0-9.]{8}) ([0-9]{2}):([0-9]{2}):([0-9]{2}), IF_[0-9a-z_-]{40,44}, ([0-9]{2,8})",
				line);
		if (line.equals(null) || line.equals(" ") || line.equals("\n")) {
			System.out.println("line 이 비었음");
		} else {
			// 날짜 + 분 까지
			String startTime = matcher.group(2) + ":" + matcher.group(3);
			int time = countTime();
			if (!map.containsKey(startTime)) {
				ResultLogDto dto = new ResultLogDto();
				setFirstDto(Integer.parseInt(matcher.group(9)), time, dto);
				map.put(startTime, dto);
			} else if (map.containsKey(startTime)) {
				setDto(Integer.parseInt(matcher.group(9)), time, map.get(startTime));
			}
		}
	}

	/**
	 * size 와 time 을 받아서 max / min / total 에 넣어 주는 메소드
	 * 
	 * @param size
	 * @param time
	 * @param dto  (넣을 dto)
	 */
	private void setDto(int size, int time, ResultLogDto dto) {
		dto.setMaxSize(size);
		dto.setMinSize(size);
		dto.setTotalSize(size);
		dto.setMaxTime(time);
		dto.setMinTime(time);
		dto.setTotalTime(time);
		dto.setCount();
	}

	/**
	 * size 와 time 을 받아서 max / firstMin / total 에 넣어 주는 메소드
	 * 
	 * @param size
	 * @param time
	 * @param dto  (넣을 dto)
	 */
	private void setFirstDto(int size, int time, ResultLogDto dto) {
		dto.setMaxSize(size);
		dto.setFirstMinSize(size);
		dto.setTotalSize(size);
		dto.setMaxTime(time);
		dto.setFirstMinTime(time);
		dto.setTotalTime(time);
		dto.setCount();
	}

	/**
	 * LogDTO 에 평균값 set하는 메소드
	 * 
	 * @param dto
	 */
	private void setAveDto(ResultLogDto dto) {
		dto.setAveSize();
		dto.setAveTime();
	}

	/**
	 * key 반환 해주는것
	 * 
	 * @return String
	 */
	private String getKey() {
		return matcher.group(2) + ":" + matcher.group(3);
	}

}
