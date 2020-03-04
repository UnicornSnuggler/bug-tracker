package main;

public class Specifications {
    public OS operatingSystem;
    public String javaVersion;
    public String softwareVersion;

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
