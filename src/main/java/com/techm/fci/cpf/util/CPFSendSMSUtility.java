package com.techm.fci.cpf.util;

import java.net.*;
import javax.net.ssl.*;
import java.io.*;
import java.util.logging.*;
import java.sql.*;

public class CPFSendSMSUtility
{
    public static void main(final String[] args) {
        String inputLine2 = "";
        try {
        	System.out.println("::::: CPF Self Service Module ::::: Inside CPFSendSMSUtility jar ::::: Main Method ::::");

            Class.forName("oracle.jdbc.xa.client.OracleXADataSource");
            System.out.println("::::: CPF Self Service Module ::::: Inside CPFSendSMSUtility jar ::::: Call forName method ::::");
            
            final Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.248.225.38:1521/PROD", "fcipayroll", "qwerty#0786");
            System.out.println("::::: CPF Self Service Module ::::: Inside CPFSendSMSUtility jar ::::: Connection Created ::::");
            
            final Statement stmt = con.createStatement();
            final Statement stmt2 = con.createStatement();
            ResultSet rs = null;
            ResultSet rs2 = null;
            rs = stmt.executeQuery("select PIN,MESSAGE,mobile_no,signature,dlt_entity_id,dlt_template_id,DEL_BY_SMSPORTAL from fcipayroll.cpf_otp_mas where nvl(del_by_smsportal,'N')='N'");
            System.out.println("::::: CPF Self Service Module ::::: Inside CPFSendSMSUtility jar ::::: Result set size : "+rs.getFetchSize());
            while (rs.next()) {
                inputLine2 = "";
                final String ValidationMessage = DataValidation(rs);
                if (ValidationMessage.equalsIgnoreCase("")) {
                    
                    final String httpsURL = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=fcilekha.sms&pin=" + rs.getString(1) + "&message=" + rs.getString(2) + "&mnumber=" + rs.getString(3) + "&signature=" + rs.getString(4) + "&dlt_entity_id=" + rs.getString(5) + "&dlt_template_id=" + rs.getString(6);
                    System.out.println("::::: CPF Self Service Module ::::: Inside CPFSendSMSUtility jar ::::: Url Created : " + httpsURL);
                    
                    final URL myUrl = new URL(httpsURL);
                    final HttpsURLConnection conn = (HttpsURLConnection)myUrl.openConnection();
                    final InputStream is = conn.getInputStream();
                    final InputStreamReader isr = new InputStreamReader(is);
                    final BufferedReader br = new BufferedReader(isr);
                    String inputLine3;
                    while ((inputLine3 = br.readLine()) != null) {
                        inputLine2 += inputLine3;
                        System.out.println(inputLine2);
                    }
                    System.out.println("::::: CPF Self Service Module ::::: OTP Send Successfully ::::");
                    br.close();
                    if (inputLine2.contains("Accepted")) {
                        rs2 = stmt2.executeQuery("update fcipayroll.cpf_otp_mas set del_by_smsportal='Y',msg_status='Accepted', api_response='" + inputLine2 + "' where mobile_no = " + rs.getString(3));
                    }else {
                        rs2 = stmt2.executeQuery("update fcipayroll.cpf_otp_mas set del_by_smsportal='Y',msg_status='Rejected', api_response='" + inputLine2 + "' where mobile_no = " + rs.getString(3));
                    }
                }else {
                    rs2 = stmt2.executeQuery("update fcipayroll.cpf_otp_mas set del_by_smsportal='Y',msg_status=" + ValidationMessage + ", api_response ='" + inputLine2 + "'  where mobile_no = " + rs.getString(3));
                }
            }
            con.close();
        }
        catch (Exception e) {
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
        }
        catch (SQLException ex) {
            Logger.getLogger(CPFSendSMSUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error_msg;
    }
}
