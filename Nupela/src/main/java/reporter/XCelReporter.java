package reporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XCelReporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private CreationHelper createHelper;
	
	public void startResult(){

	}

	public XCelReporter startTestCase(String testCaseName, String testDescription, String category, String authors){

		workbook = new XSSFWorkbook();
		createHelper = workbook.getCreationHelper();
		sheet  = workbook.createSheet(testCaseName);
		XSSFRow header = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(CellStyle.SPARSE_DOTS);

		XSSFCell cell0 = header.createCell(0);
		cell0.setCellValue("#");
		cell0.setCellStyle(style);

		XSSFCell cell1 = header.createCell(1);
		cell1.setCellValue("Step Details");
		cell1.setCellStyle(style);

		XSSFCell cell2 = header.createCell(2);
		cell2.setCellValue("Status");
		cell2.setCellStyle(style);

		XSSFCell cell3 = header.createCell(3);
		cell3.setCellValue("Snapshot");
		cell3.setCellStyle(style);
		return this;

	}

	public void reportStep(String desc,String status, long snapNumber) {
		XSSFRow nextRow = sheet.createRow(sheet.getLastRowNum()+1);
		nextRow.createCell(0).setCellValue(sheet.getLastRowNum());
		nextRow.createCell(1).setCellValue(desc);
		nextRow.createCell(2).setCellValue(status);
		XSSFCell cell = nextRow.createCell(3);
		cell.setCellValue("Click here");
		XSSFHyperlink link = (XSSFHyperlink)createHelper
				.createHyperlink(Hyperlink.LINK_FILE);
		link.setAddress("./../../images/"+snapNumber+".jpg");
		cell.setHyperlink(link);

	}

	public void endResult(){		

	}

	public void endTestcase(String fileName){
		try {
			for (int i = 0; i < 3; i++) 
				sheet.autoSizeColumn(i);
			workbook.write(new FileOutputStream(new File(fileName+" - "+new Date().getTime()+".xlsx")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}