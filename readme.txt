Build/Run:

mvn spring-boot:run

Access to service:
/move
For example:
http://localhost:8080/move?x=1&y=1&value=2

Service will return 2 boolean indicators validMove and finished, which indicate if last move is valid and if puzzle is finished
For example:
{"validMove":true,"finished":false}