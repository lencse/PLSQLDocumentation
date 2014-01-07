#!/bin/bash

rm -r temp/package_sources/*
./scripts/create_pldoc_for_all_schemas.sh
rm -r temp/package_sources/*
