package com.sist.member;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.LabelView;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MemberTest extends JFrame {
	
	JTextField jtf_no;
	JTextField jtf_name;
	JTextField jtf_addr;
	JTextField jtf_age;
	JTextField jtf_phone;
	
	MemberDao dao;
	
	JTable table;
	Vector<Vector> rowData;
	Vector colNames;

	public void printMember() {
		
		rowData.clear();
		
		ArrayList<MemberVo> list = dao.listMember();
		
		for(MemberVo m : list) {
			Vector v = new Vector();
			v.add(m.getNo());
			v.add(m.getName());
			v.add(m.getAddr());
			v.add(m.getAge());
			v.add(m.getPhone());
			rowData.add(v);
		}
		table.updateUI();
	}
	
	
	
	
	public MemberTest() {
		
		dao = new MemberDao();
		
		colNames = new Vector();
		rowData = new Vector<Vector>();
		
		colNames.add("회원번호");
		colNames.add("회원이름");
		colNames.add("회원주소");
		colNames.add("회원나이");
		colNames.add("회원전화");
		
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		
		jtf_no = new JTextField();
		jtf_name = new JTextField();
		jtf_addr = new JTextField();
		jtf_age = new JTextField();
		jtf_phone = new JTextField();
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5,2));
		p.add(new JLabel("회원번호:"));
		p.add(jtf_no);
		p.add(new JLabel("회원이름:"));
		p.add(jtf_name);
		p.add(new JLabel("회원주소:"));
		p.add(jtf_addr);
		p.add(new JLabel("회원나이:"));
		p.add(jtf_age);
		p.add(new JLabel("회원전화:"));
		p.add(jtf_phone);

		JButton btn_add = new JButton("추가");
		JButton btn_list = new JButton("목록");
		JButton btn_update = new JButton("수정");
		JButton btn_delete = new JButton("삭제");
	
		JPanel p_btn = new JPanel();
		p_btn.add(btn_add);
		p_btn.add(btn_list);
		p_btn.add(btn_update);
		p_btn.add(btn_delete);
		
		JPanel p_center = new JPanel();
		p_center.setLayout(new BorderLayout());
		p_center.add(p, BorderLayout.CENTER);
		p_center.add(p_btn, BorderLayout.SOUTH);
		
		add(p_center,BorderLayout.CENTER);
		add(jsp, BorderLayout.SOUTH);
		
		setSize(800,600);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//테이블 항목을 선택하면 선택한 항목의 자료를 각각의 텍스트 필드에 출려되도록
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
				//선택한 행의 번호를 반환해준다.
				int index = table.getSelectedRow();
				
				//rowData의 index번째의 벡터를 꺼집어내온다.
				Vector v = rowData.get(index);

				//그 벡터의 요소를 차례로 입력상자에 출력
				jtf_no.setText(v.get(0)+"");
				jtf_name.setText(v.get(1)+"");
				jtf_addr.setText(v.get(2)+"");
				jtf_age.setText(v.get(3)+"");
				jtf_phone.setText(v.get(4)+"");
		
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		
	
		//'추가'버튼 이벤트 구현
		btn_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int no = Integer.parseInt(jtf_no.getText());
				String name = jtf_name.getText();
				String addr = jtf_addr.getText();
				int age = Integer.parseInt(jtf_age.getText());
				String phone = jtf_phone.getText();
				
				MemberVo m = new MemberVo(no, name, addr, age, phone);
				
				int re =dao.insertMember(m);
				if(re==1) {
					System.out.println("등록에 성공했습니다.");
					printMember();
					
				}else {
					System.out.println("등록에 실패했습니다.");
				}
				
			}
				
		});
		
		
		
		
		//'목록'버튼 이벤트 구현
		//dao의 listMember메소드를 호출하여 반환되는 내용을 rowData에 담고
		//table를 갱신하도록 하여 목록 출력
		btn_list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				printMember();
		
			}});
		
		
		//'수정'버튼 이벤트 구현
		btn_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int no = Integer.parseInt(jtf_no.getText());
				String name = jtf_name.getText();
				String addr = jtf_addr.getText();
				int age = Integer.parseInt(jtf_age.getText());
				String phone = jtf_phone.getText();
				
				MemberVo m = new MemberVo(no, name, addr, age, phone);
				
				int re = dao.updateMember(m);
				if(re==1) {
					System.out.println("수정에 성공했습니다.");
					printMember();
				}else {
					System.out.println("수정에 실패했습니다.");
				}
				
			}});
		
		
		
		
		//'삭제'버튼 이벤트 구현
		btn_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int no = Integer.parseInt(jtf_no.getText());
				int re = dao.deleteMember(no);
				
				if(re == 1) {
					System.out.println("삭제를 성공했습니다.");
					printMember();
				}else {
					System.out.println("삭제를 실패했습니다.");
				}
				
			}});
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MemberTest();
	}

}
