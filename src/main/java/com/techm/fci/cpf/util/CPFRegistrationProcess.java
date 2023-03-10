package com.techm.fci.cpf.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CPFRegistrationProcess {

	static private int empNo;
	static private int empMobile;
	static private String empEmail;
	
	public static void main(String[] args) {

		try {
			
				File file = new File("C:\\CPF_Registration_excel_file\\10march 2023.xlsx"); 
				FileInputStream fis = new FileInputStream(file); 
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				XSSFSheet sheet = wb.getSheetAt(0); 
				Iterator<Row> itr = sheet.iterator();
				while (itr.hasNext()) {
					Row row = itr.next();
					if(row.getRowNum()!=0) {
					Iterator<Cell> cellIterator = row.cellIterator(); 
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cell.getCellType()) {
						 case Cell.CELL_TYPE_STRING:
							 if (cell.getColumnIndex()==2) {
								 empEmail=cell.getStringCellValue();
								 System.out.print(cell.getStringCellValue() + "\t\t\t");
							 }
							 
						 break; 
						 case Cell.CELL_TYPE_NUMERIC:
						 System.out.print(cell.getNumericCellValue() + "\t\t\t"); 
						 
						 if(cell.getColumnIndex()==1) {
							 empNo= (int)cell.getNumericCellValue();
						 }
						 if(cell.getColumnIndex()==3) {
							 empMobile=(int)cell.getNumericCellValue();
						 }
						 break;
						 default:
							 System.out.print("Default");
						}
					}
					System.out.println("");
			
			
			System.out.println("::::: Inside CPFRegistrationProcess ::::: Main Method ::::");
			Class.forName("oracle.jdbc.xa.client.OracleXADataSource");
			System.out.println("::::: Inside CPFRegistrationProcess ::::: Call forName method ::::");
			final Connection con = DriverManager.getConnection("jdbc:oracle:thin:@144.24.112.101:1521/PROD", "fcipayroll", "Ora#1403NdF");
			System.out.println("::::: Inside CPFRegistrationProcess ::::: Connection Created ::::");

			final Statement stmt = con.createStatement();
			final Statement stmt2 = con.createStatement();
			
			ResultSet rs = null;
			ResultSet rs2 = null;
			rs = stmt.executeQuery("SELECT * FROM fcipayroll.cpf_registered_users where emp_num in ("+empNo+")");
			while (!rs.next()) {
				final String ValidationMessage = DataValidation(rs);
				if (ValidationMessage.equalsIgnoreCase("")) {
					
					 final Statement stmt3 = con.createStatement();
					 ResultSet rs3 = null;
					 rs3 = stmt.executeQuery("select max(reg_id) from cpf_registered_users");
					 while (rs3.next()) {
						 System.out.println("Max Reg Id "+rs3.getDouble(1));
						 final Statement stmt4 = con.createStatement();
						 ResultSet rs4 = null;
						 rs4 = stmt4.executeQuery("select emp_num,emp_first_name,emp_middle_name,emp_last_name,uan from pay_emp_mast where emp_num in ("+empNo+")");
						 while (rs4.next()) {
							 System.out.println("Emp First Name - "+rs3.getString(1));
							 System.out.println("Emp Middle Name - "+rs3.getString(2));
							 System.out.println("Emp Last Name - "+rs3.getString(3));
							 System.out.println("Emp UAN Number - "+rs3.getDouble(4));
							 
						 }
					 }
				}
			}
			/*
			 * while(rs.next()) {
			 * 
			 * }
			 */
			con.close();
			}
		}	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	static String DataValidation(final ResultSet rs) {
		String error_msg = "";
		try {
			if (rs.getString(1) == null) {
				error_msg = "PIN is blank";
			}
			if (rs.getString(2) == null) {
				error_msg = "Message is blank";
			}
			if (rs.getString(3) == null) {
				error_msg = "Mobile Number is blank";
			}
			if (rs.getString(4) == null) {
				error_msg = "Signature is blank";
			}
			if (rs.getString(5) == null) {
				error_msg = "dlt_entity_id is blank";
			}
			if (rs.getString(6) == null) {
				error_msg = "dlt_template_id is blank";
			}
		} catch (SQLException ex) {
			Logger.getLogger(CPFSendSMSUtility.class.getName()).log(Level.SEVERE, null, ex);
		}
		return error_msg;
	}
}
