package kz.iitu.office.reservation.system.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.iitu.office.reservation.system.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeInfoService implements UserDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @HystrixCommand(
            fallbackMethod = "getListEmployeeFallback",
            threadPoolKey = "getListEmployee",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50")
            }
    )
    public List<Employee> getListEmployee() {
        return restTemplate.exchange(
                "http://office-employee-info:8081/employee",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {}).getBody();
    }

    public void addEmployee(Employee e) {
        e.setPassword(passwordEncoder.encode(e.getPassword()));
        restTemplate.postForEntity("http://office-employee-info:8081/employee/add", e, Employee.class);
    }

    public void updateEmployee(Long id, Employee employee) {
        restTemplate.put("http://office-employee-info:8081/employee/update/" + id, employee, Employee.class);
    }

    public void updateName(Long id, String name) {
        restTemplate.patchForObject("http://office-employee-info:8081/employee/update/" + id + "?name=" + name, null, String.class);
    }

    public void deleteEmployee(Long id) {
        restTemplate.delete("http://office-employee-info:8081/employee/delete/" + id);
    }

    public void updateRole(Long id, String role) {
        restTemplate.patchForObject("http://office-employee-info:8081/employee/role/" + id + "?role=" + role, null, String.class);
    }

    public Employee getEmployeeById(Long id) {
        return restTemplate.exchange("http://office-employee-info:8081/employee/e?id=" + id, HttpMethod.GET,
                null, Employee.class).getBody();
    }

    public List<Employee> getListEmployeeFallback() {
        List<Employee> list = new ArrayList<>();
        Employee e = new Employee();
        e.setUsername("fallback");
        e.setPassword("fallback");
        list.add(e);
        return list;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee employee = restTemplate.getForEntity("http://office-employee-info:8081/employee/" + s, Employee.class).getBody();

        if (employee == null) {
            throw new UsernameNotFoundException("Member: " + s + " not found!");
        }
        return employee;
    }
}
