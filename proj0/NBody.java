public class NBody {
    public NBody() {
    }

    public static double readRadius(String var0) {
        In var1 = new In(var0);
        int var2 = var1.readInt();
        double var3 = var1.readDouble();
        return var3;
    }

    public static Planet[] readPlanets(String var0) {
        In var1 = new In(var0);
        int var2 = var1.readInt();
        double var3 = var1.readDouble();
        Planet[] var5 = new Planet[var2];

        for (int var6 = 0; var6 < var2; ++var6) {
            var5[var6] = new Planet(var1.readDouble(), var1.readDouble(), var1.readDouble(), var1.readDouble(), var1.readDouble(), var1.readString());
        }

        return var5;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int n = planets.length;

        String imageToDraw1 = "/images/starfield.jpg";

        StdDraw.setScale(-radius,radius);
        /* Clears the drawing window. */
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        /* Stamps three copies of advice.png in a triangular pattern. */


        double time = 0;
        while(time <= T){
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for(int i = 0 ; i < n ; i++ ){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0 ; i < n ; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
                StdDraw.picture(0, 0, imageToDraw1);
            }
            for(int i = 0; i < n; i++){
                    planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
