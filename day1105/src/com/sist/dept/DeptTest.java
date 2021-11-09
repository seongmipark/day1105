package com.sist.dept;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeptTest extends JFrame {

	JTextField jtf_no;
	JTextField jtf_name;
	JTextField jtf_city;
	
	JTable table;
	Vector colNames;
	Vector<Vector> rowData;

	DeptDao dao;
	
	
	public void printDept() {
		rowData.clear();
		
		ArrayList<DeptVo> list = dao.listDept();
		
		for(DeptVo d :list) {
			Vector v= new Vector();
			v.add(d.getNo());
			v.add(d.getName());
			v.add(d.getCity());
			rowData.add(v);
		}
		
		table.updateUI();
	}
	
	
	public DeptTest() {
		
	dao = new DeptDao();

	jtf_no = new JTextField(); 
	jtf_name = new JTextField(); 
	jtf_city = new JTextField(); 
	
	colNames = new Vector<String>();
	colNames.add("부서번호");
	colNames.add("부서이름");
	colNames.add("부서위치");
	rowData = new Vector<Vector>();
	
	table = new JTable(rowData, colNames);
	JScrollPane jsp = new JScrollPane(table);	
	
	JPanel p = new JPanel();
	p.setLayout(new GridLayout(2,3));
	p.add(new JLabel("부서번호"));
	p.add(new JLabel("부서이름"));
	p.add(new JLabel("부서위치"));
	p.add(jtf_no);
	p.add(jtf_name);
	p.add(jtf_city);
	
	JPanel p_btn = new JPanel();
	JButton btn_list = new JButton("목록");
	JButton btn_add = new JButton("추가");
	JButton btn_update = new JButton("수정");
	JButton btn_delete = new JButton("삭제");
	
	p_btn.add(btn_list);
	p_btn.add(btn_add);
	p_btn.add(btn_update);
	p_btn.add(btn_delete);
	
	JPanel p_center = new JPanel();
	p_center.setLayout(new BorderLayout());
	p_center.add(p,BorderLayout.CENTER);
	p_center.add(p_btn,BorderLayout.SOUTH);
	

	add(jsp,BorderLayout.CENTER);
	add(p_center,BorderLayout.SOUTH);

	setSize(600,400);
	setVisible(true);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	//마우스클릭이벤트 생성
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
			jtf_city.setText(v.get(2)+"");
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}});
	
	

	//'목록'버튼 이벤트셍성
	btn_list.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		printDept();
	
		}});
	
	
	
	//'추가'버튼 이벤트생성
	btn_add.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int no=Integer.parseInt(jtf_no.getText());
			String name=jtf_name.getText();
			String city=jtf_city.getText();
			
			DeptVo d = new DeptVo(no, name, city);
			int re = dao.insertDept(d);
			if(re==1) {
				System.out.println("추가에 성공했습니다.");
				printDept();
			}else{
				System.out.println("추가에 실패했습니다.");
	}
		}});
	
	
	//'수정'버튼 이벤트셍성
	btn_update.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int no = Integer.parseInt(jtf_no.getText());
			String name = jtf_name.getText();
			String city = jtf_city.getText();
			
			DeptVo d = new DeptVo(no, name, city);
			
			int re = dao.updateDept(d);
			if(re==1) {
				System.out.println("수정에 성공했습니다.");
				printDept();
			}else {
				System.out.println("수정에 실패했습니다.");
			}
			
		}});
	
	
	//'삭제'버튼 이벤트셍성
	btn_delete.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int no = Integer.parseInt(jtf_no.getText());
			int re = dao.deleteDept(no);
			if(re==1) {
				System.out.println("삭제에 성공했습니다");
				printDept();
			}else {
				System.out.println("삭제에 실패했습니다.");
			}
		}});

	}//end생성자 
	
	
	public static void main(String[] args) {
		new DeptTest();
	}
}
