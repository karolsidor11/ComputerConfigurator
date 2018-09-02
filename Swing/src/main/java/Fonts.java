import java.awt.*;

public class Fonts {
    Font font = new Font("Arial", Font.BOLD, 14);

    public static void main(String[] args) {


        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] fonts = graphicsEnvironment.getAvailableFontFamilyNames();

        for (String a : fonts) {
            System.out.println(a);
        }

        Font font =new Font("Tiger", Font.BOLD, 12);
        font.toString();



    }


}


