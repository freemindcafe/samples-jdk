BEGIN
		FOR	t0 IN (
			SELECT	1
			FROM	dual
			WHERE	NOT EXISTS (
				SELECT	1
				FROM	user_tables
				WHERE	table_name = 'TAB_REL_TMP')
			) LOOP
			EXECUTE IMMEDIATE 
			'create global temporary table tab_rel_tmp (child_t VARCHAR2(30), parent_t VARCHAR2(30)) on commit preserve rows';
		END LOOP;
	EXCEPTION
		WHEN OTHERS THEN
			dbms_output.put_line(SQLERRM);
END;