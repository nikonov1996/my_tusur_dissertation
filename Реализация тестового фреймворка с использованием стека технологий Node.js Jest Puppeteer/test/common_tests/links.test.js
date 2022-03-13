import "@babel/polyfill";
import "@babel/preset-env";
let {PageLinks} = require("./service/PageLinks.js")
const url = require("./service/urls");

describe("Набор тестов для проверки ссылок на старнице",()=>{
    let pl
    beforeAll(async () => {
        pl = new PageLinks();
        
      });

    test("Автотест для проверки наличия битых ссылок на production",async()=>{
       let all_links = await pl.getLinksFromPage(url.ZENER_PAGE)
       let bad_links = await pl.getBadLinks(all_links,url.ZENER_PAGE)
    //    console.log(bad_links.length)
       expect(bad_links.length==0).toEqual(true)
    },5000000)

    test("Автотест для проверки наличия битых ссылок на dev",async()=>{
        let all_links = await pl.getLinksFromPage(url.DEV_PAGE)
       let bad_links = await pl.getBadLinks(all_links,url.DEV_PAGE)
    expect(bad_links.length==0).toEqual(true)
    // console.log(bad_links.length)
    },5000000)
})