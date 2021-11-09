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

	//list���
	//bookvo�� ��̸���Ʈ�� ���� ����Ʈ ����
	public ArrayList<BookVo> listbook(){
		//arraylist�� ���� 
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		
		String sql = "select*from book order by no";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//jdbc ����̹��ε�
			Class.forName(driver);
			//db���� ����
			conn = DriverManager.getConnection(url, user, pwd); 
			//����� ������ ���� statement��ü ����
			stmt = conn.createStatement();
			//��ɳ�����
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String publisher = rs.getString(3);
				int price = rs.getInt(4);

				list.add(new BookVo(no, name, publisher, price));
			}
	
		} catch (Exception e) {
		System.out.println("���ܹ߻�:"+e.getMessage());
		}finally {
			try {
				//�ڿ� �ݱ�
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
				System.out.println("���ܹ߻�:"+e.getMessage());			
			}
		}

		return list;
	}
	
	
	
	
	//insert���
	public int insertBook(BookVo b) {
	int re = -1;
	String sql = "insert into book values(?,?,?,?)";
	try {
		//����̹� �ε�
		Class.forName(driver);
		//db���� ����
		conn = DriverManager.getConnection(url, user, pwd);
		//����� ������ ���� statement����
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, b.getNo());
		pstmt.setString(2, b.getName());
		pstmt.setString(3, b.getPublisher());
		pstmt.setInt(4, b.getPrice());
		
		
		//��ɳ�����
		re = pstmt.executeUpdate();

	} catch (Exception e) {
		System.out.println("���ܹ߻�:"+e.getMessage());
	}finally {
		try {
			//�ڿ� �ݱ�
			if(conn != null) {
				conn.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
	}

	return re;
		
	}
	
	
	//update���
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
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return re;
	}
	
	
	
	//delete���
	public int deletebook(int no) {
		int re = -1;
		String sql = "delete book where no = ?";
		
		try {
			//jdbc����̹��ε�
			Class.forName(driver);
			//db���� ����
			conn = DriverManager.getConnection(url, user, pwd);
			
			//����� ���������� statement��ü����
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			//���
			re = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}finally{
			try {
				//�ڿ� �ݱ�
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				System.out.println("���ܹ߻�:"+e.getMessage());
			}		
			
		}

		return re;
	}

	
}
