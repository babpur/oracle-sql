<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="dao.*" %>
<%@ page import ="java.util.*" %>    

<%
	ArrayList<HashMap<String, Object>> list = EmpDAO.selectEmpSelfJoin();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>직원별 담당 매니저</h1>
	<table border="1">
		<tr>
			<td>empNo</td>
			<td>ename</td>
			<td>grade</td>
			<td>mgrName</td>
			<td>mgrGrade</td>
		</tr>
		<%
			for(HashMap<String, Object> m : list) {
		%>
				<tr>
					<td><%=(Integer)(m.get("empNo"))%></td>
					<td><%=m.get("ename") %></td>
					<td>
						<%
							for(int i = 0; i < (Integer)(m.get("grade")); i = i + 1) {
						%>
								&#129326;
						<%		
							}
						%>
					</td>				
					<td><%=m.get("mgrName") %></td>
					<td>
						<%
							for(int i = 0; i < (Integer)(m.get("mgrGrade")); i = i + 1) {
						%>
								&#129326;
						<%		
							}
						%>
					</td>					
				</tr>		
		<%		
			}
		%>		
		
	</table>
</body>
</html>