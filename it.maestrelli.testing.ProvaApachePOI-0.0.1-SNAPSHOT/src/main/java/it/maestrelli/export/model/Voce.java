package it.maestrelli.export.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
public class Voce {
	
	@XmlTransient
	private String idCodDipZuc;
	
	@XmlTransient
	private String idCodSocZuc;

	@XmlElement(name = "Topolino")
	private  String topolino;

	@XmlElement(name = "NumMin")
	private  Integer numMinuti;
	
	@XmlElement(name = "Pluto")
	private  Double pippo;
	
	@XmlElement(name = "DataInit")
	private XMLGregorianCalendar datetimeInit;
	
	@XmlElement(name = "DataFine")
	private XMLGregorianCalendar datetimeFine;
	
	public String getIdCodDipZuc() {
		return idCodDipZuc;
	}
	
	public void setIdCodDipZuc(String idCodDipZuc) {
		this.idCodDipZuc = idCodDipZuc;
	}

	public String getIdCodSocZuc() {
		return idCodSocZuc;
	}

	public void setIdCodSocZuc(String idCodSocZuc) {
		this.idCodSocZuc = idCodSocZuc;
	}

	public String getTopolino() {
		return topolino;
	}

	public void setTopolino(String topolino) {
		this.topolino = topolino;
	}

	public Integer getNumMinuti() {
		return numMinuti;
	}

	public void setNumMinuti(Integer numMinuti) {
		this.numMinuti = numMinuti;
	}

	public Double getPippo() {
		return pippo;
	}

	public void setPippo(Double pippo) {
		this.pippo = pippo;
	}

	public XMLGregorianCalendar getDatetimeInit() {
		return datetimeInit;
	}

	public void setDatetimeInit(XMLGregorianCalendar datetimeInit) {
		this.datetimeInit = datetimeInit;
	}

	public XMLGregorianCalendar getDatetimeFine() {
		return datetimeFine;
	}

	public void setDatetimeFine(XMLGregorianCalendar datetimeFine) {
		this.datetimeFine = datetimeFine;
	}

	
}
