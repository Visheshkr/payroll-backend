package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "payroll_educational_details")
@Data
public class PayrollEducationalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edu_id")
    private Long eduId;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private PayrollEmployeeMaster empId;

    @ManyToOne
    @JoinColumn(name = "qualification_id", referencedColumnName = "type_id")
    private GeneralMst qualificationId;

    @Column(name = "institute_name")
    private String instituteName;

    @Column(name = "board_or_university")
    private String boardOrUniversity;

    private String course;

    @ManyToOne
    @JoinColumn(name = "marks_cgpa_id", referencedColumnName = "type_id")
    private GeneralMst marksCgpaId;

    @Column(name = "marks_secured")
    private Integer marksSecured;

    @Column(name = "total_marks")
    private Integer totalMarks;

    private Integer cgpa;

    @Column(name = "starting_year")
    private Integer startingYear;

    @Column(name = "passing_year")
    private Integer passingYear;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst crtBy;

    @Column(name = "crt_on")
    @CreationTimestamp
    private Timestamp crtOn;

    @ManyToOne
    @JoinColumn(name = "upd_by", referencedColumnName = "user_id")
    private UserMst updBy;

    @Column(name = "upd_on")
    @UpdateTimestamp
    private Timestamp updOn;

}
