package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.RoleMenuRightProj;
import in.kpmg.hrms.payroll.models.RoleMenuMappingMst;

@Repository
public interface RoleMenuRightMapRepo extends JpaRepository<RoleMenuMappingMst, Integer> {

    @Query(nativeQuery = true, value = "SELECT RM.mapping_id rightId, RM.ROLE_ID roleId, RM.MENU_ID menuId, RM.is_downloadable isDownload, \r\n"
            + "RM.IS_VIEWABLE isView, \r\n"
            + "RM.IS_EDITABLE isEdit, MENU.LINK link,menu.icon icon,MENU.MENU_NAME menuName,r.display_order displayOrder \r\n"
            + "FROM payroll.payroll_role_menu_mapping RM JOIN payroll.payroll_user_role_mapping UR\r\n"
            + "ON RM.ROLE_ID = UR.ROLE_ID \r\n"
            + "JOIN payroll.payroll_user_mst USR ON UR.USER_ID = USR.USER_ID \r\n"
            + "JOIN payroll.payroll_menu_mst MENU ON MENU.MENU_ID = RM.MENU_ID \r\n"
            + "JOIN payroll.payroll_role_mst r ON r.ROLE_ID = RM.ROLE_ID \r\n"
            + "where USR.user_id =:userId \r\n"
            + "and MENU.PARENT_MENU_ID is null\r\n"
            + "and \r\n"
            + "RM.IS_ACTIVE =true and menu.IS_ACTIVE =true and UR.IS_ACTIVE =true order by MENU.Display_Order")
    List<RoleMenuRightProj> getMainMenuList(Integer userId);

    @Query(nativeQuery = true, value = "SELECT RM.mapping_id rightId, RM.ROLE_ID roleId, RM.MENU_ID menuId, RM.is_downloadable isDownload, RM.IS_VIEWABLE isView,\r\n"
            + "--RM.IS_EDIT isEditable, \r\n"
            + "MENU.LINK link,menu.icon icon,MENU.MENU_NAME menuName ,menu.parent_menu_id parentMenuId,r.display_order displayOrder\r\n"
            + "FROM payroll.payroll_role_menu_mapping RM JOIN payroll.payroll_user_role_mapping UR\r\n"
            + "ON RM.ROLE_ID = UR.ROLE_ID JOIN  payroll.payroll_user_mst USR ON  UR.USER_ID = USR.USER_ID\r\n"
            + "JOIN payroll.payroll_menu_mst MENU ON MENU.MENU_ID = RM.MENU_ID \r\n"
            + "JOIN payroll.payroll_role_mst r ON r.ROLE_ID = RM.ROLE_ID \r\n"
            + "where USR.user_id  =:userId and MENU.PARENT_MENU_ID is not null \r\n"
            + "and RM.IS_ACTIVE = true and menu.IS_ACTIVE =true and UR.IS_ACTIVE =true order by MENU.Display_Order ")
    List<RoleMenuRightProj> getSubMenuList(Integer userId);

}
