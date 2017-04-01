<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<html><head><title>Cluster App Test</title></head>
<body>
Server Info:
<%
out.println(request.getLocalAddr() + " : " + request.getLocalPort()+"<br>");%>
<%
  out.println("<br> ID " + session.getId()+"<br>");
  // 如果有新的 Session 属性设置
  String dataName = request.getParameter("dataName");
  if (dataName != null && dataName.length() > 0) {
     String dataValue = request.getParameter("dataValue");
     session.setAttribute(dataName, dataValue);
  }
  out.println("<b>Session 列表</b><br>");
  System.out.println("============================");
  Enumeration e = session.getAttributeNames();
  while (e.hasMoreElements()) {
     String name = (String)e.nextElement();
     String value = session.getAttribute(name).toString();
     out.println( name + " = " + value+"<br>");
         System.out.println( name + " = " + value);
   }
  out.println("============================");
  out.println("<br><b>查看JVM内存信息</b><br>");
 

  long maxMemory = Runtime.getRuntime().maxMemory(); //最大可用内存，对应-Xmx
  long freeMemory = Runtime.getRuntime().freeMemory(); //当前JVM空闲内存
  long totalMemory = Runtime.getRuntime().totalMemory(); //当前JVM占用的内存总数，其值相当于当前JVM已使用的内存及freeMemory()的总和
  out.println("maxMemory = " + maxMemory+"<br>");
  out.println("freeMemory = " + freeMemory+"<br>");
  out.println("totalMemory = " + totalMemory+"<br>");
%>
  <form action="session.jspx" method="POST">
    名称:<input type=text size=20 name="dataName">
     <br>
    值:<input type=text size=20 name="dataValue">
     <br>
    <input type=submit>
   </form>
</body>
</html>