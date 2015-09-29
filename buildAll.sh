#!/bin/sh

cd customer_management_artifact
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

cd inventory_management_artifact
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

cd messaging_event_artifact
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

cd order_management_artifact
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &

cd payment_management_artifact
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output & 
cd ..

cd product_management_artifact
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

cd shipment_management_artifact
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

