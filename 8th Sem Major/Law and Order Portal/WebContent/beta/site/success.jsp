<html>
 <head>
  <title>Example for form data submission using jsp</title>
 </head>
 <body>
  <center>
   <table width="50%" cellpadding="2" cellspacing="2" style="font-family: Arial; font-size: 18px;">
    <tr>
     <td colspan="2" style=" text-align: center; height: 61px;">Your Details</td>
    </tr>
    <tr>
     <td style="width: 40%; text-align: right">First Name : </td>
     <td style="width: 60%"><%=request.getParameter("firstName")%></td>
    </tr>
    <tr>
     <td style="width: 40%; text-align: right">Last Name : </td>
     <td style="width: 60%"><%=request.getParameter("lastName")%></td>
    </tr>
   </table>
  </center>
 </body>
</html>