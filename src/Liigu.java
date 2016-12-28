
/**
 * Created by marko on 16/11/2016.
 */
public class Liigu {
    boolean randset;
    double a = 800/460000;
    double b = 4;
    double c = 0;
    public double diagonaal(double x){
        double y = x+1;
        return y;
    }

    public double random(){
        double r = Math.random()*100;
        return r;
    }
    public double parapool(double x){
        double y = (a*Math.pow(x,2)) - (b*x) - c;
        System.out.println(y);
        return y;
    }
}

