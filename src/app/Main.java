package src.app;

import java.io.IOException;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {

        ConfigParser config;

        if(args.length > 0){ config = new ConfigParser(args[0]); }
        else{ config = new ConfigParser("config.txt"); }

        config.readToStructure();
        System.out.println(config.getValue("application.name"));
    }
}
