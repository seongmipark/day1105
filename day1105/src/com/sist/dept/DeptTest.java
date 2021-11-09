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
	colNames.add("�μ���ȣ");
	colNames.add("�μ��̸�");
	colNames.add("�μ���ġ");
	rowData = new Vector<Vector>();
	
	table = new JTable(rowData, colNames);
	JScrollPane jsp = new JScrollPane(table);	
	
	JPanel p = new JPanel();
	p.setLayout(new GridLayout(2,3));
	p.add(new JLabel("�μ���ȣ"));
	p.add(new JLabel("�μ��̸�"));
	p.add(new JLabel("�μ���ġ"));
	p.add(jtf_no);
	p.add(jtf_name);
	p.add(jtf_city);
	
	JPanel p_btn = new JPanel();
	JButton btn_list = new JButton("���");
	JButton btn_add = new JButton("�߰�");
	JButton btn_update = new JButton("����");
	JButton btn_delete = new JButton("����");
	
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
	
	
	//���콺Ŭ���̺�Ʈ ����
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
			
			//rowData�� index��°�� ���͸� ������´�.
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
	
	

	//'���'��ư �̺�Ʈ�ļ�
	btn_list.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		printDept();
	
		}});
	
	
	
	//'�߰�'��ư �̺�Ʈ����
	btn_add.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int no=Integer.parseInt(jtf_no.getText());
			String name=jtf_name.getText();
			String city=jtf_city.getText();
			
			DeptVo d = new DeptVo(no, name, city);
			int re = dao.insertDept(d);
			if(re==1) {
				System.out.println("�߰��� �����߽��ϴ�.");
				printDept();
			}else{
				System.out.println("�߰��� �����߽��ϴ�.");
	}
		}});
	
	
	//'����'��ư �̺�Ʈ�ļ�
	btn_update.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int no = Integer.parseInt(jtf_no.getText());
			String name = jtf_name.getText();
			String city = jtf_city.getText();
			
			DeptVo d = new DeptVo(no, name, city);
			
			int re = dao.updateDept(d);
			if(re==1) {
				System.out.println("������ �����߽��ϴ�.");
				printDept();
			}else {
				System.out.println("������ �����߽��ϴ�.");
			}
			
		}});
	
	
	//'����'��ư �̺�Ʈ�ļ�
	btn_delete.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int no = Integer.parseInt(jtf_no.getText());
			int re = dao.deleteDept(no);
			if(re==1) {
				System.out.println("������ �����߽��ϴ�");
				printDept();
			}else {
				System.out.println("������ �����߽��ϴ�.");
			}
		}});

	}//end������ 
	
	
	public static void main(String[] args) {
		new DeptTest();
	}
}
