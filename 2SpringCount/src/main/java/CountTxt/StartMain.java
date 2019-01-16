package CountTxt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("XmlFile/StartCount.xml");
		Container container = null;
		
		container=(Container)context.getBean("Container");
		container.getOut().out(container.getCount().txtCount(container.getIn().in()));

	}
}
