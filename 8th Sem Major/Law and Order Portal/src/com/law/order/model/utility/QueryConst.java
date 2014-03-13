package com.law.order.model.utility;

import com.law.order.controller.utility.Constants;

public interface QueryConst extends Constants {

	// Address Model
	public static final String INSERT_ADDRESS = "INSERT INTO address_table (address_block, pincode) VALUES(?,?)";
	public static final String UPDATE_ADDRESS = "UPDATE address_table pincode =?,address_block=? WHERE address_id=?";
	public static final String SELECT_ADDRESS = "select * from address_table where address_id=?";
	public static final String SELECT_AREA_DETAILS = "select pincode,area_name,district_name from area_pincode_table natural join area_name_table natural join district_area_bridge natural join district_table where pincode=?";

	// Citizen Model
	public static final String INSERT_CITIZEN = "INSERT INTO citizen_detail (citizen_name, mobile_no, identification_copy, email_id, date_of_birth, address_id, password, profilepic, gender) VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_CITIZEN = "select * from citizen_detail where citizen_id=?";
	public static final String DELETE_CITIZEN = "delete from citizen_detail where citizen_id=?";
	public static final String UPDATE_CITIZEN = "update citizen_detail set "
			+ CITIZEN_NAME + "=?," + EMAIL_ID + "=?," + DATE_OF_BIRTH + "=?,"
			+ GENDER + "=?," + ADDRESS_ID
			+ "=? , mobile_no=? , password=?  where citizen_id=?";
	public static final String SELECT_CITIZEN_COMPLAIN = "select * from complaint_table where citizen_id=?";
	public static final String SELECT_CITIZEN_SPECIFIC_COMPLAIN = "select * from complaint_table where complaint_id=?";
	public static final String CHECK_CITIZEN = "select * from citizen_detail where email_id=? and password=?";

	// VPS Model
	public static final String INSERT_VPS = "INSERT INTO vps_table ( vps_name, vps_admin_id, password) VALUES(?,?,?)";
		public static final String UPDATE_VPS = "UPDATE vps_table set vps_name =?,vps_admin_id=?,password=? WHERE vps_id=?";
	public static final String SELECT_VPS_ALL = "select * from vps_table";
	public static final String SELECT_VPS = "select * from vps_table where vps_id=?";

	// VPS DEPARTMENT

	public static final String DELETE_VPS_DEPARTMENT = "DELETE FROM vps_department where vps_id = ?";
	public static final String SELECT_VPS_DEPARTMENT = "select * from vps_department where vps_id=?";
	public static final String INSERT_VPS_DEPARTMENT = "insert into vps_department values(?,?)";

	// DEPARTMENT
	public static final String SELECT_DEPARTMENT = "select * from department_table where dept_type_id=?";
	public static final String SELECT_DEPARTMENT_ALL = "select * from department_table";

	// VPS OFFICER
	public static final String DELETE_VPS_OFFICER = "DELETE FROM vps_alloted_officer where vps_id = ?";
	public static final String INSERT_VPS_OFFICER = "insert into vps_alloted_officer values(?,?)";
	public static final String SELECT_VPS_OFFICER_ID = "select * from vps_alloted_officer where vps_id=?";

	// Police officer
	public static final String SELECT_POLICEOFFICER_BYDEPT = "select * from police_officer_detail join role on police_officer_detail.officer_id=role.user_id where dept_type_id=0 and role_name='Sub-Admin'";
public static final String UPDATE_OFFICER = "UPDATE police_officer_detail SET email_id=?,officer_name=?,mobile_no=?,gender=?  WHERE officer_id=?";
	public static final String UPDATE_OFFICER_PASS = "UPDATE police_officer_detail SET password=?  WHERE officer_id=?";
	public static final String SELECT_POLICEOFFICER = "select * from police_officer_detail where officer_id=?";
	public static final String SELECT_POLICEOFFICER_BY_VPS = "select * from police_officer_detail where vps_id=?";
	public static final String CHECK_POLICE = "select * from police_officer_detail where email_id=? and password=?";
	public static final String CHECK_ROLE = "select * from role where user_id=? and role_name=?";
	public static final String INSERT_ROLE = "insert into role values(?,?)";
		public static final String INSERT_OFFICER = "INSERT INTO police_officer_detail ( email_id, password, officer_name, address_id, identification_copy, mobile_no, vps_id, dept_type_id, gender) VALUES(?,?,?,?,?,?,?,?,?)";
	// Complaint
	public static final String INSERT_COMPLAINT = "INSERT INTO complaint_table (citizen_id, complaint_content, complaint_time, department_type_id, sub_department_id, assigned_police_report, assigned_police_id, complaint_subject, vps_id) VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String DELETE_COMPLAINT = "DELETE FROM complaint_table where complaint_id=?";
	public static final String SELECT_COMPLAINT = "";
	public static final String SELECT_DEPARTMENT_COMPLAIN = "select * from complaint_table,current_complaint_status where department_type_id=? and vps_id=? and current_status_name='pending' and complaint_table.complaint_id=current_complaint_status.complaint_id";
	public static final String SELECT_COMPLAINT_ALL = "select * from complaint_table,current_complaint_status where vps_id=? and department_type_id=0 and sub_department_id=0 and current_status_name='pending' and complaint_table.complaint_id=current_complaint_status.complaint_id";
	public static final String SELECT_DEPARTMENT_COMPLAIN_FIR = "select * from complaint_table,current_complaint_status where department_type_id=? and vps_id=? and current_status_name='pending FIR' and complaint_table.complaint_id=current_complaint_status.complaint_id";
	public static final String UPDATE_COMPLAIN = "update complaint_table set complaint_content=?,department_type_id=?,sub_department_id=?,assigned_police_id=?,complaint_subject=? where complaint_id=?";
	public static final String SELECT_DEPARTMENT_COMPLAIN_CHARGESHEET = "select * from complaint_table,current_complaint_status where department_type_id=? and vps_id=? and current_status_name='pending Chargesheet' and complaint_table.complaint_id=current_complaint_status.complaint_id";
	public static final String SELECT_CLOSED_COMPLAINTS = "select * from complaint_table,current_complaint_status where vps_id=? and current_status_name='Complete' and complaint_table.complaint_id=current_complaint_status.complaint_id";
	public static final String SELECT_ALL_COMPLAINT_DEPT = "SELECT * FROM complaint_table WHERE department_type_id =?";
	public static final String UPDATE_COMPLAIN_BY_CITIZEN = "update complaint_table set complaint_content=? ,complaint_subject=?  where complaint_id=? ";

	// Retrieve Complain
	public static final String RETRIEVE_COMPLAINT = "";
	public static final String RETRIEVE_COMPLAINT_ALL = "";

	public static final String UPDATE_COMPLAINT = "";
	// SUB_DEPT
	public static final String SELECT_SUB_DEPT = "select * from sub_department_table where sub_dept_id=?,Dept_type_id=?";

	// complaint status
	public static final String SELECT_COMPLAIN_STATUS = "select * from current_complaint_status where complaint_id=?";
	public static final String UPDATE_COMPLAIN_STATUS = "UPDATE current_complaint_status set current_status_name=? where complaint_id=?";
	public static final String INSERT_COMPLAIN_STATUS = "insert into current_complaint_status values(?,?)";

	// criminal_record
	public static final String INSERT_CRIMINAL_RECORD = "insert into criminal_record values(?,?,?,?,?,?,?)";
	public static final String DELETE_CRIMINAL_RECORD = "DELETE FROM complaint_table where criminal_id=?";
	public static final String UPDATE_CRIMINAL_RECORD = "UPDATE criminal_record SET criminal_name,height=?,weight=?,complexion=?,identification_mark=?,image_url,date_of_birth  WHERE criminal_id=?";
	public static final String SELECT_CRIMINAL_RECORD = "select *  from  criminal_record where criminal_id=?";
	public static final String SELECT_CRIMINAL_SPECIFIC_RECORD = "select *  from  criminal_record where criminal_id=?";

	// criminal status table
	public static final String INSERT_CRIMINAL_STATUS = "insert into criminal_status_table values(?,?)";
	public static final String DELETE_CRIMINAL_STATUS = "DELETE FROM criminal_status_table where criminal_status_id=?";
	public static final String UPDATE_CRIMINAL_STATUS = "UPDATE criminal_status_table SET criminal_status_name=? WHERE criminal_status_id=?";

	public static final String SELECT_CRIMINAL_STATUS = "select *  from  criminal_status_table where criminal_status_id=?";

	// criminal complaint assosiation table
	public static final String INSERT_CRIMINAL_COMPLAIN_ASSOSIATION = "insert into criminal_complain_assositation values(?,?,?)";
	public static final String DELETE_CRIMINAL_COMPLAIN_ASSOSIATION = "DELETE FROM criminal_complain_assositation where criminal_id=?";
	public static final String UPDATE_CRIMINAL_COMPLAIN_ASSOSIATION = "UPDATE criminal_complain_association SET complaint_id=?,criminal_status_id=? WHERE criminal_id=?";

	public static final String SELECT_CRIMINAL_COMPLAIN_ASSOSIATION = "select *  from  criminal_status where criminal_id=?";

	// Traffic
		public static final String INSERT_TRAFFIC = "INSERT INTO traffic_control_department_table ( traffic_time, traffic_pincode, traffic_fine, traffic_charge_id, vehicle_no, vehicle_owner_name, mobile_no, traffic_police_id) VALUES(?,?,?,?,?,?,?,?)";
	public static final String DELETE_TRAFFIC = "delete from traffic where traffic_challan_id=?";
	public static final String SELECT_TRAFFIC = "select * from traffic where traffic_challan_id=?";
	public static final String UPDATE_TRAFFIC = "update traffic set  traffic_time =?,traffic_pincode=?,traffic_fine=?,traffic_challan_id =?,vehical_no=?,vehicle_owner_name =?,mobile_no=?,traffic_police_id =?,vehicle_owner_address_id =? where traffic_challan_id=?";

	// traffic charge
	public static final String INSERT_TRAFFIC_CHARGE = "select * from traffic_charge where traffic_charge_name=?";
	public static final String DELETE_TRAFFIC_CHARGE = "delete from traffic_charge where traffic_challan_id=?";
	public static final String SELECT_TRAFFIC_CHARGE = "select * from traffic_charge where traffic_charge_id=?";

	public static final String UPDATE_TRAFFIC_CHARGE = "update traffic_charge set traffic_charge_name =? where traffic_challan_id=?";

	// accused
	public static final String SELECT_ACCUSED = "select * from accused_table where complaint_id=?";
		public static final String INSERT_ACCUSED = "INSERT INTO accused_table (complaint_id ,accused_name, accused_mobileno, accused_addr_id) VALUES(?,?,?,?)";

	// witness
	public static final String SELECT_WITNESS = "select * from witness_table where complaint_id=?";
	public static final String INSERT_WITNESS = "INSERT INTO witness_table (complaint_id, witness_name, witness_addr_id, witness_mobileno) VALUES(?,?,?,?)";

	// VPS AREA
	public static final String SELECT_VPS_AREA_LIST = "SELECT Distinct * FROM vps_area_list a JOIN area_name_table b ON a.vps_area_list_id = b.area_id where vps_id=?";
	public static final String DELETE_VPS_AREA_ID = "delete from vps_ara_list where vps_id=?";
}
