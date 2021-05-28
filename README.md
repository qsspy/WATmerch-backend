# WATmerch-backend

plikacja WATmerch Backend jest (jak sama nazwa wskazuje) aplikacją backendową wystawiającą REST API, z którego korzystają aplikacje sklepu WATmerch.
Program wykorzystuje framework Spring Boot. W celu zapewnienia uwierzytelnienia oraz autoryzacji wykorzystywane jest Spring Security. 
Aplikacja łączy się z bazą danych MySQL za pomocą ORM dostarczonego przez Hibernate za pośrednictwem Spring Data. Oprócz udostępniania API zaimplementowany
został prosty system CRM w celu zarządzania użytkownikami. Do wygenerowania szablonów HTML został użyty Thymeleaf.

Aplikacja obsługuję dostęp do bazy danych zaprojektowanej poniżej :

![image](https://user-images.githubusercontent.com/58231905/120023152-b46b0900-bfed-11eb-995e-81d9f1f4a585.png)

