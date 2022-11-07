
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
@WebService(name = "PublicadorDepartamento", targetNamespace = "http://publicadores/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PublicadorDepartamento {


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

}
