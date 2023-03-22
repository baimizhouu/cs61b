public class Planet{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	public Planet(double xP, double yP, double xV,double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet i){
		double dis;
		dis = Math.sqrt(Math.pow(this.xxPos-i.xxPos,2)+Math.pow(this.yyPos-i.yyPos,2));
		return dis;
	}
	public double calcForceExertedBy(Planet p){
		double F;
		double G = 6.67 * Math.pow(10,-11);
		double r2;
		r2 = Math.pow(this.calcDistance(p),2);
		F = G * this.mass * p.mass / r2;
		return F;
	}
	public  double calcForceExertedByX(Planet p) {
		double Fx;
		double dx = p.xxPos - this.xxPos;
		double r = this.calcDistance(p);
		double F = this.calcForceExertedBy(p);
		Fx = F * dx / r;
		return Fx;
	}
	public  double calcForceExertedByY(Planet p) {
		double Fy;
		double dy = p.yyPos - this.yyPos;
		double r = this.calcDistance(p);
		double F = this.calcForceExertedBy(p);
		Fy = F * dy / r;
		return Fy;
	}
	public double calcNetForceExertedByX(Planet[] planets){
		int l = planets.length;
		double netFx = 0;
		for(int i = 0;i < l;i++) {
			if(this == planets[i]){
				continue;
			}else{
				netFx = netFx + this.calcForceExertedByX(planets[i]);
			}
		}
		return netFx;
	}
	public double calcNetForceExertedByY(Planet[] planets){
		int l = planets.length;
		double netFy = 0;
		for(int i = 0;i < l;i++) {
			if(this == planets[i]){
				continue;
			}else{
				netFy = netFy + this.calcForceExertedByY(planets[i]);
			}
		}
		return netFy;
	}
	public void update(double dt, double Fx ,double Fy){
		double ax = Fx / this.mass;
		double ay = Fy / this.mass;
		this.xxVel = this.xxVel + ax * dt;
		this.yyVel = this.yyVel + ay * dt;
		this.xxPos = this.xxVel * dt + this.xxPos;
		this.yyPos = this.yyVel * dt + this.yyPos;
	}
}