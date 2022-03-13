#!/bin/bash
echo -n "Enter project name:"
read project
mkdir $project
cd $project
mkdir ./test
mkdir ./test/e2e test/pages test/service test/rest
echo 'Directory "e2e" for ui autotests was created..'
echo 'Directory "rest" for api autotests was created..'
echo 'Directory "pages" for web-pages methods description (Page Object pattern) was created..'
touch ./test/service/urls.js
echo 'module.exports = {
/*
В данном модуле перечисляются необходимые url,
используемые в проекте.
*/
}' > ./test/service/urls.js
echo 'Module "urls.js" for web project urls description was created..'
touch ./test/service/selectors.js
echo 'Module "selectors.js" for web project selectors description was created..'
echo '{
"presets": ["@babel/preset-env"]
}' > .babelrc
echo "Babel config file was created.."
echo 'node_modules
allure-results' > .gitignore
echo "Git ignore file was created.."
npm init
npm install @babel/core@^7.8.7 @babel/cli@^7.8.4 @babel/node@^7.8.7 @babel/preset-env@^7.8.7
npm install faker@^4.1.0 jest@^25.0.0 jest-allure@^0.1.1 log4js@^6.1.2 puppeteer@2.0.0
echo 'It is ready for work. Good luck!'
