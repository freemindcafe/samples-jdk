/**
 * If jdbcTemplate is used without transaction then nested queries will need the number of connections equal to or more then the depth of nesting
 * 1) Create a basic data source with connection size 2.
 * 2) Use three level nested query using jdbcTemplate
 * 3) It will thow connectionPoolExhustedException.
 * 
 * Second:
 * 1) Create a basic data source with connection size 2.
 * 2) Use three level nested query in transaction.
 * 3) query will successfully executed.
 */
package com.freemindcafe.jdbc.sample4;