package publicadores;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorImagenes {
	
	private Endpoint endpoint = null;
	
	public PublicadorImagenes() {}
	
	@WebMethod(exclude = true) //el exclude = true hace que no se publique ese método
    public void publicar(){
		String home = System.getProperty("user.home");
		String filepath = home + "/.turismoUy/config.properties"; //linux, mac
		//String filepath = home + "\\.turismoUy\\config.properties"; //pc
		String port = "";
	    String dir = "";
	    
	    
	    Properties prop = new Properties();
	    try {
			FileInputStream input = new FileInputStream(filepath);
			try {
				prop.load(input);
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	    dir = prop.getProperty("ip");
	    port = prop.getProperty("port");
	    
        endpoint = Endpoint.publish(dir+port+"/publicadorImagenes", this);
    }
	
	@WebMethod(exclude = true)
    public Endpoint getEndpoint() {
         return endpoint;
    }
	
	@WebMethod
    public byte[] getFile(@WebParam(name = "fileName") String name)
                    throws  IOException {
        byte[] byteArray = null;
        try {
            File f = new File("files/" + name);
            FileInputStream streamer = new FileInputStream(f);
            byteArray = new byte[streamer.available()];
            streamer.read(byteArray);
        } catch (IOException e) {
            throw e;
        }
        return byteArray;
    }
	
	@WebMethod
	public String guardarImagen(byte[] byteArray, String name) {
		
		int num = 0;
		String extension = ".jpg";
		File file = new File("files/" + name + extension);
		while (file.exists()) { //le agrega 1 al numero del nombre si ya existe
			name = name + (num++) + extension;
			file = new File("files/" + name + extension);
		}
		
		// create the object of ByteArrayInputStream class
        // and initialized it with the byte array.
        ByteArrayInputStream inStreambj = new ByteArrayInputStream(byteArray);
        BufferedImage newImage;
		try {
			// read image from byte array
			newImage = ImageIO.read(inStreambj);
            // write output image
			ImageIO.write(newImage, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name + extension;
		
	}
}
