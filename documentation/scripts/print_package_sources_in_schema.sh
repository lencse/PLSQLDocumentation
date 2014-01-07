#!/bin/bash

sch=$1
pklist=$(sqlplus -s <<EOF

	@/opt/oracle/connections/sys.sql

	SET ECHO OFF HEADING OFF FEEDBACK OFF

	VAR SCH VARCHAR2(30);

	EXEC :SCH := '$sch';

	SELECT object_name
	FROM dba_objects
	WHERE owner = UPPER(:SCH)
	  AND object_type = 'PACKAGE'
	ORDER BY object_name;

	EXIT;

EOF)

read -a packages <<<$pklist

for pkg in "${packages[@]}"
do
   ./scripts/print_package_source.sh $sch $pkg
done

