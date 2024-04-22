<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>

<%
	ArrayList<HashMap<String, String>> caseList = EmpDAO.selectJobCaseList(); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>CASE식을 이용한 정렬</h1>
	<table border="1">
		<tr>
			<td>ename</td>
			<td>job</td>
			<td>color</td>
		</tr>
			<%
				for(HashMap<String, String> c : caseList) {
			%>
				<tr>
					<td><%=(c.get("ename"))%></td>
					<td><%=(c.get("job"))%></td>
					<td><%=(c.get("color"))%></td>
				</tr>
			<%		
				}
			%>
	</table>
</body>
</html>