import "@babel/polyfill";
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
