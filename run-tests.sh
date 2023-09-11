rm -rf ./allure-results
mvn $1 clean test
cp -r ./allure-report/history ./allure-results/history
rm -rf ./allure-report
echo "Browser=Chrome" >> ./allure-results/environment.properties
echo "Browser.Version=116" >> ./allure-results/environment.properties
echo "Stand=Prod" >> ./allure-results/environment.properties
echo "User=$(whoami)" >> ./allure-results/environment.properties
echo "Agent.OS.Name=$(sw_vers -productName)" >> ./allure-results/environment.properties
echo "Agent.OS.Version=$(sw_vers -productVersion)" >> ./allure-results/environment.properties

allure generate
allure open