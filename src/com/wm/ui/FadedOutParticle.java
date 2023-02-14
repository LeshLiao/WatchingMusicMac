package com.wm.ui;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PVector;

public class FadedOutParticle extends Particle{
	private int animationColorR;
	private int animationColorG;
	private int animationColorB;
	
	private float[] floatArray;
	private float[] floatModel02;
	private float[] floatModel03;
	int _floatArrayCount = 30;
	int _floatIndex = 0;
	
	float _floatPointer = 0.0f;
	float _speed = 2.5f;		// 1.0f is one second on 30fps
								// 2.0f is half second on 30fps
								// 0.5f is two seconds on 30fps
	FadedOutParticle(PApplet p) {
		super(p);
	}
	
	FadedOutParticle(PApplet p,Particle _source)	//switch particle type
	{
		this(p);
		coordinate_x = _source.coordinate_x;
		coordinate_y = _source.coordinate_y;
		
		// create a model 02
		floatModel02 = new float[_floatArrayCount];
		for(int i=0;i<_floatArrayCount;i++)
			floatModel02[i] = (float)(_floatArrayCount-i-1)/(float)_floatArrayCount;
		
		// create a model 03
		floatModel03= new float[_floatArrayCount];
		for(int i=0;i<_floatArrayCount/2;i++)	//0~14
			floatModel03[i] = (float)(_floatArrayCount-(i*2)-1)/(float)_floatArrayCount;
		
		for(int i=0;i<_floatArrayCount/2;i++)	//15~29
			floatModel03[i+15] = (float)((i*2)+1)/(float)_floatArrayCount;
		
		
		//floatArray = Arrays.copyOf(floatModel02, floatModel02.length);
		floatArray = Arrays.copyOf(floatModel03, floatModel03.length);
	}

	@Override
	public void SetVolicity(int Volicity)
	{
		//_floatPointer = 0.0f;
		//_floatIndex = 0;
		colorR = col.colorTable[Volicity][0];
		colorG = col.colorTable[Volicity][1];	
		colorB = col.colorTable[Volicity][2];	
		MyVolicity = Volicity;
	}
	
	@Override
	public void display()
	{
		if(_floatIndex < _floatArrayCount)
		{	
			animationColorR = (int)(colorR * floatArray[_floatIndex]);
			animationColorG = (int)(colorG * floatArray[_floatIndex]);
			animationColorB = (int)(colorB * floatArray[_floatIndex]);
			_floatPointer = _floatPointer + _speed;
			_floatIndex = (int)_floatPointer;
		}
		else	// The last one of array.
		{
			animationColorR = (int)(colorR * floatArray[_floatArrayCount-1]);
			animationColorG = (int)(colorG * floatArray[_floatArrayCount-1]);
			animationColorB = (int)(colorB * floatArray[_floatArrayCount-1]);
		}

		parent.fill(animationColorR,animationColorG,animationColorB);	
		draw(shape);
	}
	
	@Override
	public String getRGB_str()
	{
		String str = "";
		str = Integer.toString(animationColorR)+","+Integer.toString(animationColorG)+","+Integer.toString(animationColorB)+",";
		return str;
	}
	

}
