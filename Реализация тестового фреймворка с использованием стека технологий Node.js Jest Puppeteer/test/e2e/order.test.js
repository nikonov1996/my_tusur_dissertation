import "@babel/polyfill";
import "@babel/preset-env";
var log4js = require('log4js');
let createBrowser = require("../service/createBrowser");
let scenario = require("../service/order_scenario");
let products = require("../service/products");
let { ProductPage } = require("../pages/ProductPage");
let { CartPage } = require("../pages/CartPage");
let { OrderPage } = require("../pages/OrderPage");
let { OrderResultPage } = require("../pages/OrderResultPage");
let { tester_user } = require("../service/users");
let logger = log4js.getLogger();
logger.level = "ALL";

let page;
let browser;

beforeAll(async () => {
  browser = await createBrowser({ visible: false });
  page = await browser.newPage();
});

describe.each(scenario)("Orders with scenario: %j", scenar => {
  let product_page;
  let cart_page;
  let order_page;
  let order_result_page;

  beforeAll(async () => {
    product_page = new ProductPage(page);
    cart_page = new CartPage(page);
    order_page = new OrderPage(page);
    order_result_page = new OrderResultPage(page);
  });

  test.each(products)(
    `Test for %s product`,
    async product => {
      await product_page.openProduct(product);
      await product_page.checkProductWasLoaded();
      await product_page.ifProductListPresent();
        logger.info("PRODUCT HAS PRICE LIST");
        let dataBeforeOrder = await product_page.getEachProductsParams();
        let orderNumbers = [];
        const list_length = await product_page.getListLength();
        for (let i = 1; i <= list_length; i++) {
          await product_page.openProduct(product);
          await product_page.checkProductWasLoaded();
          await product_page.selectElemFromProductList(i);
          await product_page.addToCart();
          await product_page.continueToCard();
          await cart_page.goToOrder();
          await order_page.fillOrderForm(tester_user, scenar);

          expect(await order_page.isOrderCorrect()).toBe(true);
        
          await order_page.waitSuccessPage();

          expect(await order_result_page.checkSuccess()).toBe(true);
  
          orderNumbers.push(await order_result_page.getOrderNumber());
        }
        let dataAfterOrder = await order_result_page.getInfoByOrderNumbers(
          orderNumbers[0],
          orderNumbers[orderNumbers.length - 1]
        ); 
        // сравнение массива с параметрами продукта до заказа и с массивом с параметрами продукта после заказа
        expect(dataAfterOrder).toEqual(dataBeforeOrder)
       
    },
    30000000
  );
});

afterAll(async () => {
  await browser.close();
});
