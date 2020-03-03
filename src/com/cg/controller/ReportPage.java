package com.cg.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.cg.dto.Claim;
import com.cg.dto.ShowClaimDetails;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ReportPage {
	public  void createPage(ShowClaimDetails userDetails, Claim claim) {
		System.out.println(userDetails.toString());
		Document document = new Document();
		System.out.println("**********inside document class");
		System.out.println("IN the pdf creation page");
	      try
	      {
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Report.pdf"));
	         document.open();
	        // document.add(new Paragraph("A Hello World PDF document."));
	         document.add( Chunk.NEWLINE );
	         document.add( Chunk.NEWLINE );

	         PdfPTable claimTable = new PdfPTable(9);
	         
	         
	         PdfPCell claim1 = new PdfPCell(new Paragraph("Claim Number"));
	         PdfPCell claim2 = new PdfPCell(new Paragraph("Claim Reason"));
	         PdfPCell claim3 = new PdfPCell(new Paragraph("Accident Location"));
	         PdfPCell claim4 = new PdfPCell(new Paragraph("Accident City"));
	         PdfPCell claim5 = new PdfPCell(new Paragraph("Accident State"));
	         PdfPCell claim6 = new PdfPCell(new Paragraph("Accident Zip"));
	         PdfPCell claim7 = new PdfPCell(new Paragraph("Claim Type"));
	         PdfPCell claim8 = new PdfPCell(new Paragraph("Policy Number"));
	         PdfPCell claim9 = new PdfPCell(new Paragraph("User Name"));
	         
	         claimTable.addCell(claim1);
	         claimTable.addCell(claim2);
	         claimTable.addCell(claim3);
	         claimTable.addCell(claim4);
	         claimTable.addCell(claim5);
	         claimTable.addCell(claim6);
	         claimTable.addCell(claim7);
	         claimTable.addCell(claim8);
	         claimTable.addCell(claim9);
	         //document.add(claimTable);
	         
	         document.add( Chunk.NEWLINE );
	         document.add( Chunk.NEWLINE );
	         PdfPCell claimd1 = new PdfPCell(new Paragraph(Integer.toString(claim.getClaimNumber())));
	         PdfPCell claimd2 = new PdfPCell(new Paragraph(claim.getClaimReason()));
	         PdfPCell claimd3 = new PdfPCell(new Paragraph(claim.getAccidentLocationStreet()));
	         PdfPCell claimd4 = new PdfPCell(new Paragraph(claim.getAccidentCity()));
	         PdfPCell claimd5 = new PdfPCell(new Paragraph(claim.getAccidentState()));
	         PdfPCell claimd6 = new PdfPCell(new Paragraph(Integer.toString(claim.getAccidentZip())));
	         PdfPCell claimd7 = new PdfPCell(new Paragraph(claim.getClaimType()));
	         PdfPCell claimd8 = new PdfPCell(new Paragraph(Integer.toString(claim.getPolicyNumber())));
	         PdfPCell claimd9 = new PdfPCell(new Paragraph(claim.getUserName()));
	         
	         claimTable.addCell(claimd1);
	         claimTable.addCell(claimd2);
	         claimTable.addCell(claimd3);
	         claimTable.addCell(claimd4);
	         claimTable.addCell(claimd5);
	         claimTable.addCell(claimd6);
	         claimTable.addCell(claimd7);
	         claimTable.addCell(claimd8);
	         claimTable.addCell(claimd9);
	         document.add(claimTable);
		     document.add(new Paragraph("This is claim Table"));
		     
		     PdfPTable pdfPTable = new PdfPTable(6);
	         document.add( Chunk.NEWLINE );
	         document.add( Chunk.NEWLINE );
	         PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Account Number"));
	         PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Policy Number"));
	         PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Claim Number"));
	         PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Policy Type"));
	         PdfPCell pdfPCell5 = new PdfPCell(new Paragraph("Policy Premium"));
	         PdfPCell pdfPCell6 = new PdfPCell(new Paragraph("Status"));
	  
	         //Add cells to table
	         pdfPTable.addCell(pdfPCell1);
	         pdfPTable.addCell(pdfPCell2);
	         pdfPTable.addCell(pdfPCell3);
	         pdfPTable.addCell(pdfPCell4);
	         pdfPTable.addCell(pdfPCell5);
	         pdfPTable.addCell(pdfPCell6);
	         //Create cells
	         PdfPCell val1 = new PdfPCell(new Paragraph(Integer.toString(userDetails.getAccountNumber())));
	         PdfPCell val2 = new PdfPCell(new Paragraph(Integer.toString(userDetails.getPolicyNumber())));
	         PdfPCell val3 = new PdfPCell(new Paragraph(Integer.toString(userDetails.getClaimNumber())));
	         PdfPCell val4 = new PdfPCell(new Paragraph(userDetails.getPolicyType()));
	         PdfPCell val5 = new PdfPCell(new Paragraph(Integer.toString(userDetails.getPolicyPremium())));
	         PdfPCell val6 = new PdfPCell(new Paragraph(userDetails.getStatus()));
	  
	         //Add cells to table
	         pdfPTable.addCell(val1);
	         pdfPTable.addCell(val2);
	         pdfPTable.addCell(val3);

	         pdfPTable.addCell(val4);
	         pdfPTable.addCell(val5);
	         pdfPTable.addCell(val6);
	  
	         //Add content to the document using Table objects.
	         document.add(pdfPTable);
	        	 
	     	/*PdfPCell val1 = new PdfPCell(new Paragraph(name));
		         PdfPCell val2 = new PdfPCell(new Paragraph(password));
		         PdfPCell val3 = new PdfPCell(new Paragraph(age));
		         pdfPTable.addCell(val1);
		         pdfPTable.addCell(val2);
		         pdfPTable.addCell(val3);
	     		//document.add(new Paragraph(name+" "+password+" "+age));
	        */ 
	         //document.add(pdfPTable);
	         document.close();
	         System.out.println("done with file");
	         writer.close();
	      } catch (DocumentException e)
	      {
	         e.printStackTrace();
	      } catch (FileNotFoundException e)
	      {
	         e.printStackTrace();
	      }
	 }


}
