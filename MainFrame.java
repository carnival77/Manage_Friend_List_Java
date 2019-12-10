package Report6;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.ietf.jgss.MessageProp;


// MainFrame : pn_list(ģ�� ���), pn_click(Ŭ�� ��ư)
public class MainFrame extends JFrame {
	
	private static final Object[] JCheckBox = null;
	static FriendList flist = new FriendList();
	int friend_num = flist.FriendList.size();
	static FriendListFile flf = new FriendListFile();
	public static int choice_number;
	
	public MainFrame(FriendList get_flist,int get_choice) {
		// MainFrame ���� ����
		System.out.println(friend_num);
		setTitle("ģ�����");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,400);
		setLocation(1400,200);
		setLocationRelativeTo(null);
		
		// �г� �߰� : pn_list(ģ�� ���), pn_click(Ŭ�� ��ư) 

		pn_list pn_lt = new pn_list(get_flist);
		add(pn_lt,BorderLayout.CENTER);
		pn_click pn_ck = new pn_click(get_choice);
		add(pn_ck,BorderLayout.EAST);
		setVisible(true);
	}
	
	// MainFrame : pn_list(ģ�� ���)
	
	// ���⼭����, 
	// �ʱ� 3���� ���� ���� friendlist����, ������ ���پ� �о�ͼ� ǥ���ϴ� ��
	// �� ���� �����ʹ� �Էµ� ģ�� �����͸� border�� ǥ��.
	// �� ��� ģ���� �����͸� grid �� ���� ǥ��.
	
	/* FriendList�� ��� ģ���� ǥ���Ѵ�.
	 * ó������ ���� 3���� �����͸� "friendlist-norm.txt"�κ��� ������,
	 * FriendList�� �����ϰ�,
	 * ���ĺ��� ���� �ӹ��� �����Ѵ�.
	 */
	
	class pn_list extends JPanel{
		
		JCheckBox[] jc = new JCheckBox[10];
		JLabel[] friend_name = new JLabel[10];
		JTextField[] friend_group = new JTextField[10];
		JTextField[] friend_phone = new JTextField[10];
		JTextField[] friend_email = new JTextField[10];
		
		public pn_list(FriendList get_flist) {
			setLayout(new GridLayout(10,6));
			// ģ�� ����� �����ִµ�, ù ���� ���̺�� �׸� �̸�,
			// �ι�° �ٺ��� �ʱ� 3�� + a (���� �߰��� �ο�) ��, 10�������
			
			// ù �ٿ� �׸� �̸����� ���̺�� ǥ��
			JLabel blank1 = new JLabel();
			JLabel name = new JLabel("�̸�");
			JLabel group = new JLabel("�׷�");
			JLabel phone = new JLabel("��ȭ��ȣ");
			JLabel email = new JLabel("Email");
			JLabel photo = new JLabel("����");
			
			add(blank1);
			add(name);
			add(group);
			add(phone);
			add(email);
			add(photo);
			
			friend_num = get_flist.FriendList.size();
			
			// FriendListFile �� readFileToList �� ���� 3���� ģ�� �����Ͱ� ����ִ�
			// "friendlist-norm.txt" �� ���� �е���  �Ѵ�.
			// �� �� FriendList�� ����.
			
			// FriendList flist ���� ArrayList<Friend> �� �ҷ� ��,
			// ģ�� �� ���� �����͸� pn_list �� add�Ѵ�.
			
			/* üũ�ڽ�, �ؽ�Ʈ�ʵ� �迭�� �����,
			 * �� ģ�� �����͸� ���� ����.
			 */
			
			for(int n=0;n<friend_num;n++) {
				this.jc[n] = new JCheckBox();
				this.friend_name[n] = new JLabel(get_flist.FriendList.get(n).name);
				this.friend_group[n] = new JTextField(get_flist.FriendList.get(n).group);
				this.friend_phone[n] = new JTextField(get_flist.FriendList.get(n).phone_number);
				this.friend_email[n] = new JTextField(get_flist.FriendList.get(n).e_mail);
				JLabel blank2 = new JLabel();
				add(jc[n]);
				add(friend_name[n]);
				add(friend_group[n]);
				add(friend_phone[n]);
				add(friend_email[n]);
				add(blank2);
			}
			for(int i=0; i<30-(friend_num-3)*6;i++) {
				JLabel blank3 = new JLabel();
				add(blank3);
			}
			
			// üũ�ڽ� üũ�ߴ� ���� üũ. 
			// �ϴ� delete �� modify �� ��ο��� ����.
			for(int i=0; i<friend_num;i++) {
				jc[i].addItemListener(new DeleteListener(i));
			}
			
			setVisible(true);
		}
	}
	
	static class DeleteListener implements ItemListener{
		int get_num;
		public DeleteListener(int i) {
			this.get_num=i;
			// TODO Auto-generated constructor stub
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.SELECTED) {
				choice_number=get_num;
			}
		}
	}
	
	// Sub_Frame : "�߰��� ģ�� ����". pn_addf(ģ�� �߰�)	
	class Sub_Frame extends JFrame{
		public Sub_Frame() {
			setLayout(new BorderLayout());
			setTitle("�߰��� ģ�� ����");
			setSize(500,150);
			setLocation(1400,200);
			setLocationRelativeTo(null);
			
			pn_addf pn_af = new pn_addf();
			add(pn_af,BorderLayout.CENTER);
			
		}
	}
			
	// MainFrame : pn_click(Ŭ�� ��ư)
	class pn_click extends JPanel{
		
		public pn_click(int choice) {
			setLayout(new GridLayout(4,1));
			
			JButton add = new JButton("Add");
			JButton del = new JButton("Delete");
			JButton mod = new JButton("Modify");
			JButton sv = new JButton("Save file");
			
			add(add);
			add(del);
			add(mod);
			add(sv);
			
			// Add Ŭ�� �� Sub_Frame ǥ��
			Sub_Frame sbf = new Sub_Frame();
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sbf.setVisible(true);
					}
			});
			setVisible(true);
			
			/* Delete Ŭ�� ��, flist ���� ģ�� ���� ����
			 * �׸��� �� flist �� MainFrame ����
			 */
			
			del.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					flist.FriendList.remove(choice_number);
					MainFrame mf = new MainFrame(flist,choice_number);
				}
			});
		}
	}
	
	// pn_addf(ģ�� �߰�)
	class pn_addf extends JPanel{
		public pn_addf() {
			setLayout(new GridLayout(2,6));
			// �⺻ 5���� �׸� ���̺�� 1�� ��ĭ
			JLabel name2 = new JLabel("�̸�");
			JLabel group2 = new JLabel("�׷�");
			JLabel phone2 = new JLabel("��ȭ");
			JLabel email2 = new JLabel("Email");
			JLabel photo2 = new JLabel("����");
			JLabel blank1 = new JLabel();
			JLabel blank2 = new JLabel();
			
			add(name2);
			add(group2);
			add(phone2);
			add(email2);
			add(photo2);
			add(blank1);
			// ģ�� �����͸� ���� �Է��� 5���� textfield
			JTextField tname=new JTextField();
			JTextField tgroup=new JTextField();
			JTextField tphone = new JTextField();
			JTextField temail = new JTextField();
			JTextField tphoto = new JTextField();
			
			add(tname);
			add(tgroup);
			add(tphone);
			add(temail);
			add(tphone);
			add(blank2);

			JButton done = new JButton("Done");

			
			/* jtextfield �� settext �� �ؽ�Ʈ�ʵ忡 �Էµ� �� ������
			 * �޾Ƽ�, �� �� Friend �ν��Ͻ��� ����,
			 * (Friend ��üȭ ���� �ʱ�)
			 * ArrayList �� FriendList flist �� �ֱ�.
			 * �׸��� �װ��� ���� pn_list ���� �� ǥ�õǴ� �� Ȯ��.
			 */
			
			// Done Ŭ�� �� Sub_Frame �� pn_addf�� �Էµ� ģ�� ������ 
			// Main_Frame �� pn_list �� �߰�.
			done.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Friend f =new Friend();
					
					f.name=tname.getText();
					f.group=tgroup.getText();
					f.phone_number=tphone.getText();
					f.e_mail=temail.getText();
					f.profile=tphoto.getText();
					
					flist.FriendList.add(friend_num,f);
					friend_num=flist.FriendList.size();
					
					tname.setText("");
					tgroup.setText("");
					tphone.setText("");
					temail.setText("");
					tphoto.setText("");
					
					MainFrame mf = new MainFrame(flist,choice_number);
					flist.show_friendlist();
				}
			});
			add(done);
			
			setVisible(true);
		}
	}
	public static void main(String[] args) {
		flist=flf.readFileToList("friendlist-norm.txt");
		new MainFrame(flist,choice_number);
	}
}