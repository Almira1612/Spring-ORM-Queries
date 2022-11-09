package com.cydeo.repository;

import com.cydeo.entity.Employee;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Display all employees with email address
    List<Employee> findByEmail(String email);

    //Display all employees with first name ' ' and last name ' ', also show all employees with email address
    List<Employee> findByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email);

    //Display all employees that first name is not ' '
    List<Employee> findByFirstNameIsNot(String firstName);

    //Display all employees where last name starts with ' '
    List<Employee> findByLastNameStartingWith(String pattern);

    //Display all employees with salaries higher than ' '
    List<Employee> findBySalaryGreaterThan(Integer salary);

    //display all employees with salaries less than ' '
    List<Employee> findBySalaryLessThanEqual(Integer salary);

    //display all employees that has been hired between ' ' and ' '
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    //display all employees where salaries greater and equal to ' ' in order
    List<Employee> findBySalaryGreaterThanEqualOrderBySalaryDesc(Integer salary);

    //Display top unique 3 employees that is making less than ' '
    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary);

    //Display all employees that do not have email address
    List<Employee> findByEmailIsNull();

    //JPQL
    @Query("SELECT e FROM Employee e WHERE e.email = 'dtrail8@tamu.edu'")
    Employee getEmployeeDetail();

    @Query("SELECT e.salary FROM Employee e WHERE e.email = 'bhendricksi@privacy.gov.au'")
    Integer getEmployeeSalary();

    @Query("SELECT e FROM Employee e WHERE e.email=?1")
    Optional<Employee> getEmployeeDetail(String email);

    @Query("SELECT e FROM Employee e WHERE e.email=?1 AND e.salary=?2")
    Employee getEmployeeDetail(String email, int salary);

    //Not equal
    @Query("SELECT e FROM Employee e WHERE e.salary <> ?1")
    List<Employee> getEmployeeSalaryNotEqual(int salary);

    //Like
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE ?1")
    List<Employee> getEmployeeFirstNameLike(String pattern);


    //Less than
    @Query("SELECT e FROM Employee e WHERE e.salary< ?1")
    List<Employee> getEmployeeSalaryLessThan(int salary);

    //Greater than
    @Query("SELECT e FROM Employee e WHERE e.salary> ?1")
    List<Employee> getEmployeeSalaryGreaterThan(int salary);

    //Before
    @Query("SELECT e FROM Employee e WHERE e.hireDate > ?1")
    List<Employee> getEmployeeHireDateBefore(LocalDate date);

    //Between
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN ?1 AND ?2")
    List<Employee> getEmployeeSalaryBetween(int salary1, int salary2);

    //Null
    @Query("SELECT e FROM Employee e WHERE e.email IS NULL")
    List<Employee> getEmployeeEmailIsNull();

    //Not Null
    @Query("SELECT e FROM Employee e WHERE e.email IS NOT NULL")
    List<Employee> getEmployeeEmailIsNotNull();

    //Sorting in ascending order
    @Query("SELECT e FROM Employee e ORDER BY e.salary")
    List<Employee> getEmployeeSalaryOrderAsc();

    //Sorting in descending order
    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
    List<Employee> getEmployeeSalaryOrderDesc();

    //Native query

    @Query(value = "SELECT * FROM employee WHERE salary ?1", nativeQuery = true)
    List<Employee> readEmployeeDetailBySalary(int salary);

    @Query("select e from Employee e where e.salary = :salary")
    List<Employee> getEmployeeSalary(@Param("salary") int salary);


}
