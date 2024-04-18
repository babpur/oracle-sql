<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.*"%>
<%@ page import = "dao.*"%>
<%@ page import = "java.util.*"%>
<!-- controller -->
<%
	// DAO 호출로 모델을 반환
	ArrayList<Dept> deptList = DeptDAO.selectDeptList();
	ArrayList<Emp> empList = EmpDAO.selectEmpList();	
	
	ArrayList<HashMap<String, Object>> deptOnOffList = DeptDAO.selectDeptOnOffList();
	ArrayList<HashMap<String, Object>> empAndDeptList = EmpDAO.selectEmpAndDeptList();
%>    
<!-- view -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Dept List</h1>
	<table border="1">
		<tr>
			<th>deptNo</th>
			<th>dName</th>
			<th>loc</th>
		</tr>
		<%
			for(Dept d : deptList) {
		%>
				<tr>
					<td><%=d.deptNo %></td>
					<td><%=d.dname %></td>
					<td><%=d.loc %></td>
				</tr>
		<%		
			}
		%>
	</table>
	
	<h1>Emp List</h1>
	<table border="1">
		<tr>
			<th>empNo</th>
			<th>ename</th>
			<th>sal</th>
		</tr>
		<%
			for(Emp e : empList) {
		%>
				<tr>
					<td><%=e.empNo %></td>
					<td><%=e.ename %></td>
					<td><%=e.sal%></td>
				</tr>
		<%		
			}
		%>
	</table>
	
	
	<h1>DeptOnOffList</h1>
	<table border="1">
		<tr>
			<th>deptNo</th>
			<th>dname</th>
			<th>loc</th>
			<th>onOff</th>
		</tr>
		<%
			// map 단점: 형 변환이 필요할 수도 있고 IDE 자동 완성 기능을 사용할 수 있다.
			for(HashMap<String, Object> don : deptOnOffList) {
		%>
				<tr>
					<td><%=(Integer)(don.get("deptNo"))%></td>
					<td><%=(String)(don.get("dname"))%></td>
					<td><%=(String)(don.get("loc"))%></td>
					<td><%=(String)(don.get("onOff"))%></td>
				</tr>
		<%		
			}
		%>
	</table>
	
	
	<h1>Emp INNER JOIN Dept List</h1>
	<table border="1">
		<tr>
			<th>empNo</th>
			<th>ename</th>
			<th>deptNo</th>
			<th>dname</th>
		</tr>
		<%
			// map 단점: 형 변환이 필요할 수도 있고 IDE 자동 완성 기능을 사용할 수 없다
			for(HashMap<String, Object> ed : empAndDeptList) {
		%>
				<tr>
					<td><%=(Integer)(ed.get("empNo"))%></td>
					<td><%=(String)(ed.get("ename"))%></td>
					<td><%=(Integer)(ed.get("deptNo"))%></td>
					<td><%=(String)(ed.get("dname"))%></td>
				</tr>
		<%		
			}
		%>
	</table>
</body>
</html>