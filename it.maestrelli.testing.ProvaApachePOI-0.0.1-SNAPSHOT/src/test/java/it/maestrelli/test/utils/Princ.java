package it.maestrelli.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilterColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;

import it.maestrelli.export.model.Dipendente;
import it.maestrelli.export.model.Fornitura;
import it.maestrelli.export.model.Movimento;
import it.maestrelli.export.model.Voce;

public class Princ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fornitura forn= new Fornitura();
		ArrayList<Dipendente> dip = new ArrayList<Dipendente>();		
		for(int j = 0;j<3;j++){
			Dipendente d = new Dipendente();
			d.setCodAziendaUfficiale("soc"+j);
			d.setCodDipendenteUfficiale("dip"+j);
			ArrayList<Voce> voc = new ArrayList<Voce>();
			ArrayList<Movimento> mov = new ArrayList<Movimento>();
			for(int i = 0;i<5;i++)
			{
				Movimento m = new Movimento();
				m.setIdCodDipZuc(d.getCodDipendenteUfficiale());
				m.setIdCodSocZuc(d.getCodAziendaUfficiale());
				Voce v = new Voce();
				v.setIdCodDipZuc(d.getCodDipendenteUfficiale());
				v.setIdCodSocZuc(d.getCodAziendaUfficiale());
				try {
					m.setDatetime(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
					v.setDatetimeFine(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
					v.setDatetimeInit(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				m.setGiornoChiusuraStraordinari("N");
				m.setNumMinuti(480);
				m.setPippo(4.56);
				m.setGiustificativo("Giustificativo");
				v.setNumMinuti(60);
				v.setPippo(100.45);
				v.setTopolino("Topo Lino");
				mov.add(m);
				voc.add(v);
			}
			d.setMovimenti(mov);
			d.setVociRetributive(voc);
			dip.add(d);
		}
		forn.setDipendente(dip);
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Fornitura.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(forn, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");Workbook wb = new XSSFWorkbook())
		{
		    XSSFSheet sheetA = (XSSFSheet)wb.createSheet("Movimenti");
//		    XSSFSheet sheetB = (XSSFSheet)wb.createSheet("VociRetributive");

		    
		    /*Impostazioni per i movimenti*/		    
		    /* Create an object of type XSSFTable */
		    
		    XSSFTable my_table = sheetA.createTable();
		                
		        /* get CTTable object*/
		    CTTable cttable = my_table.getCTTable();
		    
		    /* Let us define the required Style for the table */    
		    CTTableStyleInfo table_style = cttable.addNewTableStyleInfo();
		    table_style.setName("TableStyleMedium9");   
		        
		        /* Set Table Style Options */
		    table_style.setShowColumnStripes(false); //showColumnStripes=0
		    table_style.setShowRowStripes(true); //showRowStripes=1
		    
		    /* Define the data range including headers */
		    AreaReference my_data_range = new AreaReference(new CellReference(0, 0), new CellReference(Fornitura.getNumeroDiMovimenti(forn), 6));
		    
		    /* Set Range to the Table */
		    cttable.setRef(my_data_range.formatAsString());
		    cttable.setDisplayName("Movimenti");      /* this is the display name of the table */
		    cttable.setName("Movimenti");    /* This maps to "displayName" attribute in <table>, OOXML */            
		    cttable.setId(1L); //id attribute against table as long value
		             
		    CTTableColumns columns = cttable.addNewTableColumns();
		    CTAutoFilter autoFilter = cttable.addNewAutoFilter();
		   
		    columns.setCount(7L); //define number of columns
	
		        /* Define Header Information for the Table */
		    for (int i = 0; i < 7; i++)
		    {
		    CTTableColumn column = columns.addNewTableColumn();   
	      	  switch(i)
	      	  {
	      	  	case 0:
	      	  		column.setName("idCodDipZuc");
	      	  		break;
	      	  	case 1:
	      	  		column.setName("idCodSocZuc");
	      	  		break;
	      	  	case 2:
	      	  		column.setName("giustificativo");
	      	  		break;
	      	  	case 3:
	      	  		column.setName("NumMinuti");
	      	  		break;
	      	  	case 4:
	      	  		column.setName("GiornChiusS");
	      	  		break;
	      	  	case 5:
	      	  		column.setName("Pippo");
	      	  		break;
	      	  	case 6:
	      	  		column.setName("Data");
	      	  		break;
	
	      	  }
	      	  
	      	 
	      	  column.setId(i+1);
	      	  CTFilterColumn filter = autoFilter.addNewFilterColumn();
	      	  filter.setColId(i+1);
	      	  filter.setShowButton(true);
	      	  sheetA.autoSizeColumn(i);
	      	  sheetA.setColumnWidth(i, sheetA.getColumnWidth(i) + 1024);
	      	  
		    }
		    
		    List<Dipendente> ld = forn.getDipendente();
		    int numRowMov = -1;
		    /*Inizializzazione HEADER*/
		    XSSFRow rowAA = sheetA.createRow(++numRowMov);
		    for(int j=0;j<7;j++){
    			XSSFCell localXSSFCellFD = rowAA.createCell(j);
    			creaHeader(j, localXSSFCellFD);		
		    }
		    
//		    int numRowVoc = 0;
		    ;
		    for(Dipendente d:ld)
		    {
		    	List<Movimento> lm = d.getMovimenti();
		    	//List<Voce> lv = d.getVociRetributive();
		    	for(Movimento m:lm)
		    	{	    		
		    		XSSFRow rowA = sheetA.createRow(++numRowMov);
		    		for(int j = 0; j < 7; j++)
		    		{
		    			XSSFCell localXSSFCell = rowA.createCell(j);
						creaMovimento(wb, d, m, j, localXSSFCell);
		    		}
		    	}
//		    	for(Voce v:lv)
//		    	{
//		    		
//		    	}
		    	
		    }
		    wb.write(fileOut);
	        fileOut.flush();
		    fileOut.close();
	        wb.close();
	        
	        System.out.println("**** LEGGO IL FILE SCRITTO ****");
	        /*Aprire un altro file, leggerlo e generare l'xml*/

            FileInputStream excelFile = new FileInputStream(new File("workbook.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            XSSFSheet foglioMov = workbook.getSheet("Movimenti");
            List<XSSFTable> ltab = foglioMov.getTables();
            HashMap<String,Dipendente> hmsd = new HashMap<String,Dipendente>();
            for(XSSFTable xsfftab:ltab)
            {
            	if("Movimenti".equals(xsfftab.getName()))
            	{
            		int iIni =xsfftab.getStartCellReference().getRow();
            		int jIni =xsfftab.getStartCellReference().getCol();
            		int iFin =xsfftab.getEndCellReference().getRow();
            		int jFin =xsfftab.getEndCellReference().getCol();
            		CTTableColumns cttc = xsfftab.getCTTable().getTableColumns();
            		for(int i = iIni+1;i<=iFin;i++)
            		{
            			String codDipZuc = null;
            			String codSocZuc = null;
            			String giustificativo = null;
            			Integer numMin = null;
            			String gioChiu = null;
            			Double pippo = null;
            			java.util.Date laData = null;
            			for(int j=jIni;j<=jFin;j++)
            			{
            				System.out.println(jFin);
            				switch(cttc.getTableColumnArray(j).getName())
            				{
            					case "idCodDipZuc":
            						System.out.println(i+","+j+": "+foglioMov.getRow(i).getCell(j).getStringCellValue());
            						codDipZuc = foglioMov.getRow(i).getCell(j).getStringCellValue();           						
            						break;
            					case "idCodSocZuc":
            						System.out.println(i+","+j+": "+foglioMov.getRow(i).getCell(j).getStringCellValue());
            						codSocZuc = foglioMov.getRow(i).getCell(j).getStringCellValue();
            						break;
            					case "giustificativo":
            						System.out.println(i+","+j+": "+foglioMov.getRow(i).getCell(j).getStringCellValue());
            						giustificativo = foglioMov.getRow(i).getCell(j).getStringCellValue();
            						break;
            					case "NumMinuti":
            						System.out.println(i+","+j+": "+foglioMov.getRow(i).getCell(j).getNumericCellValue());
            						numMin = (int)foglioMov.getRow(i).getCell(j).getNumericCellValue();
            						break;
            					case "GiornChiusS":
            						System.out.println(i+","+j+": "+foglioMov.getRow(i).getCell(j).getStringCellValue());
            						gioChiu = foglioMov.getRow(i).getCell(j).getStringCellValue();
            						break;
            					case "Pippo":
            						System.out.println(i+","+j+": "+foglioMov.getRow(i).getCell(j).getNumericCellValue());
            						pippo = foglioMov.getRow(i).getCell(j).getNumericCellValue();
            						break;
            					case "Data":
            						System.out.println(i+","+j+": "+foglioMov.getRow(i).getCell(j).getDateCellValue());
            						laData = foglioMov.getRow(i).getCell(j).getDateCellValue();
            						break;
            				}

            			}
        	        	if(!hmsd.containsKey(codSocZuc+";"+codDipZuc))
        	        	{
        	        		Dipendente dip0 = new Dipendente();
        	        		dip0.setCodAziendaUfficiale(codSocZuc);
        	        		dip0.setCodDipendenteUfficiale(codDipZuc);
        	        		dip0.setMovimenti(new ArrayList<Movimento>());
        	        		hmsd.put(codSocZuc+";"+codDipZuc,dip0);
        	        	}	        	
        	        	Movimento e = new Movimento();
        	        	GregorianCalendar gc = GregorianCalendar.from(ZonedDateTime.now());
        	        	e.setIdCodDipZuc(codDipZuc);
        	        	e.setIdCodSocZuc(codSocZuc);
        	        	e.setGiornoChiusuraStraordinari(gioChiu);
        	        	e.setGiustificativo(giustificativo);
        	        	e.setNumMinuti(numMin);
        	        	e.setPippo(pippo);
        	        	gc.setTime(laData);
        	        	try {
							e.setDatetime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
							
						} catch (DatatypeConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	        	hmsd.get(codSocZuc+";"+codDipZuc).getMovimenti().add(e);	            			
            		}
            			
            	}
            }
            System.out.println("**** SCRIVO IL NUOVO XML ****");
            Fornitura forni = new Fornitura();
            forni.setDipendente(new ArrayList<Dipendente>(hmsd.values()));
    		JAXBContext jc12;
    		try {
    			jc12 = JAXBContext.newInstance(Fornitura.class);
    			Marshaller marshaller = jc12.createMarshaller();
    			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    			marshaller.marshal(forni, System.out);
    		} catch (JAXBException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
//	        for(/*TUTTE LE RIGHE DI VOCI RETRIBUTIVA*/)
//	        {
//	        	if(!hmIDDip.containsKey("idSoc;idDip"))
//	        	{
//	        		Dipendente dip = new Dipendente();
//	        		dip.setCodAziendaUfficiale("idSoc");
//	        		dip.setCodDipendenteUfficiale("idDip");
//	        		dip.setVociRetributive(new ArrayList<Voce>());
//	        		
//	        	}else if(hmIDDip.get("idSoc;idDip").getVociRetributive()==null)
//	        		hmIDDip.get("idSoc;idDip").setVociRetributive(new ArrayList<Voce>());
//	        	hmIDDip.get("idSoc;idDip").getVociRetributive().add(e);
//	        }
	        
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	    
         
		
	}

	private static void creaMovimento(Workbook wb, Dipendente d, Movimento m, int j, XSSFCell localXSSFCell) {
		switch(j)
		  {
		  	case 0:
		  		localXSSFCell.setCellValue(d.getCodDipendenteUfficiale());
		  		break;
		  	case 1:
		  		localXSSFCell.setCellValue(d.getCodAziendaUfficiale());
		  		break;
		  	case 2:
		  		localXSSFCell.setCellValue(m.getGiustificativo());
		  		break;
		  	case 3:
		  		localXSSFCell.setCellValue(m.getNumMinuti());
		  		break;
		  	case 4:
		  		localXSSFCell.setCellValue(m.getGiornoChiusuraStraordinari());
		  		break;
		  	case 5:
		  		localXSSFCell.setCellValue(m.getPippo());
		  		break;
		  	case 6:
		  		localXSSFCell.setCellValue(m.getDatetime().toGregorianCalendar());
		  		CellStyle cs  = wb.createCellStyle();
		  		CreationHelper ch = wb.getCreationHelper();
		  		cs.setDataFormat(ch.createDataFormat().getFormat("dd/mm/yyyy hh:mm"));
		  		localXSSFCell.setCellStyle(cs);
		  		break;
		  }
	}

	private static void creaHeader(int j, XSSFCell localXSSFCell) {
		switch(j)
		  {
		  	case 0:
		  		localXSSFCell.setCellValue("idCodDipZuc");
		  		break;
		  	case 1:
		  		localXSSFCell.setCellValue("idCodSocZuc");
		  		break;
		  	case 2:
		  		localXSSFCell.setCellValue("giustificativo");
		  		break;
		  	case 3:
		  		localXSSFCell.setCellValue("NumMinuti");
		  		break;
		  	case 4:
		  		localXSSFCell.setCellValue("GiornChiusS");
		  		break;
		  	case 5:
		  		localXSSFCell.setCellValue("Pippo");
		  		break;
		  	case 6:
		  		localXSSFCell.setCellValue("Data");
		  		break;
 		
		  }
	}

}
