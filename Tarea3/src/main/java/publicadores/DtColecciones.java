
package publicadores;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtColecciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtColecciones">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="setString" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="setDtActividad" type="{http://publicadores/}dtActividad" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="setDtSalida" type="{http://publicadores/}dtSalida" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="setDtPaquete" type="{http://publicadores/}dtPaquete" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="setDtInscripcion" type="{http://publicadores/}dtInscripcion" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="setDtCompra" type="{http://publicadores/}dtCompra" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="setDtUsuario" type="{http://publicadores/}dtUsuario" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtColecciones", propOrder = {
    "setString",
    "setDtActividad",
    "setDtSalida",
    "setDtPaquete",
    "setDtInscripcion",
    "setDtCompra",
    "setDtUsuario"
})
public class DtColecciones {

    @XmlElement(nillable = true)
    protected List<String> setString;
    @XmlElement(nillable = true)
    protected List<DtActividad> setDtActividad;
    @XmlElement(nillable = true)
    protected List<DtSalida> setDtSalida;
    @XmlElement(nillable = true)
    protected List<DtPaquete> setDtPaquete;
    @XmlElement(nillable = true)
    protected List<DtInscripcion> setDtInscripcion;
    @XmlElement(nillable = true)
    protected List<DtCompra> setDtCompra;
    @XmlElement(nillable = true)
    protected List<DtUsuario> setDtUsuario;

    /**
     * Gets the value of the setString property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the setString property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the setString property.
     */
    public List<String> getSetString() {
        if (setString == null) {
            setString = new ArrayList<>();
        }
        return this.setString;
    }

    /**
     * Gets the value of the setDtActividad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the setDtActividad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetDtActividad().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtActividad }
     * 
     * 
     * @return
     *     The value of the setDtActividad property.
     */
    public List<DtActividad> getSetDtActividad() {
        if (setDtActividad == null) {
            setDtActividad = new ArrayList<>();
        }
        return this.setDtActividad;
    }

    /**
     * Gets the value of the setDtSalida property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the setDtSalida property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetDtSalida().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtSalida }
     * 
     * 
     * @return
     *     The value of the setDtSalida property.
     */
    public List<DtSalida> getSetDtSalida() {
        if (setDtSalida == null) {
            setDtSalida = new ArrayList<>();
        }
        return this.setDtSalida;
    }

    /**
     * Gets the value of the setDtPaquete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the setDtPaquete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetDtPaquete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPaquete }
     * 
     * 
     * @return
     *     The value of the setDtPaquete property.
     */
    public List<DtPaquete> getSetDtPaquete() {
        if (setDtPaquete == null) {
            setDtPaquete = new ArrayList<>();
        }
        return this.setDtPaquete;
    }

    /**
     * Gets the value of the setDtInscripcion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the setDtInscripcion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetDtInscripcion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtInscripcion }
     * 
     * 
     * @return
     *     The value of the setDtInscripcion property.
     */
    public List<DtInscripcion> getSetDtInscripcion() {
        if (setDtInscripcion == null) {
            setDtInscripcion = new ArrayList<>();
        }
        return this.setDtInscripcion;
    }

    /**
     * Gets the value of the setDtCompra property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the setDtCompra property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetDtCompra().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtCompra }
     * 
     * 
     * @return
     *     The value of the setDtCompra property.
     */
    public List<DtCompra> getSetDtCompra() {
        if (setDtCompra == null) {
            setDtCompra = new ArrayList<>();
        }
        return this.setDtCompra;
    }

    /**
     * Gets the value of the setDtUsuario property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the setDtUsuario property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetDtUsuario().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtUsuario }
     * 
     * 
     * @return
     *     The value of the setDtUsuario property.
     */
    public List<DtUsuario> getSetDtUsuario() {
        if (setDtUsuario == null) {
            setDtUsuario = new ArrayList<>();
        }
        return this.setDtUsuario;
    }

}
