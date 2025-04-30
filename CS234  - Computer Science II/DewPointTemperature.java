
public class DewPointTemperature {
    private static final double  a =17.27;
    private static final  double b =237.7;
    public double RH;
    public double T;
    
    public DewPointTemperature(double RH,double T){
     this.RH=RH;
     this.T=T;
    }
    
    public double getDewPoint(){
        double f =((a*T)/(b+T)+Math.log(RH));
        return (b*f)/(a-f);
    }
}
