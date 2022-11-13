
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
@WebService(name = "PublicadorDepartamento", targetNamespace = "http://publicadores/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PublicadorDepartamento {


    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg10
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @param arg7
     * @param arg8
     * @param arg9
     * @return
     *     returns boolean
     * @throws DepartamentoNoExisteException_Exception
     * @throws ProveedorNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/ingresarDatosActividadRequest", output = "http://publicadores/PublicadorDepartamento/ingresarDatosActividadResponse", fault = {
        @FaultAction(className = ProveedorNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/ingresarDatosActividad/Fault/proveedorNoExisteException"),
        @FaultAction(className = DepartamentoNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/ingresarDatosActividad/Fault/departamentoNoExisteException")
    })
    public boolean ingresarDatosActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2,
        @WebParam(name = "arg3", partName = "arg3")
        float arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        XMLGregorianCalendar arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        DtColecciones arg8,
        @WebParam(name = "arg9", partName = "arg9")
        String arg9,
        @WebParam(name = "arg10", partName = "arg10")
        String arg10)
        throws DepartamentoNoExisteException_Exception, ProveedorNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @param arg7
     * @param arg8
     * @return
     *     returns boolean
     * @throws ActividadNoExisteException_Exception
     * @throws DepartamentoNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/ingresarDatosSalidaRequest", output = "http://publicadores/PublicadorDepartamento/ingresarDatosSalidaResponse", fault = {
        @FaultAction(className = DepartamentoNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/ingresarDatosSalida/Fault/departamentoNoExisteException"),
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/ingresarDatosSalida/Fault/actividadNoExisteException")
    })
    public boolean ingresarDatosSalida(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        int arg1,
        @WebParam(name = "arg2", partName = "arg2")
        XMLGregorianCalendar arg2,
        @WebParam(name = "arg3", partName = "arg3")
        XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", partName = "arg4")
        int arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        String arg8)
        throws ActividadNoExisteException_Exception, DepartamentoNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtActividad
     * @throws ActividadNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerDatosActividadRequest", output = "http://publicadores/PublicadorDepartamento/obtenerDatosActividadResponse", fault = {
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/obtenerDatosActividad/Fault/actividadNoExisteException")
    })
    public DtActividad obtenerDatosActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtSalida
     * @throws SalidaNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerDatosSalidaRequest", output = "http://publicadores/PublicadorDepartamento/obtenerDatosSalidaResponse", fault = {
        @FaultAction(className = SalidaNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/obtenerDatosSalida/Fault/salidaNoExisteException")
    })
    public DtSalida obtenerDatosSalida(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws SalidaNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws ActividadPerteneceAPaqueteException_Exception
     * @throws ActividadTieneSalidasVigentesException_Exception
     */
    @WebMethod
    @Action(input = "http://publicadores/PublicadorDepartamento/finalizarActividadRequest", output = "http://publicadores/PublicadorDepartamento/finalizarActividadResponse", fault = {
        @FaultAction(className = ActividadTieneSalidasVigentesException_Exception.class, value = "http://publicadores/PublicadorDepartamento/finalizarActividad/Fault/actividadTieneSalidasVigentesException"),
        @FaultAction(className = ActividadPerteneceAPaqueteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/finalizarActividad/Fault/actividadPerteneceAPaqueteException")
    })
    public void finalizarActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadPerteneceAPaqueteException_Exception, ActividadTieneSalidasVigentesException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws SalidaNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/salidaEstaVigenteRequest", output = "http://publicadores/PublicadorDepartamento/salidaEstaVigenteResponse", fault = {
        @FaultAction(className = SalidaNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/salidaEstaVigente/Fault/salidaNoExisteException")
    })
    public boolean salidaEstaVigente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws SalidaNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtColecciones
     * @throws ActividadNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerCategoriasActividadRequest", output = "http://publicadores/PublicadorDepartamento/obtenerCategoriasActividadResponse", fault = {
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/obtenerCategoriasActividad/Fault/actividadNoExisteException")
    })
    public DtColecciones obtenerCategoriasActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerDeptoActividadRequest", output = "http://publicadores/PublicadorDepartamento/obtenerDeptoActividadResponse")
    public String obtenerDeptoActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtColecciones
     * @throws ActividadNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerDatosSalidasParaActividadRequest", output = "http://publicadores/PublicadorDepartamento/obtenerDatosSalidasParaActividadResponse", fault = {
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/obtenerDatosSalidasParaActividad/Fault/actividadNoExisteException")
    })
    public DtColecciones obtenerDatosSalidasParaActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtColecciones
     * @throws ActividadNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerDatosSalidasVigentesRequest", output = "http://publicadores/PublicadorDepartamento/obtenerDatosSalidasVigentesResponse", fault = {
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/obtenerDatosSalidasVigentes/Fault/actividadNoExisteException")
    })
    public DtColecciones obtenerDatosSalidasVigentes(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtColecciones
     * @throws DepartamentoNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerDatosActividadesConfirmadasDptoRequest", output = "http://publicadores/PublicadorDepartamento/obtenerDatosActividadesConfirmadasDptoResponse", fault = {
        @FaultAction(className = DepartamentoNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/obtenerDatosActividadesConfirmadasDpto/Fault/departamentoNoExisteException")
    })
    public DtColecciones obtenerDatosActividadesConfirmadasDpto(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws DepartamentoNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicadores.DtColecciones
     * @throws CategoriaNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerDatosActividadesConfirmadasCatRequest", output = "http://publicadores/PublicadorDepartamento/obtenerDatosActividadesConfirmadasCatResponse", fault = {
        @FaultAction(className = CategoriaNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/obtenerDatosActividadesConfirmadasCat/Fault/categoriaNoExisteException")
    })
    public DtColecciones obtenerDatosActividadesConfirmadasCat(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws CategoriaNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws SalidaNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerNombreActividadDeSalidaRequest", output = "http://publicadores/PublicadorDepartamento/obtenerNombreActividadDeSalidaResponse", fault = {
        @FaultAction(className = SalidaNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/obtenerNombreActividadDeSalida/Fault/salidaNoExisteException")
    })
    public String obtenerNombreActividadDeSalida(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws SalidaNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws ActividadNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicadores/PublicadorDepartamento/obtenerNombreProveedorDeActividadRequest", output = "http://publicadores/PublicadorDepartamento/obtenerNombreProveedorDeActividadResponse", fault = {
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://publicadores/PublicadorDepartamento/obtenerNombreProveedorDeActividad/Fault/actividadNoExisteException")
    })
    public String obtenerNombreProveedorDeActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception
    ;

}
