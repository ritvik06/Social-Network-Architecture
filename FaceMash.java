import java.util.*;

public class FaceMash{		
	public static String[] textArr;
	private user[] users;
	HashMap<Integer,user> tuMap;
	// A hashmap of text id with their corresponding user id's
	HashMap<user,Integer> lastRead;
	static int i = 0;
	static int count = 0;
	//keeps count of no of text strings added

	public FaceMash(){
		textArr = new String[100];
		textArr[0] = "@"; 
		users = new user[100];
		tuMap = new HashMap<Integer,user>();
		lastRead = new HashMap<user,Integer>();

		for(int i=0; i<100; i++){
			textArr[i] = "";
			users[i] = new user(i);
		}
	}

	public void performAction(String actionMessage)
	{
		// for(i=0;i<100;i++){
			// System.out.print(users[i].getId() + " ");
		// }

		Scanner input = new Scanner(actionMessage);
		String action = input.next();
		
		if(action.equals("SUBSCRIBE")){
			//System.out.println("SUBSCRIBE");

			int time = input.nextInt(); 
			int id1 = input.nextInt();
			int id2 = input.nextInt();

			user uid = users[id1];
			// System.out.println(uid.getId()); 
			user pid = users[id2];

			uid.addFriend(pid,time);
			//pid.addFriend(uid);

			System.out.println("SUBSCRIBE " + time + " " + id1 + " " + id2);
		}

		else if(action.equals("UNSUBSCRIBE")){
			int time = input.nextInt(); 
			int id1 = input.nextInt();
			int id2 = input.nextInt();

			System.out.print("UNSUBSCRIBE " + time + " " + id1 + " " + id2);
 
			user uid = users[id1]; 
			user pid = users[id2];

			if(uid.getFriends().contains(pid)){
				uid.rmvFriend(pid);
				System.out.println();
			}
			//pid.rmvFriend(uid);
			else
				System.out.println("---" + id1 + " has not subscribed to " + id2);
		}

		else if(action.equals("PUBLISH")){

			int time = input.nextInt();
			int id1 = input.nextInt();

			String nextStr = input.next();

			if(nextStr.equals("NEW")){
				user uid = users[id1];

				String str = input.next();
				int tid = input.nextInt();

				textArr[tid] = str;
				uid.addText(time,str);
				tuMap.put(tid,uid);
				count++;
			}

	        else{
	            if(nextStr.equals("REPOST")){
	            	int ptid = input.nextInt();
	                int tid = input.nextInt();

	                // System.out.println(count);
	                // System.out.println("PTID-" + ptid + " TID-" + tid);

	                if(ptid>textArr.length || tid<=textArr.length){
	                	System.out.print("PUBLISH :");
	                	System.out.println("Input of text id's not correct");
	                }
	                else{
	                	System.out.println("PUBLISH ");
		                textArr[ptid] = textArr[tid];
		                
		                user uid = users[id1]; 
		                
		                uid.addText(time,textArr[tid]); 
		                tuMap.put(tid,uid);
		                count++;
		            }
	            }
	  	          
	            else if(nextStr.equals("REPLY")){ 
	            	System.out.println("PUBLISH ");
	            	// System.out.println("HELLO");
	            	int ptid = input.nextInt();
	                String text = input.next();
	                int tid = input.nextInt();
	                textArr[tid] = text;
	                
	                user pid = tuMap.get(ptid);

	                //System.out.println(pid.getId());
	                // System.out.println("Time is " + time);
	                // System.out.println("Text is " + text);

	                if(pid!=null)
	                	pid.addReply(time,text);
	                // else{
	                	// System.out.print(" Message not found");
	                // }
	                
	               // System.out.print("Replies to " + pid.getId() + "---");
	                // HashMap<Integer,String> map = pid.getReplies();

	                // for(int i=0; i<=time; i++){
	                	// if(map.get(i)!=null){
	                	//	System.out.println(" " + i);
	                		// System.out.print(map.get(i) + " ");
	                	// }
	                // }
	                // System.out.println();
	                // count++;
	           } 
	            
	            
	            
	            
	        }

		}

		else if(action.equals("READ")){
			System.out.println("PUBLISH ");
			ArrayList<String> readArr = new ArrayList<String>();

			int time = input.nextInt();

			int id = input.nextInt();

			user uid = users[id];

			System.out.print("READ " + time + " " + id + ": ");

			ArrayList<user> friends = uid.getFriends();

			// System.out.print("Friends of " + id + "----");

			// for(int a=0; a<friends.size(); a++){
				// System.out.print(friends.get(a).getId() + " ");
			// }

			// System.out.print("------Text-----");


			//if(friends==null){
			//	System.out.println("EMPTY");
			//}

			HashMap<String,Integer> publishing;

			int k;

			if(i==0){
				// System.out.println("*");
				k=0;
			}
			else{
				// System.out.println("**");
				if(lastRead.get(uid)==null){
					k=0;
				 }
				 else{
					 k=lastRead.get(uid);
				 }
			}

			user friend=null;

			while(k<=time){
				// System.out.println("K is " + k);
				for(int j=0; j<friends.size(); j++){
					friend = friends.get(j);
					// System.out.println("At friend " + friend.getId());
					if(friend.getText(k)!=null)
						readArr.add(friend.getText(k));
				}

				 if(uid.getReplies()!=null)
					 if(uid.getReplies().get(k)!=null)
						 readArr.add(uid.getReplies().get(k));
				k++;
			}
			

			if(readArr.size()==0){
				System.out.print(id + " is empty right now");
			}
			else{
				
				Object[] objects = readArr.toArray();

				for (Object obj : objects) 
            		System.out.print(obj + " ");
			}
			System.out.println();
			lastRead.put(uid,time);

			i++;
		}

	}
}
