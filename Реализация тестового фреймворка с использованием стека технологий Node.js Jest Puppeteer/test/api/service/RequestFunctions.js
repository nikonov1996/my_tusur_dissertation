// import "@babel/polyfill";
// import "@babel/preset-env";
const axios = require('axios');
// cheerio = require("cheerio"),
axios.defaults.adapter = require('axios/lib/adapters/http')



 class RequestFunctions { 
    async getOrdersList(url,order_num1,order_num2 = order_num1){
      let apiUrl = `${url}/rest/orders.php?ID_FROM=${order_num1}&ID_TO=${order_num2}`
      return await axios.get(apiUrl)
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

    async getOrderItemInfo(url,order_num1){
      // let apiUrl = `${url}/rest/orders.php?ID_FROM=${order_num1}&ID_TO=${order_num2}`
      let orders_list =  await this.getOrdersList(url,order_num1)
      let order_info = orders_list[order_num1];
        return order_info.ITEMS
    }

    async getOrderInfo(url,order_num1){
      // let apiUrl = `${url}/rest/orders.php?ID_FROM=${order_num1}&ID_TO=${order_num2}`
      let orders_list =  await this.getOrdersList(url,order_num1)
      let order_info = orders_list[order_num1];
        return order_info
    }
}
module.exports = {RequestFunctions};