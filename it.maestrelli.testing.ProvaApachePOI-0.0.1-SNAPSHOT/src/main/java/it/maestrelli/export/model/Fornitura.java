 package it.maestrelli.export.model;


import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Fornitura")
public class Fornitura {
	
	private List<Dipendente> dipendente;

	@XmlElement(name = "Dipendente")
	public List<Dipendente> getDipendente() {
		return dipendente;
	}

	public void setDipendente(List<Dipendente> dipendente) {
		this.dipendente = dipendente;
	}
	
	public static int getNumeroDiMovimenti(Fornitura f)
	{
		int i = 0;
		for(Dipendente d:f.dipendente)
		{
			i += d.getMovimenti().size();
		}
		return i;
	}
	
	public static int getNumeroDiVociRetributive(Fornitura f)
	{
		int i = 0;
		for(Dipendente d:f.dipendente)
		{
			i+= d.getVociRetributive().size();
		}
		return i;
		
	}
}
