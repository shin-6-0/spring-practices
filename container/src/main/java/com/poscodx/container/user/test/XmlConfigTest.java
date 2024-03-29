package com.poscodx.container.user.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.poscodx.container.user.User;

public class XmlConfigTest {

	public static void main(String[] args) {
		// XML Auto Configuration(Annotation Scanning)
		//testApplicationContext01();	

		// XML Auto Configuration(Annotation Scanning)
		testApplicationContext02();			
		
		// XML Auto Configuration(Annotation Scanning)
		// testBeanFactory01();
		
		// XML Bean Configuration(Explicit Scanning)
		//testBeanFactory02();		
		
	}

	private static void testApplicationContext01() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/poscodx/container/user/applicationContext01.xml");
		
		User user = null;
		//Type으로 빈 가져오기
		user = ac.getBean(User.class);
		System.out.println(user.getName());
		
		//id로 빈 가져오기
		//Annotation Scan(Auto Configuration)에서는 Bean id 가 자동으로 부여된다. 
		user = (User) ac.getBean("user");
		System.out.println(user.getName());
	}

	private static void testApplicationContext02() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/poscodx/container/user/applicationContext02.xml");
		
		User user = null;
		
		//Type으로 빈 가져오기
		//user = ac.getBean(User.class);
		//System.out.println(user.getName());
		
		//id로 빈 가져오기
		//Annotation Scan(Auto Configuration)에서는 Bean id 가 자동으로 부여되지만,
		//Bean Configuration 에서는 가져오지 않고 오류가 발생한다 -> No bean named 'user' available
		//bean id = "user"라고 정해주어야함
		user = (User) ac.getBean("user");
		System.out.println(user.getName());
		
		//name으로 가져오기
		user = (User) ac.getBean("usr");
		System.out.println(user.getName());
		
		
		// Type으로 빈 가져오기
		// 같은 타입의 빈이 2개이상 있으면 Type으로 가져오기는 실패
		user = ac.getBean("user2", User.class);
		System.out.println(user);
		
		// 파라미터 2개인 생성자로 생성된 빈I 가져오기
		user = ac.getBean("user3", User.class);
		System.out.println(user);

		// 파라미터 2개인 생성자로 생성된 빈II 가져오기
		user = ac.getBean("user4", User.class);
		System.out.println(user);

		// setter를 사용한 빈I 가져오기
		user = ac.getBean("user5", User.class);
		System.out.println(user);

		// setter를 사용한 빈II 가져오기: DI
		user = ac.getBean("user6", User.class);
		System.out.println(user);

		// setter를 사용한 빈III 가져오기: Collection Property
		user = ac.getBean("user7", User.class);
		System.out.println(user);
	}

	private static void testBeanFactory02() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("com/poscodx/container/user/applicationContext02.xml"));
		User user = bf.getBean(User.class);
		System.out.println(user.getName());
	}
}
