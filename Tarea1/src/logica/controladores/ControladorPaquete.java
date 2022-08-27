package logica.controladores;

import java.util.HashSet;
import java.util.Set;

import data.ManejadorPaquete;
import logica.Paquete;

public class ControladorPaquete implements IControladorPaquete{
	public ControladorPaquete() {
		
	}
	@Override
	public String[] mostrarSalidasAsociadas(String[] actividadesOfrecidas) {
		Set<String> salidas = new HashSet<String>();
		HashSet<String> acts = new HashSet<String>();
		for (String act: actividadesOfrecidas) {
			acts.add(act);
		}
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		for (Paquete paq: mp.getPaquetes().values()) {
			salidas.addAll(paq.obtenerNombresSalidasAsociadas(acts));
		}
		return salidas.toArray(new String[salidas.size()]);
	}


}
