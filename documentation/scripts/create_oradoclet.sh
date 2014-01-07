#!/bin/bash

count=0

while read line
do
   if [[ $line =~ ^[a-zA-Z] ]]
   then
      schemas[$count]=`echo $line | sed s/[^a-zA-Z_0-9]//`
      ((count++))
   fi
done < schemas.conf

for sch in "${schemas[@]}"
do
   conn=`head -1 /opt/oracle/connections/$sch.sql | sed 's/connect //' | sed s/[:space:]+//`
   mkdir output/$sch
   java -d64 -classpath ./oradoclet/oradoclet.jar:./oradoclet/ojdbc6.jar net.oradoclet.OraDoclet $conn@dwstgd2 output/$sch
done
