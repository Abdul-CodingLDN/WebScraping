package Wiki_Scrape.Maven_Wiki_Scrape;
import java.io.IOException;
import org.jsoup.*; 
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

/*
 * -> Tags (the things inside the <>,such as <p>
 * -> Attributes (special words that customise text, such as "height", "colour"
 * -> classes (organise content into major sections, looks like <tag class="classname"> 
 * -> ID's (unique labels
 * 
 * li.product HTML element. This includes:
 * -> An a HTML element: contains the URL associated with the product
 * -> An img HTML element: contains the product image.
 * -> A h2 HTML element: contains the product name.
 * -> A span HTML element: contains the product price. 
 * 
 * -> Get content by tag:
 * Elements pokemonName = doc.getElementsByTag("h2");
	for(Element e: pokemonName)
	{
		System.out.println(e.text());
		System.out.println("Link: " + e.attr("href")); ->> pulls the links
	}
	
 * -> Get content by ID:
 * Element content = doc.getElementById("main"); -> Parent
 * Elements text = content.getElementsByTag("h2"); -> Child
	System.out.println(content);
	for(Element e: text)
	{
		System.out.println(e.text());
	}
 * -> element selector 
 * Elements product = doc.select("li.product");
	for(Element e: product) 
	{
		System.out.println(e.text());
	}
 * 
 * -> Class selector
 * Elements productName = doc.select("h2.woocommerce-loop-product__title, span.price");
	for(Element product: productName)
	{
		System.out.println(product.text());
	}
 * 
 * -> a.page-numbers this is the code for page numbers
 * //System.out.println(pokemonProducts.select("li").size()); -> outputs 16 products
 * 
 */

public class PokemonProduct {
	public static int i;
    public static void main(String[] args) {
        try {
            String baseUrl = "https://scrapeme.live/shop/";
            Set<String> visitedUrls = new HashSet<>();
            processPage(baseUrl, visitedUrls); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processPage(String url, Set<String> visitedUrls) throws IOException {
        if (!visitedUrls.contains(url)) {
            Document doc = Jsoup.connect(url).timeout(10000).get();
            extractProductDetails(doc);

            visitedUrls.add(url);

            // Handle pagination if needed
            Elements paginationElements = doc.select("a.page-numbers");
            for (Element paginationElement : paginationElements) {
                String nextPageUrl = paginationElement.attr("href");
                processPage(nextPageUrl, visitedUrls);
            }
        }
    }

    private static void extractProductDetails(Document doc) {
        Elements pokemonProducts = doc.select("li.product");
        
        
        
        for (Element e : pokemonProducts) {
           
        	
        	
        	String link = e.select("a.woocommerce-LoopProduct-link").attr("href");
            System.out.println("\"Url\": " + link);
            String img = e.select("img").attr("src");
            System.out.println("\"Image Link\": " + img);
            String name = e.select("h2.woocommerce-loop-product__title").text();
            System.out.println("\"Pokémon\": " + name);
            String price = e.select("span.price").text();
            System.out.println("\"Price\": " + price);
            i++;
            System.out.println("Number: " + i);
            
        }
    }
}
      



