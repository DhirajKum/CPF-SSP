<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE">
<%
  java.util.Date systemDate = new java.util.Date();
  java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("d-MMM-yyyy");
  String strSystemDate = dateFormat.format(systemDate);
  StringBuffer requestString = request.getRequestURL();
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>FCI-Payroll Management System on T5-4</title>
</head>
<body onload="displayDate()"></body>
</html>

<script type="text/javascript" language="javascript">
var sysdt='<%=strSystemDate%>';
function displayDate()
{  
  var d=new Date(); 
  var curdate=d.getDate()+"/"+eval(d.getMonth()+1)+"/"+d.getFullYear(); 
  var dt=change_DateFormatMon(sysdt);
  if(curdate==dt)
  {
    //alert("redirecting")
       window.open("RedirectMap.do?action=frmLogin|LoginPage","Login","menubar=no,toolbar=no,scrollbars=yes,resizable=yes");    
  }
  else
       alert("Please Change your Date settings as server Date is "+sysdt);
}
function change_DateFormatMon(cdate)
{
    firstIndex=cdate.indexOf("-");
    lastIndex=cdate.lastIndexOf("-");
    if((firstIndex != -1) && (lastIndex != -1) && (firstIndex != lastIndex))
    {
        var arr=cdate.split("-");
        var monIndx=getMonthIndx(arr[1]);
        if(monIndx!=-1)
        cdate = arr[0] + "/" + monIndx + "/" + arr[2];
    }
    return cdate;
}
function getMonthIndx(strMon)
{
  //var arrCal=new Array();
  var arrCal=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
  for(i=0;i<arrCal.length;i++)
  {
    if(arrCal[i].toUpperCase()==strMon.toUpperCase())
    {
      return i+1;
    }
  }
  return -1;
}
</script>