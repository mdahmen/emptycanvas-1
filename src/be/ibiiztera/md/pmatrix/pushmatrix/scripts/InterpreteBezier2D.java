/*

    Copyright (C) 2010-2012  DAHMEN, Manuel, Daniel

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

*/
package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.awt.Color;
import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.BezierCubique2D;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

public class InterpreteBezier2D implements Interprete{
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
	private int pos;
	@Override
	public Object interprete(String text, int pos) throws InterpreteException{
		
		
		
		Point3D [][] points = new Point3D[4][4];
		
		
		ArrayList<Integer> pattern = null;
		InterpretesBase ib = null;
		ib = new  InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		
		
		
		pos = ib.getPosition();
		
		
		InterpreteCouleur pc = new InterpreteCouleur();
		Color c = (Color) pc.interprete(text, pos);
		pos = pc.getPosition();
		
		ib = new  InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		
		pos = ib.getPosition();
		
		int i=0;
		while(i<4)
		{
			InterpreteIdentifier ii  = new InterpreteIdentifier();
			String ligne = (String) ii.interprete(text, pos);
			pos = ii.getPosition();
			if("ligne".equals(ligne))
			{
				
				ib = new  InterpretesBase();
				pattern = new ArrayList<Integer>();
				pattern.add(ib.BLANK);
				pattern.add(ib.LEFTPARENTHESIS);
				pattern.add(ib.BLANK);
				ib.compile(pattern);
				ib.read(text, pos);
				
				pos = ib.getPosition();
				
				for(int j = 0; j<4; j++)
				{
					InterpretePoint3DBAK ip = new InterpretePoint3DBAK();
					points[i][j] = (Point3D) ip.interprete(text, pos);
					points[i][j].setC(c);
					pos = ip.getPosition();
					
					
					ib = new  InterpretesBase();
					pattern = new ArrayList<Integer>();
					pattern.add(ib.BLANK);
					ib.compile(pattern);
					ib.read(text, pos);
					pos = ib.getPosition();
				}
				ib = new  InterpretesBase();
				pattern = new ArrayList<Integer>();
				pattern.add(ib.RIGHTPARENTHESIS);
				pattern.add(ib.BLANK);
				ib.compile(pattern);
				ib.read(text, pos);
				pos = ib.getPosition();
			}
			i++;
		}
		this.pos = pos;
		return new BezierCubique2D(points);
	}
	@Override
	public int getPosition() {
		return pos;
	}
	@Override
	public InterpreteConstants constant() {
		return null;
	}
	@Override
	public void setConstant(InterpreteConstants c) {
		
	}
}
