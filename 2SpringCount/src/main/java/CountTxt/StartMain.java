package CountTxt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartMain {
	Container startCommon=new Container();
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("XmlFile/StartCount.xml");
		
		Container container=(Container)context.getBean("Container");
		container.getOut().out(container.getCount().txtCount(container.getIn().in()));
	}

}
