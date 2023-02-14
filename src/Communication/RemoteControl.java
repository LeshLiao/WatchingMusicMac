package Communication;


import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;


public class RemoteControl {
	
	String user;
	String password;
	String host;
	Session session;
	Channel channel;
	
	public RemoteControl(String _user,String _password,String _host) throws JSchException
	{
		user = _user;
		password = _password;
		host = _host;
		int port=22;

		JSch jsch = new JSch();
		java.util.Properties config = new java.util.Properties(); 
		config.put("StrictHostKeyChecking", "no");

		session = jsch.getSession(user, host);
		session.setPassword(password);
		session.setConfig(config); 
		session.connect();
		
	}
	public boolean sendCommend(String _command) throws JSchException
	{
		channel = session.openChannel("exec");
		((ChannelExec)channel).setCommand(_command);
		channel.setInputStream(null);
		((ChannelExec)channel).setErrStream(System.err);
		channel.connect();
		System.out.println("Channel Connected to machine " + host + " server with command: " + _command ); 
		 
//		InputStream in;
//		try {
//			in = channel.getInputStream();
//
//			byte[] tmp = new byte[1024];
//	        while (true) 
//	        {
//	            while (in.available() > 0) {
//	                int i = in.read(tmp, 0, 1024);
//	                if (i < 0) break;
//	                System.out.print(new String(tmp, 0, i));
//	            }
//	            if (channel.isClosed()) {
//	                System.out.println("Exit status: " + channel.getExitStatus());
//	                break;
//	            }
//	        }
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return true;
	}

	public Boolean Close()
	{
		channel.disconnect();
		session.disconnect();
		return true;
	}
	

}
