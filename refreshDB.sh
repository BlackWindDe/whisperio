#!/bin/sh

mysql whisperio -uroot < ./Database/createTables.sql
mysql whisperio -uroot < ./Database/initTestEnvironment.sql