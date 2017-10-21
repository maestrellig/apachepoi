package it.maestrelli.export.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class Movimento {

	@XmlTransient
	private String idCodDipZuc;
	
	@XmlTransient
	private String idCodSocZuc;
	
	
	@XmlElement(name = "Giust")
	private  String giustificativo;

	@XmlElement(name = "NumMin")
	private  Integer numMinuti;
	
	@XmlElement(name = "GiornChiusS")
	private  String giornoChiusuraStraordinari = "N";
	
	@XmlElement(name = "Pippo")
	private  Double pippo;
	
	@XmlElement(name = "Data")
	private XMLGregorianCalendar datetime;
	
	public String getGiustificativo() {
		return giustificativo;
	}
	
	public void setGiustificativo(String giustificativo) {
		this.giustificativo = giustificativo;
	}
	public Integer getNumMinuti() {
		return numMinuti;
	}
	
	public void setNumMinuti(Integer numMinuti) {
		this.numMinuti = numMinuti;
	}
	
	public String getGiornoChiusuraStraordinari() {
		return giornoChiusuraStraordinari;
	}
	
	public void setGiornoChiusuraStraordinari(String giornoChiusuraStraordinari) {
		this.giornoChiusuraStraordinari = giornoChiusuraStraordinari;
	}
	
	public Double getPippo() {
		return pippo;
	}
	
	public void setPippo(Double pippo) {
		this.pippo = pippo;
	}
	
	public XMLGregorianCalendar getDatetime() {
		return datetime;
	}
	
	public void setDatetime(XMLGregorianCalendar datetime) {
		this.datetime = datetime;
	}
	
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
}
