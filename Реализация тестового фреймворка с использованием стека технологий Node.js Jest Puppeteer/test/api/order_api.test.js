import "@babel/polyfill";
import "@babel/preset-env";
var log4js = require('log4js');
let logger = log4js.getLogger();
let {RequestFunctions} = require("./service/RequestFunctions")
let url = require("./service/urls")
logger.level = "ALL";


describe("", () => {
    let rf

    beforeAll(async () => {
      rf = new RequestFunctions()
    });
  
    test(`Test`,async()=>{
      let order_item_info = await rf.getOrderItemInfo(url.DEV_PAGE, 238029)
      let is_ID_present = order_item_info[0].ID!=undefined && order_item_info[0].ID!="";
      let is_NAME_present = order_item_info[0].NAME!=undefined && order_item_info[0].NAME!="";
      let is_MANUFACTURER_present = order_item_info[0].MANUFACTURER!=undefined && order_item_info[0].MANUFACTURER!="";
      let is_PRODUCT_STOCK_present = order_item_info[0].PRODUCT_STOCK!=undefined && order_item_info[0].PRODUCT_STOCK!="";
      let is_STOCK_PRICE_present = order_item_info[0].STOCK_PRICE!=undefined && order_item_info[0].STOCK_PRICE!="";
      let is_PRICE_present = order_item_info[0].PRICE!=undefined && order_item_info[0].PRICE!="";

      expect(order_item_info.length!=0).toEqual(true) // проверить что api возвращает не пустые данные о корзине
      
      // проверить, что определенные данные из информации о продуктах в корзине приходят корректно
      expect(is_ID_present).toEqual(true)
      expect(is_NAME_present).toEqual(true)
      expect(is_MANUFACTURER_present).toEqual(true)
      expect(is_PRODUCT_STOCK_present).toEqual(true)
      expect(is_STOCK_PRICE_present).toEqual(true)
      expect(is_PRICE_present).toEqual(true)

    },
      30000000
    );
});
  
 
  