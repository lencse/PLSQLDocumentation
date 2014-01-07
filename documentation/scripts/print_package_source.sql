-- Usage: print_script.sql schema package
-- Example: print_script.sql engine cln_dedup
-- Output: schema-package.sql with package source

SET HEADING OFF
SET LINESIZE 1000
SET PAGESIZE 0
SET FEED OFF
SET TERMOUT OFF
SET VERIFY OFF
SET TRIMSPOOL ON

VAR SCH VARCHAR2(30)
VAR PKG VARCHAR2(30)

EXEC :SCH := '&&1';
EXEC :PKG := '&&2';

SPOOL temp/package_sources/&&1/&&2..sql REPLACE

SELECT
   REGEXP_REPLACE(
      REPLACE(REPLACE(text, CHR(10)), CHR(13)),
      '(PACKAGE\s+|PACKAGE\s+BODY\s+)(' || :PKG || ')',
      '\1' || :SCH || '.\2',
      1, 0, 'i'
   )
FROM dba_source
WHERE 1=1
   AND owner = UPPER(:SCH)
   AND name = UPPER(:PKG)
   AND type IN ('PACKAGE', 'PACKAGE BODY')
ORDER BY DECODE(type, 'PACKAGE', 1, 'PACKAGE BODY', 2), line;

SPOOL OFF

EXIT
