/**
 * \brief global temporary table with on commit delete rows;
 * 1) create table using basicDataSource and then resuse
 * 2) Create temporary table using jdbcTemple and then reuse
 * 3) Create table using jdbcTemplate via sql script file and then reuse
 * 
 * <h3>Concepts</h3>
 * <p>
 * <ul>
 * <li>sqlplus login opens up a connection and session. We can close the session by doing a disconnect;</li>
 * <li>We can again open the session by doing a connect;</li>
 * <li>Two sqlplus processes means two connections and two sessions.</li>
 * <li>If we do disconnect on the two sessions then we will have two connections but no sessions .</li>
 * <li>The table is visible across the sessions.</li>
 * <li>Data will not be visible across the sessions.</li>
 * <li>Because of 'on commit delete rows', the data will not be available after the transaction is done.</li>
 * </ul>
 * </p>
 * 
 * <h3>Steps</h3>
 * <ul>
 * <li>Open two sqlplus sessions.</li>
 * <li>Run the following in any session to make sure that we start in clean state<br>truncate table tab_tmp;<br>drop table tab_tmp;</li>
 * <li>Run the following in First session <br><i>create global temporary table tab_tmp (name VARCHAR2(30)) on commit delete rows;</i></li>
 * <li>Run the following in Second session <br><i>select * from tab_tmp;</i><br>Second session can see the table</li>
 * <li>Run the following in First session <br><i>insert into tab_tmp values('abc');</i></li>
 * <li>Run the following in First session <br><i>select * from tab_tmp;</i>First session can see the row</li>
 * <li>Run the following in Second session <br><i>select * from tab_tmp;</i><br>Second session can't see the row</li>
 * <li>Run the following in First session <br><i>disconnect;</i></li>
 * <li>Run the following in First session <br><i>connect;</i></li>
 * <li>Run the following in First session <br><i>select * from tab_tmp;</i>First session can't see the row</li>
 * <li>Run the following in First session <br><i>show autocommit;</i>autocommit should be off</li>
 * <li>Run the following in First session <br><i>insert into tab_tmp values('abc');</i></li>
 * <li>Run the following in First session <br><i>commit;</i></li>
 * <li>Run the following in First session <br><i>select * from tab_tmp;</i>First session can't see the row as the data is not available after commit</li>
 * </ul>
 */
package com.freemindcafe.jdbc.sample3;