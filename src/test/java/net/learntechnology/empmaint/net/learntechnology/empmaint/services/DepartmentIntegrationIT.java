package net.learntechnology.empmaint.net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.BaseIntegrationIT;
import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.services.DepartmentService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

@RunWith(Arquillian.class)
public class DepartmentIntegrationIT extends BaseIntegrationIT {
	private final static Logger logger = LoggerFactory.getLogger(DepartmentIntegrationIT.class);

	/* Still new to using arquillian. Following the help I'm getting here
	https://developer.jboss.org/message/919798
	 */
	@Deployment
	public static WebArchive createDeployment() {

		WebArchive archive = ShrinkWrap.create(ZipImporter.class, "mybatis-cdi-zk-1.0.war").importFrom(new File("target/mybatis-cdi-zk-1.0.war"))
		            .as(WebArchive.class);
		String key = "org.mybatis:mybatis-cdi";
		JavaArchive[] libraries = Maven.resolver().loadPomFromFile("pom.xml").resolve(key).withTransitivity().as(JavaArchive.class);
		archive.addAsLibraries(libraries);

//		System.out.println("Archive = "+archive.toString(true));
//		archive.as(ZipExporter.class).exportTo(
//		    new File("/Users/rick/projects/mybatis-cdi-zk/temp/departmentTest.zip"), true);
		return archive;
	}

	@Inject
	private DepartmentService departmentService;

	@Test
	public void getDepartmentsTest() {
		List<Department> departments = departmentService.getAllDepartments();
		for(Department d: departments) {
			logger.debug("Dept: {}", d);
		}
		Assert.assertTrue(departments.size() > 1);
	}
}
