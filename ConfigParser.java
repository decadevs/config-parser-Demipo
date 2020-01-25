import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ConfigParser {

    Map<String, String> config = new HashMap<String, String>();
    String[] arr = new String[2]; //holds key and value per time
    String fileName = "";
    int itr = 0;
    int carriageCounter = 0;
    int applicationCounter = 0;

    public ConfigParser(String fileName){
        this.fileName = fileName;
    }

    public void readToStructure(){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while(line != null){
                if(line.equals("")){
                    carriageCounter++;
                }
               else if(line.equals("[application]")){
                    applicationCounter++;
                }
                else if(carriageCounter%2 == 0){
                    for (String word : line.split("=", 0)) {
                        arr[itr] = word;
                        itr++;
                    }
                    config.put(arr[0], arr[1]);
                    itr = 0;
                }
                else if(carriageCounter%2 == 1){
                    for (String word : line.split("=", 0)) {
                        arr[itr] = word;
                        itr++;
                    }
                    String s = "";
                    if(applicationCounter == 1){
                        s = "application."+arr[0];
                    }
                    else{
                        s = "application"+applicationCounter+"."+arr[0];
                    }
                    config.put(s, arr[1]);
                    itr = 0;
                }
                line = reader.readLine();
            }
            reader.close();
        }catch(Exception e){
            System.err.println("Exception: " + e.getMessage());
        }
        System.out.println(config);
    }
    public void getMap(){
        System.out.println(config);
    }
    public void getDbName(){
        System.out.println("dbname="+ config.get("dbname"));
    }
    public void getHost(){
        System.out.println("host="+ config.get("host"));
    }
    public void getName(){
        System.out.println("name="+ config.get("application.name"));
    }
    public void getPort(){
        System.out.println("port="+ config.get("application.port"));
    }
    public void getContextUrl(){
        System.out.println("context-url="+ config.get("application.context-url"));
    }
    public void getMode(){
        System.out.println("mode="+ config.get("mode"));
    }
    public void getTheme(){
        System.out.println("theme="+ config.get("theme"));
    }
    public void getPipeline(){
        System.out.println("pipeline="+ config.get("pipeline"));
    }
    public void getNameTwo(){
        System.out.println("name="+ config.get("application2.name"));
    }

}
