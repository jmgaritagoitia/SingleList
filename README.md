# Ejecutar Todos los test
mvn test
# Ejecutar test de los métodos con tag add
mvn test -Dgroups="add"
#Ejecutar los test de una clase
mvn test -Dtest=AddTest test# SingleList
