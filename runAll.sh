#!/bin/sh


echo  "Do you want to start embedded broker:"
read embedded_broker_input
if [ $embedded_broker_input == "y" ]; then
	echo "Starting embedded broker"
	java -jar ./embedded_activemq_broker/target/activemq-broker-1.2.6.RELEASE.jar  > embedded_broker.log 2>&1 &
	echo $! >> services.pid
fi

echo "Do you want to start customer management service :" 
read custom_service_input
if [ $custom_service_input == "y" ]; then
	echo "Starting customer management service"
	java -jar ./customer_management_artifact/target/customer-management-1.2.6.RELEASE.jar -Dcom.sun.management.jmxremote.port=1101 -Dcom.sun.management.jmxremote.authenticate=false > customer_management.log 2>&1 &
	echo $! >> services.pid
fi

echo  "Do you want to start inventory management service :" 
read inventory_service_input
if [ $inventory_service_input == "y" ]; then
	echo "Starting inventory management service"
	java -jar ./inventory_management_artifact/target/inventory-management-1.2.6.RELEASE.jar -Dcom.sun.management.jmxremote.port=1102 -Dcom.sun.management.jmxremote.authenticate=false > inventory_management.log 2>&1 &
	echo $! >> services.pid
fi

echo  "Do you want to start order management service:" 
read order_service_input
if [ $order_service_input == "y" ]; then
	echo "Starting order management service"
	java -jar ./order_management_artifact/target/order-management-1.2.6.RELEASE.jar -Dcom.sun.management.jmxremote.port=1103 -Dcom.sun.management.jmxremote.authenticate=false > order_management.log 2>&1 &
	echo $! >> services.pid
	
fi

echo  "Do you want to start shipment management service:"
read shipment_service_input
if [ $shipment_service_input == "y" ]; then
	echo "Starting shipment management service"
	java -jar ./shipment_management_artifact/target/shipment-management-1.2.6.RELEASE.jar -Dcom.sun.management.jmxremote.port=1103 -Dcom.sun.management.jmxremote.authenticate=false > shipment_management.log 2>&1 &
	echo $! >> services.pid
fi


