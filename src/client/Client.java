package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static final int MESSAGE_SIZE=100;
	
	private String name;
	private Socket s;
	private InputStream is;
	private OutputStream os;
	
	public Client(String name){
		setName(name);
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void connectServer(String address,int port) throws UnknownHostException, IOException{
		s = new Socket(address,port);
		is = s.getInputStream();
		os = s.getOutputStream();
	}
	
	public String getMessage() throws IOException{
		byte[] buffer = new byte[MESSAGE_SIZE];
		is.read(buffer);
		
		return new String(buffer,"UTF-8");
	}
	
	public void sendMessage(String message) throws IOException{
		os.write(message.getBytes());
		
	}
	
}
