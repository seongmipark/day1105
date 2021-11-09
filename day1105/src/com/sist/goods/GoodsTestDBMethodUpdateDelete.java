package com.sist.goods;
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

public class GoodsTestDBMethodUpdateDelete extends JFrame {

	JTextField jtf_no;     //��ǰ��ȣ �Է»���
	JTextField jtf_item;   //��ǰ�� �Է»���
	JTextField jtf_qty;    //���� �Է»���
	JTextField jtf_price;  //���� �Է»���
	
	JTable table;  //��� ��ǰ����� ������ ���� ������� �����ֱ� ���� ���̺��� ����
	Vector colNames; //���̺��� �÷��̸��� ���� ���͸� ����
	Vector<Vector> rowData; //���̺��� �����͸� ���� ���͸� ����
	
	
	//������ ���� �޼ҵ� ����
	public void updateGoods(int no, String item, int qty, int price){
		
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
			pstmt.setString(1,item);
			pstmt.setInt(2,qty);
			pstmt.setInt(3,price);
			pstmt.setInt(4,no);
			
			//4.�����ͺ��̽� ����� �����Ѵ�.
			//PreparedStatement ��ü �����ÿ� �̹� sql�� ���޵Ǿ���
			//�׸��� ������ ������ ?���� ����� �����̱� ������
			//executeUpdate�Ҷ� sql�� �������� �ʾƾ� �Ѵ�.
			//���� ���⼭ �Ű������� sql�� �����ϰԵǸ� ?���� �������� ���� ���·� ����� �����ϰԵǾ� �����߻�
			int re = pstmt.executeUpdate();
			
			//������ ��ó���� �Ѵ�.
			if(re == 1) {
				System.out.println("��ǰ������ �����߽��ϴ�.");
				jtf_no.setText("");
				jtf_item.setText("");
				jtf_qty.setText("");
				jtf_price.setText("");
				
				//��ǰ������ �����ϸ� ���̺��� ������ �ٽ� �о�� ȣ���Ѵ�
				printGoods();
				
			}else {
				System.out.println("��ǰ������ �����߽��ϴ�.");
			}
			
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
		
	}
	
	
	
	
	//������ ��ǰ��ȣ�� �Ű������� ���޹޾� �ش� ��ǰ�� DB�� �����ϴ� �޼ҵ� ����
	public void deleteGoods(int no) {
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
			int re = pstmt.executeUpdate();
			if(re == 1) {
				System.out.println("��ǰ������ �����߽��ϴ�.");
				jtf_no.setText("");
				jtf_item.setText("");
				jtf_qty.setText("");
				jtf_price.setText("");
				
				//��ǰ������ �����ϸ� ���̺��� ������ �ٽ� �о�� ȣ���Ѵ�
				printGoods();
				
			}else {
				System.out.println("��ǰ������ �����߽��ϴ�.");
			}
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
	}
	
	
	//DB�� �����Ͽ� goods���̺��� ��� ��ǰ�� �о�ͼ�(=��ȸ=�˻�)
	//���̺� ����ϴ� �޼ҵ� ����
	public void insertGoods(int no, String item, int qty, int price){
		
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
			pstmt.setInt(1,no);
			pstmt.setString(2,item);
			pstmt.setInt(3,qty);
			pstmt.setInt(4,price);
			
			//4.�����ͺ��̽� ����� �����Ѵ�.
			int re = pstmt.executeUpdate();
			if(re == 1) {
				System.out.println("��ǰ��Ͽ� �����߽��ϴ�.");
				jtf_no.setText("");
				jtf_item.setText("");
				jtf_qty.setText("");
				jtf_price.setText("");
				
				//��ǰ����� �����ϸ� ���̺��� ������ �ٽ� �о�� ȣ���Ѵ�
				printGoods();
				
			}else {
				System.out.println("��ǰ��Ͽ� �����߽��ϴ�.");
			}
			

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
	}
	
	
	public void printGoods(){
		
		//���ο� ��ǰ�� ����ϸ� ����Ͽ� ȣ�� �� �� �־�
		//rowData�� ���� ������ ����ش�.
		rowData.clear();
		
		//�����ͺ��̽��� �����Ͽ� ������ ��ɾ�
		String sql = "select*from goods order by no";
		
		//�����ͺ��̽� ����� �ʿ��� �������� �����Ѵ�.
		Connection conn = null; //DB������ �����ϱ� ���� ���� 
		PreparedStatement pstmt = null;	//�����ͺ��̽� ����� �����ϱ� ���� ����
		ResultSet rs = null;	//�о�� �ڷḦ ������� ����
		
		try {
			//1.jdbc����̹��� �޸𸮷� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.db������ �����Ѵ�
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");

			
			//3.statement ��ü�� �����Ѵ�.
			pstmt = conn.prepareStatement(sql);
			
			//4.�����ͺ��̽� ����� ����Ѵ�
			rs = pstmt.executeQuery();
			
			//5.��ó���� �Ѵ�.
			//�˻��� �ڷᰡ �ִ� ��ŭ �ݺ� �����Ͽ� �� �྿ �о�ͼ�
			//�װ��� ���ͷ� ����� �� ���͸� ���̺� �ڷḦ ���� rowData�� ��´�.
			
			//ResultSet�� ������� Ư�� ���ڵ带 ����Ű�� Ŀ���� �ִ�.
			//�� Ŀ���� �� �྿ �Ű��ִ� �޼ҵ�� next()�̴�.
			//�� �޼ҵ�� ������ ���ڵ尡 �־ ���������� �Ű����� true�� ��ȯ�ϰ�
			//������ ���ڵ尡 ���̻� ���� �Ű����� ������ false�� ��ȯ�Ѵ�.
			//�׷��� ���� ResultSet�� ����� ��� �о ó���Ϸ���  
			//������ ���� while���� �̿��Ѵ�.
			while(rs.next()){
				//���� Ŀ���� �ٶ󺸰��ִ� ���ڵ��� �Ӽ����� �о���� ���ؼ���
				//�ڷ����� ���� get~()�޼ҵ带 ����Ѵ�.
				//�÷��� ���� ���ڿ��̶�� getString()�� �̿��ϰ�
				//�÷��� ���� �������̶�� getInt()�� ����Ѵ�.
				//�̶� �Ű������� �÷��� �ε����� �÷��̸��� ���ڿ��� �����Ѵ�.
				//���⼭ �ε����� 1���� �����Ѵ�.

				int no = rs.getInt(1);
				String item = rs.getString(2);
				int qty = rs.getInt(3);
				int price = rs.getInt(4);
				
				//���͸� �ϳ� ����� �� ���Ϳ� no,item,qty,price�� ��´�.
				Vector v = new Vector();
				v.add(no);
				v.add(item);
				v.add(qty);
				v.add(price);
				
				rowData.add(v);

				//�ٲ� �������� ���̺��� �ٽ� �׸���.
				//ó�� ���̺��� ���鶧���� rowData���Ͱ� ����ִ� �����̴�.
				//���߿� ���̺��� �������� rowData�� �ٲ��ڷḦ �����ϱ� ���ؼ���
				//updataUI()�� ȣ���ؾ� �Ѵ�.
				table.updateUI();
			
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
	
	}
	

	public GoodsTestDBMethodUpdateDelete() {
		
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
				
				updateGoods(no,item,qty,price);	
			}	
		});
	
		
		//'����'��ư�� ������ �ؽ�Ʈ�ʵ��� ������ ��ǰ��ȣ�� ����� ������ �ϴ� �޼ҵ� ȣ��
		btn_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int no = Integer.parseInt(jtf_no.getText());
				deleteGoods(no);

				}	
		});


		
		//'���'��ư�� ������ �����ͺ��̽��� �����Ͽ�
		//��� ��ǰ����� �о�ͼ� ���̺� ����ϴ� �޼ҵ� ȣ��
		btn_list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				printGoods();
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
				
				insertGoods(no,item,qty,price);

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
		new GoodsTestDBMethodUpdateDelete();
	}

}
