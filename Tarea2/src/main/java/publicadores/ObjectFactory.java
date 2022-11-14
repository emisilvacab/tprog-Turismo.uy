
package publicadores;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicadores package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ActividadNoExisteException_QNAME = new QName("http://publicadores/", "actividadNoExisteException");
    private final static QName _ActividadPerteneceAPaqueteException_QNAME = new QName("http://publicadores/", "actividadPerteneceAPaqueteException");
    private final static QName _ActividadTieneSalidasVigentesException_QNAME = new QName("http://publicadores/", "actividadTieneSalidasVigentesException");
    private final static QName _CategoriaNoExisteException_QNAME = new QName("http://publicadores/", "categoriaNoExisteException");
    private final static QName _DepartamentoNoExisteException_QNAME = new QName("http://publicadores/", "departamentoNoExisteException");
    private final static QName _ProveedorNoExisteException_QNAME = new QName("http://publicadores/", "proveedorNoExisteException");
    private final static QName _SalidaNoExisteException_QNAME = new QName("http://publicadores/", "salidaNoExisteException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicadores
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ActividadNoExisteException }
     * 
     * @return
     *     the new instance of {@link ActividadNoExisteException }
     */
    public ActividadNoExisteException createActividadNoExisteException() {
        return new ActividadNoExisteException();
    }

    /**
     * Create an instance of {@link ActividadPerteneceAPaqueteException }
     * 
     * @return
     *     the new instance of {@link ActividadPerteneceAPaqueteException }
     */
    public ActividadPerteneceAPaqueteException createActividadPerteneceAPaqueteException() {
        return new ActividadPerteneceAPaqueteException();
    }

    /**
     * Create an instance of {@link ActividadTieneSalidasVigentesException }
     * 
     * @return
     *     the new instance of {@link ActividadTieneSalidasVigentesException }
     */
    public ActividadTieneSalidasVigentesException createActividadTieneSalidasVigentesException() {
        return new ActividadTieneSalidasVigentesException();
    }

    /**
     * Create an instance of {@link CategoriaNoExisteException }
     * 
     * @return
     *     the new instance of {@link CategoriaNoExisteException }
     */
    public CategoriaNoExisteException createCategoriaNoExisteException() {
        return new CategoriaNoExisteException();
    }

    /**
     * Create an instance of {@link DepartamentoNoExisteException }
     * 
     * @return
     *     the new instance of {@link DepartamentoNoExisteException }
     */
    public DepartamentoNoExisteException createDepartamentoNoExisteException() {
        return new DepartamentoNoExisteException();
    }

    /**
     * Create an instance of {@link ProveedorNoExisteException }
     * 
     * @return
     *     the new instance of {@link ProveedorNoExisteException }
     */
    public ProveedorNoExisteException createProveedorNoExisteException() {
        return new ProveedorNoExisteException();
    }

    /**
     * Create an instance of {@link SalidaNoExisteException }
     * 
     * @return
     *     the new instance of {@link SalidaNoExisteException }
     */
    public SalidaNoExisteException createSalidaNoExisteException() {
        return new SalidaNoExisteException();
    }

    /**
     * Create an instance of {@link DtActividad }
     * 
     * @return
     *     the new instance of {@link DtActividad }
     */
    public DtActividad createDtActividad() {
        return new DtActividad();
    }

    /**
     * Create an instance of {@link DtColecciones }
     * 
     * @return
     *     the new instance of {@link DtColecciones }
     */
    public DtColecciones createDtColecciones() {
        return new DtColecciones();
    }

    /**
     * Create an instance of {@link DtCompra }
     * 
     * @return
     *     the new instance of {@link DtCompra }
     */
    public DtCompra createDtCompra() {
        return new DtCompra();
    }

    /**
     * Create an instance of {@link DtInscripcion }
     * 
     * @return
     *     the new instance of {@link DtInscripcion }
     */
    public DtInscripcion createDtInscripcion() {
        return new DtInscripcion();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtProveedor }
     * 
     * @return
     *     the new instance of {@link DtProveedor }
     */
    public DtProveedor createDtProveedor() {
        return new DtProveedor();
    }

    /**
     * Create an instance of {@link DtSalida }
     * 
     * @return
     *     the new instance of {@link DtSalida }
     */
    public DtSalida createDtSalida() {
        return new DtSalida();
    }

    /**
     * Create an instance of {@link DtTurista }
     * 
     * @return
     *     the new instance of {@link DtTurista }
     */
    public DtTurista createDtTurista() {
        return new DtTurista();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActividadNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "actividadNoExisteException")
    public JAXBElement<ActividadNoExisteException> createActividadNoExisteException(ActividadNoExisteException value) {
        return new JAXBElement<>(_ActividadNoExisteException_QNAME, ActividadNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadPerteneceAPaqueteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActividadPerteneceAPaqueteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "actividadPerteneceAPaqueteException")
    public JAXBElement<ActividadPerteneceAPaqueteException> createActividadPerteneceAPaqueteException(ActividadPerteneceAPaqueteException value) {
        return new JAXBElement<>(_ActividadPerteneceAPaqueteException_QNAME, ActividadPerteneceAPaqueteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadTieneSalidasVigentesException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActividadTieneSalidasVigentesException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "actividadTieneSalidasVigentesException")
    public JAXBElement<ActividadTieneSalidasVigentesException> createActividadTieneSalidasVigentesException(ActividadTieneSalidasVigentesException value) {
        return new JAXBElement<>(_ActividadTieneSalidasVigentesException_QNAME, ActividadTieneSalidasVigentesException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CategoriaNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "categoriaNoExisteException")
    public JAXBElement<CategoriaNoExisteException> createCategoriaNoExisteException(CategoriaNoExisteException value) {
        return new JAXBElement<>(_CategoriaNoExisteException_QNAME, CategoriaNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepartamentoNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DepartamentoNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "departamentoNoExisteException")
    public JAXBElement<DepartamentoNoExisteException> createDepartamentoNoExisteException(DepartamentoNoExisteException value) {
        return new JAXBElement<>(_DepartamentoNoExisteException_QNAME, DepartamentoNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProveedorNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProveedorNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "proveedorNoExisteException")
    public JAXBElement<ProveedorNoExisteException> createProveedorNoExisteException(ProveedorNoExisteException value) {
        return new JAXBElement<>(_ProveedorNoExisteException_QNAME, ProveedorNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SalidaNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SalidaNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "salidaNoExisteException")
    public JAXBElement<SalidaNoExisteException> createSalidaNoExisteException(SalidaNoExisteException value) {
        return new JAXBElement<>(_SalidaNoExisteException_QNAME, SalidaNoExisteException.class, null, value);
    }

}
