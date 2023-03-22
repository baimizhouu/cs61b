public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int n = in.readInt();
        double raius = in.readDouble();
        Planet[] list = new Planet[n];
        for(int i = 0; i < n ; i++){
            list[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return list;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-100,100);
        StdDraw.clear();
    }
}