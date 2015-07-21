package com.freemindcafe.jdbc.sample4;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class NestedQueryTest {

	@Test
	public void test() throws SQLException, IOException{
		
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setUrl("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=XE)))");
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUsername("system");
		dataSource.setPassword("neha1");
		dataSource.setMaxActive(2);
		dataSource.setMaxIdle(2);
		dataSource.setMaxOpenPreparedStatements(2);
		dataSource.setMaxWait(1000l);
		dataSource.setPoolPreparedStatements(true);
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		final List<Integer> list=new ArrayList<Integer>();
		String query="Select 1 a from dual where 2="
				+ "(select 2 from dual where 3="
				+ "(select 3 from dual where 4="
				+ "(select 4 from dual)))";
		Boolean exception=false;
		
		try{
		
			jdbcTemplate.query(query, new ResultSetExtractor<Object> (){
	
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					 if (rs.next())
				      {
						 jdbcTemplate.query("select 2 from dual", new ResultSetExtractor<Object> (){
	
							public Object extractData(ResultSet arg0)
									throws SQLException, DataAccessException {
								if (arg0.next()){
									 jdbcTemplate.query("select 3 from dual", new ResultSetExtractor<Object> (){
	
											public Object extractData(ResultSet arg0)
													throws SQLException, DataAccessException {
												if (arg0.next()){
													list.add(arg0.getInt(1));
												}
												return true;
											}});
										 
								         return new Boolean(true);
								      }
								return true;
							}});
						 
				         return new Boolean(true);
				      }
					 return true;
				}
				
			});
		}catch(Exception ex){
			exception = true;
		}
		Assert.assertTrue(exception);
		

		TransactionTemplate transactionTemplate=new TransactionTemplate();
		DataSourceTransactionManager transactionManager =new DataSourceTransactionManager(dataSource);
		transactionTemplate.setTransactionManager(transactionManager );
		
		transactionTemplate.execute(new TransactionCallback<Object>() {

			public Object doInTransaction(TransactionStatus arg0) {	
				return jdbcTemplate.query("select 1 from dual", new ResultSetExtractor<Object> (){
					public Object extractData(ResultSet rs) throws SQLException,DataAccessException {
								
						 if (rs.next())
					      {
							 jdbcTemplate.query("select 2 from dual", new ResultSetExtractor<Object> (){
			
								public Object extractData(ResultSet arg0)
										throws SQLException, DataAccessException {
									if (arg0.next()){
										 jdbcTemplate.query("select 3 from dual", new ResultSetExtractor<Object> (){
			
												public Object extractData(ResultSet arg0)
														throws SQLException, DataAccessException {
													if (arg0.next()){
														list.add(arg0.getInt(1));
													}
													return true;
												}});
											 
									         return new Boolean(true);
									      }
									return true;
								}});
							 
					         return new Boolean(true);
					      }
						 return true;
					}
		
				});
			}			
		});

		Assert.assertEquals(list.size(), 1);

		Assert.assertEquals((Integer)list.get(0), new Integer(3));
	}
}
