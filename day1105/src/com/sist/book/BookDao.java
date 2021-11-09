package com.sist.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDao {
	
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##sist";
	String pwd ="sist";

	Connection conn=null;
	PreparedStatement pstmt = null;

	//list기능
	//bookvo를 어레이리스트에 담을 리스트 생성
	public ArrayList<BookVo> listbook(){
		//arraylist를 생성 
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		
		String sql = "select*from book order by no";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//jdbc 드라이버로드
			Class.forName(driver);
			//db서버 연결
			conn = DriverManager.getConnection(url, user, pwd); 
			//명령을 내리기 위해 statement객체 생성
			stmt = conn.createStatement();
			//명령내리기
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String publisher = rs.getString(3);
				int price = rs.getInt(4);

				list.add(new BookVo(no, name, publisher, price));
			}
	
		} catch (Exception e) {
		System.out.println("예외발생:"+e.getMessage());
		}finally {
			try {
				//자원 닫기
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
	
	
	
	
	//insert기능
	public int insertBook(BookVo b) {
	int re = -1;
	String sql = "insert into book values(?,?,?,?)";
	try {
		//드라이버 로드
		Class.forName(driver);
		//db서버 연결
		conn = DriverManager.getConnection(url, user, pwd);
		//명령을 내리기 위한 statement생성
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, b.getNo());
		pstmt.setString(2, b.getName());
		pstmt.setString(3, b.getPublisher());
		pstmt.setInt(4, b.getPrice());
		
		
		//명령내리기
		re = pstmt.executeUpdate();

	} catch (Exception e) {
		System.out.println("예외발생:"+e.getMessage());
	}finally {
		try {
			//자원 닫기
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
	
	
	//update기능
	public int updateBook(BookVo b) {
		int re = -1;
		
		String sql = "update book set name=?, publisher=?,price=? where no=? ";
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pwd);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,b.getName());
		pstmt.setString(2,b.getPublisher());
		pstmt.setInt(3,b.getPrice());
		pstmt.setInt(4,b.getNo());
		
		re = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	
	
	//delete기능
	public int deletebook(int no) {
		int re = -1;
		String sql = "delete book where no = ?";
		
		try {
			//jdbc드라이버로드
			Class.forName(driver);
			//db서버 연결
			conn = DriverManager.getConnection(url, user, pwd);
			
			//명령을 내리기위한 statement객체생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			//명령
			re = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally{
			try {
				//자원 닫기
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
