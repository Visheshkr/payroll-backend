package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "payroll.payroll_user_role_mapping")
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleMappingMst {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Integer roleMapId;

    // @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private UserMst user;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    @ToString.Exclude
    private RoleMst role;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserMst.class)
    @JoinColumn(name = "crt_by", nullable = false)
    @ToString.Exclude
    private UserMst crtBy;

    @Column(name = "crt_on")
    @CreationTimestamp
    private Timestamp createdOn;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserMst.class)
    @JoinColumn(name = "upd_by", referencedColumnName = "user_id")
    @ToString.Exclude
    private UserMst updBy;

    // @UpdateTimestamp
    @Column(name = "upd_on")
    // @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm aa",
    // timezone = "Asia/Kolkata")
    private Timestamp updatedOn;

    @Column(name = "is_active")
    private Boolean isActive;

}
