
package publicadores;

import javax.xml.datatype.XMLGregorianCalendar;
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
@WebService(name = "PublicadorUsuario", targetNamespace = "http://publicadores/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PublicadorUsuario {


    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @throws InscripcionExisteException_Exception
     * @throws LimiteSuperadoException_Exception
     * @throws PaqueteNoExisteException_Exception
     * @throws SalidaNoExisteException_Exception
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @Action(input = "http://publicadores/PublicadorUsuario/ingresarDatosInscripcionPaqRequest", output = "http://publicadores/PublicadorUsuario/ingresarDatosInscripcionPaqResponse", fault = {
        @FaultAction(className = SalidaNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/ingresarDatosInscripcionPaq/Fault/salidaNoExisteException"),
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/ingresarDatosInscripcionPaq/Fault/usuarioNoExisteException"),
        @FaultAction(className = PaqueteNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/ingresarDatosInscripcionPaq/Fault/paqueteNoExisteException"),
        @FaultAction(className = InscripcionExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/ingresarDatosInscripcionPaq/Fault/inscripcionExisteException"),
        @FaultAction(className = LimiteSuperadoException_Exception.class, value = "http://publicadores/PublicadorUsuario/ingresarDatosInscripcionPaq/Fault/limiteSuperadoException")
    })
    public void ingresarDatosInscripcionPaq(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2,
        @WebParam(name = "arg3", partName = "arg3")
        XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4)
        throws InscripcionExisteException_Exception, LimiteSuperadoException_Exception, PaqueteNoExisteException_Exception, SalidaNoExisteException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws UsuarioRepetidoException_Exception
     */
    @WebMethod
    @Action(input = "http://publicadores/PublicadorUsuario/altaProveedorRequest", output = "http://publicadores/PublicadorUsuario/altaProveedorResponse", fault = {
        @FaultAction(className = UsuarioRepetidoException_Exception.class, value = "http://publicadores/PublicadorUsuario/altaProveedor/Fault/UsuarioRepetidoException")
    })
    public void altaProveedor(
        @WebParam(name = "arg0", partName = "arg0")
        DtProveedor arg0)
        throws UsuarioRepetidoException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws UsuarioRepetidoException_Exception
     */
    @WebMethod
    @Action(input = "http://publicadores/PublicadorUsuario/altaTuristaRequest", output = "http://publicadores/PublicadorUsuario/altaTuristaResponse", fault = {
        @FaultAction(className = UsuarioRepetidoException_Exception.class, value = "http://publicadores/PublicadorUsuario/altaTurista/Fault/UsuarioRepetidoException")
    })
    public void altaTurista(
        @WebParam(name = "arg0", partName = "arg0")
        DtTurista arg0)
        throws UsuarioRepetidoException_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns publicadores.DtUsuario
     * @throws IngresoInvalidoException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorUsuario/iniciarSesionRequest", output = "http://publicadores/PublicadorUsuario/iniciarSesionResponse", fault = {
        @FaultAction(className = IngresoInvalidoException_Exception.class, value = "http://publicadores/PublicadorUsuario/iniciarSesion/Fault/ingresoInvalidoException")
    })
    public DtUsuario iniciarSesion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws IngresoInvalidoException_Exception
    ;

}
