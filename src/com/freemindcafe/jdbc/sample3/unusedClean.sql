DECLARE 
	cursor c_cur is select table_name, constraint_name from user_constraints where constraint_type='R' and delete_rule='NO ACTION' and constraint_name not in ('FK_DCF_PROJECT_ACTIVITY_1','FK_DCF_PROJECT_DEVICE_1');
	sql_stmt VARCHAR2(256);
	row_cnt  NUMBER := 0;
BEGIN
	BEGIN
	  FOR REC IN C_CUR LOOP
	    EXECUTE IMMEDIATE 'ALTER TABLE '||REC.TABLE_NAME||' DISABLE CONSTRAINT '||REC.CONSTRAINT_NAME;
	  END LOOP;
	END;
	


	BEGIN
		INSERT  INTO tab_rel_tmp
		SELECT  a.table_name child_t, b.table_name parent_t
		FROM    user_constraints a, user_constraints b
		WHERE   a.r_constraint_name = b.constraint_name
		AND     a.constraint_type = 'R'
		AND	a.table_name != b.table_name;
	
		INSERT  INTO tab_rel_tmp
		SELECT	table_name child_t, null
		FROM	user_tables
		WHERE	table_name NOT IN (
			SELECT	child_t
			FROM	tab_rel_tmp)
		AND	table_name NOT IN (
			'TS_TAB_MAP', 'TAB_REL_TMP');
	
		FOR 	idx IN 1..6 LOOP
	                FOR     rec_tab IN (
	                        SELECT  DISTINCT child_t
	                        FROM    tab_rel_tmp
	                        WHERE   child_t NOT IN (
	                                SELECT  parent_t
	                                FROM    tab_rel_tmp
					WHERE	parent_t IS NOT NULL)
					ORDER	BY child_t
	                        ) LOOP
	                        sql_stmt := 'delete from '||rec_tab.child_t;
				dbms_output.put_line(sql_stmt);
				EXECUTE IMMEDIATE sql_stmt;
	
	                        DELETE
	                        FROM    tab_rel_tmp
	                        WHERE   child_t = rec_tab.child_t;
	
	                        COMMIT;
	
				row_cnt := row_cnt + 1;
	                END LOOP;
			dbms_output.put_line('Row count is '||row_cnt);
	        END LOOP;
	EXCEPTION
		WHEN OTHERS THEN
			dbms_output.put_line(SQLERRM);
	END;

 FOR REC IN C_CUR LOOP
    EXECUTE IMMEDIATE 'ALTER TABLE '||REC.TABLE_NAME||' ENABLE CONSTRAINT '||REC.CONSTRAINT_NAME;
  END LOOP;
  
END;
