package logica.datatypes;

import java.util.HashSet;
import java.util.Set;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTColecciones {
	private Set<String> setString = new HashSet<String>();
	
	public DTColecciones(){}

	public Set<String> getSetString() {
		return setString;
	}

	public void setSetString(Set<String> setString) {
		this.setString = setString;
	}

}
