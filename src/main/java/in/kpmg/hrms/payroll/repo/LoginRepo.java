package in.kpmg.hrms.payroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.UserLogin;

@Repository
public interface LoginRepo extends JpaRepository<UserLogin, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(1) FROM payroll_txn_user_login_log WHERE user_name= :username and password_hash = :password and status = 1")
    Integer checkPasswordHash(String username, String password);
}
