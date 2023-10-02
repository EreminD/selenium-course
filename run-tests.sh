rm -rf ./allure-results
mvn $1 clean test
cp -r ./allure-report/history ./allure-results/history

echo "browser.name=Chrome" > ./allure-results/environment.properties
echo "browser.version=116" >> ./allure-results/environment.properties
echo "stand=test" >> ./allure-results/environment.properties
echo "agent.os.name=$(uname -o)" >> ./allure-results/environment.properties
echo "agent.os.kernel=$(uname -v)" >> ./allure-results/environment.properties
echo "agent.os.version=$(uname -r)" >> ./allure-results/environment.properties

allure generate --clean
allure open