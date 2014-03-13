package com.law.order.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import com.law.order.controller.utility.Constants;
import com.law.order.model.Address;
import com.law.order.model.PoliceOfficer;
import com.law.order.model.Vps;
import com.law.order.model.VpsArea;
import com.law.order.model.VpsDepartment;
import com.law.order.model.VpsOfficer;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.DBUtility;

public class VpsController implements Constants {

	private PoliceOfficer policeofficer = null;
	private Vps vps = null;
	private VpsDepartment vpsdepartment = null;
	private VpsArea vpsArea = null;
	private VpsOfficer vpsofficer = null;

	@GET
	@Path("/updatevps")
	public Response vpsupdate(

			@QueryParam(value = "id") int id,
			@QueryParam(value = "password") String password,
			@QueryParam(value = "vps_name") String vps_name,
			@QueryParam(value = "vps_admin_id") int vps_admin_id,
			@QueryParam(value = "vps_department_id") String vps_department_id[],
			@QueryParam(value = "vps_alloted_officer") String vps_alloted_officer[],
			@QueryParam(value = "pincodelist") String pincodelist[])
			throws JSONException {

		vps = new Vps(id, vps_name, vps_admin_id, password);
		vps.update();

		new VpsDepartment().delete(id);
		for (String dept : vps_department_id) {
			vpsdepartment = new VpsDepartment(id, Integer.valueOf(dept));
			vpsdepartment.create();
		}

		new VpsOfficer().delete(id);

		for (String alloted_officer : vps_alloted_officer) {
			vpsofficer = new VpsOfficer(id, Integer.valueOf(alloted_officer));
			vpsofficer.create();
		}
		new VpsArea().delete(id);
		String resp = "";
		for (String pincode : pincodelist) {
			try {
				resp = new DBUtility().insertAreaId(id, pincode);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (resp.equals(SUCCESS))
			return Response.ok(SUCCESS).build();

		return Response.ok(FAILED).build();
	}

	@GET
	@Path("/createvps")
	public Response vpscreate(

			@QueryParam(value = "password") String password,
			@QueryParam(value = "vps_name") String vps_name,
			@QueryParam(value = "vps_admin_id") int vps_admin_id,
			@QueryParam(value = "vps_department_id") String vps_department_id[],
			@QueryParam(value = "vps_alloted_officer") String vps_alloted_officer[],
			@QueryParam(value = "pincodelist") String pincodelist[])
			throws JSONException {

		vps = new Vps(0, vps_name, vps_admin_id, password);
		vps.create();

		int vps_id = vps.getVps_id();

		for (String dept : vps_department_id) {
			vpsdepartment = new VpsDepartment(vps_id, Integer.valueOf(dept));
			System.out.println(dept);
			vpsdepartment.create();
		}

		for (String alloted_officer : vps_alloted_officer) {
			vpsofficer = new VpsOfficer(vps_id,
					Integer.valueOf(alloted_officer));
			System.out.println(alloted_officer);
			vpsofficer.create();
		}
		String resp = "";
		for (String pincode : pincodelist) {
			try {
				resp = new DBUtility().insertAreaId(vps_id, pincode);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resp.equals(SUCCESS))
			return Response.ok(SUCCESS).build();

		return Response.ok(FAILED).build();
	}

	@GET
	public Response retrieveVpsAll() throws JSONException {

		Vps vps = new Vps();
		return Response.ok(vps.selectAll().toString(20)).build();
	}

	@GET
	public Response getDeptIdList(@QueryParam(value = "vps_id") int vps_id) {
		VpsDepartment vpsDepartment = new VpsDepartment();
		vpsDepartment.setVps_id(vps_id);
		return Response.ok(vpsDepartment.select()).build();
	}

	@GET
	public Response getOfficerIdList(@QueryParam(value = "vps_id") int vps_id) {

		VpsOfficer vpsOfficer = new VpsOfficer();
		vpsOfficer.setVps_id(vps_id);
		return Response.ok(vpsOfficer.selectVpsId()).build();
	}

	@GET
	public Response getVPSNAMEBYVPSID(int vps_id) {

		String vps_name = null;
		try {
			PreparedStatement preparedStatement = DBConnection.connect()
					.prepareStatement("select * from vps_table where vps_id=?");
			preparedStatement.setInt(1, vps_id);
			ResultSet result = preparedStatement.executeQuery();

			if (result.next())
				vps_name = result.getString(2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.ok(vps_name).build();
	}

	@GET
	public Response getVpsIdByPincode(String pincode) {
		int vps_id = 0;
		try {
			PreparedStatement preparedStatement = DBConnection
					.connect()
					.prepareStatement(
							"SELECT * FROM vps_area_list, area_pincode_table WHERE vps_area_list.vps_area_list_id = area_pincode_table.area_id AND area_pincode_table.pincode = ?");
			preparedStatement.setString(1, pincode);
			ResultSet result = preparedStatement.executeQuery();

			if (result.next())
				vps_id = result.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.ok(vps_id).build();
	}

}
