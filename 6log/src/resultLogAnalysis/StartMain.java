package resultLogAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class StartMain {
	static long startTime;
	static long preUseMemory;

	public static void main(String[] args) {
		start();
		Tool4Result toolR = new Tool4Result();
		Util4Result utilR = new Util4Result();

		HashMap<String, ResultLogDto> mapR = new HashMap<String, ResultLogDto>();
	
		ArrayList<String> keyListR = new ArrayList<String>();
		ArrayList<String> logListR = new ArrayList<String>();

		toolR.startAnalisysLog(utilR.makeBufReader("C:\\Users\\meta\\Desktop\\교육\\6주차\\result\\", "result.log"), keyListR, logListR, mapR);
		keyListR = utilR.removeDup(keyListR);
		logListR=toolR.makeLogList(keyListR, mapR);
		utilR.makeLogFast("C:/Users/meta/Desktop/교육/6주차/result/result2.log", logListR, false);
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
