package com.learning.demo.repo;

import com.learning.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Employee Repository: Provides Methods to Store Employee Data IN Database
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
