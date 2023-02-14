package com.wm.ui;
import processing.core.*;


public class Particle {
	PApplet parent; 
	int MyVolicity;
	protected int colorR;
	protected int colorG;
	protected int colorB;
	protected int shape;
	protected int particleWidth;
	protected int particleHeight;
	protected int particleDepth;
	protected int coordinate_x;
	protected int coordinate_y;
	protected int coordinate_z;
	protected ColorMappingTable col; 

	Particle(PApplet p)
	{
		parent = p;
		coordinate_x = 0;
		coordinate_y = 0;
		coordinate_z = 0;
		particleWidth = 0;
		particleHeight = 0;
		particleDepth = 0;
		shape = 1;
		MyVolicity = 0;
		col = new ColorMappingTable(); 
	}

	Particle(PApplet p,Particle _source)	//switch particle type
	{
		this(p);
		coordinate_x = _source.coordinate_x;
		coordinate_y = _source.coordinate_y;
		coordinate_z = _source.coordinate_z;
		particleWidth = _source.particleWidth; 
		particleHeight = _source.particleHeight;
		particleDepth = _source.particleDepth;	
	}

	public void SetVolicity(int Volicity)
	{
		colorR = col.colorTable[Volicity][0];
		colorG = col.colorTable[Volicity][1];	
		colorB = col.colorTable[Volicity][2];	
		MyVolicity = Volicity;
	}

	public void SetPosition(int _x,int _y,int _z,int _width,int _height,int _depth)
	{
		coordinate_x = _x;
		coordinate_y = _y;
		coordinate_z = _z;
		particleWidth = _width;
		particleHeight = _height;
		particleDepth = _depth;
	}
	
	public void SetShape(int _shape)
	{
		shape = _shape;
	}
	
	public void display()
	{
		parent.fill(colorR,colorG,colorB);
		draw(shape);
	}
	
	public void display(int _shape)
	{
		parent.fill(colorR,colorG,colorB);
		draw(_shape);
	}
	
	public String getRGB_str()
	{
		String str = Integer.toString(colorR)+","+Integer.toString(colorG)+","+Integer.toString(colorB)+",";
		return str;
	}
	
	public void draw(int _shape)
	{
		switch(_shape)
		{
			case 1:
			{
				parent.ellipse(coordinate_x+100, coordinate_y+50, particleWidth, particleHeight);
				break;
			}
			case 2:
			{
				parent.rect(coordinate_x+50, coordinate_y,particleWidth,particleHeight,20);	
				break;
			}	
			case 3:
			{
				float testX = coordinate_x;
				float testY = coordinate_y;
				int ratio = 2;
				parent.beginShape();
				parent.vertex((50*ratio)+testX, (15*ratio)+testY); 
				parent.bezierVertex((50*ratio)+testX, (-5*ratio)+testY, (100*ratio)+testX, (10*ratio)+testY, (50*ratio)+testX, (40*ratio)+testY); 
				parent.vertex((50*ratio)+testX, (15*ratio)+testY); 
				parent.bezierVertex((50*ratio)+testX, (-5*ratio)+testY, (0*ratio)+testX, (10*ratio)+testY, (50*ratio)+testX, (40*ratio)+testY); 
				parent.endShape();
				break;
			}
			case 4:
			{
				parent.box(particleWidth,particleHeight,particleDepth);
				break;
			}
			case 5:
			{
				parent.sphere(15);
				break;
			}
			default:
			{
				parent.rect(coordinate_x+50, coordinate_y,particleWidth,particleHeight,20);
				break;	
			}
		}
	}
	

}



