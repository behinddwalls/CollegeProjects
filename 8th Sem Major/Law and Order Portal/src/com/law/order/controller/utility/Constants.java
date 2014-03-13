package com.law.order.controller.utility;

import java.sql.Connection;

import com.law.order.model.utility.DBConnection;

public interface Constants {

	// Connect
	public static final Connection CONNECTION = DBConnection.connect();
	// DB CONST
	public static final String DB_DRIVER = "com.ibm.db2.jcc.DB2Driver";
	public static final String DB_USER = "admin";
	public static final String DB_PASSWORD = "admin";
	public static final String DB_HOST = "jdbc:db2://localhost:";
	public static final String DB_NAME = "/TGMC";
	public static final String DB_PORT = "50000";
	// Controller Const

	// COMPLAINT

	public static final String COMPLAINT_ID = "complaint_id";
	public static final String CITIZEN_ID = "citizen";
	public static final String COMPLAINT_CONTENT = "complaint_content";
	public static final String COMPLAINT_TIME = "complaint_time";
	public static final String COMPLAINT_STATUS = "complaint_status";
	public static final String DEPARTMENT_TYPE = "department_type";
	public static final String SUB_DEPT = "sub_dept";
	public static final String ASSIGNED_POLICE_REPORT = "assigned_police_report";
	public static final String ASSIGNED_POLICE_ID = "assigned_police_id";
	public static final String COMPLAIN_SUBJECT = "complain_subject";

	// COMPLAINT STATUS
	public static final String COMPLAINT_STATUS_ID = "complaint_status_id";
	public static final String COMPLAINT_STATUS_NAME = "complaint_status_name";

	// GENERAL
	public static final String SUCCESS = "Success";
	public static final String FAILED = "Failed";

	// citizen
	public static final String UID = "uid";
	public static final String CITIZEN_NAME = "citizen_name";
	public static final String EMAIL_ID = "email_id";
	public static final String DATE_OF_BIRTH = "date_of_birth";
	public static final String GENDER = "gender";
	public static final String PASSWORD = "password";
	public static final String ADDRESS_ID = "address_id";
	public static final String MOBILE_NUMBER = "mobile_no";
	public static final String IDENTIFICATION_COPY = "identification_copy";
	public static final String USER_PROFILE_PIC = "profilepic";

	// Police officer

	public static final String OFFICER_ID = "officer_id";
	public static final String OFFICER_NAME = "officer_name";
	public static final String OFFICER_EMAIL_ID = "email_id";
	public static final String OFFICER_PASSWORD = "password";
	public static final String OFFICER_VPS_ID = "vps_id";
	public static final String OFFICER_ADDRESS_ID = "address_id";
	public static final String OFFICER_DEPT_ID = "dept_type_id";
	public static final String OFFICER_MOBILE_NUMBER = "mobile_no";
	public static final String OFFICER_IDENTIFICATION_COPY = "identification_copy";

	// DEPARTMENT
	public static final String DEPT_TYPE_ID = "dep_type_id";
	public static final String DEPT_NAME = "dep_name";

	// VPS DEPARTMENT
	public static final String VPS_DEPARTMENT_ID = "vps_department_id";

	// VPS
	public static final String VPS_ID = "vps_id";
	public static final String VPS_NAME = "vps_name";

	// ADDRESS
	// public static final String ADDRESS_ID = "address_id";
	public static final String PINCODE = "pincode";
	public static final String ADDRESS_BLOCK = "address_block";
	public static final String AREA_NAME = "area_name";
	public static final String DISTRICT = "district";

	// Location

	// SUB_DEPT
	public static final String SUB_DEPT_ID = "sub_dept_id";
	public static final String SUB_DEPT_NAME = "sub_department_name";

	// criminal complaint association***********************************

	public static final String COMPLAIN_ID = "complain_id";

	// *****criminal record**********
	public static final String CRIMINAL_ID = "criminal_id";
	public static final String CRIMINAL_NAME = "criminal_name";
	public static final String HEIGHT = "height";
	public static final String WEIGHT = "weight";
	public static final String COMPLEXION = "complexion";
	public static final String IDENTIFICATION_MARKS = "identification_marks";
	public static final String IMAGE_URL = "image_url";

	// criminal status
	public static final String CRIMINAL_STATUS_ID = "criminal_status_id";
	public static final String CRIMINAL_STATUS_NAME = "criminal_status_name";

	/********************************************************************
	 * TRAFFIC
	 * *****************************************************************/
	public static final String TRAFFIC_CHALLAN_ID = "traffic_challan_id";
	public static final String TRAFFIC_TIME = "traffic_time";
	public static final String TRAFFIC_PINCODE = "traffic_pincode";
	public static final String TRAFFIC_FINE = "traffic_fine";
	public static final String TRAFFIC_CHARGE_ID = "traffic_charge_id";
	public static final String TRAFFIC_CHARGE_NAME = "traffic_chrge_name";
	public static final String VEHICAL_NO = "vehicle_no";
	public static final String VEHICAL_OWNER_NAME = "vehicle_owner_name";
	public static final String MOBILE_NO = "mobile_no";
	public static final String TRAFFIC_POLICE_ID = "traffic_police_id";
	public static final String VEHICAL_OWNER_ADDRESS_ID = "vehicle_owner_address_id";

	// Accused
	public static final String ACCUSED_NAME = "accused_name";
	public static final String ACCUSED_MOBILE_NO = "accused_mobileno";

	// witness
	public static final String WITNESS_NAME = "witness_name";
	public static final String WITNESS_MOBILE_NO = "witness_mobileno";

}
