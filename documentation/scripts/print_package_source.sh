#!/bin/bash

#sqlplus apps/apps @print_package_source.sql $1 $2

sqlplus -s <<EOF

   @/opt/oracle/connections/sys.sql

   @scripts/print_package_source.sql $1 $2

EOF > /dev/null
