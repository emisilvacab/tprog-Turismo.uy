
package publicadores;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtSalida complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtSalida">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="maxTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="alta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="fechaDTSalida" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="hora" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="lugarDTSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="linkImagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtSalida", propOrder = {
    "nombre",
    "maxTuristas",
    "alta",
    "fechaDTSalida",
    "hora",
    "lugarDTSalida",
    "linkImagen"
})
public class DtSalida {

    protected String nombre;
    protected int maxTuristas;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar alta;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaDTSalida;
    protected int hora;
    protected String lugarDTSalida;
    protected String linkImagen;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the maxTuristas property.
     * 
     */
    public int getMaxTuristas() {
        return maxTuristas;
    }

    /**
     * Sets the value of the maxTuristas property.
     * 
     */
    public void setMaxTuristas(int value) {
        this.maxTuristas = value;
    }

    /**
     * Gets the value of the alta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAlta() {
        return alta;
    }

    /**
     * Sets the value of the alta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAlta(XMLGregorianCalendar value) {
        this.alta = value;
    }

    /**
     * Gets the value of the fechaDTSalida property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaDTSalida() {
        return fechaDTSalida;
    }

    /**
     * Sets the value of the fechaDTSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaDTSalida(XMLGregorianCalendar value) {
        this.fechaDTSalida = value;
    }

    /**
     * Gets the value of the hora property.
     * 
     */
    public int getHora() {
        return hora;
    }

    /**
     * Sets the value of the hora property.
     * 
     */
    public void setHora(int value) {
        this.hora = value;
    }

    /**
     * Gets the value of the lugarDTSalida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarDTSalida() {
        return lugarDTSalida;
    }

    /**
     * Sets the value of the lugarDTSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarDTSalida(String value) {
        this.lugarDTSalida = value;
    }

    /**
     * Gets the value of the linkImagen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkImagen() {
        return linkImagen;
    }

    /**
     * Sets the value of the linkImagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkImagen(String value) {
        this.linkImagen = value;
    }

}
