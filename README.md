## Code Structure
### maybank-service
It is to keep the service expose to other module. And mainly handle database related.

### maybank-application
It is a website allow user to login and do filter on the transaction record.

### maybank-batch
<tbc>

## Diagram
Located at folder maybank-parent/diagram.  
It mainly keep the class diagram & activity diagram. It is write in plantuml format

## Design Pattern
1. facade - use interface for the TxnRecordService in maybank-service.  
Other module access to the interface instead of actual implementation.