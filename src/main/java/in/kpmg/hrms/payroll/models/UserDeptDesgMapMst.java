package in.kpmg.hrms.payroll.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payroll.payroll_scheme_user_dept_desgn_mpng")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDeptDesgMapMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "crt_by")
    private Integer createdBy;

    @Column(name = "crt_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    @CreationTimestamp
    private Timestamp createdOn;

    @Column(name = "upd_by")
    private Integer updatedBy;

    @Column(name = "upd_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp updatedOn;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "desgn_id", nullable = false)
    private DesignationMst designation;

    @OneToOne
    @JoinColumn(name = "scheme_dept_mapp_id", referencedColumnName = "mapping_id")
    private SchemeDeptDesignMappingModal mapping;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserMst user;

    @OneToOne
    @JoinColumn(name = "regular_or_incharge", referencedColumnName = "g_type_id")
    private PayrollGeneralTypeMaster isRegular;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;
}
