package com.sist.goods02;
//테이블 목록까지 나오는 프로그램 작성
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GoodsTest extends JFrame {

	JTextField jtf_no;     //상품번호 입력상자
	JTextField jtf_item;   //상품명 입력상자
	JTextField jtf_qty;    //수량 입력상자
	JTextField jtf_price;  //가격 입력상자
	
	JTable table;  //모든 상품목록을 엑셀과 같은 모양으로 보여주기 위한 테이블을 선언
	Vector colNames; //테이블의 컬럼이름을 위한 벡터를 선언
	Vector<Vector> rowData; //테이블의 데이터를 위한 벡터를 선언
	
	//DB에 접근하여 추가,목록,수정,삭제기능을 갖고 있는 dao로 멤버로 선언
	GoodsDao dao;
	
	
	public GoodsTest() {
		
		//dao를 생성한다.
		dao = new GoodsDao();
		
		//테이블에 들어갈 칼럼이름을 위한 벡터를 생성하고 자료를 추가한다.
		colNames = new Vector<String>();
		colNames.add("상품번호");
		colNames.add("상품명");
		colNames.add("수량");
		colNames.add("단가");
		
		//테이블에 들어갈 실제 데이터들을 담기 위한 벡터를 생성하고 자료를 추가한다.
		rowData = new Vector<Vector>();

		//컬럼이름이 있는 colNames벡터와 실제데이터가있는 rowData벡터를 갖고
		//엑셀과 같은 화면의 테이블을 생성
		table = new JTable(rowData,colNames);
		
		
		//테이블의 자료가 너무 많아서 한 화면에 보이지 않을 때에 자동으로
		//스크롤을 만들어 주는 스크롤 팬을 생성
		JScrollPane jsp = new JScrollPane(table);		
		
		
		jtf_no = new JTextField();
		jtf_item = new JTextField();
		jtf_qty = new JTextField();
		jtf_price = new JTextField();
		
		//입력상자들과 무엇을 입력해야할지 설명하는 라벨들을 담기위한 패널 생성
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,2));
		
		//라벨과 입력상자(텍스트필드)들을 패널에 차례대로 담는다.
		p.add(new JLabel("상품번호:"));
		p.add(jtf_no);
		p.add(new JLabel("상품이름:"));
		p.add(jtf_item);
		p.add(new JLabel("상품수량:"));
		p.add(jtf_qty);
		p.add(new JLabel("상품단가:"));
		p.add(jtf_price);
		
		//'추가'버튼 만들기
		JButton btn_add = new JButton("추가");
		
		//'목록'버튼 만들기
		JButton btn_list = new JButton("목록");

		//'수정'버튼 만들기
		JButton btn_update = new JButton("수정");
		
		//'삭제'버튼 만들기
		JButton btn_delete = new JButton("삭제");

		//버튼들을 담을 패널을 생성
		JPanel p2 = new JPanel();
		p2.add(btn_add);
		p2.add(btn_list);
		p2.add(btn_update);
		p2.add(btn_delete);
		
		//입력상자들이 있는 패널과 버튼이 있는 패널을 담기위한 패널을 만든다.
		JPanel p_center = new JPanel();
		
		p_center.setLayout(new BorderLayout());
		p_center.add(p,BorderLayout.CENTER);
		p_center.add(p2,BorderLayout.SOUTH);
		
		//프레임의 가운데에 입력상자와 버튼을 담고있는 p_center패널을 담는다.
		add(p_center, BorderLayout.CENTER);
		//테이블을 담고있는 스크롤팬을 프레임의 아래쪽에 담는다.
		add(jsp,BorderLayout.SOUTH);
		
		//프레임의 가로길이,세로길이를 설정하고 화면에 보여주도록 설정
		setSize(800,600);
		setVisible(true);
		
		//창을 닫으면 프로그램도 종료하도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//'수정'버튼을 누르면 수정할 상품의 번호,이름,수량,가격을 텍스트필드로부터 읽어와
		//그것을 메소드에게 전달하여 수정하도록 한다.
		btn_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int no = Integer.parseInt(jtf_no.getText());
				String item = jtf_item.getText();
				int qty = Integer.parseInt(jtf_qty.getText());
				int price = Integer.parseInt(jtf_price.getText());
				
				dao.updateGoods(no,item,qty,price);	
			}	
		});
	
		
		//'삭제'버튼을 누르면 텍스트필드의 삭제할 상품번호를 갖고와 삭제를 하는 메소드 호출
		btn_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int no = Integer.parseInt(jtf_no.getText());
				dao.deleteGoods(no);

				}	
		});


		
		//'목록'버튼을 누르면 데이터베이스를 연결하여
		//모든 상품목록을 읽어와서 테이블에 출력하는 메소드 호출
		btn_list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//printGoods();
			}
			
		});
		

		//'추가'버튼을 누르면 사용자가 입력한 상품번호, 상품이름, 상품수량,상품가격으로
		//데이터베이스 테이블에 자료를 추가하도록 하자
		btn_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//데이터베이스 insert를 수행하는 메소드를 만들고 호출하도록 수정
				int no = Integer.parseInt(jtf_no.getText());
				String item = jtf_item.getText();
				int qty = Integer.parseInt(jtf_qty.getText());
				int price = Integer.parseInt(jtf_price.getText());
				
				dao.insertGoods(no,item,qty,price);
				
				}	
		});
		
		
		
		//테이블에 마우스 이벤트를 등록하여 선택한 행의 상품의 정보를 각각의 입력상자에 출력
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = table.getSelectedRow(); //선택한 행의 번호를 반환해준다.

				//rowData의 index번째의 벡터를 꺼집어내온다.
				Vector vr = rowData.get(index);
				
				//그 벡터의 요소를 차례로 입력상자에 출력
				jtf_no.setText(vr.get(0)+"");
				jtf_item.setText(vr.get(1)+"");
				jtf_qty.setText(vr.get(2)+"");
				jtf_price.setText(vr.get(3)+"");
			
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//InsertGoods 객체 생성
		new GoodsTest();
	}

}
