package net.learntechnology.empmaint;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryProvider {

	@Produces
	@ApplicationScoped
	public SqlSessionFactory produceFactory() {
		System.out.println("LOADING SqlSessionFactory");
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);

		} catch (IOException e) {
			e.printStackTrace();
		}
		//TODO use properties
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
			inputStream, "dev");
		System.out.println("LOADED SqlSessionFactory");
		return sqlSessionFactory;
	}
}
