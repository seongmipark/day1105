
package com.sist.book;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BookTest extends JFrame {
	
	JTextField jtf_no;
	JTextField jtf_name;
	JTextField jtf_publisher;
	JTextField jtf_price;
	JTable table;
	
	Vector colNames;
	Vector<Vector> rowData;
	
	BookDao dao;

	public void printBook() {
		rowData.clear();
		ArrayList<BookVo> list = dao.listbook();
		
		for(BookVo b : list ) {
			Vector v = new Vector();
			v.add(b.getNo());
			v.add(b.getName());
			v.add(b.getPublisher());
			v.add(b.getPrice());
			rowData.add(v);
		}
		
		table.updateUI();
		
	}
	
	
	
	
	public BookTest() {
		
	dao = new BookDao();
		
	jtf_no = new JTextField();
	jtf_name = new JTextField();
	jtf_publisher = new JTextField();
	jtf_price = new JTextField();
	
	JPanel p = new JPanel();
	p.setLayout(new GridLayout(4,1));
	p.add(new JLabel("책 번호"));
	p.add(jtf_no);	
	p.add(new JLabel("책 이름"));
	p.add(jtf_name);	
	p.add(new JLabel("출판사명"));
	p.add(jtf_publisher);	
	p.add(new JLabel("책 가격"));
	p.add(jtf_price);
	
	
	JButton btn_read = new JButton("목록");
	JButton btn_add = new JButton("추가");
	JButton btn_update = new JButton("수정");
	JButton btn_delete = new JButton("삭제");
	
	JPanel p_btn = new JPanel();
	p_btn.add(btn_read);	
	p_btn.add(btn_add);	
	p_btn.add(btn_update);	
	p_btn.add(btn_delete);	
	
	JPanel p_center = new JPanel();
	p_center.setLayout(new BorderLayout());
	p_center.add(p,BorderLayout.CENTER);
	p_center.add(p_btn,BorderLayout.SOUTH);
	
	colNames = new Vector();
	colNames.add("책 번호");
	colNames.add("책 이름");
	colNames.add("출판사명");
	colNames.add("책 가격");
	
	rowData = new Vector<Vector>();
	
	table = new JTable(rowData, colNames);
	JScrollPane jsp = new JScrollPane(table);
	
	add(jsp,BorderLayout.CENTER);
	add(p_center,BorderLayout.WEST);
	
	setSize(800,400);
	setVisible(true);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
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
			int index = table.getSelectedRow();
			
			//rowData의 index번째의 벡터를 꺼집어내온다.
			Vector v = rowData.get(index);
			jtf_no.setText(v.get(0)+"");
			jtf_name.setText(v.get(1)+"");
			jtf_publisher.setText(v.get(2)+"");
			jtf_price.setText(v.get(3)+"");
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}});
	
	
	
	//'목록'버튼 이벤트생성
	btn_read.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			printBook();
			System.out.println("읽어오기 성공");
			
		}});

	
	
	//'추가'버튼 이벤트 생성
	btn_add.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int no = Integer.parseInt(jtf_no.getText());
			String name = jtf_name.getText();
			String publisher = jtf_publisher.getText();
			int price = Integer.parseInt(jtf_price.getText());
			
			BookVo b = new BookVo(no, name, publisher, price);
			int re = dao.insertBook(b);
			if(re ==1) {
				System.out.println("추가에 성공했습니다.");
				printBook();
			}else {
				System.out.println("추가에 실패했습니다.");
			}
			
		}});
	

	
	//'수정'버튼 이벤트 생성
	btn_update.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int no = Integer.parseInt(jtf_no.getText());
			String name = jtf_name.getText();
			String publisher = jtf_publisher.getText();
			int price = Integer.parseInt(jtf_price.getText());
			
			BookVo b = new BookVo(no, name, publisher, price);
			int re = dao.updateBook(b);
			
			if(re==1) {
				System.out.println("수정에 성공했습니다.");
				printBook();
			}else {
				System.out.println("수정에 실패했습니다.");
			}
			
		}});
	
	
	
	//'삭제'버튼 이벤트 생성
	btn_delete.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int no = Integer.parseInt(jtf_no.getText());
			int re = dao.deletebook(no);
			if(re==1) {
				System.out.println("삭제에 성공했습니다.");
				printBook();
			}else {
				System.out.println("삭제에 실패했습니다.");
			}		
			
			
		}});
	

	
	
	
	}
	

	public static void main(String[] args) {
			new BookTest();
	}

}
