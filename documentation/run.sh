#!/bin/bash

rm -r output/*
./scripts/create_oradoclet.sh
./scripts/create_pldoc.sh
./scripts/merge_oradoclet_and_pldoc.sh
