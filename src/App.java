//imports
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int userSelection = userSelectionProcess(sc);
        String url = urlSelection(userSelection);

        Document doc = Jsoup.connect(url).get();
        
        Elements top10 = doc.select(".js-tabs-content");
        infoProcessor(top10);

        
    }

    public static void infoProcessor(Elements top10){
        for (Element e : top10) {
            Elements titles = e.select("span .js-headline-text");
            Elements links = e.select(".most-popular__link a");

            for (int i = 0; i < titles.size(); i++) {
                System.out.println(i + 1 + ".Title: " + titles.get(i).text());
                System.out.println(i + 1 + ".Link: " + links.get(i).attr("href"));
                System.out.println("\n");
            }
        }  
    }

    public static String urlSelection(int selection){
        String[] urls = {"international" , "uk/commentisfree" , "uk/sport" , "uk/culture", "uk/lifeandstyle" , "uk/technology"};
        String resultedString = "";
        if(selection == 1){
            System.out.println("This is top 10 most viwed news in " + urls[0]);
            resultedString ="https://www.theguardian.com/" + urls[0];
        }
        else if(selection == 2){
            System.out.println("This is top 10 most viwed news in "+urls[1].substring(3) +" | Opinion");
            resultedString ="https://www.theguardian.com/" + urls[1];
        }
        else if(selection == 3){
            System.out.println("This is top 10 most viwed news in " + urls[2].substring(3));
            resultedString ="https://www.theguardian.com/" + urls[2];
        }
        else if(selection == 4){
            System.out.println("This is top 10 most viwed news in " + urls[3].substring(3));
            resultedString ="https://www.theguardian.com/" + urls[3];  
        }
        else if(selection == 5){
            System.out.println("This is top 10 most viwed news in " + urls[4].substring(3));
            resultedString ="https://www.theguardian.com/" + urls[4];
        }
        else if(selection == 6){
            System.out.println("This is top 10 most viwed news in " + urls[5].substring(3));
            resultedString ="https://www.theguardian.com/" + urls[5];
        }
        return resultedString;
    }

    public static int userSelectionProcess(Scanner sc){
        System.out.println("Welcome to The Guardian News| Top 10 Most viwed news");
        System.out.println("We are offering these pages: \n[1] International [2] Opinion [3] Sport [4] Culture [5] LifeStyle");
        int resultedInt = 0;
        while(true){
            int userOption = userVal(sc, "Select the respective number: ");
            if(userOption > 5){
                continue;
            }
            else{
                resultedInt = userOption;
                break;
            }
        }
        return resultedInt;
    }

    //user validation so that we get only integer
    public static int userVal(Scanner sc , String promt){

        int userInput = -1;//var to neglect any -ve number

        while (userInput<=0){
            System.out.print(promt);

            String userString = sc.nextLine();//takig a string from user

            try{

                userInput = Integer.valueOf(userString);//try to convert it to int
            }
            catch(NumberFormatException nFE){//if can't be converted to string then throw this error
                System.out.println(userString+ " is not a vlid input!");
                userInput = -1;//restarts the while loop
            }
        }
        return userInput;//finally returns the new value
    }


}

