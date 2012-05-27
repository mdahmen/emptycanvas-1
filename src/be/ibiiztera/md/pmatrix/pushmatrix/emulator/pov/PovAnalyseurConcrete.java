package be.ibiiztera.md.pmatrix.pushmatrix.emulator.pov;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Manuel DAHMEN
 */
public class PovAnalyseurConcrete implements PovAnalyseur{
    private HashMap<String, Object> objetsDéclarés;

    
    @Override
    public String povVersion() {
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }

    protected String suppressionDesCommentaires(String pCode)
    {
        String MyCommentsRegex = "(?://.*)|(/\\*(?:.|[\\n\\r])*?\\*/)";
	return pCode.replaceAll(MyCommentsRegex, " ");
    }
    
    @Override
    public void analyse(File povfile) {
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }

    @Override
    public void analyse(String povstring, Scene scene) {
        int position = 0;
        
        // Suppression des commentaires
        povstring = suppressionDesCommentaires(povstring);
        // Eliminer les blancs
        povstring = suppressionDesBlancs(povstring);
        // Lecture du fichier
        
        while(position < povstring.length())
        {
            String ligne = povstring.substring(position, povstring.indexOf(Character.LINE_SEPARATOR, position));
            String trim = ligne.trim();
            if(trim.startsWith("#include"))
            {
               analyse(new File(trim.substring(ligne.indexOf("\"")+1, trim.lastIndexOf("\""))));        
            }
            else if(trim.startsWith("#declare"))
            {
                
            }
            
            position += ligne.length();
        }
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }

    protected String suppressionDesBlancs(String povstring) {
        return povstring;
    }
    
}
