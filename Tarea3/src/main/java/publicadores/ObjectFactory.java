
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

    private final static QName _CompraExisteException_QNAME = new QName("http://publicadores/", "compraExisteException");
    private final static QName _PaqueteNoExisteException_QNAME = new QName("http://publicadores/", "paqueteNoExisteException");
    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://publicadores/", "usuarioNoExisteException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicadores
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CompraExisteException }
     * 
     * @return
     *     the new instance of {@link CompraExisteException }
     */
    public CompraExisteException createCompraExisteException() {
        return new CompraExisteException();
    }

    /**
     * Create an instance of {@link PaqueteNoExisteException }
     * 
     * @return
     *     the new instance of {@link PaqueteNoExisteException }
     */
    public PaqueteNoExisteException createPaqueteNoExisteException() {
        return new PaqueteNoExisteException();
    }

    /**
     * Create an instance of {@link UsuarioNoExisteException }
     * 
     * @return
     *     the new instance of {@link UsuarioNoExisteException }
     */
    public UsuarioNoExisteException createUsuarioNoExisteException() {
        return new UsuarioNoExisteException();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CompraExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CompraExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "compraExisteException")
    public JAXBElement<CompraExisteException> createCompraExisteException(CompraExisteException value) {
        return new JAXBElement<>(_CompraExisteException_QNAME, CompraExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaqueteNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PaqueteNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "paqueteNoExisteException")
    public JAXBElement<PaqueteNoExisteException> createPaqueteNoExisteException(PaqueteNoExisteException value) {
        return new JAXBElement<>(_PaqueteNoExisteException_QNAME, PaqueteNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicadores/", name = "usuarioNoExisteException")
    public JAXBElement<UsuarioNoExisteException> createUsuarioNoExisteException(UsuarioNoExisteException value) {
        return new JAXBElement<>(_UsuarioNoExisteException_QNAME, UsuarioNoExisteException.class, null, value);
    }

}
