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

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.image.RenderedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class InterpreteListeTriangle implements Interprete {
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }

    private int pos = 0;
    private int numFacettes;

    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        TRIObject fo = new TRIObject();
        InterpretesBase ib;
        ArrayList<Integer> pattern;
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();

        boolean ok = true;
        while (ok) {
            InterpreteTriangle ifacette = new InterpreteTriangle();
            try {
                fo.add((TRI) ifacette.interprete(text, pos));
                if (ifacette.getPosition() > pos) {
                    pos = ifacette.getPosition();
                    numFacettes++;
                }
            } catch (Exception ex) {
                ok = false;
            }

        }
        System.out.println(numFacettes + "" + text.substring(pos));
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        this.pos = ib.getPosition();
        return fo;
    }

    @Override
    public int getPosition() {
        return pos;
    }

    @Override
    public InterpreteConstants constant() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setConstant(InterpreteConstants c) {
        // TODO Auto-generated method stub
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        File file = new File("PMMATRIX.DATA\\TESTSCRIPTS\\CUBE.TXT");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DataInputStream ds = new DataInputStream(fis);
        String text = "";
        String t = "";
        try {
            while ((text = ds.readLine()) != null) {
                t += text;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InterpreteListeTriangle ilf = new InterpreteListeTriangle();
        TRIObject fo = null;
        try {
            fo = (TRIObject) ilf.interprete(t, 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ZBuffer z = new ZBufferImpl(600, 600);
        Scene s = new Scene();
        s.add(fo);
        z.scene(s);
        z.dessinerSilhouette3D();
        try {
            ImageIO.write((RenderedImage) z.image(), "jpeg", new File("rendu.interpretelistetriangle.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
