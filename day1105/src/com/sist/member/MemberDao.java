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
	
	//�߰��� ȸ�������� MemberVo�� �Ű������� ���޹޾�
	//���̺� member�� insert�� �����ϰ�
	//�� ����� ������ ��ȯ�ϴ� �޼ҵ� ����
	public int insertMember(MemberVo m) {
		int re = -1;

		String sql = "insert into member values(?,?,?,?,?)";

		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			//1. jdbc����̹��� �޸𸮷� �ε��Ѵ�.
			Class.forName(driver);
			
			//2.DB������ ����
			conn = DriverManager.getConnection(url,user,pwd);
			
			//3.�����ͺ��̽������ ������ �� �ִ� Statement��ü�� ����
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,m.getNo());
			pstmt.setString(2,m.getName());
			pstmt.setString(3,m.getAddr());
			pstmt.setInt(4,m.getAge());
			pstmt.setString(5,m.getPhone());
			
			//4.�����ͺ��̽� ����� �����Ѵ�.
			re = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}finally {
			try {
				//5.����ߴ� �ڿ��� �ݾ��ش�.
				if(pstmt !=null) {
					pstmt.close();
				}

				if(conn != null) {
					conn.close();	
				}
					
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
			}

		}	
		return re;
	}
	
	
	
	//������ ȸ���� ������ MemberVo�� �Ű������� ���޹޾�
	// �� ����� int�� ��ȯ�ϴ� �޼ҵ� ������
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
			System.out.println("���ܹ߻�:"+e.getMessage());
		}finally {
			try {
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
	
	
	
	//������ ȸ���� ��ȣ�� ������ �Ű������� ���޹޾�
	//�ش� ȸ�������� DB���� ������ �� ����� ������ ��ȯ�ϴ� �޼ҵ� ����
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
			System.out.println("���ܹ߻�:"+e.getMessage());
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
				
			} catch (Exception e) {
				System.out.println("���ܹ߻�:"+e.getMessage());
			}
			
		}

		return re; 
		
	}
	
	
	//��� ȸ������� ��ȸ�Ͽ� �Ѹ��Ѹ��� ȸ���� MemberVo�� �����
	//�װ��� ��� ArrayList�� ��� ��ȯ�ϴ� �޼ҵ� ����
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
		
		//���ڵ��� ������ GoodsVo��ü�� ����� list�� ���
		MemberVo m = new MemberVo(no, name, addr, age, phone);
		list.add(m);
		}
	
		} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
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
			System.out.println("���ܹ߻�:"+e.getMessage());
			}
			
		}	
		return list;
	}
	
	
	
	
}
