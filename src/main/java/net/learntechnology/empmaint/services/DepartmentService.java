package net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.mapper.DepartmentMapper;
import org.mybatis.cdi.Mapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

//ZK requires named beans
@Named("departmentService")
public class DepartmentService {

	@Inject
	@Mapper
	private DepartmentMapper departmentMapper;

	public List<Department> getAllDepartments() {
		return departmentMapper.getAllDepartments();
	}
}
