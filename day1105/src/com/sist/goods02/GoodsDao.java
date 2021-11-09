package com.sist.goods02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class GoodsDao {
	//������ ���� �޼ҵ� ����
	public void updateGoods(int no, String item, int qty, int price){
		
		//String sql = "update goods set item='"+item+"',qty="+qty+",price="+price+" where no="+no;
		String sql = "update goods set item=?,qty=?,price=? where no=?";
		Connection conn =null;
		
		//sql��ɾ�ȿ� ?�� �ִ� ��� Statement�� �ļ�Ŭ������ PreparedStatement�� �̿�
		PreparedStatement pstmt = null;
		
		try {
			//1. jdbc����̹��� �޸𸮷� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			
			//2.DB������ ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
			
			
			//3.�����ͺ��̽������ ������ �� �ִ� Statement��ü�� ����
			//PreparedStatement �����ÿ� ?�� �ִ� �̸� �������� ��ɾ �Ű������� ����
			pstmt = conn.prepareStatement(sql);
			
			//PerparedStatement ��ü�� �������� �ʴ� ?�� ���ʴ�� ���� �����ؾ� �Ѵ�.
			pstmt.setString(1,item);
			pstmt.setInt(2,qty);
			pstmt.setInt(3,price);
			pstmt.setInt(4,no);
			
			//4.�����ͺ��̽� ����� �����Ѵ�.
			//PreparedStatement ��ü �����ÿ� �̹� sql�� ���޵Ǿ���
			//�׸��� ������ ������ ?���� ����� �����̱� ������
			//executeUpdate�Ҷ� sql�� �������� �ʾƾ� �Ѵ�.
			//���� ���⼭ �Ű������� sql�� �����ϰԵǸ� ?���� �������� ���� ���·� ����� �����ϰԵǾ� �����߻�
			int re = pstmt.executeUpdate();
			
			//������ ��ó���� �Ѵ�.
			if(re == 1) {
				System.out.println("��ǰ������ �����߽��ϴ�.");
				
			}else {
				System.out.println("��ǰ������ �����߽��ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception ex) {
				// TODO: handle exception
			}
		}
		
	}
	
	
	
	
	//������ ��ǰ��ȣ�� �Ű������� ���޹޾� �ش� ��ǰ�� DB�� �����ϴ� �޼ҵ� ����
	public void deleteGoods(int no) {
		//String sql = "delete goods where no="+no;
		String sql ="delete goods where no=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			//1. jdbc����̹��� �޸𸮷� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB������ ����
			String url ="jdbc:oracle:thin:@localhost:1521:XE";
			String user ="c##sist";
			String pwd="sist";
			conn = DriverManager.getConnection(url,user,pwd);
			
			//3.�����ͺ��̽������ ������ �� �ִ� Statement��ü�� ����
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,no);
			
			//4.�����ͺ��̽� ����� �����Ѵ�.
			int re = pstmt.executeUpdate();
			if(re == 1) {
				System.out.println("��ǰ������ �����߽��ϴ�.");
				
			}else {
				System.out.println("��ǰ������ �����߽��ϴ�.");
			}
		} catch (Exception ex) {
			System.out.println("���ܹ߻�:"+ex.getMessage());
		}finally {
			try {
				//5.����ߴ� �ڿ��� �ݾ��ش�.
				if(pstmt !=null) {
					pstmt.close();
				}

				if(conn != null) {
					conn.close();	
				}
					
			} catch (Exception ex) {
			}

		}
	}
	
	
	//DB�� �����Ͽ� goods���̺��� ��� ��ǰ�� �о�ͼ�(=��ȸ=�˻�)
	//���̺� ����ϴ� �޼ҵ� ����
	public void insertGoods(int no, String item, int qty, int price){
		
		//String sql = "insert into goods values("+no+",'"+item+"',"+qty+","+price+")";
		String sql = "insert into goods values(?,?,?,?)";
		
		
		//finally������ ������ �� �ֵ��� Connection�� Statement������
		//try�� �ٱ��� �����Ѵ�
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			//1. jdbc����̹��� �޸𸮷� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB������ ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
			
			//3.�����ͺ��̽������ ������ �� �ִ� Statement��ü�� ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			pstmt.setString(2,item);
			pstmt.setInt(3,qty);
			pstmt.setInt(4,price);
			
			//4.�����ͺ��̽� ����� �����Ѵ�.
			int re = pstmt.executeUpdate();
			if(re == 1) {
				System.out.println("��ǰ��Ͽ� �����߽��ϴ�.");
				
			}else {
				System.out.println("��ǰ��Ͽ� �����߽��ϴ�.");
			}
			

		} catch (Exception ex) {
			System.out.println("���ܹ߻�:"+ex.getMessage());
		}finally {
			try {
				//5.����ߴ� �ڿ��� �ݾ��ش�.
				if(pstmt !=null) {
					pstmt.close();
				}

				if(conn != null) {
					conn.close();	
				}
					
			} catch (Exception ex) {
			}

		}
	}
	
}
