package in.kpmg.hrms.payroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollGeneralTypeMaster;

@Repository
public interface GeneralTypeMstRepo extends JpaRepository<PayrollGeneralTypeMaster, Integer> {

}
