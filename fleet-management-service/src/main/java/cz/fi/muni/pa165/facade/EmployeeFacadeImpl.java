package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.EmployeeDTO;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import cz.fi.muni.pa165.service.interfaces.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Jozef Krcho
 */

@Service
@Transactional
public class EmployeeFacadeImpl implements EmployeeFacade {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public EmployeeDTO findEmployeeById(Long id) {
        if (id == null) return null;
        Employee employee = employeeService.findById(id);
        return (employee == null) ? null : beanMappingService.mapTo(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findEmployeeByEmail(String email) {
        if (email == null || email.isEmpty()) return null;
        return beanMappingService.mapTo(employeeService.findByEmail(email), EmployeeDTO.class);
    }

    @Override
    public void registerEmployee(EmployeeDTO employeeDTO, String unencryptedPassword) {
        Employee employee = beanMappingService.mapTo(employeeDTO, Employee.class);
        employeeService.registerEmployee(employee, unencryptedPassword);
        employeeDTO.setId(employee.getId());
    }

    @Override
    public Collection<EmployeeDTO> findAllEmployee() {
        return beanMappingService.mapTo(employeeService.findAll(), EmployeeDTO.class);
    }

    @Override
    public boolean authenticate(EmployeeDTO employeeDTO, String password) {
        return employeeService.authenticate(beanMappingService.mapTo(employeeDTO, Employee.class), password);
    }

    @Override
    public void removeById(Long id) {
        employeeService.removeById(id);
    }
}
