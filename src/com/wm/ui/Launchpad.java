package com.wm.ui;
import java.util.ArrayList;

import oscP5.OscMessage;
import processing.core.PApplet;

public class Launchpad
{
	ArrayList<Particle> MainMatrix;
	ColorMappingTable MyColor;
	final static int MatrixWidth = 8;
	final static int MatrixHeight = 8;                
	final static int particleWidth = 30;
	final static int particleHeight = 30;
	final static int particleDepth = 20;	//8
	final static int SlotGapX = 20;			//10
	final static int SlotGapY = 20;			//10
	public int padPosition_x;
	public int padPosition_y;
	public int padPosition_z;
	private int angleX;
	private int angleY;
	private int angleZ;
	
	PApplet parent; // The parent PApplet that we will render ourselves onto
	OscMessage myMatrixMessage; 
	String PadName;
	int[][] NoteNumber;
	int[] MidiMappingTable;
	
	Launchpad(PApplet _p,String _PadName,int _padPosition_x,int _padPosition_y,int _padPosition_z,int _rotateMode)
	{
		parent = _p;
		PadName = _PadName;
		padPosition_x = _padPosition_x;
		padPosition_y = _padPosition_y;
		padPosition_z = _padPosition_z;
		angleX = 45;
		angleY = 0;
		angleZ = 0;
		MyColor = new ColorMappingTable(); 
		myMatrixMessage = new OscMessage("/MatrixVelocity");
		
		MainMatrix = new ArrayList<Particle>();   
		for(int _note = 0;_note < 128; _note++)		//Launchpad has 128 notes 
			MainMatrix.add(new Particle(parent));   		// here!!
			//MainMatrix.add(new FadedOutParticle(parent));   // Polymorphism

		InitParticlePosition();
		InitMidiRotate(_rotateMode);
	}
	
	private void InitParticlePosition()
	{
		NoteNumber = new int[][]{ {  0, 28, 29, 30, 31, 32, 33, 34, 35,  0},
	                			  {108, 64, 65, 66, 67, 96, 97, 98, 99,100},
	                			  {109, 60, 61, 62, 63, 92, 93, 94, 95,101},
	                			  {110, 56, 57, 58, 59, 88, 89, 90, 91,102},
	                			  {111, 52, 53, 54, 55, 84, 85, 86, 87,103},
	                			  {112, 48, 49, 50, 51, 80, 81, 82, 83,104},
	                			  {113, 44, 45, 46, 47, 76, 77, 78, 79,105},
	                			  {114, 40, 41, 42, 43, 72, 73, 74, 75,106},
	                			  {115, 36, 37, 38, 39, 68, 69, 70, 71,107},
	                			  {  0,116,117,118,119,120,121,122,123,  0}};

		//int[][] NoteNumber = rotateLeftPad.clone();
		//int[][] NoteNumber = defaultPad.clone();
	
		int rectY = padPosition_y;
		for(int i = 0;i < 10; i++)
		{
			int rectX = 0;
			for(int j = 0;j < 10; j++)
			{	
				Particle P1 = MainMatrix.get(NoteNumber[i][j]);
				P1.SetPosition(rectX+padPosition_x,rectY,padPosition_z,particleWidth,particleHeight,particleDepth);
				rectX = rectX + particleWidth + SlotGapX;
			}
			rectY = rectY + particleHeight + SlotGapY;
		}
	}
	
	private void InitMidiRotate(int _rotateMode)
	{
		MidiMappingTable = new int[128];
		switch(_rotateMode)
		{
			case 1: //left
			{
				for(int i = 0;i < 10; i++)
				{
					for(int j = 0;j < 10; j++)
					{	
						int _index = NoteNumber[i][j];
						MidiMappingTable[_index] = NoteNumber[9-j][i];
					}
				}
				break;
			}
			default: // No rotate
			{
				for(int i = 0;i < 10; i++)
				{
					for(int j = 0;j < 10; j++)
					{	
						int _index = NoteNumber[i][j];
						MidiMappingTable[_index] = NoteNumber[i][j];
					}
				}
				break;
			}
		}
	}
	
	public void updatePositionX(int _valueX)
	{
		padPosition_x = padPosition_x + _valueX;
		InitParticlePosition();
	}
	
	public void updatePositionY(int _valueY)
	{
		padPosition_y = padPosition_y + _valueY;
		InitParticlePosition();
	}
	
	public void updatePositionZ(int _valueZ)
	{
		padPosition_z = padPosition_z + _valueZ;
		InitParticlePosition();
	}
	

	public void updateVolicity(int Note,int Volicity)
	{
		int index = MidiMappingTable[Note];
		Particle P1 = MainMatrix.get(index); 
		P1.SetVolicity(Volicity);
	}
	
	public void updateMode(int Note,int Mode)
	{
		int index = Note;
		Particle P1 = MainMatrix.get(index); 
		P1.SetShape(Mode);	//test
	}
	
	public void AddAngleX(int _value)
	{
		angleX += _value;
	}
	public void AddAngleY(int _value)
	{
		angleY += _value;
	}
	public void AddAngleZ(int _value)
	{
		angleZ+= _value;
	}
	
	public void changeParticle(int Note,int Mode)
	{
		Particle P1 = MainMatrix.get(Note); 
		Particle _newParticle = null;
		if(Mode == 2)	 				// Faded out 
		{
			_newParticle = new FadedOutParticle(parent,P1);
		}
		else if(Mode == 0 || Mode == 1)	// default mode
		{
			_newParticle = new Particle(parent,P1);
		}
		_newParticle.SetVolicity(P1.MyVolicity);
		MainMatrix.set(Note,_newParticle);
	}
	
	public void changeShape()
	{
		int shape = (int) parent.random(3)+1; // 1~3
		for (int i = 1; i < MainMatrix.size(); i++)
		{
			Particle P1 = MainMatrix.get(i);
			P1.SetShape(shape);
		}
	}

	public void display()
	{
		//parent.background(0);
		for (int i = 1; i < MainMatrix.size(); i++)
		{
			Particle P1 = MainMatrix.get(i);
			P1.display();
		}
	}
	
	public void display3D()
	{
		int _pointerX = 0;	
		int _pointerY = 0;
		int _pointerZ = 0;

		//text
		parent.fill(200, 200, 200);
		parent.textSize(20);
		String _Text = "Pad:"+PadName+",x:"+Integer.toString(padPosition_x)+",y:"+Integer.toString(padPosition_y);
		parent.text(_Text, padPosition_x-5, padPosition_y-50); 
		
		//parent.directionalLight(255, 255, 255, (float)0.5, (float)0.5, 0);
		//parent.directionalLight(255, 255, 255, (float)-0.5, (float)-0.5, 0);
		//parent.ambientLight(0,100,255);
//		parent.rotateX(PApplet.radians(angleX));
//		parent.rotateY(PApplet.radians(angleY));
//		parent.rotateZ(PApplet.radians(angleZ));
		
		//parent.stroke(200);
	
		for (int i = 1; i < MainMatrix.size(); i++)
		{
			Particle P1 = MainMatrix.get(i);
			parent.translate(P1.coordinate_x - _pointerX, P1.coordinate_y - _pointerY, P1.coordinate_z - _pointerZ);
			P1.display(4);
			//P1.display(3);
			_pointerX = P1.coordinate_x;
			_pointerY = P1.coordinate_y;
			_pointerZ = P1.coordinate_z;
		}	
	}

}