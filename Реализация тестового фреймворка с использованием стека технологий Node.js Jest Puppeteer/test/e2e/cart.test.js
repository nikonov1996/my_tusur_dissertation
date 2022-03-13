import "@babel/polyfill";
import "@babel/preset-env";
import "jest-allure/dist/setup";
import { Severity } from "jest-allure/dist/Reporter";
var log4js = require("log4js");
let createBrowser = require("../e2e/service/createBrowser");
let { ProductPage } = require("../e2e/pages/ProductPage");
let { CartPage } = require("../e2e/pages/CartPage");
let products = require("../e2e/service/products");
let logger = log4js.getLogger();
logger.level = "ALL";

describe("Набор автотестов для проверки работы функций корзины", () => {
  let page;
  let browser;
  let product_page;
  let cart_page;

  beforeAll(async () => {
    browser = await createBrowser({visible: false});
    page = await browser.newPage();
    logger.info("BROWSER IS OPENED");
    product_page = new ProductPage(page);
    cart_page = new CartPage(page);    
  });

  test.each(products)(
    `Автотест для проверки надписи о бесплатной доставке`,
    async product => {
      await product_page.openProduct(product);
      logger.info("Product page did open");
      await product_page.checkProductWasLoaded();
      if (await product_page.ifProductListPresent()) {
        logger.info("Product list is present");
        await product_page.addToCart();
        await product_page.continueToCard();
        if (await cart_page.isFreeDeliveryCorrect()) {
          logger.info("Label about free delivery correctly present");
        } else {
          logger.error("Label about free delivery INCORRECT");
        }
        reporter.startStep("Проверить, что надпись отображается корректно");
        expect(await cart_page.isFreeDeliveryCorrect()).toBe(true);
        reporter.endStep("Конец шага");
      } else {
        logger.warn("Product havn't got price list..");
      }
    },
    60000
  );

  test("Автотест для примера", async () => {
    reporter.feature("Здесь название тестируемой функциональности ");
  }, 15000);

  afterAll(async () => {
    await browser.close();
    logger.info("BROWSER IS CLOSED");
  });
});

describe("Набор условных автотестов для проверки сравнений чисел", () => {
  let l = 5;
  test("Первый тест", async () => {
    reporter.feature("Здесь название тестируемой функциональности номер 1");
    reporter.description("Здесь описание тестового сценария");
    reporter.severity(Severity.Trivial);
    reporter.addLabel("Status","BUG"); // можно добавить лейбл
    reporter.startStep("Название шага тестового сценария: 5 должно быть больше чем 4");
    expect(l > 4).toBe(true);
    reporter.endStep("Конец шага");
  }, 15000);
  test("Второй тест", async () => {
    reporter.feature("Здесь название тестируемой функциональности номер 2");
    reporter.startStep("Название шага тестового сценария: 5 - 4 должно быть равно 1");
    expect(l - 4).toEqual(1);
    reporter.endStep("Конец шага");
  }, 15000);
  test("Третий тест", async () => {
    reporter.feature("Здесь название тестируемой функциональности номер 3");
    reporter.startStep("Название шага тестового сценария: 5 +1 должно быть равно 6");
    expect(l + 1).toEqual(6);
    reporter.endStep("Конец шага");
  }, 15000);
});
