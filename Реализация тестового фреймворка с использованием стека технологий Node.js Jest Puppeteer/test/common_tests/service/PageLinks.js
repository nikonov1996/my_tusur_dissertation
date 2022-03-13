// import "@babel/polyfill";
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
module.exports = {PageLinks}





    
    
    






