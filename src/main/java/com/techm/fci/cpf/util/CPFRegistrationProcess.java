package com.techm.fci.cpf.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CPFRegistrationProcess {

	static private String empNo;
	static private String empMobile;
	static private String empEmail;
	static private int regIdCounter;
	static private List<String> passUpdateEmpNo=null;
	static private List<String> noMobileEmailEmpNo=null;

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try {
			LocalDateTime startDateTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
			System.out.println("::::: Inside CPFRegistrationProcess ::::: Main Method :::: Start Time ::: "+startDateTime);
			Class.forName("oracle.jdbc.xa.client.OracleXADataSource");
			System.out.println("::::: Inside CPFRegistrationProcess ::::: Call forName method ::::");
			con = DriverManager.getConnection("jdbc:oracle:thin:@144.24.112.101:1521/PROD", "fcipayroll",
					"Ora#1403NdF");
			con.setAutoCommit(false);
			System.out.println("::::: Inside CPFRegistrationProcess ::::: Connection Created ::::");

			final Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("select max(reg_id) from cpf_registered_users");
			while (rs2.next()) {
				regIdCounter = rs2.getInt(1);
				System.out.println("Max Reg Id :::: " + regIdCounter);
			}

			passUpdateEmpNo=new ArrayList<String>();
			noMobileEmailEmpNo=new ArrayList<String>();
			
			File file = new File("C:\\CPF_Registration_excel_file\\test.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			while (itr.hasNext()) {
				Row row = itr.next();
				if (row.getRowNum() != 0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							if (cell.getColumnIndex() == 2) {
								empEmail = cell.getStringCellValue().toLowerCase().trim();
								System.out.print(empEmail + "\t\t\t");
							}
							break;
						case Cell.CELL_TYPE_NUMERIC:

							if (cell.getColumnIndex() == 1) {
								empNo = NumberToTextConverter.toText(cell.getNumericCellValue()).trim();
								System.out.print(empNo + "\t\t\t");
							}
							if (cell.getColumnIndex() == 3) {
								empMobile = NumberToTextConverter.toText(cell.getNumericCellValue()).trim();
								System.out.print(empMobile + "\t\t\t\n");
							}
							break;
						default:
							//System.out.print("Default\n");
						}
					}
					
					if(empNo!=null) {
					System.out.println("Processing Start For Employee Number ::: " + empNo);
					final Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM fcipayroll.cpf_registered_users where emp_num =" + empNo);
					if (!rs.next()) {
						System.out.println("Registration going to start for Employee Number ::: " + empNo);

						final Statement stmt3 = con.createStatement();
						ResultSet rs3 = stmt3.executeQuery("select emp_num,emp_first_name,emp_middle_name,emp_last_name,uan from pay_emp_mast where emp_num="+empNo);
						while (rs3.next()) {
							String empFirstName = rs3.getString(2) != null ? rs3.getString(2) : "";
							String empMiddleName = rs3.getString(3) != null ? rs3.getString(3) : "";
							String empLastName = rs3.getString(4) != null ? rs3.getString(4) : "";
							if (empMobile != null && empEmail != null) {
								System.out.println("REG_ID - " + ++regIdCounter);
								System.out.println("Emp Number - " + empNo);
								System.out.println("Emp Phone - " + empMobile);
								System.out.println("Emp Email - " + empEmail);
								System.out.println("Emp First Name - " + empFirstName);
								System.out.println("Emp Middle Name - " + empMiddleName);
								System.out.println("Emp Last Name - " + empLastName);
								System.out.println("Emp UAN Number - " + rs3.getLong(5));

								final Statement stmt4 = con.createStatement();
								System.out.println("Going to insert records for Employee Number :::: " + empNo);
								stmt4.executeUpdate(
										"insert into fcipayroll.cpf_registered_users (REG_ID, UAN, EMP_NUM, EMP_NAME, EMP_PHONE, EMP_EMAIL, ROLE_NAME, STATE, PASSWORD, "
												+ "CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, LAST_LOGIN_DATE, INFO_1, INFO_2, INFO_3, INFO_4, INFO_5) "
												+ "values (" + regIdCounter + ",'" + rs3.getLong(5) + "'," + empNo
												+ ",'" + empFirstName + " " + empMiddleName + " " + empLastName + "','"
												+ empMobile + "','" + empEmail + "', 'USER', 'Active', "
												+ "'$2a$10$NWxgAEXPjJj.8UmRUqrVzelvotk6jI2NpVke75B.hPT4vB5G6vp9C', 'system', to_date(to_char(SYSDATE, 'dd-mm-YYYY HH24:MI:SS'),'dd-mm-YYYY HH24:MI:SS'), 'system', "
												+ "to_date(to_char(SYSDATE, 'dd-mm-YYYY HH24:MI:SS'),'dd-mm-YYYY HH24:MI:SS'), to_date(to_char(SYSDATE, 'dd-mm-YYYY HH24:MI:SS'),'dd-mm-YYYY HH24:MI:SS'), "
												+ "null, null, null, null, null)");
								
								empMobile=null;
								empEmail=null;
								System.out.println("Registration Done For Employee Number :::: " + empNo);
								empNo=null;
							}else{
								System.out.println("Not going for registration because of not found of Mobile number or Email ID for Emp Number ::: "+empNo);
								empMobile=null;
								empEmail=null;
								noMobileEmailEmpNo.add(empNo);
							}
						}
					} else {
						System.out.println("Going for password update for Employee Number :::: " + empNo);
						final Statement stmt4 = con.createStatement();
						stmt4.executeUpdate("update cpf_registered_users set password='$2a$10$NWxgAEXPjJj.8UmRUqrVzelvotk6jI2NpVke75B.hPT4vB5G6vp9C', modified_date= to_date(to_char(SYSDATE, 'dd-mm-YYYY HH24:MI:SS'),'dd-mm-YYYY HH24:MI:SS')	"
								+ "where emp_num in ("+empNo +")");
						passUpdateEmpNo.add(empNo);
						System.out.println("Password updation done for Employee Number :::: " + empNo);
						empNo=null;
						
					}
					con.setAutoCommit(true);
					System.out.println("Auto commit true now");
				}
				}
				
			}
			con.close();
			System.out.println("connection closed");
			System.out.println("Password Update Employee Number :"+passUpdateEmpNo);
			System.out.println("No Mobile/Email for Employee Number :"+noMobileEmailEmpNo);
			LocalDateTime endDateTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
			System.out.println("::::: Inside CPFRegistrationProcess ::::: Main Method :::: End Time ::: "+endDateTime);
		} catch (Exception e) {
			con.rollback();
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * static String DataValidation(final ResultSet rs3) { String error_msg =
	 * ""; try { if (rs3.getString(2) == null) { error_msg = "Message is blank";
	 * } if (rs3.getString(3) == null) { error_msg = "Mobile Number is blank"; }
	 * if (rs3.getString(4) == null) { error_msg = "Signature is blank"; } if
	 * (rs3.getLong(5) == 0) { error_msg = "dlt_entity_id is blank"; } if
	 * (rs.getString(6) == null) { error_msg = "dlt_template_id is blank"; } }
	 * catch (SQLException ex) {
	 * Logger.getLogger(CPFSendSMSUtility.class.getName()).log(Level.SEVERE,
	 * null, ex); } return error_msg; }
	 */
}
