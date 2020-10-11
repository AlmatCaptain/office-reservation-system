package kz.iitu.office.reservation.system.service;

import kz.iitu.office.reservation.system.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeInfoService implements UserDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Employee[] getListEmployee() {
        return restTemplate.getForEntity("http://localhost:8081/employee", Employee[].class).getBody();
    }

    public void addEmployee(Employee e) {
        e.setPassword(passwordEncoder.encode(e.getPassword()));
        restTemplate.postForEntity("http://localhost:8081/employee/add", e, Employee.class);
    }

    public void updateEmployee(Long id, Employee employee) {
        restTemplate.put("http://localhost:8081/employee/update/" + id, employee, Employee.class);
    }

    public void updateName(Long id, String name) {
        restTemplate.patchForObject("http://localhost:8081/employee/update/" + id + "?name=" + name, null, String.class);
    }

    public void deleteEmployee(Long id) {
        restTemplate.delete("http://localhost:8081/employee/delete/" + id);
    }

    public void updateRole(Long id, String role) {
        restTemplate.patchForObject("http://localhost:8081/employee/role/" + id + "?role=" + role, null, String.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee employee = restTemplate.getForEntity("http://localhost:8081/employee/" + s, Employee.class).getBody();

        if (employee == null) {
            throw new UsernameNotFoundException("Member: " + s + " not found!");
        }
        return employee;
    }
}
