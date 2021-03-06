package edu.berkeley.cs.amplab.carat.android.storage;

import java.io.Serializable;

import edu.berkeley.cs.amplab.carat.android.CaratApplication;
import edu.berkeley.cs.amplab.carat.android.CaratApplication.Type;

/**
 * Simple container class for Hog/Bug data to save memory.
 * @author Eemil Lagerspetz
 *
 */
public class SimpleHogBug implements Serializable{
    /**
     * Auto-generated UID for serialization
     */
    private static final long serialVersionUID = 8272459694607111058L;
    
    private Type type = null;
    
    public Type getType(){
        return type;
    }
    
    public boolean isBug(){ return type == Type.BUG; }
    
    public SimpleHogBug(String appName, Type type){
        this.type = type;
        if (type == Type.OS)
            appPriority = CaratApplication.importanceString(CaratApplication.IMPORTANCE_SUGGESTION);
        this.appName = appName;
    }
    
    
    private String appName; // optional
    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }
    /**
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }
    /**
     * @return the wDistance
     */
    public double getwDistance() {
        return wDistance;
    }
    /**
     * @param wDistance the wDistance to set
     */
    public void setwDistance(double wDistance) {
        this.wDistance = wDistance;
    }
    /**
     * @return the expectedValue
     */
    public double getExpectedValue() {
        return expectedValue;
    }
    /**
     * @param expectedValue the expectedValue to set
     * TODO: FAKE ERROR
     */
    public void setExpectedValue(double expectedValue) {
        this.expectedValue = expectedValue;
    }
    /**
     * @return the expectedValueWithout
     */
    public double getExpectedValueWithout() {
        return expectedValueWithout;
    }
    /**
     * @param expectedValueWithout the expectedValueWithout to set
     * TODO: FAKE ERROR
     */
    public void setExpectedValueWithout(double expectedValueWithout) {
        this.expectedValueWithout = expectedValueWithout;
    }
    /**
     * @return the appLabel
     */
    public String getAppLabel() {
        return appLabel;
    }
    /**
     * @param appLabel the appLabel to set
     */
    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }
    /**
     * @return the appPriority
     */
    public String getAppPriority() {
        return appPriority;
    }
    /**
     * @param appPriority the appPriority to set
     */
    public void setAppPriority(String appPriority) {
        this.appPriority = appPriority;
    }
    private double wDistance; // optional
    private double expectedValue; // optional
    private double expectedValueWithout; // optional
    private String appLabel; // optional
    private String appPriority; // optional
    
    // TODO: FAKE ERROR
    // error of with dist in %/s
    private double error = 0;
    // error of without dist in %/s
    private double errorWithout = 0;
    
    private int samples = -1;
    
    private int samplesWithout = -1;
    
    public double getError(){ return error;}
    public void setError(double error){this.error = error;}
    public double getErrorWithout(){ return errorWithout; }
    public void setErrorWithout(double error){this.errorWithout = error;}
    
    
    public String textBenefit() {
        double ev = getExpectedValue();
        double evWo = getExpectedValueWithout();
        double error = getError();
        double errorWo = getErrorWithout();
        return textBenefit(ev, error, evWo, errorWo);
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(double samples) {
        this.samples = (int) samples;
    }

    public int getSamplesWithout() {
        return samplesWithout;
    }

    public void setSamplesWithout(double samplesWithout) {
        this.samplesWithout = (int) samplesWithout;
    }
    
    public static String textBenefit(double ev, double error, double evWo, double errorWo){
        double benefit = 100.0 / evWo - 100.0 / ev;

        int min = (int) (benefit / 60);
        int hours = (int) (min / 60);
        min -= hours * 60;

        double minimumBenefit = 100 / (evWo + errorWo) - 100 / (ev - error);

        int errorMins = (int) ((benefit - minimumBenefit) / 60);

        return hours + "h " + min + "m \u00B1 " + errorMins + "m";
    }
    
    public static String textError(double ev, double error, double evWo, double errorWo){
        double benefit = 100.0 / evWo - 100.0 / ev;

        int min = (int) (benefit / 60);
        int hours = (int) (min / 60);
        min -= hours * 60;

        double minimumBenefit = 100 / (evWo + errorWo) - 100 / (ev - error);

        int errorMins = (int) ((benefit - minimumBenefit) / 60);

        return errorMins + "m";
    }
}
