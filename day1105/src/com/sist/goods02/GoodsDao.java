package com.sist.goods02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class GoodsDao {
	//수정을 위한 메소드 정의
	public void updateGoods(int no, String item, int qty, int price){
		
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
			pstmt.setString(1,item);
			pstmt.setInt(2,qty);
			pstmt.setInt(3,price);
			pstmt.setInt(4,no);
			
			//4.데이터베이스 명령을 실행한다.
			//PreparedStatement 객체 생성시에 이미 sql이 전달되었고
			//그리고 위에서 각각의 ?값이 연결된 상태이기 때문에
			//executeUpdate할때 sql를 전달하지 않아야 한다.
			//만약 여기서 매개변수로 sql를 전달하게되면 ?값이 정해지지 않은 상태로 명령을 실행하게되어 오류발생
			int re = pstmt.executeUpdate();
			
			//나머지 일처리를 한다.
			if(re == 1) {
				System.out.println("상품수정에 성공했습니다.");
				
			}else {
				System.out.println("상품수정에 실패했습니다.");
			}
			
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
		
	}
	
	
	
	
	//삭제할 상품번호를 매개변수로 전달받아 해당 상품을 DB에 삭제하는 메소드 정의
	public void deleteGoods(int no) {
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
			int re = pstmt.executeUpdate();
			if(re == 1) {
				System.out.println("상품삭제에 성공했습니다.");
				
			}else {
				System.out.println("상품삭제에 실패했습니다.");
			}
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
	}
	
	
	//DB에 연결하여 goods테이블의 모든 상품을 읽어와서(=조회=검색)
	//테이블에 출력하는 메소드 정의
	public void insertGoods(int no, String item, int qty, int price){
		
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
			pstmt.setInt(1,no);
			pstmt.setString(2,item);
			pstmt.setInt(3,qty);
			pstmt.setInt(4,price);
			
			//4.데이터베이스 명령을 실행한다.
			int re = pstmt.executeUpdate();
			if(re == 1) {
				System.out.println("상품등록에 성공했습니다.");
				
			}else {
				System.out.println("상품등록에 실패했습니다.");
			}
			

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
	}
	
}
