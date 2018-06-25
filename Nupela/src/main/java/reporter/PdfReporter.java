package reporter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.openqa.selenium.WebDriver;

public class PdfReporter{

	static int pageSegment=0;	
	PDDocument document;
	PDPage page = null;
	PDPageContentStream	contentStream = null ;
	PDFont font = PDType1Font.HELVETICA_BOLD;

	
	public void startResult(){

	}

	public PdfReporter startTestCase(String testCaseName, String testDescription, String category, String authors){
		try {
			document = new PDDocument();
			// Write the header -- the first page
			page = new PDPage();
			document.addPage(page);
			contentStream = new PDPageContentStream(document, page);
			contentStream.setFont(font, 18);
			contentStream.beginText();	
			contentStream.moveTextPositionByAmount(120,  450);	
			contentStream.drawString("Test Case Name :"+testCaseName);
			contentStream.endText();
			contentStream.beginText();	
			contentStream.moveTextPositionByAmount(120,  430);	
			contentStream.drawString("Test Case Description :"+testDescription);
			contentStream.endText();
			contentStream.beginText();	
			contentStream.moveTextPositionByAmount(120,  410);	
			contentStream.drawString("Test Cateogy :"+category);
			contentStream.endText();
			contentStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}



	public void reportStep(String desc,String status, long snapNumber) {

		try {
			// Create a document and add a page to it
			float scale = 0.44f; // alter this value to set the image size
			// Create a new font object selecting one of the PDF base fonts
			// Start a new content stream which will "hold" the to be created content	
			page = new PDPage();
			document.addPage(page);
			contentStream = new PDPageContentStream(document, page);
			contentStream.setFont(font, 14);
			desc = status.toUpperCase()+" : " + desc;
			if(desc.length() > 80){

				int numWraps = (desc.length() / 80)+1;
				int startPos = 0;
				int endPos = 80;
				int lastIndex;
				for (int i = 1; i<=numWraps; i++) {

					// find the position of the white spaces from the last position
					if(endPos != desc.length()){
						lastIndex = desc.substring(startPos, endPos).lastIndexOf("\n");
						if(lastIndex == -1)
							lastIndex = desc.substring(startPos, endPos).lastIndexOf(" ");
					}else
						lastIndex = -1;

					// if there is no white spaces (specially at the end line. lastIndex will be end position
					if(lastIndex == -1){
						lastIndex = desc.length();
						endPos = desc.length();
					}else{
						lastIndex = startPos + lastIndex;
					}


					contentStream.beginText();	
					contentStream.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - (42+(20*(i-1))));
					//contentStream.moveTextPositionByAmount(20,  750);	
					contentStream.drawString(desc.substring(startPos, lastIndex));
					contentStream.endText();

					startPos = lastIndex+1;
					if(startPos+80 >= desc.length() )
						endPos = desc.length();
					else
						endPos = startPos+80;


				}				

			}else {
				contentStream.beginText();	
				contentStream.moveTextPositionByAmount(20,  750);	
				contentStream.drawString(desc);
				contentStream.endText();
			}
			BufferedImage awtImage = ImageIO.read(new File("./reports/images/"+snapNumber+".jpg"));
			PDPixelMap ximage = new PDPixelMap(document, awtImage);
			contentStream.drawXObject(ximage, 20, 350, ximage.getWidth()*scale, ximage.getHeight()*scale);
			contentStream.close();

		}catch(Exception e) {

		}
	}

	public void endResult(){		

	}

	public void endTestcase(String fileName){
		try {
			try {
				//addHeaderAndFooter(testCaseName + " - " +testDescription );
				document.save(new File(fileName+" - "+new Date().getTime()+".pdf"));
			} catch (COSVisitorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addHeaderAndFooter(String headerMsg) {
		try{
			List allPages = document.getDocumentCatalog().getAllPages();
			//PDFont font = PDType1Font.HELVETICA_BOLD;
			for( int i=0; i<allPages.size(); i++ )
			{
				PDPage page = (PDPage)allPages.get( i );
				PDRectangle pageSize = page.findMediaBox();
				PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true,true);
				PDFont font = PDType1Font.TIMES_ROMAN;
				float fontSize = 15.0f;
				contentStream.beginText();
				// set font and font size
				contentStream.setFont( font, fontSize);
				contentStream.moveTextPositionByAmount(700, 1150);
				contentStream.drawString(headerMsg);
				contentStream.endText();

				//contentStream.
				contentStream.close();}

		}catch(Exception e) {
			
		}

	}



}
