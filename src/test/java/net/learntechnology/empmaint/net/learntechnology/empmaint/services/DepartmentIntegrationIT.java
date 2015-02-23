package net.learntechnology.empmaint.net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.BaseIntegrationIT;
import net.learntechnology.empmaint.SqlSessionFactoryProvider;
import net.learntechnology.empmaint.domain.BaseVO;
import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.mapper.DepartmentMapper;
import net.learntechnology.empmaint.services.DepartmentService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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

	/* no idea how to get this stuff to work . frustrating
	 */
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap.create(WebArchive.class)
			.addClass(SqlSessionFactoryProvider.class)
			.addClass(BaseVO.class)
			.addClass(Department.class)
			.addClass(DepartmentService.class)
			.addClass(DepartmentMapper.class)
			.addAsManifestResource(new File("src/main/webapp//META-INF/context.xml"))
			.addAsResource("mybatis-config.xml").addAsResource("mybatis-config.properties")
			.addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
			;
		System.out.println("Archive = "+archive.toString(true));
		archive.as(ZipExporter.class).exportTo(
		    new File("/Users/rick/projects/mybatis-cdi-zk/temp/departmentTest.zip"), true);
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
