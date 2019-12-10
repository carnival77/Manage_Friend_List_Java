package Report6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FriendListFile {
	// ���Ͽ��� ���� �о����.
	String name;
	String group;
	String phone_number;
	String e_mail;
	String fileName;
	Friend[] f = new Friend[100]; 
	int n=0;
	int friend_count=0; // �Էµ� friend �� ���� ��.
	ArrayList<Friend> input_FriendList = new ArrayList<Friend>();
	ArrayList<Friend> flist_to_file = new ArrayList<Friend>();
	
	public String Make_File(ArrayList<Friend> flist) { // ArrayList FriendList �� �޾Ƽ�, 
		Scanner writefile=null;
		PrintWriter pw = null;
		String fileName="FriendList_saved.txt";
		// "FriendList_saved.txt" ��� ���Ϸ� ����.
		try {
			pw = new PrintWriter(fileName);
			writefile = new Scanner(System.in); // system.in�� �ֿܼ��� ���Ϸ� �Է�
			for(int n=0;n<150;n++) {
				String str=writefile.nextLine().trim();
				if(str.length()!=0) {
					pw.println(str);
					if(str.toUpperCase().equals("QUIT")) {
					break;
					}
				}
			}
			System.out.println("����!");
		} catch (Exception e) {
			System.out.println("���� ����");
			// TODO: handle exception
		}finally {
			if(pw!=null) {
				pw.close();
			}
		}
		FriendListFile flf=new FriendListFile();
		flf.readFileToList(fileName);
		// readFileToList �޼ҵ�� ����, "FriendList_saved.txt" �� �о�, ����Ʈ�� ����� ��.
		this.fileName=fileName;
		return fileName;
	}
	
	public FriendList readFileToList(String fileName) {
		//���޹��� ������ �о� ģ�� ����Ʈ�� �����.

		String file_line; // string ���Ͽ��� ���� �� �پ� �ڸ� ��
		// ģ���� ���� ���� string �ٵ��� �� �پ� friend �� ��ȯ�Ͽ� FriendList �� ����.
		String[] trim_info = new String[5];
		// �ش� ģ���� 5���� ��� ����. �̸�,�׷�,����,email,����
		Scanner readfile1 = null; // ��ĳ�ʷ� �о�� �� �����̸� �̸� null�� ����.
		// String �̸� ���� �� ��!
		try {
			readfile1 = new Scanner(new File(fileName));
			// string ���� Ŭ������ ��ü�� ��ĳ�� �������� �μ��� �����Ѵ�.
			while(readfile1.hasNextLine()) {
				// hasNextLine = ���Ͽ� ���� ���� ������ �ݺ�.
				file_line = readfile1.nextLine().trim();
				// String line �� ���� ���� ����.
				if(file_line.length()!=0) {
					// ���� ������ �ִٸ�, 
					Scanner readline=new Scanner(file_line);
					// �� ���� ������ �д´�.
					String one_line=""; // �� �پ�.
					while(readline.hasNext()) {
						// hasNext = �� �ٿ� ���� ������, �ݺ�.
						one_line+=readline.next();
					}
					while(friend_count<100) {
						// one_line = �� ���� ���� �����.
						// �Ľ��� �ѵ�, �� �׸� ������ trim�ϰ�,
						//Friend Ŭ���� �����Ͽ� Friend �ν��Ͻ��� �����
						// ��, "//"�� �����ϸ� �ڸ�Ʈ ó��
						if(one_line.startsWith("//")){
							break;
						}
						else {
							// input_FriendList[friend_count]=new Friend();
							f[friend_count]=new Friend();
							/* ArrayList�� �� Friend[100]�� ���� ����,
							 *  ģ�� �� ��� Friend �ν��Ͻ� 1���� ������� �� �� �ְ� �Ѵ�.
							 */
							
							String[] info = one_line.split(":",0);
							
							/* info[0] = name;
							 * info[1] = group;...
							 */
							
							for(int n=0;n<info.length;n++) {
								trim_info[n]=info[n].trim();
							}
							// Friend �迭 f �� ģ�� �� ���� ������ 5���� �ִ´�.
							
							f[friend_count].name=trim_info[0];
							f[friend_count].group=trim_info[1];
							f[friend_count].phone_number=trim_info[2];
							f[friend_count].e_mail=trim_info[3];
							f[friend_count].profile=trim_info[4];
							
							// �� ���� ģ���� �����͸� ��� ���� �ϳ��� Friend �ν��Ͻ� �ϼ�.
							
							input_FriendList.add(f[friend_count]);
							friend_count++; //ģ�� ���� �ϳ� �÷��ְ�,������ ���� ģ�� ĭ.
							break;
						}
					}
				}
			}
		} catch (Exception e) { 
			System.out.println("���� ����");
			e.printStackTrace();
		}
		
		FriendList friend_list = new FriendList(input_FriendList);
		/* FriendList Ŭ������ friend_list �� ��üȭ�� ��, �ű� �ִ� ArrayList<Friend> �� 
		 * ArrayList<Friend> input_FriendList �� �ִ´�.
		 */
		
		return friend_list; // friend_list �� 100���� ������ �ν��Ͻ��� ����Ǿ� ����.
	}
}
	

