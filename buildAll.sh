#!/bin/sh

cd customer_management_artifact
rm -rf build.output
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

cd inventory_management_artifact
rm -rf build.output
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

cd messaging_event_artifact
rm -rf build.output
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

cd order_management_artifact
rm -rf build.output
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &

cd payment_management_artifact
rm -rf build.output
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output & 
cd ..

cd product_management_artifact
rm -rf build.output
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

cd shipment_management_artifact
rm -rf build.output
mvn -T 1C clean install -DskipTests=true -DdownloadSources=false > build.output &
cd ..

#tail -f customer_management_artifact/build.output inventory_management_artifact/build.output messaging_event_artifact/build.output order_management_artifact/build.output

