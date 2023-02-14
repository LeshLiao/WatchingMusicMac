package com.wm.ui;
import processing.core.PApplet;
import oscP5.*;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.ArrayList;

import Config.MyStation;
import Data.ConfigTable;
import UserInterface.*;
import netP5.*;

public class MatrixClassProject extends PApplet{
	OscP5 oscP5;
	NetAddress myRemoteLocation;
	NetAddress AbletonliveLocation;
	NetAddress TestLocation;
	OscMessage myMatrixMessage;
	ConfigTable NewConfig;
	ArrayList<Launchpad> MidiDevice;
	
	int _PadNumber = 0;
	int _Velocity = 0;
	int _Mode = 0;
	int _Note = 0;
	int test_count = 0; //testing
	
	//2 pads        x:45 y:0 z:0 
	//full screen 2 x:51 y:0 z:0 
	int _angleX = 67;  
	int _angleY = 0;
	int _angleZ = 0;
	public static void main(String[] args) {
		PApplet.main("com.wm.ui.MatrixClassProject");
	}

	public void settings() 
	{
		size(1600,800); 		// 2D
		//fullScreen(1);
		//fullScreen(2);
		//size(1500,1000,P3D);	// 3D
	}

	public void setup() 
	{
		surface.setResizable(true);
		noStroke(); 				// no border line
		frameRate(60);				// 30Hz  30fps
		
		oscP5 = new OscP5(this,2346);
		myRemoteLocation = new NetAddress("10.1.1.6",2346);
		AbletonliveLocation = new NetAddress("127.0.0.1",8000);
		TestLocation = new NetAddress("172.20.10.13",2346);
		NewConfig = new ConfigTable();

		try {
			NewConfig.LoadJsonFile("StationSetup.json");
			NewConfig.initNetSettings();
			System.out.println("Json TimeStamp:"+NewConfig.getTimeStamp());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MidiDevice = new ArrayList<Launchpad>(); 
		//MidiDevice.add(new Launchpad(this,"Launchpad Pro",570,500,0,1));//500
		//MidiDevice.add(new Launchpad(this,"Launchpad Mini",570,80,0,1));//80
		
		//full screen 2,only pad2, and rotate pad to left
		MidiDevice.add(new Launchpad(this,"Launchpad Pro",780,1300,00,1));
		MidiDevice.add(new Launchpad(this,"Launchpad Mini",735,512,-293,1));
			
		myMatrixMessage = new OscMessage("/MatrixVelocity");
		
		ControlPanel newPanel2 = new ControlPanel(NewConfig); 
		newPanel2.setVisible(true);
		
		//surface.setVisible(false); //Hide Main frame
		surface.setVisible(true); //Show Main frame
	}

	public void draw()     
	{
		
//		if(test_count == 0)
//		{
//			System.out.println("sta:"+Instant.now());
//		}
//		test_count++;  //testing
		
		background(0);
		
		//text angle info
		fill(200, 200, 200);
		textSize(20);
		String _Text = "_angleX:"+Integer.toString(_angleX)+"_angleY"+Integer.toString(_angleY)+"_angleZ"+Integer.toString(_angleZ);
		text(_Text, 100, 100); 
		_Text = "x:"+Integer.toString(MidiDevice.get(1).padPosition_x)+",y:"+Integer.toString(MidiDevice.get(1).padPosition_y)+",z:"+Integer.toString(MidiDevice.get(1).padPosition_z);
		text(_Text, 100, 150); 
		
		
		rotateX(PApplet.radians(_angleX));
		rotateY(PApplet.radians(_angleY));
		rotateZ(PApplet.radians(_angleZ));

		for (int i = 0; i < MidiDevice.size(); i++) 
		{
			//MidiDevice.get(i).display();	//2D
			MidiDevice.get(i).display3D();  //3D
		}
		

		for (int i = 0; i < NewConfig._myJsonConfig.getMyStations().size(); i++)
		{
			MyStation myStation = NewConfig._myJsonConfig.getMyStations().get(i);
			String tempStr = "";
			for (int j = 0; j < myStation.getRules().size(); j++)
			{
				int Note = myStation.getRules().get(j).getInput();
				int PadNumber = myStation.getRules().get(j).getPadNo();
				Particle P1 = MidiDevice.get(PadNumber).MainMatrix.get(Note);
				tempStr = tempStr + P1.getRGB_str();
			}
			if(tempStr.equals(myStation.getLastTempString()) == false)
			{
				myMatrixMessage.clearArguments();
				myMatrixMessage.add(tempStr);
				oscP5.send(myMatrixMessage, myStation.getNetSettings());
			}
			myStation.setLastTempString(tempStr);
		}
		
		if (keyPressed == true) 
		{
			changeAngle();
		}
		
//		if(test_count >= 50) //testing
//		{
//			System.out.println("End:"+Instant.now());
//			test_count = 0;
//		}
		
	}
	
	void oscEvent(OscMessage theOscMessage) 
	{
		String temp_Addr = theOscMessage.addrPattern().substring(0,10);
		if(temp_Addr.equals("/PitchAndV"))	//PitchAndVelocity
		{
			_PadNumber = theOscMessage.get(0).intValue();
			_Note = theOscMessage.get(1).intValue();
			_Velocity = theOscMessage.get(2).intValue(); 	
			if(_PadNumber < MidiDevice.size())
				MidiDevice.get(_PadNumber).updateVolicity(_Note,_Velocity);	
		}
		else if(temp_Addr.equals("/PitchAndM"))	//PitchAndMode
		{
			_PadNumber = theOscMessage.get(0).intValue();
			_Note = theOscMessage.get(1).intValue();
			_Mode = theOscMessage.get(2).intValue(); 	
			if(_PadNumber < MidiDevice.size())
			{
				MidiDevice.get(_PadNumber).changeParticle(_Note,_Mode);
			}
		}
	}
	
	public void mousePressed() 
	{
		/*
		// Bundle Test
		OscBundle TestBundle = new OscBundle();
		OscMessage myMessage = new OscMessage("/TagAndVelocity");
		int ValueRan = (int) (random(100));
		myMessage.add(111); 
		myMessage.add(222);
		TestBundle.add(myMessage);
		oscP5.send(TestBundle, TestLocation); 
		*/
	}

	public void keyPressed() 
	{
		for (int i = 0; i < MidiDevice.size(); i++) 
		{
			MidiDevice.get(i).changeShape();
		}
	}
	
	public void changeAngle()
	{
		
		switch(key)
		{
			case '8':
			{
				_angleX++;
				break;
			}
			case '2':
			{
				_angleX--;
				break;
			}
			case '4':
			{
				_angleY--;
				break;
			}
			case '6':
			{
				_angleY++;
				break;
			}
			case '9':
			{
				_angleZ--;
				break;
			}
			case '1':
			{
				_angleZ++;
				break;
			}
			case '5':
			{
				_angleX = 0;
				_angleY = 0;
				_angleZ = 0;
				break;
			}
			case 'w':
			{
				for (int i = 0; i < MidiDevice.size(); i++) 
				{
					MidiDevice.get(i).updatePositionY(-1);
				}
				break;
			}
			case 's':
			{
				for (int i = 0; i < MidiDevice.size(); i++) 
				{
					MidiDevice.get(i).updatePositionY(1);
				}
				break;
			}
			case 'a':
			{
				for (int i = 0; i < MidiDevice.size(); i++) 
				{
					MidiDevice.get(i).updatePositionX(-1);
				}
				break;
			}
			case 'd':
			{
				for (int i = 0; i < MidiDevice.size(); i++) 
				{
					MidiDevice.get(i).updatePositionX(1);
				}
				break;
			}
			case 'r':
			{
				for (int i = 0; i < MidiDevice.size(); i++) 
				{
					MidiDevice.get(i).updatePositionZ(1);
				}
				break;
			}
			case 'f':
			{
				for (int i = 0; i < MidiDevice.size(); i++) 
				{
					MidiDevice.get(i).updatePositionZ(-1);
				}
				break;
			}
			
		}
		
	}
}
