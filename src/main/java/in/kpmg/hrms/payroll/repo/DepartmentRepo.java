package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

    Optional<Department> findByDeptIdAndIsActiveIsTrue(Integer deptId);
    List<Department> findAllByIsActiveIsTrue();

}
