package by.tc.FileAnalyzer.view;


import by.tc.FileAnalyzer.bean.NodeInfo;
import by.tc.FileAnalyzer.controller.Analizator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Runner {
    public static void main(String[] args){
//
//         BufferedReader fileReader;
//        try {
//            fileReader = new BufferedReader(new FileReader("D:\\EPAM\\Training\\Repository_Java11_16_Helda\\Task3_Helda\\src\\by\\tc\\FileAnalyzer\\source\\notes.xml"));
//            char[] c = new char[100];
//            try {
//                fileReader.read(c, 0, 100);
//                System.out.println(c);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        Analizator analizator = new Analizator();
        analizator.setFile("D:\\EPAM\\Training\\Repository_Java11_16_Helda\\Task3_Helda\\src\\by\\tc\\FileAnalyzer\\source\\notes.xml");

        NodeInfo node = analizator.next();

        while (node != null){
            System.out.println(node.getContent() + ": " + node.getType());
            node = analizator.next();
        }




    }
}
