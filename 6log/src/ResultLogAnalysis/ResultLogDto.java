package ResultLogAnalysis;

public class ResultLogDto {
	private int count=0;
	private int totalTime=0;
	private int aveTime;
	private int minTime;
	private int maxTime;
	private int totalSize=0;
	private int aveSize;
	private int minSize;
	private int maxSize;
	
	
	public int getCount() {
		return count;
	}
	public void setCount() {
		this.count = this.count+1;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int time) {
		this.totalTime = this.totalTime+time;
	}
	public int getAveTime() {
		return aveTime;
	}
	public void setAveTime() {
		this.aveTime = this.totalTime/this.count;
	}
	public int getMinTime() {
		return minTime;
	}
	public void setFirstMinTime(int time) {
		this.minTime=time;
	}
	public void setMinTime(int time) {
		if(this.minTime>time) {
			this.minTime = time;	
		}
	}
	public int getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(int time) {
		if(this.maxTime<time) {
			this.maxTime = time;
		}
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize =this.totalSize + totalSize;
	}
	public int getAveSize() {
		return aveSize;
	}
	public void setAveSize() {
		this.aveSize = this.totalSize/this.count;
	}
	public int getMinSize() {
		return minSize;
	}
	public void setFirstMinSize(int size) {
		this.minSize=size;
	}
	public void setMinSize(int size) {
		if(this.minSize>size) {
			this.minSize = size;
		}
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int size) {
		if(this.maxSize<size) {
			this.maxSize = size;
		}
	}
	
	@Override
	public String toString() {
		return  count + ", " + ", " + aveTime + ", " + minTime
				+ ", " + maxTime + ", " + ", " + aveSize + ", " + minSize
				+ ", " + maxSize;
	}
	
}
