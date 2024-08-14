package in.kpmg.hrms.payroll.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.UserMst;

@Repository
public interface UserRepo extends JpaRepository<UserMst, Integer> {


    @Query("SELECT u FROM UserMst u WHERE LOWER(u.userName)=:username AND u.isActive = true")
    UserMst getUserDataByUserName(String username);

    Optional<UserMst> findByUserNameIgnoreCaseAndIsActiveIsTrue(String username);

    public Optional<UserMst> findByUserName(String username);
}
