echo "Rumble Performance Tests - Pagination"

echo "----------------------------------------"
echo "Running tests for branch: report/ma/matteo-all-changes"
echo "----------------------------------------"
cd rumble/
echo "Checking out branch: report/ma/matteo-all-changes"
git checkout report/ma/matteo-all-changes
echo "Building rumble jar"
mvn clean compile assembly:single
cd ..
echo "Running pagination test suite"
mvn test -Dtest=TestPagination#all

echo "----------------------------------------"
echo "Running tests for branch: report/ma/master-without-matteo-work"
echo "----------------------------------------"
cd rumble/
echo "Checking out branch: report/ma/master-without-matteo-work"
git checkout report/ma/master-without-matteo-work
echo "Building rumble jar"
mvn clean compile assembly:single
cd ..
echo "Running pagination test suite"
mvn test -Dtest=TestPagination#all

echo "----------------------------------------"
echo "All tests completed successfully"
echo "----------------------------------------"
