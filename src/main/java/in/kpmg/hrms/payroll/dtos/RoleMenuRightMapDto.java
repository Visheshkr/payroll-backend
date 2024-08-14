package in.kpmg.hrms.payroll.dtos;

public interface RoleMenuRightMapDto {
    Integer getMenuId();

    Integer getRightId();

    String getMenuName();

    Integer getParentMenuId();

    String getParentMenuName();

    Boolean getIsView();

    Boolean getIsEdit();

    Boolean getIsDownload();
}
