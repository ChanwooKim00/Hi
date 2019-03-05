package GalileologAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool4Galileo {
	Matcher matcher;
	String threadId = null;

	/**
	 * 분석 시작 메소드
	 * 
	 * @param outMap
	 * @param bufReader
	 * @return ArrayList<String>
	 */
	public ArrayList<String> startAnalysisLine(HashMap<String, GaLileoLogDto> outMap, BufferedReader bufReader) {
		String line = "";
		ArrayList<String> logList = new ArrayList<>();
		try {
			while ((line = bufReader.readLine()) != null) {
				boolean flag = analysisLine(line, outMap, logList);
				if (!flag) {
					flag = analysisStopWatchLine(outMap.get(threadId), bufReader);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logList;
	}

	/**
	 * line을 분석하여 dto 에 저장
	 * 
	 * @param line
	 * @param outMap
	 * @param logList
	 * @return
	 */
	private boolean analysisLine(String line, HashMap<String, GaLileoLogDto> outMap, ArrayList<String> logList) {
		threadId = patternSearch("eclipse.galileo-bean-thread-([0-9]{8})", line, 1);
		boolean flag = true;

		if (line.contains("##galileo_bean start") && !outMap.containsKey(threadId)) {
			String startTime = patternSearch("([0-9.]{8}) ([0-9]{2}):([0-9]{2}):([0-9]{2})", line, 0);
			GaLileoLogDto dto = new GaLileoLogDto();
			dto.setStart(startTime);
			outMap.put(threadId, dto);
		} // "##galileo_bean start" END
		else if (line.contains("ESB_TRAN_ID") && outMap.containsKey(threadId)) {
			outMap.get(threadId).setESB_TRAN_ID(patternSearch("IF_([0-9a-z_-]{40,44})", line, 0));
		} // "ESB_TRAN_ID" END
		else if (line.contains("Content-Length") && outMap.containsKey(threadId)) {
			outMap.get(threadId).setLength(patternSearch("Content-Length:([0-9]{2,10})", line, 1));
		} // "Content-Length" END
		else if (line.contains("#galileo call time") && outMap.containsKey(threadId)) {
			outMap.get(threadId).setGalileoCallTime(patternSearch("#galileo call time:([0-9]{1,20})", line, 1));
		} // "#galileo call time" END
		else if (line.contains("StopWatch") && outMap.containsKey(threadId)) {
			flag = false;
		} // "StopWatch" END
		else if (line.contains("##galileo_bean end") && outMap.containsKey(threadId)) {
			GaLileoLogDto dto = outMap.get(threadId);
			dto.setEnd(patternSearch("([0-9.]{8}) ([0-9]{2}):([0-9]{2}):([0-9]{2})", line, 0));
			if (dto.checkNull() == true) {
				logList.add(dto.toString2());
				outMap.remove(threadId);
			}
		}
		return flag;
	}

	/**
	 * stopWatch 밑 부분 분석 / dto 에 저장
	 * 
	 * @param dto
	 * @param bufReader
	 * @return boolean flag 값 true 로 변경을 위해
	 */
	private boolean analysisStopWatchLine(GaLileoLogDto dto, BufferedReader bufReader) {
		String line = "";
		boolean flag = false;
//		ArrayList<String> checkNull = new ArrayList<String>();

		//여기서 while 문으로 검색 하지 말고 한줄 한줄 하고 만약에 하나라도 없으면, 모두 null 로 넣고 마무리!
		try {
			while (line.contains(" 4. Unmarshalling and Send to CmmMod Server")) {
				line = bufReader.readLine();
				String result = null;

				if (line.contains("1. Before Marshalling")) {
					result = patternSearch("([0-9]{5})", line, 0);
					dto.setBeforeMarshalling(result);
//					checkNull.add(result);
				} else if (line.contains("2. Marshalling")) {
					result = patternSearch("([0-9]{5})", line, 0);
					dto.setMarshalling(result);
//					checkNull.add(result);
				} else if (line.contains("3. Invoking galileo")) {
					result = patternSearch("([0-9]{5})", line, 0);
					dto.setInvokingGalileo(result);
//					checkNull.add(result);
				} else if (line.contains("4. Unmarshalling and Send to CmmMod Server")) {
					result = patternSearch("([0-9]{5})", line, 0);
					dto.setUnmarshalling(result);
//					checkNull.add(result);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		if (checkNull.size() > 3) {
//			setNull(dto);
//		}
		flag = true;
		return flag;
	}

	private String patternSearch(String patternIn, String str, int index) {
		String result = "";

		Pattern pattern = Pattern.compile(patternIn);
		Matcher matcher = pattern.matcher(str);// String 에 분석 할 문자열 들어가야 되는듯.
		if (matcher.find()) {
			result = matcher.group(index);
		}
		return result;
	}

	/**
	 * dto 값 4가지 를 모두 null 로 바꿔 주는것
	 * 
	 * @param dto
	 */
	private void setNull(GaLileoLogDto dto) {
		dto.setBeforeMarshalling(null);
		dto.setMarshalling(null);
		dto.setInvokingGalileo(null);
		dto.setUnmarshalling(null);
	}
}
