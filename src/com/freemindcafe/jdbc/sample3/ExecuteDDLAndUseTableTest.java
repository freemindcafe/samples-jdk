package com.freemindcafe.jdbc.sample3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
public class ExecuteDDLAndUseTableTest {
	
	@Test
	public void test() throws SQLException, IOException{
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setUrl("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=XE)))");
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUsername("system");
		dataSource.setPassword("neha1");
		dataSource.setMaxOpenPreparedStatements(5);
		dataSource.setPoolPreparedStatements(true);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Connection connection1 = dataSource.getConnection();
		try{
			connection1.createStatement().executeQuery("CREATE global temporary TABLE ABC(i varchar(10))");
		}finally{
			connection1.close();
		}
		
		Connection connection = dataSource.getConnection();
		try{
			connection.createStatement().executeQuery("DROP TABLE ABC");
		}finally{
			connection.close();
		}
		
		jdbcTemplate.execute("CREATE global temporary TABLE ABC(i varchar(10))");
		jdbcTemplate.execute("DROP TABLE ABC");
		
		
		String string = IOUtils.toString( ExecuteDDLAndUseTableTest.class.getResourceAsStream("/com/freemindcafe/jdbc/sample3/unusedGlobalTempTable.sql"));
		
		String string2 = IOUtils.toString( ExecuteDDLAndUseTableTest.class.getResourceAsStream("/com/freemindcafe/jdbc/sample3/unusedClean.sql"));
	
		jdbcTemplate.execute(string);
		jdbcTemplate.execute(string2);
		
	}

}
