package base;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import gui.App;

public class Craw {

	static String start = "http://www.syndics-de-france.com/annuaire-entreprises-liste-par-champ.html";	
	static LinkedList<String> tobedownlaoded = new LinkedList<>();

	public static void getLinks(){
		Document doc;
		try {
			doc = Jsoup.connect(start)
					.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.timeout(10000).get();
			Elements e = doc.getElementsByClass("CompanyDirectoryItem");
			for (Element element : e) {
				Elements href = element.getElementsByAttribute("href");
				for (Element element2 : href) {
					tobedownlaoded.add(element2.attr("href"));
				}

			}
			System.out.println("\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}


	public static void getData(String location){
		String temp="";
		for (String url : tobedownlaoded) {
			Document doc;
			try {
				doc = Jsoup.connect(url)
						.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
						.timeout(10000).get();
				Elements e = doc.getElementsByClass("CompanyDirectoryItemDetail");
				for (Element element : e) {

					temp = temp+element.getElementsByTag("h3").first().text()+"\n";
					Elements p = element.getElementsByTag("p");
					for (Element element2 : p) {
						temp = temp + element2.text()+"\n";
					}
				}
				temp = temp+ "\n";

				try {
					FileWriter f = new FileWriter(location+"data.xls");
					f.write(temp);
					f.close();
				} catch (IOException ee) {
					ee.printStackTrace();
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}





	}

	public static void main(String[] args){

		App window = new App();
		window.frmStatsbisorgScraper.setVisible(true);
	}
}
