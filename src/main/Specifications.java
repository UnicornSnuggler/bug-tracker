package main;

public class Specifications {
    OS operatingSystem;
    String javaVersion;
    String softwareVersion;

    public enum OS {
        Windows("Windows"),
        MacOS("MacOS"),
        Linux("Linux");

        String enumLongName;

        OS(String enumLongName){
            this.enumLongName = enumLongName;
        }

        public String getName(){
            return enumLongName;
        }
    }
}
