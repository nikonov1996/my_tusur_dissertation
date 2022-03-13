import "@babel/polyfill";
import "@babel/preset-env";
const { CART_PAGE } = require("../service/selectors");
const { BasePage } = require("./BasePage");

class CartPage extends BasePage {
  async goToOrder() {
    await this.page.waitForSelector(CART_PAGE.ORDER_BTN, { timeout: 5000 });
    await this.page.click(CART_PAGE.ORDER_BTN);
  }

  async neededSum() {
    await this.page.waitForSelector(".j-total-sum", {timeout:5000});
    let sum = await this.page.evaluate(() => {
      return document.querySelector(".j-total-sum").innerText;
    });
    if (Number(sum) >= 4000) {
      return true;
    } else {
      return false;
    }
  }

  async isFreeDeliveryTextPresent() {
    return await this.page.evaluate(() => {
      let display = document.querySelector(".j-cart__delivery--free").style.display;
      if (display == "none") {
        return false;
      } else {
        return true;
      }
    });
  }

  async isFreeDeliveryCorrect() {
    if (await this.neededSum()){
      if (await this.isFreeDeliveryTextPresent()){
        return true;
      } else {
        return false;
      }
    } else{
      return false;
    }
  }
}
module.exports = { CartPage };
