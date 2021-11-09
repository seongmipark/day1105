package com.sist.goods03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class GoodsDao {
	
	//데이터베이스에 접근하여 모든 상품목록을 읽어와서 반환하는 메소드정의
	//select * from goods
	//상품레코드 하나하나를 GoodsVo로 포장하고 
	//이것들을 모두 ArrayList에 담아 반환
	public ArrayList<GoodsVo> listGoods(){
		ArrayList<GoodsVo> list = new ArrayList<GoodsVo>();
		
		//데이터베이스에 연결하여 실행할 명령어
				String sql = "select*from goods order by no";
				
				//데이터베이스 연결과 필요한 변수들을 선언한다.
				Connection conn = null; //DB서버에 연결하기 위한 변수 
				PreparedStatement pstmt = null;	//데이터베이스 명령을 실행하기 위한 변수
				ResultSet rs = null;	//읽어온 자료를 담기위한 변수
				
				try {
					//1.jdbc드라이버를 메모리로 로드한다.
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()){
						int no = rs.getInt(1);
						String item = rs.getString(2);
						int qty = rs.getInt(3);
						int price = rs.getInt(4);
						
						//레코드의 내용을 GoodsVo객체로 만들어 list에 담기
						GoodsVo g = new GoodsVo(no, item, qty, price);
						list.add(g);
					}
					
				} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
				}finally {
					try {
						//사용했던 자원들을 닫아준다.
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
	
	
	
	//수정을 위한 메소드 정의
	public int updateGoods(GoodsVo g){
		int re =-1;
		//String sql = "update goods set item='"+item+"',qty="+qty+",price="+price+" where no="+no;
		String sql = "update goods set item=?,qty=?,price=? where no=?";
		Connection conn =null;
		
		//sql명령어안에 ?가 있는 경우 Statement의 후손클래스인 PreparedStatement를 이용
		PreparedStatement pstmt = null;
		
		try {
			//1. jdbc드라이버를 메모리로 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			
			//2.DB서버에 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
			
			
			//3.데이터베이스명령을 실행할 수 있는 Statement객체를 생성
			//PreparedStatement 생성시에 ?가 있는 미리 만들어놓은 명령어를 매개변수로 전달
			pstmt = conn.prepareStatement(sql);
			
			//PerparedStatement 객체의 결정되지 않는 ?에 차례대로 값을 설정해야 한다.
			pstmt.setString(1,g.getItem());
			pstmt.setInt(2,g.getQty());
			pstmt.setInt(3,g.getPrice());
			pstmt.setInt(4,g.getNo());
			
			//4.데이터베이스 명령을 실행한다.
			//PreparedStatement 객체 생성시에 이미 sql이 전달되었고
			//그리고 위에서 각각의 ?값이 연결된 상태이기 때문에
			//executeUpdate할때 sql를 전달하지 않아야 한다.
			//만약 여기서 매개변수로 sql를 전달하게되면 ?값이 정해지지 않은 상태로 명령을 실행하게되어 오류발생
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
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
	
	
	
	
	//삭제할 상품번호를 매개변수로 전달받아 해당 상품을 DB에 삭제하는 메소드 정의
	public int deleteGoods(int no) {
		int re = -1;
		
		//String sql = "delete goods where no="+no;
		String sql ="delete goods where no=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			//1. jdbc드라이버를 메모리로 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB서버에 연결
			String url ="jdbc:oracle:thin:@localhost:1521:XE";
			String user ="c##sist";
			String pwd="sist";
			conn = DriverManager.getConnection(url,user,pwd);
			
			//3.데이터베이스명령을 실행할 수 있는 Statement객체를 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,no);
			
			//4.데이터베이스 명령을 실행한다.
			re = pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("예외발생:"+ex.getMessage());
		}finally {
			try {
				//5.사용했던 자원을 닫아준다.
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
	
	
	//DB에 연결하여 goods테이블의 모든 상품을 읽어와서(=조회=검색)
	//테이블에 출력하는 메소드 정의
	public int insertGoods(GoodsVo g){
		int re = -1;
		
		//String sql = "insert into goods values("+no+",'"+item+"',"+qty+","+price+")";
		String sql = "insert into goods values(?,?,?,?)";
		
		
		//finally에서도 접근할 수 있도록 Connection과 Statement변수를
		//try문 바깥에 선언한다
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			//1. jdbc드라이버를 메모리로 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB서버에 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
			
			//3.데이터베이스명령을 실행할 수 있는 Statement객체를 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,g.getNo());
			pstmt.setString(2,g.getItem());
			pstmt.setInt(3,g.getQty());
			pstmt.setInt(4,g.getPrice());
			
			//4.데이터베이스 명령을 실행한다.
			re = pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("예외발생:"+ex.getMessage());
		}finally {
			try {
				//5.사용했던 자원을 닫아준다.
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
