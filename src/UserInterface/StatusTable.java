package UserInterface;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import netP5.NetAddress;
import netP5.NetAddressList;

public class StatusTable extends JTable{
	private String [] BookField;
	private String [][] tmpTableData;
	private DefaultTableModel tmodel;
	NetAddressList reachableAddressList = new NetAddressList();
	NetAddressList availableAddressList = new NetAddressList();
	List<List<String>> listOfLists;
	
	public StatusTable() {
		String [][] td = {{"<No Device>","","","",""}};
		//String [][] td = {{"192.168.0.11","","","",""},{"192.168.0.22","","","",""},{"192.168.0.33","","","",""}};
		tmpTableData = td;
        BookField = new String[]{"IP","Port","Git SHA","Json Timestamp","OSC server","Info"};
        tmodel = new DefaultTableModel(tmpTableData,BookField); //建立表格
        this.setModel(tmodel);
        
        this.getColumnModel().getColumn(0).setPreferredWidth(70);
        this.getColumnModel().getColumn(1).setPreferredWidth(30);	
        this.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.getColumnModel().getColumn(4).setPreferredWidth(60);
        this.getColumnModel().getColumn(5).setPreferredWidth(300);
        
        
        listOfLists = new ArrayList<List<String>>();
	}
	public boolean ClearTable()
	{
		reachableAddressList.list().clear();
		availableAddressList.list().clear();
		
		int rowCount = tmodel.getRowCount();
		//Remove rows one by one from the end of the table  
		for (int i = rowCount - 1; i >= 0; i--) {
			tmodel.removeRow(i);
		}
		return true;
	}
	
	public boolean ClearTableStatus()
	{
		availableAddressList.list().clear();
		
		int rowCount = tmodel.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			tmodel.setValueAt("", i, 2);
			tmodel.setValueAt("", i, 3);
			tmodel.setValueAt("", i, 4);
			tmodel.setValueAt("", i, 5);
		}
		return true;
	}
	
	public boolean AddDevice(String _IPAddress,String _Port)
	{
		tmodel.addRow(new Object[]{_IPAddress,_Port,"","",""});
		return true;
	}
	
	public void AddToReachableAddressList(String theIPaddress,int port) 
	{
		if (!reachableAddressList.contains(theIPaddress, port)) 
		{
			reachableAddressList.add(new NetAddress(theIPaddress, port));
			tmodel.addRow(new Object[]{theIPaddress,Integer.toString(port),"","",""});
			System.out.println("### adding "+theIPaddress+" to the reachable AddressList.");
		} 
		else 
		{
			System.out.println("### "+theIPaddress+" has already existed.");
		}
	}
	
	public void AddToAvailableAddressList(String theIPaddress,int port) 
	{
		if (!availableAddressList.contains(theIPaddress, port)) 
		{
			int index = getIndexByAddress(theIPaddress);
			availableAddressList.add(new NetAddress(theIPaddress, port));
			if(index >= 0)	tmodel.setValueAt("Serving", index, 4);
			System.out.println("### adding "+theIPaddress+" to the available AddressList.");
		} 
		else 
		{
			System.out.println("### "+theIPaddress+" has already existed.");
		}
	}
	
	public void UpdateGitHash(String theIPaddress,String GitSha) 
	{
		
		int index = getIndexByAddress(theIPaddress);
		//System.out.println("UpdateGitHash:"+theIPaddress+",GitSha:"+GitSha+",index="+Integer.toString(index));
		if(index >= 0)	tmodel.setValueAt(GitSha,index , 2);
	}
	
	public void UpdateJsonTimestamp(String theIPaddress,String JsonTimestamp) 
	{
		
		int index = getIndexByAddress(theIPaddress);
		//System.out.println("UpdateJsonTimestamp:"+theIPaddress+",GitSha:"+JsonTimestamp+",index="+Integer.toString(index));
		if(index >= 0)	tmodel.setValueAt(JsonTimestamp,index , 3);
	}
	
	public void UpdateInfo(String theIPaddress,String _info) 
	{
		int index = getIndexByAddress(theIPaddress);
		if(index >= 0)	tmodel.setValueAt(_info,index , 5);
	}
	
	private int getIndexByAddress(String theIPaddress) 
	{
		int IpIndex = -1;
		for(int i = 0;i < reachableAddressList.size() ;i++)
		{
			if(theIPaddress.equals(reachableAddressList.get(i).address()))
			{
				IpIndex = i;
				break;
			}
		}
		return IpIndex;
	}
	
}
