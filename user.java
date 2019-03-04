import java.util.*;

public class user{
	private int userId;
	private ArrayList<user> friends;
	//a arrayList of the friendlist of the user
	private HashMap<Integer,String> publishing;
	// publishing is a hashmap with time as a key and value as text
	//private HashMap<user,Integer> timeStamp;
	//time stamp a hashmap with keys as userId and values as the time when they got subscribed
	private HashMap<Integer,String> replies;
	private int time;

	public user(int userId)
	{
		this.userId = userId;
		this.friends = new ArrayList<user>();
		this.publishing = new HashMap<Integer,String>();
		this.replies = new HashMap<Integer,String>();
	}

	public int getId()
	{
		return this.userId;
	}

	public void addFriend(user uId,int time)
	{	
		//User id now subscribed to object of user
		//timeStamp.put(uId,time);
		this.friends.add(uId);
	}

	public void rmvFriend(user uId)
	{
		//User id is now removed from friend list of object of user
		//timeStamp.remove(uId);
		this.friends.remove(uId);

	}

	public ArrayList<user> getFriends()
	{
		return this.friends;
	}

	public Boolean status(int uId)
	{
		if(this.friends.contains(uId))
			return true;

		return false;
	}

	public void subsTime(int time)
	{
		this.time = time;
	}

	public int getTime()
	{
		return this.time;
	}

	public void addText(int time, String str)
	{
		publishing.put(time,str);
	}

	public void addText(int tId, int time, String str)
	{
		FaceMash.textArr[tId] = str;
	}

	public String getText(int time)
	{
		return publishing.get(time);
	}

	public void addReply(int time,String str)
	{
    	replies.put(time,str);
	}

	public HashMap<Integer,String> getReplies()
	{
		return replies;
	}
	
	// add function that includes a list of users which reply to r or 

}