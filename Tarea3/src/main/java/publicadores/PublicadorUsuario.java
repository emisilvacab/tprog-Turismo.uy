
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
     * @return
     *     returns publicadores.DtColecciones
     * @throws ActividadNoExisteException_Exception
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorUsuario/obtenerActividadesOfrecidasConfirmadasDTRequest", output = "http://publicadores/PublicadorUsuario/obtenerActividadesOfrecidasConfirmadasDTResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerActividadesOfrecidasConfirmadasDT/Fault/usuarioNoExisteException"),
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerActividadesOfrecidasConfirmadasDT/Fault/actividadNoExisteException")
    })
    public DtColecciones obtenerActividadesOfrecidasConfirmadasDT(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtColecciones
     * @throws ActividadNoExisteException_Exception
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorUsuario/obtenerActividadesOfrecidasDTRequest", output = "http://publicadores/PublicadorUsuario/obtenerActividadesOfrecidasDTResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerActividadesOfrecidasDT/Fault/usuarioNoExisteException"),
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerActividadesOfrecidasDT/Fault/actividadNoExisteException")
    })
    public DtColecciones obtenerActividadesOfrecidasDT(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtColecciones
     * @throws ActividadNoExisteException_Exception
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorUsuario/obtenerSalidasConfirmadasDTRequest", output = "http://publicadores/PublicadorUsuario/obtenerSalidasConfirmadasDTResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerSalidasConfirmadasDT/Fault/usuarioNoExisteException"),
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerSalidasConfirmadasDT/Fault/actividadNoExisteException")
    })
    public DtColecciones obtenerSalidasConfirmadasDT(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception, UsuarioNoExisteException_Exception
    ;

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
     * @return
     *     returns publicadores.DtColecciones
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorUsuario/obtenerInscripcionesTuristaRequest", output = "http://publicadores/PublicadorUsuario/obtenerInscripcionesTuristaResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerInscripcionesTurista/Fault/usuarioNoExisteException")
    })
    public DtColecciones obtenerInscripcionesTurista(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtColecciones
     * @throws ActividadNoExisteException_Exception
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorUsuario/obtenerSalidasOfrecidasDTRequest", output = "http://publicadores/PublicadorUsuario/obtenerSalidasOfrecidasDTResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerSalidasOfrecidasDT/Fault/usuarioNoExisteException"),
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerSalidasOfrecidasDT/Fault/actividadNoExisteException")
    })
    public DtColecciones obtenerSalidasOfrecidasDT(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @return
     *     returns publicadores.DtColecciones
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorUsuario/obtenerUsuariosDTRequest", output = "http://publicadores/PublicadorUsuario/obtenerUsuariosDTResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerUsuariosDT/Fault/usuarioNoExisteException")
    })
    public DtColecciones obtenerUsuariosDT()
        throws UsuarioNoExisteException_Exception
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

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtColecciones
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorUsuario/obtenerComprasTuristaRequest", output = "http://publicadores/PublicadorUsuario/obtenerComprasTuristaResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerComprasTurista/Fault/usuarioNoExisteException")
    })
    public DtColecciones obtenerComprasTurista(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtUsuario
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorUsuario/obtenerUsuarioRequest", output = "http://publicadores/PublicadorUsuario/obtenerUsuarioResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://publicadores/PublicadorUsuario/obtenerUsuario/Fault/usuarioNoExisteException")
    })
    public DtUsuario obtenerUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioNoExisteException_Exception
    ;

}
