package com.techm.fci.cpf.daoimpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class RequestIdGenerator implements IdentifierGenerator {

	public static final Logger logger = LoggerFactory.getLogger(RequestIdGenerator.class);
	
	private final String DEFAULT_SEQUENCE_NAME = "cpf_claim_form_details_seq";
	
	public static final String DATE_FORMAT_PARAMETER = "dateFormat";
    public static final String DATE_FORMAT_DEFAULT = "%tY-%tm";
     
    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%05d";
     
    public static final String DATE_NUMBER_SEPARATOR_PARAMETER = "dateNumberSeparator";
    public static final String DATE_NUMBER_SEPARATOR_DEFAULT = "_";
     
    private String format;

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Serializable result = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
       
        String prefix = new SimpleDateFormat("yyMMdd").format(new Date());
        try {
            connection = session.connection();
            statement = connection.createStatement();
            try {
                
                resultSet = statement.executeQuery("SELECT "+DEFAULT_SEQUENCE_NAME+".NEXTVAL FROM DUAL");
            } catch (Exception e) {

                System.out.println("In catch, cause : Table is not available.");
                // if sequence is not found then creating the sequence
                // Below code is for MySql database you change according to your database
                statement.execute("CREATE table " + DEFAULT_SEQUENCE_NAME + " (next_val INT NOT NULL)");
                statement.executeUpdate("INSERT INTO " + DEFAULT_SEQUENCE_NAME + " VALUES(0)");
                //==> LAST_INSERT_ID(next_val+1)  -> this is inbuilt function of MySql so by using this we can achieve our custom sequence like auto increment
                statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=LAST_INSERT_ID(next_val+1)");
                resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);
                //e.printStackTrace();
            }
            if (resultSet.next()) {

                int nextValue = resultSet.getInt(1);
                String suffix = String.format("%04d", nextValue);
                result = prefix.concat(suffix);
                
               logger.info("Custom generated sequence is ::::: " + result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
}