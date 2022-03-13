gitignore_template = '''node_modules
allure-results
'''

babelrc_template = '''{
  "presets": ["@babel/preset-env"]
}
'''

package_template = '''{
  "name": "PROJECT_NAME",
  "version": "1.0.0",
  "description": "autotests for project: PROJECT_NAME ",
  "scripts": {
    "test": "jest",
    "report": "allure serve"
  },
  "repository": {
    "type": "git",
    "url": "PROJECT_URL"
  },
  "keywords": [
    "KEYWORD1",
    "KEYWORD2",
    "e2e",
    "ui"
  ],
  "author": "PROJECT_AUTHOR",
  "license": "ISC",
  "jest": {
    "setupFilesAfterEnv": [
      "jest-allure/dist/setup"
    ]
  },
  "dependencies": {
    "@babel/cli": "^7.8.4",
    "@babel/core": "^7.8.7",
    "@babel/polyfill": "^7.8.7",
    "@babel/preset-env": "^7.8.7",
    "faker": "^4.1.0",
    "jest": "^25.1.0",
    "jest-allure": "^0.1.1",
    "log4js": "^6.1.2",
    "puppeteer": "^2.0.0"
  }
}'''

url_template = '''module.exports = {
  page_name1: "page_url1",
  home_google: `http:/www.google.com` //example
}'''

selectors_templates = '''module.exports = {
  HOME_PAGE: {
    web_element : "selector_name" // for example
  },

  CART_PAGE: {
    web_element : "selector_name" // for example
  },

  ORDER_PAGE: {
    web_element : "selector_name" // for example
  }
};
'''

users_template = '''const faker = require("faker");

module.exports = {
  random_user: {
    firstname: faker.name.firstName(),
    lastname: faker.name.lastName(),
    email: faker.internet.email(),
    phone: faker.phone.phoneNumber(),
    fullname: `${faker.name.firstName()} ${faker.name.lastName()}`,
    city:'Томск', //todo add random data
    index: '364000',
    street:'TestStreet',
    home:'13',
    flat:'1',
    company:'TestCompany',
    inn:'123456789100'
  },
  tester_user: {
    firstname: 'testname',
    lastname: 'testlastname',
    email: 'nva.1996@yandex.ru',
    phone: '9138766597',
    fullname: 'testfullname',
    city:'Томск',
    index: '364000',
    street:'TestStreet',
    home:'13',
    flat:'1',
    company:'TestCompany',
    inn:'123456789100'
  }
}
'''

createbrowser_template = '''import "@babel/polyfill";
import "@babel/preset-env";
let puppeteer = require("puppeteer");

module.exports = async function({ visible }) {
  if (visible) {
    return await puppeteer.launch({
      headless: false,
      slowMo: 50,
      defaultViewport: null,
      ignoreDefaultArgs: ['--disable-extensions'],
      args: [
        '--no-sandbox',
        '--disable-setuid-sandbox'
    ]
    });
  } else {
    return await puppeteer.launch({
      args: [
        '--no-sandbox',
        '--disable-setuid-sandbox'
    ]
    });
  }
};
'''

basepage_template = '''import "@babel/polyfill";
import "@babel/preset-env";
class BasePage{
  
  constructor(page){
    this.page = page;
  }
}

module.exports = {BasePage};

'''

homepage_template = '''import "@babel/polyfill";
import "@babel/preset-env";
const { example } = require("../service/selectors");
const { BasePage } = require("./BasePage");

class HomePage extends BasePage {
  
}
module.exports = { CartPage };
'''

request_func_template = '''const axios = require('axios');
cheerio = require("cheerio"),
axios.defaults.adapter = require('axios/lib/adapters/http')



 class RequestFunctions { 
    async exampleFunction(url){
      return await axios.get(url)
      .then(response=>{
          // const $ = cheerio.load(response.data);
          // let links = []
          // $('a').each((i,elem)=>{
          //     links.push($(elem).attr('href'));
          // });
          // return links
         return response.data
      })
      .catch(error =>{
          console.error(error);
      })
    }

}
module.exports = {RequestFunctions};
'''

ui_test_example_template = '''import "@babel/polyfill";
import "@babel/preset-env";
import "jest-allure/dist/setup";
import { Severity } from "jest-allure/dist/Reporter";
var log4js = require("log4js");
let createBrowser = require("../e2e/service/createBrowser");
let { HomePage } = require("../e2e/pages/HometPage");
let logger = log4js.getLogger();
logger.level = "ALL";  // уровень логирования

describe("Набор автотестов для примера, для проверки сравнений чисел:", () => {  // название тестового набора
  let l = 5;

  test("Первый тест", async () => {  // название теста
    reporter.feature("Здесь название тестируемой функциональности номер 1");  // название тестируемой функциональности
    reporter.description("Здесь описание тестового сценария"); // описание тестируемой функциональности
    reporter.severity(Severity.Trivial);  // severity
    reporter.addLabel("Status","BUG"); // добавление лейбла 
    reporter.startStep("Название шага тестового сценария: 5 должно быть больше чем 4"); // начало шага
    expect(l > 4).toBe(true);
    reporter.endStep("Конец шага"); // конец шага
  }, 15000);

  test("Второй тест", async () => {  // название теста
    reporter.feature("Здесь название тестируемой функциональности номер 2"); // название тестируемой функциональности
    reporter.startStep("Название шага тестового сценария: 5 - 4 должно быть равно 1"); // начало шага
    expect(l - 4).toEqual(1);
    reporter.endStep("Конец шага"); // конец шага
  }, 15000);

});
'''

api_test_example_template = '''import "@babel/polyfill";
import "@babel/preset-env";
var log4js = require('log4js');
let logger = log4js.getLogger();
let {RequestFunctions} = require("./service/RequestFunctions")
let url = require("./service/urls")
logger.level = "ALL"; // уровень логирования

describe("Набор автотестов для примера, для проверки сравнений чисел:", () => {  // название тестового набора
  let l = 5;
  let rf

    beforeAll(async () => {
      rf = new RequestFunctions()
    });

  test("Первый тест", async () => {  // название теста
    reporter.feature("Здесь название тестируемой функциональности номер 1");  // название тестируемой функциональности
    reporter.description("Здесь описание тестового сценария"); // описание тестируемой функциональности
    reporter.severity(Severity.Trivial);  // severity
    reporter.addLabel("Status","BUG"); // добавление лейбла 
    reporter.startStep("Название шага тестового сценария: 5 должно быть больше чем 4"); // начало шага
    expect(l > 4).toBe(true);
    reporter.endStep("Конец шага"); // конец шага
  }, 15000);

});
'''
common_test_example_template = '''import "@babel/polyfill";
import "@babel/preset-env";
var log4js = require('log4js');
let logger = log4js.getLogger();
let url = require("./service/urls")
logger.level = "ALL"; // уровень логирования

describe("Набор автотестов для примера, для проверки сравнений чисел:", () => {  // название тестового набора
  let l = 5;
  let rf

    beforeAll(async () => {
      rf = new RequestFunctions()
    });

  test("Первый тест", async () => {  // название теста
    reporter.feature("Здесь название тестируемой функциональности номер 1");  // название тестируемой функциональности
    reporter.description("Здесь описание тестового сценария"); // описание тестируемой функциональности
    reporter.severity(Severity.Trivial);  // severity
    reporter.addLabel("Status","BUG"); // добавление лейбла 
    reporter.startStep("Название шага тестового сценария: 5 должно быть больше чем 4"); // начало шага
    expect(l > 4).toBe(true);
    reporter.endStep("Конец шага"); // конец шага
  }, 15000);

});
'''