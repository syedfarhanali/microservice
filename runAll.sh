#!/bin/sh

java -jar ./customer_management_artifact/target/customer-management-1.2.6.RELEASE.jar > customer_management.log &
java -jar ./inventory_management_artifact/target/inventory-management-1.2.6.RELEASE.jar > inventory_management.log &
#java -jar ./messaging_event_artifact/target/messaging-infrastructure-1.0.0.jar
java -jar ./order_management_artifact/target/order-management-1.2.6.RELEASE.jar > order_management.log &
#java -jar ./payment_management_artifact/target/payment-management-1.0-SNAPSHOT.jar > payment_management.log &
java -jar ./shipment_management_artifact/target/shipment-management-1.2.6.RELEASE.jar > shipment_management.log &
