package Report6;

import java.util.ArrayList;

public class FriendList {
	ArrayList<Friend> FriendList = new ArrayList<Friend>();
	int friend_count=FriendList.size();
	
	public FriendList(ArrayList<Friend> input_FriendList){
		FriendList = input_FriendList;
	}
	public ArrayList<Friend> getback_flist() {
		return FriendList;
	}
	public FriendList(){
	}
	
	public int numFriends() {
		int numFriends=friend_count;
		return numFriends;
	}
	
	public Friend getFriend(int i) {
		return FriendList.get(i);
	}
	
	public void show_friendlist() {
		for(int i=0;i<FriendList.size();i++) {
			System.out.println(i+":"+FriendList.get(i).name);
		}
	}
}