
package publicadores;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "PublicadorDepartamentoService", targetNamespace = "http://publicadores/")
public class PublicadorDepartamentoService
    extends Service
{

    private final static URL PUBLICADORDEPARTAMENTOSERVICE_WSDL_LOCATION;
    private final static WebServiceException PUBLICADORDEPARTAMENTOSERVICE_EXCEPTION;
    private final static QName PUBLICADORDEPARTAMENTOSERVICE_QNAME = new QName("http://publicadores/", "PublicadorDepartamentoService");

    static {
        URL url = null;
        WebServiceException e = null;
        
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
			} catch (IOException error) {
				// TODO Auto-generated catch block
				error.printStackTrace();
			}
		} catch (FileNotFoundException error) {
			// TODO Auto-generated catch block
			error.printStackTrace();
		}
	  
	    dir = prop.getProperty("ip");
	    port = prop.getProperty("port");
	            
        try {
            url = new URL(dir+port+"/publicadorDepartamento?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        
        PUBLICADORDEPARTAMENTOSERVICE_WSDL_LOCATION = url;
        PUBLICADORDEPARTAMENTOSERVICE_EXCEPTION = e;
    }

    public PublicadorDepartamentoService() {
        super(__getWsdlLocation(), PUBLICADORDEPARTAMENTOSERVICE_QNAME);
    }

    public PublicadorDepartamentoService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PUBLICADORDEPARTAMENTOSERVICE_QNAME, features);
    }

    public PublicadorDepartamentoService(URL wsdlLocation) {
        super(wsdlLocation, PUBLICADORDEPARTAMENTOSERVICE_QNAME);
    }

    public PublicadorDepartamentoService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PUBLICADORDEPARTAMENTOSERVICE_QNAME, features);
    }

    public PublicadorDepartamentoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PublicadorDepartamentoService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PublicadorDepartamento
     */
    @WebEndpoint(name = "PublicadorDepartamentoPort")
    public PublicadorDepartamento getPublicadorDepartamentoPort() {
        return super.getPort(new QName("http://publicadores/", "PublicadorDepartamentoPort"), PublicadorDepartamento.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PublicadorDepartamento
     */
    @WebEndpoint(name = "PublicadorDepartamentoPort")
    public PublicadorDepartamento getPublicadorDepartamentoPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://publicadores/", "PublicadorDepartamentoPort"), PublicadorDepartamento.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PUBLICADORDEPARTAMENTOSERVICE_EXCEPTION!= null) {
            throw PUBLICADORDEPARTAMENTOSERVICE_EXCEPTION;
        }
        return PUBLICADORDEPARTAMENTOSERVICE_WSDL_LOCATION;
    }

}
