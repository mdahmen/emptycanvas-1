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
package be.ibiiztera.md.pmatrix.serialise;

/**
 *
 * @author Manuel DAHMEN
 */
public class Lecture {
    private final String string;
    public Lecture(String s)
    {
        this.string = s;
    }
    public Serialisable lire()
    {
        Serialisable res = null;
        String type = string.substring(0, string.indexOf(" ("));
        if(type.equals("tableau"))
            return new Tableau().lecture(string);
        if(type.equals("table"))
            return new Table().lecture(string);
        if(type.equals("atome"))
            return new TypePrimitif().lecture(string);
        return null;
    }
}
