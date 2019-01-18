package CountTxt;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class ContainObject implements InitializingBean, DisposableBean{
	InInterface In;
	OutInterface Out;
	CountInterface Count;

	public InInterface getIn() {
		return In;
	}
	public void setIn(InInterface in) {
		In = in;
	}
	public OutInterface getOut() {
		return Out;
	}
	public void setOut(OutInterface out) {
		Out = out;
	}
	public CountInterface getCount() {
		return Count;
	}

	public void setCount(CountInterface count) {
		Count = count;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("ContainObject START");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("ContainObject END");
	}

}




