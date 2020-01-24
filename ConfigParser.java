import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ConfigParser {

    //private fields
    Map<Object, Object> mainHashMap = new HashMap<Object, Object>(); //object preferred because value type inconsistency
    Map<String, String> innerHashMap1 = new HashMap<String, String>();
    Map<String, String> innerHashMap2 = new HashMap<String, String>();
    Map[] map = {innerHashMap1, innerHashMap2}; //prevent over-writing of map
    ArrayList application = new ArrayList();
    String[] arr = new String[2]; //holds key and value per time
    String fileName = "";
    int itr = 0; //array index iterator
    int appCounter = 0; //takes care of [APPLICATION]


    public ConfigParser(String fileName){
        this.fileName = fileName;
    }

    public void readToStructure(){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line != null){

                if (line.startsWith("[")){
                    map[appCounter].remove(" ");
                    application.add(map[appCounter]);
                    appCounter++;
                }

                else if((!(line.startsWith("["))  && appCounter == 0)){
                    for (String word : line.split("=", 0)) {
                        arr[itr] = word;
                        itr++;
                    }
                    mainHashMap.put(arr[0], arr[1]);
                    itr = 0;
                }
                else if(!(line.startsWith("[")  && appCounter > 0)){
                    for (String word : line.split("=", 0)) {
                        arr[itr] = word;
                        itr++;
                    }
                    map[appCounter - 1].put(arr[0], arr[1]);
                    itr = 0;
                }
                line = reader.readLine();
            }
            reader.close();
        }catch(Exception e){
            System.err.println("Exception: " + e.getMessage());
        }
        mainHashMap.remove("");
        innerHashMap1.remove("");
        mainHashMap.put("application", application);

    }

    public void getDbName(){
        System.out.println("DBName......>"+mainHashMap.get("dbname").toString());
    }
    public void getHost(){
        System.out.println("Host......>"+mainHashMap.get("host").toString());
    }
    public void getName(){
        System.out.println("Name......>"+ innerHashMap1.get("name"));
    }
    public void getPort(){
        System.out.println("Port......>"+ innerHashMap1.get("port"));
    }
    public void getContextUrl(){
        System.out.println("Context-URL......>"+ innerHashMap1.get("context-url"));
    }
    public void getMode(){
        System.out.println("Mode......>"+ innerHashMap1.get("mode"));
    }
    public void getTheme(){
        System.out.println("Theme......>"+ innerHashMap1.get("theme"));
    }
    public void getPipeLine(){
        System.out.println("Pipeline......>"+ innerHashMap1.get("pipeline"));
    }
}
