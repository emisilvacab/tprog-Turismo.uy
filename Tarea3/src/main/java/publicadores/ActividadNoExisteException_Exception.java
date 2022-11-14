
package publicadores;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "actividadNoExisteException", targetNamespace = "http://publicadores/")
public class ActividadNoExisteException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ActividadNoExisteException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ActividadNoExisteException_Exception(String message, ActividadNoExisteException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public ActividadNoExisteException_Exception(String message, ActividadNoExisteException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: publicadores.ActividadNoExisteException
     */
    public ActividadNoExisteException getFaultInfo() {
        return faultInfo;
    }

}