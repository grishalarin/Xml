
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Serialization implements Strategy{

    public Serialization() {
    }

    public void writeObj(OutputFileProcessor outputProcessor, Object obj) {
        try {
            Class myClass = obj.getClass(); //use reflection

            outputProcessor.writeLineToFile("<Serialization>");
            outputProcessor.writeLineToFile(" <complexType xsi:type=\"" + myClass.getName() + "\">");

            Field[] fieldList = myClass.getFields();
            for (Field field : fieldList){
                String buildString = "  <" + field.getName() + " xsi:type=\"xsd:" + field.getType() + "\">" + field.get(obj).toString() + "</" + field.getName() + ">";
                outputProcessor.writeLineToFile(buildString);
            }
            outputProcessor.writeLineToFile(" </complexType>");
            outputProcessor.writeLineToFile("</Serialization>");
        } catch (IllegalAccessException e){
            e.printStackTrace();
            System.exit(0);
        } finally {}

    }


    public Object readObj(InputFileProcessor inputProcessor) {
        String line = inputProcessor.readLine();
        line = inputProcessor.readLine();
        String className = line.split("\"")[1];
        Class c = null;
        SerializableObject object = null;

        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e){
            System.out.println("class: " + className + " not found.");
        } finally {}
        try {
            object = (SerializableObject) c.newInstance();
        } catch (InstantiationException e){
            System.out.println("Unable to instantiate class: " + className);
        } catch (IllegalAccessException e){
            System.out.println("Illegal acccess to class: " + className);
        } finally {}


        line = inputProcessor.readLine();

        while(!line.equals(" </complexType>")){
            String varName = line.substring(line.indexOf("<") + 1, line.indexOf(" ",line.indexOf("<")));
            String methodName = "set" + firstCharToUpper(varName);
            String value = line.substring(line.indexOf(">") + 1, line.indexOf("<", line.indexOf("<") + 1));
            Field f = null;
            Method m = null;
            try {
                f = c.getDeclaredField(varName);
            } catch (NoSuchFieldException e){
                System.out.println("No such field: " + varName);
            } finally {}
            Class[] signature = new Class[1];
            signature[0] = f.getType();

            try {

                m = c.getDeclaredMethod(methodName, signature);
                try {

                    if (signature[0].getSimpleName().equals("int")){
                        m.invoke(object, new Integer(value));
                    } else if (signature[0].getSimpleName().equals("float")){
                        m.invoke(object, new Float(value));
                    } else if (signature[0].getSimpleName().equals("double")){
                        m.invoke(object, new Double(value));
                    } else if (signature[0].getSimpleName().equals("short")){
                        m.invoke(object, new Short(value));
                    } else if (signature[0].getSimpleName().equals("long")){
                        m.invoke(object, new Long(value));
                    } else if (signature[0].getSimpleName().equals("char")){
                        m.invoke(object, new Character(value.charAt(0)));
                    } else if (signature[0].getSimpleName().equals("String")){
                        m.invoke(object, value);
                    }
                } catch (IllegalAccessException | InvocationTargetException e){
                    System.out.println("Failed to add value to object");
                } finally {}
            } catch (NoSuchMethodException e){
                System.out.println("Method: " + methodName + " does not exist.");
            } catch (SecurityException e){
                System.out.println("Security exception on method: " + methodName);
            } finally {}
            line = inputProcessor.readLine();
        }
        line = inputProcessor.readLine();
        return object;
    }
    private String firstCharToUpper(String str){
        return Character.toString(str.charAt(0)).toUpperCase() + str.substring(1);
    }


        /*for (String lines : line) {
            if (i == 1) {
                try {
                    Class clazz = Class.forName(line.substring(1, line.length() - 2));
                    object = clazz.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            if (i > 1 & i < line.length - 1) {
                String pasreField = line.substring(1, line.indexOf(" "));
                String parseValue = line.substring(line.indexOf("value = \"") + 9, line.indexOf("\"", line.indexOf("value = \"") + 9));
                for (Field field : object.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.getName().equals(pasreField)) {
                        try {
                            switch (field.getType().getSimpleName()) {
                                case "byte":
                                    field.set(object, Integer.parseInt(parseValue));
                                    break;
                                case "short":
                                    field.set(object, Short.parseShort(parseValue));
                                    break;
                                case "char":
                                    field.set(object, (char) Integer.parseInt(parseValue));
                                    break;
                                case "int":
                                    field.set(object, Integer.parseInt(parseValue));
                                    break;
                                case "long":
                                    field.set(object, Long.parseLong(parseValue));
                                    break;
                                case "float":
                                    field.set(object, Float.parseFloat(parseValue));
                                    break;
                                case "double":
                                    field.set(object, Double.parseDouble(parseValue));
                                    break;
                                case "boolean":
                                    field.set(object, Boolean.parseBoolean(parseValue));
                                    break;
                                case "String":
                                    field.set(object, parseValue);
                                    break;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            i++;
        }*/
        /*try {
            FileInputStream fis = new FileInputStream(xmlFilePath);

            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


    }

