package logica.controladores;

import java.util.HashSet;
import java.util.Set;

import data.ManejadorPaquete;
import logica.Paquete;

public class ControladorPaquete implements IControladorPaquete{
	public ControladorPaquete() {
		
	}
	@Override
	public Set<String> mostrarSalidasAsociadas(Set<String> actividadesOfrecidas) {
		Set<String> salidas = new HashSet<String>();
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		for (Paquete paq: mp.getPaquetes().values()) {
			salidas.addAll(paq.obtenerNombresSalidasAsociadas(actividadesOfrecidas));
		}
		return salidas;
	}

}
