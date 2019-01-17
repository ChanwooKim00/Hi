package CountTxt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartMain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("XmlFile/StartCount.xml");
		ContainObject container = null;
		
		container=context.getBean("Container",ContainObject.class);
		container.getOut().out(container.getCount().txtCount(container.getIn().in()));

	}
}
 