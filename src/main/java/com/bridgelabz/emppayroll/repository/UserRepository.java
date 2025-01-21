package com.bridgelabz.emppayroll.repository;

import com.bridgelabz.emppayroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Employee,Long> {
}
