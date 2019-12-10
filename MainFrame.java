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


// MainFrame : pn_list(친구 목록), pn_click(클릭 버튼)
public class MainFrame extends JFrame {
	
	private static final Object[] JCheckBox = null;
	static FriendList flist = new FriendList();
	int friend_num = flist.FriendList.size();
	static FriendListFile flf = new FriendListFile();
	public static int choice_number;
	
	public MainFrame(FriendList get_flist,int get_choice) {
		// MainFrame 정보 설정
		System.out.println(friend_num);
		setTitle("친구목록");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,400);
		setLocation(1400,200);
		setLocationRelativeTo(null);
		
		// 패널 추가 : pn_list(친구 목록), pn_click(클릭 버튼) 

		pn_list pn_lt = new pn_list(get_flist);
		add(pn_lt,BorderLayout.CENTER);
		pn_click pn_ck = new pn_click(get_choice);
		add(pn_ck,BorderLayout.EAST);
		setVisible(true);
	}
	
	// MainFrame : pn_list(친구 목록)
	
	// 여기서부터, 
	// 초기 3개는 전의 과제 friendlist에서, 데이터 한줄씩 읽어와서 표시하는 거
	// 그 이후 데이터는 입력된 친구 데이터를 border로 표시.
	// 그 모든 친구들 데이터를 grid 로 순차 표시.
	
	/* FriendList의 모든 친구를 표시한다.
	 * 처음에는 기존 3명의 데이터를 "friendlist-norm.txt"로부터 가져와,
	 * FriendList에 저장하고,
	 * 이후부터 원래 임무를 수행한다.
	 */
	
	class pn_list extends JPanel{
		
		JCheckBox[] jc = new JCheckBox[10];
		JLabel[] friend_name = new JLabel[10];
		JTextField[] friend_group = new JTextField[10];
		JTextField[] friend_phone = new JTextField[10];
		JTextField[] friend_email = new JTextField[10];
		
		public pn_list(FriendList get_flist) {
			setLayout(new GridLayout(10,6));
			// 친구 목록을 보여주는데, 첫 줄은 레이블로 항목 이름,
			// 두번째 줄부터 초기 3명 + a (이후 추가될 인원) 단, 10명까지만
			
			// 첫 줄에 항목 이름들을 레이블로 표시
			JLabel blank1 = new JLabel();
			JLabel name = new JLabel("이름");
			JLabel group = new JLabel("그룹");
			JLabel phone = new JLabel("전화번호");
			JLabel email = new JLabel("Email");
			JLabel photo = new JLabel("사진");
			
			add(blank1);
			add(name);
			add(group);
			add(phone);
			add(email);
			add(photo);
			
			friend_num = get_flist.FriendList.size();
			
			// FriendListFile 의 readFileToList 에 기존 3명의 친구 데이터가 들어있는
			// "friendlist-norm.txt" 를 보내 읽도록  한다.
			// 그 후 FriendList에 저장.
			
			// FriendList flist 에서 ArrayList<Friend> 를 불러 와,
			// 친구 한 명씩의 데이터를 pn_list 에 add한다.
			
			/* 체크박스, 텍스트필드 배열을 만들어,
			 * 각 친구 데이터를 순차 삽입.
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
			
			// 체크박스 체크했는 지를 체크. 
			// 일단 delete 와 modify 둘 모두에게 보냄.
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
	
	// Sub_Frame : "추가될 친구 정보". pn_addf(친구 추가)	
	class Sub_Frame extends JFrame{
		public Sub_Frame() {
			setLayout(new BorderLayout());
			setTitle("추가될 친구 정보");
			setSize(500,150);
			setLocation(1400,200);
			setLocationRelativeTo(null);
			
			pn_addf pn_af = new pn_addf();
			add(pn_af,BorderLayout.CENTER);
			
		}
	}
			
	// MainFrame : pn_click(클릭 버튼)
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
			
			// Add 클릭 시 Sub_Frame 표시
			Sub_Frame sbf = new Sub_Frame();
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sbf.setVisible(true);
					}
			});
			setVisible(true);
			
			/* Delete 클릭 시, flist 에서 친구 정보 제거
			 * 그리고 그 flist 를 MainFrame 에게
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
	
	// pn_addf(친구 추가)
	class pn_addf extends JPanel{
		public pn_addf() {
			setLayout(new GridLayout(2,6));
			// 기본 5가지 항목 레이블과 1개 빈칸
			JLabel name2 = new JLabel("이름");
			JLabel group2 = new JLabel("그룹");
			JLabel phone2 = new JLabel("전화");
			JLabel email2 = new JLabel("Email");
			JLabel photo2 = new JLabel("사진");
			JLabel blank1 = new JLabel();
			JLabel blank2 = new JLabel();
			
			add(name2);
			add(group2);
			add(phone2);
			add(email2);
			add(photo2);
			add(blank1);
			// 친구 데이터를 각각 입력할 5개의 textfield
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

			
			/* jtextfield 의 settext 로 텍스트필드에 입력된 각 내용을
			 * 받아서, 한 명씩 Friend 인스턴스를 만들어서,
			 * (Friend 객체화 잊지 않기)
			 * ArrayList 인 FriendList flist 에 넣기.
			 * 그리고 그것이 위의 pn_list 에서 잘 표시되는 지 확인.
			 */
			
			// Done 클릭 시 Sub_Frame 의 pn_addf에 입력된 친구 정보가 
			// Main_Frame 의 pn_list 에 추가.
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