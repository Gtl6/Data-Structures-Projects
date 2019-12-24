package Hashing;

import java.awt.geom.Point2D;

public class Circle {

  private double x;
  private double y;
  private double radius;
  
  public Circle(double x, double y, double radius) {
    this.radius = radius;
    this.x = x;
    this.y = y;
  }

  public Point2D.Double getLocation() {
    return new Point2D.Double(x, y);
  }
  
  public double getRadius() {
    return radius;
  }
  
  public int hashCode() {
    String xS = Double.toString(x);
    String yS = Double.toString(y);
    String rS = Double.toString(radius);
    String fullStr = xS + yS + rS;
    fullStr = fullStr.replace(".", "");
    return Integer.parseInt(fullStr);
    // Will this overflow? Yes, quite probably. Especially for large circles. But do I know a better way?
    // Not really. I'd need some number theory stuffs.
  }
}