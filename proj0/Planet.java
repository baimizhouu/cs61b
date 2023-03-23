//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double var1, double var3, double var5, double var7, double var9, String var11) {
        this.xxPos = var1;
        this.yyPos = var3;
        this.xxVel = var5;
        this.yyVel = var7;
        this.mass = var9;
        this.imgFileName = var11;
    }

    public Planet(Planet var1) {
        this.xxPos = var1.xxPos;
        this.yyPos = var1.yyPos;
        this.xxVel = var1.xxVel;
        this.yyVel = var1.yyVel;
        this.mass = var1.mass;
        this.imgFileName = var1.imgFileName;
    }

    public double calcDistance(Planet var1) {
        double var2 = Math.sqrt(Math.pow(this.xxPos - var1.xxPos, 2.0D) + Math.pow(this.yyPos - var1.yyPos, 2.0D));
        return var2;
    }

    public double calcForceExertedBy(Planet var1) {
        double var4 = 6.67D * Math.pow(10.0D, -11.0D);
        double var6 = Math.pow(this.calcDistance(var1), 2.0D);
        double var2 = var4 * this.mass * var1.mass / var6;
        return var2;
    }

    public double calcForceExertedByX(Planet var1) {
        double var4 = var1.xxPos - this.xxPos;
        double var6 = this.calcDistance(var1);
        double var8 = this.calcForceExertedBy(var1);
        double var2 = var8 * var4 / var6;
        return var2;
    }

    public double calcForceExertedByY(Planet var1) {
        double var4 = var1.yyPos - this.yyPos;
        double var6 = this.calcDistance(var1);
        double var8 = this.calcForceExertedBy(var1);
        double var2 = var8 * var4 / var6;
        return var2;
    }

    public double calcNetForceExertedByX(Planet[] var1) {
        int var2 = var1.length;
        double var3 = 0.0D;

        for(int var5 = 0; var5 < var2; ++var5) {
            if (this != var1[var5]) {
                var3 += this.calcForceExertedByX(var1[var5]);
            }
        }

        return var3;
    }

    public double calcNetForceExertedByY(Planet[] var1) {
        int var2 = var1.length;
        double var3 = 0.0D;

        for(int var5 = 0; var5 < var2; ++var5) {
            if (this != var1[var5]) {
                var3 += this.calcForceExertedByY(var1[var5]);
            }
        }

        return var3;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw(){
        String dir = "images/";
        String filepath = dir+imgFileName;
        StdDraw.picture(xxPos,yyPos,filepath);
    }
}