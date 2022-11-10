
package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "PublicadorImagenes", targetNamespace = "http://publicadores/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PublicadorImagenes {


    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorImagenes/guardarImagenRequest", output = "http://publicadores/PublicadorImagenes/guardarImagenResponse")
    public String guardarImagen(
        @WebParam(name = "arg0", partName = "arg0")
        byte[] arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param fileName
     * @return
     *     returns byte[]
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorImagenes/getFileRequest", output = "http://publicadores/PublicadorImagenes/getFileResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://publicadores/PublicadorImagenes/getFile/Fault/IOException")
    })
    public byte[] getFile(
        @WebParam(name = "fileName", partName = "fileName")
        String fileName)
        throws IOException_Exception
    ;

}
