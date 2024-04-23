<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%
	
	ArrayList<Emp> list = null;	

	String[] ck = request.getParameterValues("ck");
	
	if(ck == null) { 
		// ck가 null이면 ck를 출력
		System.out.println("ck: " + ck);
	} else { 
		// ck가 null이 아니면 ck.length를 출력
		System.out.println("ck.length: " + ck.length);
		
		ArrayList<Integer> ckList = new ArrayList<>();
		for(String s : ck) {
			ckList.add(Integer.parseInt(s));
			// 타입이 다르기에 형 변환.
		}
		
		list = EmpDAO.selectEmpListByGrade(ckList);
		System.out.println("결과셋의 행(list.size): " + list.size());
	}
	
%>

<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title></title>
</head>
<body>
   <h1>EMP GRADE 검색</h1>

	<!-- action page가 자기 자신 -->
   <form action="./q004WhereIn.jsp" method="post">
      GRADE : 
      <%
         for(int i=1; i<6; i=i+1) {
      %>
            <input name="ck" type="checkbox" value="<%=i%>"><%=i%>
      <%      
         }
      %>
      <br>
      <button type="submit">검색</button>
   </form>
   
   <hr>
   <h1>결과 View</h1>
   <%
   
	   	if(ck == null) {
			return; 
			// ck가 null이면 아무것도 출력하지 않고 종료
			// list가 null이면 아무것도 출력하지 않고 종료로 표현할 수도 있음.
	   	}
   %>
	<table border="1">
		<tr>
			<th>ename</th>
			<th>grade</th>
		</tr>
		<%
			for(Emp e : list) {
		%>
				<!-- vo/Emp가 private -->
				<tr>
					<td><%=e.getEname()%></td>
					<td><%=e.getGrade()%></td>
				</tr>
		<% 		
				
			}
		%>
	</table>
</body>
</html>