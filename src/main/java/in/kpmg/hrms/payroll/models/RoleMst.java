package in.kpmg.hrms.payroll.models;

import java.io.Serializable;
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
@Table(name = "payroll.payroll_role_mst")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleMst implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_name")
    private String name;


    @Column(name = "crt_by", updatable = false)
    private Integer createdBy;

    @Column(name = "crt_on", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    @CreationTimestamp
    private Timestamp createdOn;

    @Column(name = "upd_by")
    private Integer updatedBy;

    @Column(name = "upd_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    // @UpdateTimestamp
    private Timestamp updatedOn;

    @Column(name = "is_active")
    private Boolean isActive;


    @Column(name = "role_desc")
    private String roledesc;

    @Column(name = "display_order")
    private Integer displayOrder;

}
