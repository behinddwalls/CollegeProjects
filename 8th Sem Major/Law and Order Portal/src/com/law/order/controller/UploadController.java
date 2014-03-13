package com.law.order.controller;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.http.protocol.HttpContext;
import org.eclipse.jetty.http.HttpContent;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

public class UploadController {

	@POST
	@Path("/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(List<Attachment> attachments,
			@Context HttpServletRequest request) {
		String filename = request.getRealPath("/upload/").replace("\\", "/");
		String realFileName = "";
		for (Attachment attr : attachments) {
			DataHandler handler = attr.getDataHandler();

			try {
				InputStream stream = handler.getInputStream();
				MultivaluedMap map = attr.getHeaders();

				realFileName = getFileName(map);

				OutputStream out = new FileOutputStream(new File(filename + ""
						+ realFileName));

				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = stream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				stream.close();
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return Response.ok(filename + "=" + realFileName).build();
	}

	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition")
				.split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = "/"
						+ name[1].trim().replaceAll("\"", "");
				System.out.println(finalFileName);
				return finalFileName;
			}
		}
		return "unknown";
	}

	@POST
	@Path("file1")
	public Response uploadFile1(List<Attachment> atts,
			@Context HttpServletRequest request) {
		for (Attachment att : atts) {
			DataHandler dataHandler = att.getDataHandler();
			ContentDisposition disp = att.getContentDisposition();
			try {
				InputStream stream = dataHandler.getInputStream();
				System.out.println(disp.getParameter("name"));
				OutputStream out = new FileOutputStream("/upload/"
						+ disp.getParameter("filename"));
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = stream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}

				stream.close();
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out
						.println("IOException thrown while saving one of the attachments!");
				e.printStackTrace();
			}
		}

		return Response.ok("DONE").build();
	}

	@GET
	@Path("test")
	public Response test(@Context HttpServletRequest request) {
		String path = request.getRealPath("/upload/");

		return Response.ok(path).build();
	}

}