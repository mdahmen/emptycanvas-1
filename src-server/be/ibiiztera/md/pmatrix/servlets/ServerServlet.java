package be.ibiiztera.md.pmatrix.servlets;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import javax.servlet.http.*;

import be.ibiiztera.md.pmatrix.pushmatrix.ZBuffer;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBufferFactory;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
public class ServerServlet extends HttpServlet
{
	public void doPost(HttpRequest req, HttpResponse resp)
	{
	    String nom_projet = "destiny";
		//resp.getWriter();
		String task = "config";
		
		if("config".equals(task))
		{
		} else if("render".equals(task))
		{
			int id = new Random().nextInt();
			String txt = "Scene(\n)\n";
			File output = new File("~/"+nom_projet);
			int resx = 640;
			int resy = 480;
			ZBuffer z = ZBufferFactory.instance(resx, resy);
			Scene sc = new Scene();
			new Loader().loadIF(txt, sc);
			z.scene(sc);
			z.dessinerSilhouette3D();
		
			try
			{
				javax.imageio.ImageIO.write((RenderedImage)z.image(), "jpg", output);
			}
			catch(Exception ex)
		
			{
			}
		}
		else if("result".equals(task))
		{
		}
	}

}
