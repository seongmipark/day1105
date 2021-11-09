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
		
		colNames.add("ȸ����ȣ");
		colNames.add("ȸ���̸�");
		colNames.add("ȸ���ּ�");
		colNames.add("ȸ������");
		colNames.add("ȸ����ȭ");
		
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		
		jtf_no = new JTextField();
		jtf_name = new JTextField();
		jtf_addr = new JTextField();
		jtf_age = new JTextField();
		jtf_phone = new JTextField();
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5,2));
		p.add(new JLabel("ȸ����ȣ:"));
		p.add(jtf_no);
		p.add(new JLabel("ȸ���̸�:"));
		p.add(jtf_name);
		p.add(new JLabel("ȸ���ּ�:"));
		p.add(jtf_addr);
		p.add(new JLabel("ȸ������:"));
		p.add(jtf_age);
		p.add(new JLabel("ȸ����ȭ:"));
		p.add(jtf_phone);

		JButton btn_add = new JButton("�߰�");
		JButton btn_list = new JButton("���");
		JButton btn_update = new JButton("����");
		JButton btn_delete = new JButton("����");
	
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
		
		
		//���̺� �׸��� �����ϸ� ������ �׸��� �ڷḦ ������ �ؽ�Ʈ �ʵ忡 ����ǵ���
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
				//������ ���� ��ȣ�� ��ȯ���ش�.
				int index = table.getSelectedRow();
				
				//rowData�� index��°�� ���͸� ������´�.
				Vector v = rowData.get(index);

				//�� ������ ��Ҹ� ���ʷ� �Է»��ڿ� ���
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
		
		
	
		//'�߰�'��ư �̺�Ʈ ����
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
					System.out.println("��Ͽ� �����߽��ϴ�.");
					printMember();
					
				}else {
					System.out.println("��Ͽ� �����߽��ϴ�.");
				}
				
			}
				
		});
		
		
		
		
		//'���'��ư �̺�Ʈ ����
		//dao�� listMember�޼ҵ带 ȣ���Ͽ� ��ȯ�Ǵ� ������ rowData�� ���
		//table�� �����ϵ��� �Ͽ� ��� ���
		btn_list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				printMember();
		
			}});
		
		
		//'����'��ư �̺�Ʈ ����
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
					System.out.println("������ �����߽��ϴ�.");
					printMember();
				}else {
					System.out.println("������ �����߽��ϴ�.");
				}
				
			}});
		
		
		
		
		//'����'��ư �̺�Ʈ ����
		btn_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int no = Integer.parseInt(jtf_no.getText());
				int re = dao.deleteMember(no);
				
				if(re == 1) {
					System.out.println("������ �����߽��ϴ�.");
					printMember();
				}else {
					System.out.println("������ �����߽��ϴ�.");
				}
				
			}});
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MemberTest();
	}

}
