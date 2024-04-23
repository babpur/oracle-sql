package dao;

import java.sql.*;
import java.util.*;

import vo.Emp;
import vo.Dept;

public class EmpDAO {
	// q004WhereIn.jsp
	public static ArrayList<Emp> selectEmpListByGrade(ArrayList<Integer> ckList)
		throws Exception {
		ArrayList<Emp> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		/*
			WHERE grade in (1~5)
		 */
		String sql = "SELECT ename, grade"
				+ " FROM emp"
				+ " WHERE grade IN ";
		PreparedStatement stmt = null;
		
		// n개를 선택했을 때 n번째의 값
		// stmt.setInt(1, ckList.get(0));
		// ㄴ 배열과 마찬가지로 0~4번째임을 주의.
		if(ckList.size() == 1) {
			sql = sql + "(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
		} else if(ckList.size() == 2) {
			sql = sql + "(?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
		} else if(ckList.size() == 3) {
			sql = sql + "(?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
		} else if(ckList.size() == 4) {
			sql = sql + "(?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
			stmt.setInt(4, ckList.get(3));
		} else if(ckList.size() == 5) {
			sql = sql + "(?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
			stmt.setInt(4, ckList.get(3));
			stmt.setInt(5, ckList.get(4));
		}
		
		ResultSet rs = stmt.executeQuery();	
		
		while(rs.next()) {
			Emp e = new Emp();
			e.setEname(rs.getString("ename"));
			e.setGrade(rs.getInt("grade"));
			list.add(e);
		}
		conn.close();
		return list;
	}
	
	
	// q003Case
	public static ArrayList<HashMap<String, String>> selectJobCaseList() 
			throws Exception {
		
		ArrayList<HashMap<String, String>> list =
				new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql ="SELECT ename, job,"
			+ " CASE"
			+ " WHEN job = 'PRESIDENT' THEN '빨강'"
			+ " WHEN job = 'MANAGER' THEN '주황'"
			+ " WHEN job = 'ANALYST' THEN '노랑'"
			+ " WHEN job = 'CLERK' THEN '초록'"
			+ " ELSE '파랑' END color"
			+ " FROM emp"
			+ " ORDER BY (CASE "
			+ " WHEN color = '빨강' THEN 1"
			+ " WHEN color = '주황' THEN 2"
			+ " WHEN color = '노랑' THEN 3"
			+ " WHEN color = '초록' THEN 4"
			+ " ELSE 5 END) ASC";
	
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			HashMap<String, String> m = new HashMap<>();
			m.put("ename", rs.getString("ename"));
			m.put("job", rs.getString("job"));
			m.put("color", rs.getString("color"));
			list.add(m);
		}
		conn.close();
		
		return list;
	}
	
	// DEPTNO 뒤 부서별 인원을 같이 조회하는 method
	public static ArrayList<HashMap<String, Integer>> selectDeptNoCntList() 
			throws Exception {
		
		ArrayList<HashMap<String, Integer>> list =
				new ArrayList<>();
		Connection conn = DBHelper.getConnection();
		
		// COUNT(*)의 *은 모든 열이 아니고 rowid를 가리킨다.
		String sql = "SELECT count(*) cnt, deptno deptNo"
			+ " FROM emp"
			+ " WHERE deptno IS NOT NULL"
			+ " GROUP BY deptno"
			+ " ORDER BY deptno ASC";	
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			HashMap<String, Integer> m = new HashMap<>();
			m.put("cnt", rs.getInt("cnt"));
			m.put("deptNo", rs.getInt("deptNo"));
			list.add(m);
		}
		conn.close();
		return list; // 구현 후에 수정
	}
	
	// 중복값을 제외한 DEPTNO 목록을 출력하는 method
	public static ArrayList<Integer> selectDeptNoList() 
			throws Exception {
		ArrayList<Integer> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT DISTINCT deptno deptNo" // 3. deptno 4. deptno -> deptNo 5.DISTINCT로 중복값 삭제
				+ " FROM emp" // 1
				+ " WHERE deptno IS NOT NULL" // 2
				+ " ORDER BY deptno ASC"; 
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Integer i = rs.getInt("deptNo"); // wrapper 타입과 기본 타입 간 Auto Boxing
			list.add(i);
		}
		conn.close();
		
		return list;
	}
	
	// join으로 Map을 사용하는 경우
	public static ArrayList<HashMap<String, Object>> selectEmpAndDeptList()
			throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " emp.empno empNo, emp.ename ename, emp.deptno deptNo,"
				+ " dept.dname dname"
				+ " FROM emp INNER JOIN dept"
				+ " ON emp.deptno = dept.deptno";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("empNo", rs.getInt("empNo"));
			m.put("ename", rs.getString("ename"));
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("dname", rs.getString("dname"));
			list.add(m);
		}
		return list;
	}
	
	// VO 사용
	public static ArrayList<Emp> selectEmpList() 
			throws Exception {
		ArrayList<Emp> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " empno empNo, ename, sal"
				+ " FROM emp";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			e.setEmpNo(rs.getInt("empNo"));
			e.setEname(rs.getString("ename"));
			e.setSal(rs.getDouble("sal"));
			list.add(e);
		}
		
		return list;
	}
}
