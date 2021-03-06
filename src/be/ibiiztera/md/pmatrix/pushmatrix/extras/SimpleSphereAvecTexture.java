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

package be.ibiiztera.md.pmatrix.pushmatrix.extras;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         27 oct. 2011
 * 
 */
public class SimpleSphereAvecTexture extends SimpleSphere {

    private BufferedImage img;
    private Axe axe;
    private double angle;
    private String fichier;

    /**
     * @param c
     * @param r
     * @param col
     */
    public SimpleSphereAvecTexture(Point3D c, double r, Color col) {
        super(c, r, col);
    }

    public SimpleSphereAvecTexture(Point3D c, double r, Color col,
            BufferedImage img) {
        super(c, r, col);
        texture(img);
    }

    public void fichier(String f) {
        fichier = f;
    }

    public SimpleSphereAvecTexture(Point3D c, Matrix3D m3d, double angle, double r,
            Color col, BufferedImage img) {
        super(c, r, col);
        this.axe = axe;
        this.angle = angle;
        texture(img);
    }

    public void texture(BufferedImage img) {
        this.img = img;
    }

    @Override
    public TRIObject generate() {
        TRIObject t = new TRIObject();
        po = new PObjet();

        double a = -Math.PI / 2;
        double b;
        double incrLat;
        double incrLong;
        Point3D[] pCur = new Point3D[4];

        incrLat = 2 * Math.PI / numLatQuad;
        while (a < Math.PI / 2) {
            incrLong = 2 * Math.PI * Math.cos(a) / numLongQuad;
            b = 0;
            while (b < 2 * Math.PI && incrLong > 0.0001) {
                //System.out.println("a;b " + a +";"+b);
                pCur[0] = CoordPoint(a, b);
                pCur[1] = CoordPoint(a + incrLat, b);
                pCur[2] = CoordPoint(a, b + incrLong);
                pCur[3] = CoordPoint(a + incrLat, b + incrLong);
                try {
                    Color color = new Color(img.getRGB(
                            (int) ((a + Math.PI) / Math.PI * img.getHeight()),
                            (int) ((b) / 2 / Math.PI * img.getWidth())));
                    t.add(new TRI(pCur[0], pCur[1], pCur[3], color));
                    t.add(new TRI(pCur[0], pCur[2], pCur[3], color));
                } catch (Exception ex) {
                }
                b += incrLong;
            }
            a += incrLat;
        }
        return t;
    }

    public String toString() {
        return "\nsimpleSphereAvecTexture(\n\t" + centre.toString() + "\n\t" + radius + " \n\t\"" + fichier + "\"\n)\n";
    }
}
