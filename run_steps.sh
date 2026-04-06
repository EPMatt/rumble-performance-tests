cd rumble/
git checkout report/ma/matteo-all-changes
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestSteps#all

cd rumble/
git checkout report/ma/master-without-matteo-work
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestSteps#all