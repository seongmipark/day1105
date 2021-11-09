package com.sist.goods03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class GoodsDao {
	
	//�����ͺ��̽��� �����Ͽ� ��� ��ǰ����� �о�ͼ� ��ȯ�ϴ� �޼ҵ�����
	//select * from goods
	//��ǰ���ڵ� �ϳ��ϳ��� GoodsVo�� �����ϰ� 
	//�̰͵��� ��� ArrayList�� ��� ��ȯ
	public ArrayList<GoodsVo> listGoods(){
		ArrayList<GoodsVo> list = new ArrayList<GoodsVo>();
		
		//�����ͺ��̽��� �����Ͽ� ������ ��ɾ�
				String sql = "select*from goods order by no";
				
				//�����ͺ��̽� ����� �ʿ��� �������� �����Ѵ�.
				Connection conn = null; //DB������ �����ϱ� ���� ���� 
				PreparedStatement pstmt = null;	//�����ͺ��̽� ����� �����ϱ� ���� ����
				ResultSet rs = null;	//�о�� �ڷḦ ������� ����
				
				try {
					//1.jdbc����̹��� �޸𸮷� �ε��Ѵ�.
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()){
						int no = rs.getInt(1);
						String item = rs.getString(2);
						int qty = rs.getInt(3);
						int price = rs.getInt(4);
						
						//���ڵ��� ������ GoodsVo��ü�� ����� list�� ���
						GoodsVo g = new GoodsVo(no, item, qty, price);
						list.add(g);
					}
					
				} catch (Exception e) {
				System.out.println("���ܹ߻�:"+e.getMessage());
				}finally {
					try {
						//����ߴ� �ڿ����� �ݾ��ش�.
						if(rs != null) {
							rs.close();
						}
						if(pstmt != null) {
							pstmt.close();
						}
						if(conn != null) {
							conn.close();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
		return list;
	}
	
	
	
	//������ ���� �޼ҵ� ����
	public int updateGoods(GoodsVo g){
		int re =-1;
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
			pstmt.setString(1,g.getItem());
			pstmt.setInt(2,g.getQty());
			pstmt.setInt(3,g.getPrice());
			pstmt.setInt(4,g.getNo());
			
			//4.�����ͺ��̽� ����� �����Ѵ�.
			//PreparedStatement ��ü �����ÿ� �̹� sql�� ���޵Ǿ���
			//�׸��� ������ ������ ?���� ����� �����̱� ������
			//executeUpdate�Ҷ� sql�� �������� �ʾƾ� �Ѵ�.
			//���� ���⼭ �Ű������� sql�� �����ϰԵǸ� ?���� �������� ���� ���·� ����� �����ϰԵǾ� �����߻�
			re = pstmt.executeUpdate();
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
		return re;
	}
	
	
	
	
	//������ ��ǰ��ȣ�� �Ű������� ���޹޾� �ش� ��ǰ�� DB�� �����ϴ� �޼ҵ� ����
	public int deleteGoods(int no) {
		int re = -1;
		
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
			re = pstmt.executeUpdate();
			
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
		return re;
	}
	
	
	//DB�� �����Ͽ� goods���̺��� ��� ��ǰ�� �о�ͼ�(=��ȸ=�˻�)
	//���̺� ����ϴ� �޼ҵ� ����
	public int insertGoods(GoodsVo g){
		int re = -1;
		
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
			pstmt.setInt(1,g.getNo());
			pstmt.setString(2,g.getItem());
			pstmt.setInt(3,g.getQty());
			pstmt.setInt(4,g.getPrice());
			
			//4.�����ͺ��̽� ����� �����Ѵ�.
			re = pstmt.executeUpdate();
			
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
		return re;
	}
	
}
