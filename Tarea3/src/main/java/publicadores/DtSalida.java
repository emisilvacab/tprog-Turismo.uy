
package publicadores;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtSalida complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad nombre.
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
     * Define el valor de la propiedad nombre.
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
     * Obtiene el valor de la propiedad maxTuristas.
     * 
     */
    public int getMaxTuristas() {
        return maxTuristas;
    }

    /**
     * Define el valor de la propiedad maxTuristas.
     * 
     */
    public void setMaxTuristas(int value) {
        this.maxTuristas = value;
    }

    /**
     * Obtiene el valor de la propiedad alta.
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
     * Define el valor de la propiedad alta.
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
     * Obtiene el valor de la propiedad fechaDTSalida.
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
     * Define el valor de la propiedad fechaDTSalida.
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
     * Obtiene el valor de la propiedad hora.
     * 
     */
    public int getHora() {
        return hora;
    }

    /**
     * Define el valor de la propiedad hora.
     * 
     */
    public void setHora(int value) {
        this.hora = value;
    }

    /**
     * Obtiene el valor de la propiedad lugarDTSalida.
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
     * Define el valor de la propiedad lugarDTSalida.
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
     * Obtiene el valor de la propiedad linkImagen.
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
     * Define el valor de la propiedad linkImagen.
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