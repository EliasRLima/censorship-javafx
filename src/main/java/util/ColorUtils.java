package util;

import java.awt.Color;
import java.util.ArrayList;


public class ColorUtils {
    
	
    public static final int [] [] colors  = {
            {-1,-10,-360,-0,-100,-10,-80},
            {1,0,360,0,100,0,50},
            {2,0,360,0,15,50,130},
            {3,0,360,0,15,130,210},
            {4,-15,15,15,100,50,255},
            {5,15,45,15,100,50,255},
            {6,45,75,15,100,50,255},
            {7,75,105,15,100,50,255},
            {8,105,135,15,100,50,255},
            {9,135,165,15,100,50,255},
            {10,165,195,15,100,50,255},
            {11,195,225,15,100,50,255},
            {12,225,255,15,100,50,255},
            {13,255,285,15,100,50,255},
            {14,285,315,15,100,50,255},
            {15,315,345,15,100,50,255},
            {16,345,375,15,100,50,255},
            {17,0,360,0,15,210,255} 
    };

    /**
     * Color names.
     */
    public static final String [] humanColors = {
            "nao reconhecida", 
            "preto",
            "cinza",
            "cinza claro",
            "vermelho",
            "laranja",
            "amarelo",
            "verde claro",
            "verde",
            "verde",
            "turquesa",
            "azul claro",
            "azul",
            "violeta",
            "violeta",
            "rosa",
            "vermelho",
            "branco",
        };

    /**
     * Convert from  RGB do HSV
     * 
     * @param r
     * @param g
     * @param b
     * @param hsv
     */
    public static void rgb2hsv(int r, int g, int b, int hsv[]) {

        int min; // Min. value of RGB
        int max; // Max. value of RGB
        int delMax; // Delta RGB value

        if (r > g) {
            min = g;
            max = r;
        } else {
            min = r;
            max = g;
        }
        if (b > max)
            max = b;
        if (b < min)
            min = b;

        delMax = max - min;

        float H = 0, S;
        float V = max;

        if (delMax == 0) {
            H = 0;
            S = 0;

        } else {
            S = delMax / (float) max;
            if (r == max)
                H = ((g - b) / (float) delMax) * 60;
            else if (g == max)
                H = (2 + (b - r) / (float) delMax) * 60;
            else if (b == max)
                H = (4 + (r - g) / (float) delMax) * 60;

        }

        hsv[0] = (int) (H);
        hsv[1] = (int) (S * 100);
        hsv[2] = (int) V;
    }

    /**
     * Return human color index {@link Color#colors}
     * 
     * @param r
     * @param g
     * @param b
     * @return
     */
    public static int [] getHumanColor(int r, int g, int b) {
        int [] res = {0, 0, 0, 0};
        int[] hsv = new int[3];
        rgb2hsv(r, g, b, hsv);
        for (int i = 1; i < colors.length; i++) {
            if (hsv[0] <= colors[i][2] && hsv[0] >= colors[i][1]
                    && hsv[1] <= colors[i][4] && hsv[1] >= colors[i][3]
                    && hsv[2] <= colors[i][6] && hsv[2] >= colors[i][5]) {
                res[3] = i;
                res[0] = hsv[0];
                res[1] = hsv[1];
                res[2] = hsv[2];
                return res;
            }

        }
        return res;
    }

}