```plantuml
@startuml
start
:Login Page;
if(login success?) then (yes)
    :Home Page;
    :Click Filter Button;
    :Click the pagination;
else (no)
    :error;
endif
end
@enduml
```