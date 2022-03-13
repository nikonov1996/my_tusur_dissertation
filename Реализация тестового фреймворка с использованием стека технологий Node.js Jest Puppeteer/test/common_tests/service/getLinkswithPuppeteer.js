import "@babel/polyfill";
import "@babel/preset-env";
let createBrowser = require("../../e2e/service/createBrowser");

module.exports = async function(url) {
    let browser = await createBrowser({visible: false});
    let page = await browser.newPage();
    await page.goto(url);
    let all_links = await page.evaluate(() => {
            let elems =  document.querySelectorAll("a")
            let hrefs = []
            elems.forEach(element => {
                hrefs.push(element.getAttribute("href"))
            });
            return hrefs
          });
    await browser.close();
    return all_links;
};
