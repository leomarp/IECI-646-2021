package edu.pucmm.iect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;


public class App {


    public static void main(String[] args) {
        print("PRACTICA #1 - CLIENTE HTTP INTERNET AVANZADO");
        Scanner scan = new Scanner(System.in);
        print("Inserte una url: ");
        String url = scan.nextLine();
        print("Parsing...");

        try {
            Response doc = Jsoup.connect(url).execute();
            Document document = Jsoup.connect(url).get();
            print("NAME OF THE PAGE: " + document.title());
            print("A)- LINES: " + doc.body().split("\n").length);
            Elements parrafos = document.getElementsByTag("p");
            print("B)- PARAGRAPHS: " + parrafos.size());
            print("C)- IMG IN PARAGRAPHS: " + imginparagraph(parrafos));
            Elements form = document.getElementsByTag("form");
            print("D - E)- FORMS: " + form.size());
            int[] forms = new int[2];
            forms = clasifyForms(form);
            print("\t GET FORMS: " + forms[0]);
            print("\t POST FORMS: " + forms[1]);
            new ArrayList();
            print("\t INPUTS: " + forms[1]);
            int j = 1;

            for(Iterator var12 = form.iterator(); var12.hasNext(); ++j) {
                Element fo = (Element)var12.next();
                ArrayList<String> in = inputs(fo);
                print("\t  THE FORM " + j + ", HAS " + in.size() + " INPUTS");
                int z = 1;

                for(Iterator var14 = in.iterator(); var14.hasNext(); ++z) {
                    String i = (String)var14.next();
                    print("\t   INPUT " + z + ": " + i);
                }
            }

            print("F)- POST REQUEST: \n" + post(forms[1], form));
        } catch (IOException var15) {
            System.out.println(var15.toString());
            System.out.println("Could not get the page");
        }

    }

    private static int imginparagraph(Elements par) {
        int aux = 0;

        Element p;
        for(Iterator var3 = par.iterator(); var3.hasNext(); aux += p.getElementsByTag("img").size()) {
            p = (Element)var3.next();
        }

        return aux;
    }

    public static int[] clasifyForms(Elements forms) {
        int getForms = 0;
        int postForms = 0;
        int[] f = new int[2];
        Iterator var5 = forms.iterator();

        while(true) {
            while(var5.hasNext()) {
                Element form = (Element)var5.next();
                if (!form.attr("method").equalsIgnoreCase("GET") && !form.attr("method").equals("")) {
                    if (form.attr("method").equals("POST")) {
                        ++postForms;
                    }
                } else {
                    ++getForms;
                }
            }

            f[0] = getForms;
            f[1] = postForms;
            return f;
        }
    }

    public static ArrayList<String> inputs(Element form) {
        ArrayList<String> in = new ArrayList();
        new String();
        Iterator var4 = form.getElementsByTag("input").iterator();

        while(var4.hasNext()) {
            Element input = (Element)var4.next();
            String string = input.attr("type").toString();
            in.add(string);
        }

        return in;
    }

    public static String post(int verify, Elements forms) throws IOException {
        String respuesta = new String();
        Response res;
        if (verify != 0) {
            for(Iterator var4 = forms.iterator(); var4.hasNext(); respuesta = respuesta + "\t" + res.body() + "\n") {
                Element f = (Element)var4.next();
                res = ((FormElement)f).submit().data("Internet avanzado", "Practica #1").header("Matricula", "20160338(10128009)").execute();
            }
        } else {
            respuesta = "\tTHERE'S NO POST FORMS IN THE DOCUMENT";
        }

        return respuesta;
    }

    public static void print(String string) {
        System.out.println(string);
    }
}
