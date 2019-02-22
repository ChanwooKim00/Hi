
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import GalileologAnalysis.*;
import ResultLogAnalysis.*;

public class StartMain extends FileData {
	static long startTime;
	static long preUseMemory;

	public static void main(String[] args) {

		Util util = new Util();
		startTime = util.startTime();
		preUseMemory = util.startUseMemory();
		
		Tool4Galileo toolG = new Tool4Galileo();
		Tool4Result toolR = new Tool4Result();
		
		ArrayList<String> logListG = new ArrayList<>();
		HashMap<String, GaLileoLogDto> mapG = new HashMap<String, GaLileoLogDto>();
		BufferedReader bufReader=null;
		try {
		bufReader=util.makeBufReader(getGalileoLogInPath(), getGalileoLogInName());
		logListG = toolG.startAnalysisLine(mapG, bufReader);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			util.closeBuf(bufReader);
		}
		util.makeLogFast(getGalileoLogOut(), logListG, false);
		// 갈릴래오 로그 분석 완료
		
		HashMap<String, ResultLogDto> mapR = new HashMap<String, ResultLogDto>();
		ArrayList<String> keyListR = new ArrayList<String>();
		ArrayList<String> logListR = new ArrayList<String>();
		try {
		bufReader=util.makeBufReader(getResultLogInPath(), getResultLogInName());
		toolR.startAnalisysLog(bufReader, keyListR, logListR, mapR);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			util.closeBuf(bufReader);
		}
		keyListR = util.removeDup(keyListR);
		logListR = toolR.makeLogList(keyListR, mapR);
		util.makeLogFast(getResultLogOut(), logListR, false);
		// result 로그 분석 완료
		util.end(startTime, preUseMemory);
	}

}