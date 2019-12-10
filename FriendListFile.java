package Report6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FriendListFile {
	// 파일에서 내용 읽어오기.
	String name;
	String group;
	String phone_number;
	String e_mail;
	String fileName;
	Friend[] f = new Friend[100]; 
	int n=0;
	int friend_count=0; // 입력된 friend 의 숫자 셈.
	ArrayList<Friend> input_FriendList = new ArrayList<Friend>();
	ArrayList<Friend> flist_to_file = new ArrayList<Friend>();
	
	public String Make_File(ArrayList<Friend> flist) { // ArrayList FriendList 를 받아서, 
		Scanner writefile=null;
		PrintWriter pw = null;
		String fileName="FriendList_saved.txt";
		// "FriendList_saved.txt" 라는 파일로 저장.
		try {
			pw = new PrintWriter(fileName);
			writefile = new Scanner(System.in); // system.in은 콘솔에서 파일로 입력
			for(int n=0;n<150;n++) {
				String str=writefile.nextLine().trim();
				if(str.length()!=0) {
					pw.println(str);
					if(str.toUpperCase().equals("QUIT")) {
					break;
					}
				}
			}
			System.out.println("저장!");
		} catch (Exception e) {
			System.out.println("파일 없음");
			// TODO: handle exception
		}finally {
			if(pw!=null) {
				pw.close();
			}
		}
		FriendListFile flf=new FriendListFile();
		flf.readFileToList(fileName);
		// readFileToList 메소드로 보내, "FriendList_saved.txt" 를 읽어, 리스트로 만들게 함.
		this.fileName=fileName;
		return fileName;
	}
	
	public FriendList readFileToList(String fileName) {
		//전달받은 파일을 읽어 친구 리스트를 만든다.

		String file_line; // string 파일에서 내용 한 줄씩 자를 것
		// 친구들 내용 담은 string 줄들을 한 줄씩 friend 로 변환하여 FriendList 에 저장.
		String[] trim_info = new String[5];
		// 해당 친구의 5가지 모든 정보. 이름,그룹,전번,email,사진
		Scanner readfile1 = null; // 스캐너로 읽어올 게 파일이면 미리 null로 선언.
		// String 이면 하지 말 것!
		try {
			readfile1 = new Scanner(new File(fileName));
			// string 파일 클래스의 객체를 스캐너 생성자의 인수로 전달한다.
			while(readfile1.hasNextLine()) {
				// hasNextLine = 파일에 다음 줄이 있으면 반복.
				file_line = readfile1.nextLine().trim();
				// String line 에 다음 줄을 저장.
				if(file_line.length()!=0) {
					// 줄의 내용이 있다면, 
					Scanner readline=new Scanner(file_line);
					// 그 줄의 내용을 읽는다.
					String one_line=""; // 한 줄씩.
					while(readline.hasNext()) {
						// hasNext = 각 줄에 내용 있으면, 반복.
						one_line+=readline.next();
					}
					while(friend_count<100) {
						// one_line = 각 줄의 내용 담겨짐.
						// 파싱을 한뒤, 각 항목 내용을 trim하고,
						//Friend 클래스 참조하여 Friend 인스턴스로 만든다
						// 단, "//"로 시작하면 코멘트 처리
						if(one_line.startsWith("//")){
							break;
						}
						else {
							// input_FriendList[friend_count]=new Friend();
							f[friend_count]=new Friend();
							/* ArrayList에 들어갈 Friend[100]를 만든 다음,
							 *  친구 한 명당 Friend 인스턴스 1개씩 순서대로 들어갈 수 있게 한다.
							 */
							
							String[] info = one_line.split(":",0);
							
							/* info[0] = name;
							 * info[1] = group;...
							 */
							
							for(int n=0;n<info.length;n++) {
								trim_info[n]=info[n].trim();
							}
							// Friend 배열 f 에 친구 한 명씩의 데이터 5개를 넣는다.
							
							f[friend_count].name=trim_info[0];
							f[friend_count].group=trim_info[1];
							f[friend_count].phone_number=trim_info[2];
							f[friend_count].e_mail=trim_info[3];
							f[friend_count].profile=trim_info[4];
							
							// 한 명의 친구의 데이터를 모두 담은 하나의 Friend 인스턴스 완성.
							
							input_FriendList.add(f[friend_count]);
							friend_count++; //친구 숫자 하나 늘려주고,다음에 들어올 친구 칸.
							break;
						}
					}
				}
			}
		} catch (Exception e) { 
			System.out.println("파일 없음");
			e.printStackTrace();
		}
		
		FriendList friend_list = new FriendList(input_FriendList);
		/* FriendList 클래스를 friend_list 로 객체화한 뒤, 거기 있는 ArrayList<Friend> 에 
		 * ArrayList<Friend> input_FriendList 를 넣는다.
		 */
		
		return friend_list; // friend_list 에 100개의 프렌드 인스턴스가 저장되어 있음.
	}
}
	

