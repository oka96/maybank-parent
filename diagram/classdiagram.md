```plantuml
@startuml
namespace com.maybank.application #DDDDDD {
    class UserController
    class HomeController

    HomeController *-- com.maybank.service.TxnRecordService
}

namespace com.maybank.service #DDDDDD {
    interface TxnRecordService
    class TxnRecordServiceImpl implements TxnRecordService
    interface TxnRecordExtMapper
    TxnRecordServiceImpl *-- TxnRecordExtMapper
}

@enduml
```