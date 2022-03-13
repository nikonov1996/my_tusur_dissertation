#  класс для работы с ссылками тестируемой страницы
page_link_class = '''// import "@babel/polyfill";
// import "@babel/preset-env";
const axios = require("axios")
const cheerio = require("cheerio")
axios.defaults.adapter = require('axios/lib/adapters/http')

class PageLinks{

    async getLinksFromPage(url){
        return await axios.get(url)
        .then(response=>{
            const $ = cheerio.load(response.data);
            let links = []
            $('a').each((i,elem)=>{
                links.push($(elem).attr('href'));
            });
            return links
        })
        .catch(error =>{
            console.error(error);
        })
    }

    async itBadLink(elem,url){
        let href;
        if  (elem!=undefined){
            if (elem[0]=="/" || elem[0]=="#"){ href = url+elem;}else{ href = elem;}   
            return await axios.get(href)
            .then(res=>{ return (res.status)})
            .catch(() =>{return href;})
        }
    }
    

     async getBadLinks(links,url){
        let bad_links =[]
        var length = links.length
        for (var i=0;i<length;i++){
            let bad = await this.itBadLink(links[i],url)
            if (typeof(bad)=="string"){bad_links.push(bad)}
        }
        return bad_links;  
    }
    async getBadLinksList(url){
       let all_links = await this.getLinksFromPage(url)
       return await this.getBadLinks(all_links,url)
    }

        
}
module.exports = {PageLinks}'''

# тест для поиска битых ссылок на тестируемой странице

bad_links_test_template = '''import "@babel/polyfill";
import "@babel/preset-env";
let {PageLinks} = require("./service/PageLinks.js")
const url = require("./service/urls");

describe("Набор тестов для проверки ссылок на старнице",()=>{
    let pl
    beforeAll(async () => {
        pl = new PageLinks();
        
      });

    test("Автотест для проверки наличия битых ссылок на production",async()=>{
       let all_links = await pl.getLinksFromPage(url.ссылка)
       let bad_links = await pl.getBadLinks(all_links,url.ссылка)
       expect(bad_links.length==0).toEqual(true) // если длина массива с битыми ссылками равна нулю, то успех
    },5000000)

    test("Автотест для проверки наличия битых ссылок на dev",async()=>{
        let all_links = await pl.getLinksFromPage(url.ссылка)
       let bad_links = await pl.getBadLinks(all_links,url.ссылка)
    expect(bad_links.length==0).toEqual(true) // если длина массива с битыми ссылками равна нулю, то успех
    },5000000)
})'''