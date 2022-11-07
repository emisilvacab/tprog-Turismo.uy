
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
    private final static QName _CategoriaNoExisteException_QNAME = new QName("http://publicadores/", "categoriaNoExisteException");
    private final static QName _DepartamentoNoExisteException_QNAME = new QName("http://publicadores/", "departamentoNoExisteException");

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
     * Create an instance of {@link DtColecciones }
     * 
     * @return
     *     the new instance of {@link DtColecciones }
     */
    public DtColecciones createDtColecciones() {
        return new DtColecciones();
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

}
