import java.io.Serializable;
import java.util.Vector;

public class Driver {

    public static void main(String args[]) {

        String filename = "C://Example//xmlfile.xml";
        Serialization serialization = new Serialization();
        OutputFileProcessor outputFileProcessor = new OutputFileProcessor(filename);
        InputFileProcessor inputFileProcessor = new InputFileProcessor(filename);
        serialization.readObj(inputFileProcessor);


        int myInt = 0;
        String myString = "a";
        double myDouble = 1.0;
        long myLong = 2;
        char myChar = 'a';
        int NUM_OF_OBJECTS = 0;

        MyAllTypes myAllTypes;
        Vector <SerializableObject> myVector = new Vector<>();

        for (int i = 0; i < NUM_OF_OBJECTS; i++) {
            myAllTypes = new MyAllTypes(myInt, myString + String.valueOf(i), myDouble, myLong, myChar);
            myVector.add(myAllTypes);


        }

        serialization.writeObj(outputFileProcessor,myVector);
    }
}
