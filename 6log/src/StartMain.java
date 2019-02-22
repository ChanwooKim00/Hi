


import java.util.ArrayList;
import java.util.HashMap;

import GalileologAnalysis.*;
import ResultLogAnalysis.*;



public class StartMain {
	static long startTime;
	static long preUseMemory;

	public static void main(String[] args) {
		Util util = new Util();
		startTime=util.startTime();
		preUseMemory=util.startUseMemory();
		Tool4Galileo toolG = new Tool4Galileo();
		Tool4Result toolR = new Tool4Result();
		ArrayList<String> logListG = new ArrayList<>();
		HashMap<String, GaLileoLogDto> mapG = new HashMap<String, GaLileoLogDto>();
		logListG=toolG.startAnalysisLine(mapG, util.makeBufReader("C:\\Users\\meta\\Desktop\\교육\\6주차\\", "galileo.log"));
		util.makeLogFast("C:/Users/meta/Desktop/교육/6주차/result/result.log", logListG, false);
		//갈릴래오 로그 분석 완료
		HashMap<String, ResultLogDto> mapR = new HashMap<String, ResultLogDto>();
		ArrayList<String> keyListR = new ArrayList<String>();
		ArrayList<String> logListR = new ArrayList<String>();
		toolR.startAnalisysLog(util.makeBufReader("C:\\Users\\meta\\Desktop\\교육\\6주차\\result\\", "result.log"), keyListR, logListR, mapR);
		keyListR = util.removeDup(keyListR);
		logListR=toolR.makeLogList(keyListR, mapR);
		util.makeLogFast("C:/Users/meta/Desktop/교육/6주차/result/result2.log", logListR, false);
		//result 로그 분석 완료
		util.end(startTime,preUseMemory);
	}

	
}