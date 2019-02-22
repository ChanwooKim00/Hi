package logAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import resultLogAnalysis.ResultLogDto;
import resultLogAnalysis.Tool4Result;
import resultLogAnalysis.Util4Result;

public class StartMain {
	static long startTime;
	static long preUseMemory;

	public static void main(String[] args) {
		start();
		Util util = new Util();
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
		end();
	}

	public static void start() {
		Runtime.getRuntime().gc();
		startTime = System.currentTimeMillis();
		preUseMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	public static void end() {
		long afterUseMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long useMemory = (afterUseMemory - preUseMemory) / 1000;
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println(new Date() + " | Elapsed Time : " + elapsedTime + " | Use Memory : " + useMemory);
	}
}