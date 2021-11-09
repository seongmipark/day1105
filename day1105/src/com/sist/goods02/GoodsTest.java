package com.sist.goods02;
//���̺� ��ϱ��� ������ ���α׷� �ۼ�
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

	JTextField jtf_no;     //��ǰ��ȣ �Է»���
	JTextField jtf_item;   //��ǰ�� �Է»���
	JTextField jtf_qty;    //���� �Է»���
	JTextField jtf_price;  //���� �Է»���
	
	JTable table;  //��� ��ǰ����� ������ ���� ������� �����ֱ� ���� ���̺��� ����
	Vector colNames; //���̺��� �÷��̸��� ���� ���͸� ����
	Vector<Vector> rowData; //���̺��� �����͸� ���� ���͸� ����
	
	//DB�� �����Ͽ� �߰�,���,����,��������� ���� �ִ� dao�� ����� ����
	GoodsDao dao;
	
	
	public GoodsTest() {
		
		//dao�� �����Ѵ�.
		dao = new GoodsDao();
		
		//���̺� �� Į���̸��� ���� ���͸� �����ϰ� �ڷḦ �߰��Ѵ�.
		colNames = new Vector<String>();
		colNames.add("��ǰ��ȣ");
		colNames.add("��ǰ��");
		colNames.add("����");
		colNames.add("�ܰ�");
		
		//���̺� �� ���� �����͵��� ��� ���� ���͸� �����ϰ� �ڷḦ �߰��Ѵ�.
		rowData = new Vector<Vector>();

		//�÷��̸��� �ִ� colNames���Ϳ� ���������Ͱ��ִ� rowData���͸� ����
		//������ ���� ȭ���� ���̺��� ����
		table = new JTable(rowData,colNames);
		
		
		//���̺��� �ڷᰡ �ʹ� ���Ƽ� �� ȭ�鿡 ������ ���� ���� �ڵ�����
		//��ũ���� ����� �ִ� ��ũ�� ���� ����
		JScrollPane jsp = new JScrollPane(table);		
		
		
		jtf_no = new JTextField();
		jtf_item = new JTextField();
		jtf_qty = new JTextField();
		jtf_price = new JTextField();
		
		//�Է»��ڵ�� ������ �Է��ؾ����� �����ϴ� �󺧵��� ������� �г� ����
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,2));
		
		//�󺧰� �Է»���(�ؽ�Ʈ�ʵ�)���� �гο� ���ʴ�� ��´�.
		p.add(new JLabel("��ǰ��ȣ:"));
		p.add(jtf_no);
		p.add(new JLabel("��ǰ�̸�:"));
		p.add(jtf_item);
		p.add(new JLabel("��ǰ����:"));
		p.add(jtf_qty);
		p.add(new JLabel("��ǰ�ܰ�:"));
		p.add(jtf_price);
		
		//'�߰�'��ư �����
		JButton btn_add = new JButton("�߰�");
		
		//'���'��ư �����
		JButton btn_list = new JButton("���");

		//'����'��ư �����
		JButton btn_update = new JButton("����");
		
		//'����'��ư �����
		JButton btn_delete = new JButton("����");

		//��ư���� ���� �г��� ����
		JPanel p2 = new JPanel();
		p2.add(btn_add);
		p2.add(btn_list);
		p2.add(btn_update);
		p2.add(btn_delete);
		
		//�Է»��ڵ��� �ִ� �гΰ� ��ư�� �ִ� �г��� ������� �г��� �����.
		JPanel p_center = new JPanel();
		
		p_center.setLayout(new BorderLayout());
		p_center.add(p,BorderLayout.CENTER);
		p_center.add(p2,BorderLayout.SOUTH);
		
		//�������� ����� �Է»��ڿ� ��ư�� ����ִ� p_center�г��� ��´�.
		add(p_center, BorderLayout.CENTER);
		//���̺��� ����ִ� ��ũ������ �������� �Ʒ��ʿ� ��´�.
		add(jsp,BorderLayout.SOUTH);
		
		//�������� ���α���,���α��̸� �����ϰ� ȭ�鿡 �����ֵ��� ����
		setSize(800,600);
		setVisible(true);
		
		//â�� ������ ���α׷��� �����ϵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//'����'��ư�� ������ ������ ��ǰ�� ��ȣ,�̸�,����,������ �ؽ�Ʈ�ʵ�κ��� �о��
		//�װ��� �޼ҵ忡�� �����Ͽ� �����ϵ��� �Ѵ�.
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
	
		
		//'����'��ư�� ������ �ؽ�Ʈ�ʵ��� ������ ��ǰ��ȣ�� ����� ������ �ϴ� �޼ҵ� ȣ��
		btn_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int no = Integer.parseInt(jtf_no.getText());
				dao.deleteGoods(no);

				}	
		});


		
		//'���'��ư�� ������ �����ͺ��̽��� �����Ͽ�
		//��� ��ǰ����� �о�ͼ� ���̺� ����ϴ� �޼ҵ� ȣ��
		btn_list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//printGoods();
			}
			
		});
		

		//'�߰�'��ư�� ������ ����ڰ� �Է��� ��ǰ��ȣ, ��ǰ�̸�, ��ǰ����,��ǰ��������
		//�����ͺ��̽� ���̺� �ڷḦ �߰��ϵ��� ����
		btn_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//�����ͺ��̽� insert�� �����ϴ� �޼ҵ带 ����� ȣ���ϵ��� ����
				int no = Integer.parseInt(jtf_no.getText());
				String item = jtf_item.getText();
				int qty = Integer.parseInt(jtf_qty.getText());
				int price = Integer.parseInt(jtf_price.getText());
				
				dao.insertGoods(no,item,qty,price);
				
				}	
		});
		
		
		
		//���̺� ���콺 �̺�Ʈ�� ����Ͽ� ������ ���� ��ǰ�� ������ ������ �Է»��ڿ� ���
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
				int index = table.getSelectedRow(); //������ ���� ��ȣ�� ��ȯ���ش�.

				//rowData�� index��°�� ���͸� ������´�.
				Vector vr = rowData.get(index);
				
				//�� ������ ��Ҹ� ���ʷ� �Է»��ڿ� ���
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
		//InsertGoods ��ü ����
		new GoodsTest();
	}

}
