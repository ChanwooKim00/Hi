package CountTxt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class StartMain {
	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("XmlFile/StartCount.xml");
		GenericXmlApplicationContext context=new GenericXmlApplicationContext("classpath:XmlFile/StartCount.xml");
		
		ContainObject containObject = null;
		
		containObject=context.getBean("Container",ContainObject.class);
		
		context.close();
		
		containObject.getOut().out(containObject.getCount().txtCount(containObject.getIn().in()));
		
	}
}
 



