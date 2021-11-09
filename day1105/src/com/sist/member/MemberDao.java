package com.sist.member;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.goods03.GoodsVo;

public class MemberDao {
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##sist";
	String pwd ="sist";
	
	//추가할 회원정보를 MemberVo로 매개변수로 전달받아
	//테이블 member에 insert를 수행하고
	//그 결과를 정수로 반환하는 메소드 정의
	public int insertMember(MemberVo m) {
		int re = -1;

		String sql = "insert into member values(?,?,?,?,?)";

		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			//1. jdbc드라이버를 메모리로 로드한다.
			Class.forName(driver);
			
			//2.DB서버에 연결
			conn = DriverManager.getConnection(url,user,pwd);
			
			//3.데이터베이스명령을 실행할 수 있는 Statement객체를 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,m.getNo());
			pstmt.setString(2,m.getName());
			pstmt.setString(3,m.getAddr());
			pstmt.setInt(4,m.getAge());
			pstmt.setString(5,m.getPhone());
			
			//4.데이터베이스 명령을 실행한다.
			re = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			try {
				//5.사용했던 자원을 닫아준다.
				if(pstmt !=null) {
					pstmt.close();
				}

				if(conn != null) {
					conn.close();	
				}
					
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
			}

		}	
		return re;
	}
	
	
	
	//수정할 회원의 정보를 MemberVo로 매개변수로 전달받아
	// 그 결과를 int로 반환하는 메소드 저으이
	public int updateMember(MemberVo m) {
		int re = -1;
		String sql = "update member set name=?, addr=?,age=?,phone=? where no=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
		Class.forName(driver);
		conn=DriverManager.getConnection(url, user, pwd);
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,m.getName());
		pstmt.setString(2,m.getAddr());
		pstmt.setInt(3,m.getAge());
		pstmt.setString(4,m.getPhone());
		pstmt.setInt(5,m.getNo());
		
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
	
	
	
	//삭제할 회원의 번호를 정수로 매개변수로 전달받아
	//해당 회원정보를 DB에서 삭제한 후 결과를 정수로 반환하는 메소드 정의
	public int deleteMember(int no) {
		int re = -1;
		
		String sql = "delete member where no=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,no);
			re = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
				
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
			
		}

		return re; 
		
	}
	
	
	//모든 회원목록을 조회하여 한명한명의 회원을 MemberVo로 만들어
	//그것을 모두 ArrayList에 담아 반환하는 메소드 정의
	public ArrayList<MemberVo> listMember() {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		String sql = "select*from member order by no";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null ;
		try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pwd);
		stmt = conn.createStatement();
		
		rs= stmt.executeQuery(sql);
		while(rs.next()) {
		int no = rs.getInt(1);
		String name = rs.getString(2);
		String addr = rs.getString(3);
		int age = rs.getInt(4);
		String phone = rs.getString(5);
		
		//레코드의 내용을 GoodsVo객체로 만들어 list에 담기
		MemberVo m = new MemberVo(no, name, addr, age, phone);
		list.add(m);
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
	
	
	
	
}
