package autotests;

import utils.FileGenerator;
import utils.Pinger;
import utils.XmlJSONconverter;

public class test1 {

    public static void main(String[] args)  {

        FileGenerator.createDummyFile();
        Pinger.ping("infotecs.ru");
        XmlJSONconverter.convertXMLtoJSON();

    }

}
