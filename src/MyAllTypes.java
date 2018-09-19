import java.io.Serializable;

public class MyAllTypes extends SerializableObject {
    public int myInt;
    public String myString;
    public double myDouble;
    public long myLong;
    public char myChar;

    public MyAllTypes(){}
    public MyAllTypes(int myIntIn, String myStringIn, double myDoubleIn, long myLongIn, char myCharIn){
        myInt = myIntIn;
        myString = myStringIn;
        myDouble = myDoubleIn;
        myLong = myLongIn;
        myChar = myCharIn;
    }

    public int getMyInt(){
        return myInt;
    }

    public String getMyString(){
        return myString;
    }

    public double getMyDouble(){
        return myDouble;
    }

    public long getMyLong(){
        return myLong;
    }

    public char getMyChar(){
        return myChar;
    }


    @Override
    public boolean equals(Object obj){
        try {
            return (myInt == ((MyAllTypes) obj).getMyInt()) && (myString.equals(((MyAllTypes) obj).getMyString())) && (myDouble == ((MyAllTypes) obj).getMyDouble()) && (myLong == ((MyAllTypes) obj).getMyLong()) && (myChar == ((MyAllTypes) obj).getMyChar());
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public int hashCode(){
        return (myInt * 769) % 193;
    }

    @Override
    public String toString(){
        return "MyAllTypesFirst: " + getMyString() + " " + Integer.toString(getMyInt()) + " " + Double.toString(getMyDouble()) + " " + Long.toString(getMyLong()) + " " + Character.toString(getMyChar());
    }
}

