package Communication;

import com.jcraft.jsch.*;


public class SyncFile {
	
	String user;
	String password;
	String host;
	ChannelSftp sftpChannel;
	Session session;
	
	public SyncFile(String _user,String _password,String _host) throws JSchException, SftpException
	{
		user = _user;
		password = _password;
		host = _host;
		int port=22;
		
		//String remoteFile="sample.txt";
		    
		JSch jsch = new JSch();
		
		java.util.Properties config = new java.util.Properties(); //
		config.put("StrictHostKeyChecking", "no");//
		
		
		session = jsch.getSession(user, host);
		session.setPassword(password);
		session.setConfig(config); //
		session.connect();
		
		sftpChannel = (ChannelSftp) session.openChannel("sftp");
		sftpChannel.connect();

		
	}
	
	// putFile("C:/goal.txt", "/rpi-ws281x-python-and-osc/examples/config/goal.txt");
	public Boolean putFile(String _src,String _dst)
	{
		try 
		{
			sftpChannel.put(_src,_dst);
		} 
		catch (SftpException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public Boolean Close()
	{
		sftpChannel.disconnect();
		sftpChannel.exit();
		session.disconnect();
		return true;
	}
	

}
