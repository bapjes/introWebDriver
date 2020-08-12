package bIntro;

import static java.lang.Float.parseFloat;

public class Float {

    public static void main(String[] args) {
        try {
            String p = "$2,999.00";
            String l;
            l= (p.replace("$", "").replace(",",""));


            float f = parseFloat(l);



            System.out.println("Float : " +f);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }



}
