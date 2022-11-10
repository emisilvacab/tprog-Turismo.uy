
package publicadores;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estado.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="estado">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="AGREGADA"/>
 *     <enumeration value="CONFIRMADA"/>
 *     <enumeration value="RECHAZADA"/>
 *     <enumeration value="FINALIZADA"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estado")
@XmlEnum
public enum Estado {

    AGREGADA,
    CONFIRMADA,
    RECHAZADA,
    FINALIZADA;

    public String value() {
        return name();
    }

    public static Estado fromValue(String v) {
        return valueOf(v);
    }

}
