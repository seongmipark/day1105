package com.sist.dept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DeptDao {
	
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##sist";
	String pwd ="sist";
	
	Connection conn=null;
	PreparedStatement pstmt = null;
	

	//read메소드
	public ArrayList<DeptVo> listDept(){
		
		ArrayList<DeptVo> list = new ArrayList<DeptVo>();
		String sql = "select*from dept";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
			int no = rs.getInt(1);
			String name = rs.getString(2);
			String city = rs.getString(3);
			
			//레코드의 내용을 DeptVo객체로 만들어 list에 담기
			list.add(new DeptVo(no, name, city));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null) {
					rs.close();
				}
				
				
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		
		return list;
	}

	
	
	
	//insert메소드
	public int insertDept(DeptVo d) {
		int re = -1;
		String sql = "insert into dept values(?,?,?)";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,d.getNo());
			pstmt.setString(2,d.getName());
			pstmt.setString(3,d.getCity());
			
			re = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		
		return re;
	}
	
	
	
	//Update메소드
	public int updateDept(DeptVo d) {
		int re = -1;
		String sql ="update dept set name =?,city=? where no=? ";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,d.getName());
			pstmt.setString(2,d.getCity());
			pstmt.setInt(3,d.getNo());
			
			re = pstmt.executeUpdate();
		
		} catch (Exception e) {
		System.out.println("예외발생:"+e.getMessage());	
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		return re;
	}
	
	
	//Delete메소드
	public int deleteDept(int no) {
		int re = -1;
		String sql = "delete dept where no=?";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			re = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		
		
		return re;
	}
	
	

}

