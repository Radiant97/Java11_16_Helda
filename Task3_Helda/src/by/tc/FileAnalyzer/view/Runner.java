package by.tc.FileAnalyzer.view;


import by.tc.FileAnalyzer.controller.Analizator;
import by.tc.FileAnalyzer.service.factory.ServiceFactory;

public class Runner {
    public static void main(String[] args){

        Analizator analizator = new Analizator();
        analizator.setFile("D:\\EPAM\\Training\\Repository_Java11_16_Helda\\Task3_Helda\\src\\by\\tc\\FileAnalyzer\\source\\notes.xml");

        String str = analizator.next();

        while (str != null){
            System.out.println(str);
            str = analizator.next();
        }




    }
}
