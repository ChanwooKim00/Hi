package GalileologAnalysis;

public class GaLileoLogDto {
	String start;
	String ESB_TRAN_ID;
	String Length;
	String galileoCallTime;
	String beforeMarshalling;
	String Marshalling;
	String InvokingGalileo;
	String Unmarshalling;
	String end;
	public boolean checkNull() {
		if(start == null || ESB_TRAN_ID == null || Length == null || Length.equals(" ") || Length.equals("") || galileoCallTime == null || beforeMarshalling == null ||
				Marshalling == null || InvokingGalileo == null || Unmarshalling == null || end == null) {
			return false;
		}
		return true;
	}

	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getESB_TRAN_ID() {
		return ESB_TRAN_ID;
	}
	public void setESB_TRAN_ID(String eSB_TRAN_ID) {
		ESB_TRAN_ID = eSB_TRAN_ID;
	}
	public String getLength() {
		return Length;
	}
	public void setLength(String length) {
		Length = length;
	}
	public String getGalileoCallTime() {
		return galileoCallTime;
	}
	public void setGalileoCallTime(String galileoCallTime) {
		this.galileoCallTime = galileoCallTime;
	}
	public String getBeforeMarshalling() {
		return beforeMarshalling;
	}
	public void setBeforeMarshalling(String beforeMarshalling) {
		this.beforeMarshalling = beforeMarshalling;
	}
	public String getMarshalling() {
		return Marshalling;
	}
	public void setMarshalling(String marshalling) {
		Marshalling = marshalling;
	}
	public String getInvokingGalileo() {
		return InvokingGalileo;
	}
	public void setInvokingGalileo(String invokingGalileo) {
		InvokingGalileo = invokingGalileo;
	}
	public String getUnmarshalling() {
		return Unmarshalling;
	}
	public void setUnmarshalling(String unmarshalling) {
		Unmarshalling = unmarshalling;
	}
	public String toString() {
		return "LogDto [start=" + start + ", ESB_TRAN_ID=" + ESB_TRAN_ID + ", Length=" + Length + ", galileoCallTime="
				+ galileoCallTime + ", beforeMarshalling=" + beforeMarshalling + ", Marshalling=" + Marshalling
				+ ", InvokingGalileo=" + InvokingGalileo + ", Unmarshalling=" + Unmarshalling + ", end=" + end + "]";
	}
	public String toString2() {
		return start + ", " + end + ", " +ESB_TRAN_ID + ", " + Length + ", "
				+ galileoCallTime + ", " + beforeMarshalling + ", " + Marshalling
				+ ", " + InvokingGalileo + ", " + Unmarshalling ;
	}
	
	
}
