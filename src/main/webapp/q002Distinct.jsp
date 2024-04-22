<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.* "%>  
<%@ page import="java.util.*" %>  

<%

	ArrayList<Integer> distinctList = EmpDAO.selectDeptNoList();
	ArrayList<HashMap<String, Integer>> groupByList = EmpDAO.selectDeptNoCntList(); 

%>
	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>DISTINCT만 사용하는 방식</h1>
	<select name="deptNo">
		<option value=""> ===== 선택 =====
		<%
			for(Integer d : distinctList) {
		%>
				<option value="<%=d%>"><%=d%>
		<%		
			}
		%>
	</select>
	
	<h1>DISTINCT 대신 GROUP BY를 사용하는 방식</h1>
	<select name="deptNo">
		<option value=""> ===== 선택 =====
		<%
			for(HashMap<String, Integer> g : groupByList) {
		%>
				<option value="<%=g.get("deptNo")%>">
					<%=g.get("cnt")%>명
					<%=g.get("deptNo")%>번
		<%		
			}
		%>
	</select>
	
	
</body>
</html>