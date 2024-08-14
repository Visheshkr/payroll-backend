package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payroll.payroll_txn_user_login_log")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "status")
    private Integer status;

    @Column(name = "password_hash")
    private String password_hash;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "api_token")
    private String apiToken;

    @Column(name = "created_on")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp createdOn;

    @Column(name = "logout_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp logoutDate;



}
