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
   mkdir temp/package_sources/$sch
   ./scripts/print_package_sources_in_schema.sh $sch
   ./pldoc/pldoc.sh -d output/$sch/pldoc temp/package_sources/$sch/*.sql
done
