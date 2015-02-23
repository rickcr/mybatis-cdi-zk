package net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.domain.Employee;
import net.learntechnology.empmaint.mapper.EmployeeMapper;
import org.mybatis.cdi.Mapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

//ZK requires named beans
@Named("employeeService")
public class EmployeeService {

	@Inject
	@Mapper
	private EmployeeMapper employeeMapper;

	public List<Employee> getAllEmployees() {
		return employeeMapper.getAllEmployees();
	}

	public void updateEmployee(Employee emp) {
		employeeMapper.updateEmployee(emp);
	}

	public void deleteEmployee(Integer id) {
		employeeMapper.deleteEmployee(id);
	}

	public Employee getEmployee(Integer id) {
		return employeeMapper.getEmployee(id);
	}

	public void insertEmployee(Employee emp) {
		employeeMapper.insertEmployee(emp);
	}
}
