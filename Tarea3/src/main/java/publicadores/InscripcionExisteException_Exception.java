
package publicadores;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "inscripcionExisteException", targetNamespace = "http://publicadores/")
public class InscripcionExisteException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InscripcionExisteException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InscripcionExisteException_Exception(String message, InscripcionExisteException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public InscripcionExisteException_Exception(String message, InscripcionExisteException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: publicadores.InscripcionExisteException
     */
    public InscripcionExisteException getFaultInfo() {
        return faultInfo;
    }

}