package notetaker;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Class0Notes {

    File f = new File("C:\\");
    ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));

    File fNames = new File("C:\\");
    ArrayList<String> names = new ArrayList<String>(Arrays.asList(fNames.list()));
    
}
