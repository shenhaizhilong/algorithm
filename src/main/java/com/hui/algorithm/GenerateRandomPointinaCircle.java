package com.hui.algorithm;

/**
 *
 *
 *
 * 478. Generate Random Point in a Circle
 * DescriptionHintsSubmissionsDiscussSolution
 * Given the radius and x-y positions of the center of a circle, write a function randPoint which generates a uniform random point in the circle.
 *
 * Note:
 *
 * input and output values are in floating-point.
 * radius and x-y position of the center of the circle is passed into the class constructor.
 * a point on the circumference of the circle is considered to be in the circle.
 * randPoint returns a size 2 array containing x-position and y-position of the random point, in that order.
 * Example 1:
 *
 * Input:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[1,0,0],[],[],[]]
 * Output: [null,[-0.72939,-0.65505],[-0.78502,-0.28626],[-0.83119,-0.19803]]
 * Example 2:
 *
 * Input:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[10,5,-7.5],[],[],[]]
 * Output: [null,[11.52438,-8.33273],[2.46992,-16.21705],[11.13430,-12.42337]]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has three arguments, the radius, x-position of the center, and y-position of the center of the circle. randPoint has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 *
 * https://blogs.sas.com/content/iml/2016/03/30/generate-uniform-2d-ball.html
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/10/6 9:09
 */
public class GenerateRandomPointinaCircle {

    private double radius;
    private double x_center;
    private double y_center;
    private double r_square;
    public GenerateRandomPointinaCircle(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
        this.r_square =this.radius*this.radius;
    }


    //Rejection sampling
    public double[] randPoint() {
        double x = 0.0D;
        double y = 0.0D;

        do {
            x = (2*Math.random() -1)*this.radius;   //x in  [-1,1)*r
            y = (2*Math.random() -1)*this.radius;  //y in  [-1,1)*r
        }while ( x*x + y*y > this.r_square);   //x,y not  in  circle

        return new double[]{x + x_center, y + y_center};
    }




    /**
     *polar coordinates
     * https://leetcode.com/problems/generate-random-point-in-a-circle/discuss/154037/Polar-Coordinates-10-lines
     * http://www.anderswallin.net/2009/05/uniform-random-points-in-a-circle-using-polar-coordinates/
     *
     * https://stats.stackexchange.com/questions/120527/simulate-a-uniform-distribution-on-a-disc
     *
     *https://stackoverflow.com/questions/5837572/generate-a-random-point-within-a-circle-uniformly/50746409#50746409
     * You want the proportion of points to be uniformly proportional to area rather than distance to the origin.
     * Since area is proportional to the squared distance, generate uniform random radii and take their square roots
     * S = Pi*r^2
     *
     *
     *
     * The basic premise is here that you can create a variable with a desired distribution from a uniform by mapping the uniform by the inverse function of the cumulative distribution function of the desired probability density function. Why? Just take it for granted for now, but this is a fact.
     *
     * Here's my somehwat intuitive explanation of the math. The density function f(r) with respect to r has to be proportional to r itself. Understanding this fact is part of any basic calculus books. See sections on polar area elements. Some other posters have mentioned this.
     *
     * So we'll call it f(r) = C*r;
     *
     * This turns out to be most of the work. Now, since f(r) should be a probability density, you can easily see that by integrating f(r) over the interval (0,R) you get that C = 2/R^2 (this is an exercise for the reader.)
     *
     * Thus, f(r) = 2*r/R^2
     *
     * OK, so that's how you get the formula in the link.
     *
     * Then, the final part is going from the uniform random variable u in (0,1) you must map by the inverse function of the cumulative distribution function from this desired density f(r). To understand why this is the case you need to find an advanced probability text like Papoulis probably (or derive it yourself.)
     *
     * Integrating f(r) you get F(r) = r^2/R^2
     *
     * To find the inverse function of this you set u = r^2/R^2 and then solve for r, which gives you r = R * sqrt(u)
     *
     * This totally makes sense intuitively too, u = 0 should map to r = 0. Also, u = 1 shoudl map to r = R. Also, it goes by the square root function, which makes sense and matches the link.
     *
     *
     * @return
     */
    public double[] randPoint2() {
        //https://blogs.sas.com/content/iml/2016/03/30/generate-uniform-2d-ball.html
        double rho = Math.sqrt(Math.random())*this.radius;  //  radius proportional to sqrt(U), U~U(0,1)
        double theta = Math.random()*2*Math.PI;  // angle theta is uniform
        double x = rho*Math.cos(theta);
        double y = rho*Math.sin(theta);
        return new double[]{x + this.x_center, y + this.y_center};
    }


    /**
     *
     * http://mathworld.wolfram.com/SphericalCoordinates.html
     *http://mathworld.wolfram.com/SpherePointPicking.html
     * https://karthikkaranth.me/blog/generating-random-points-in-a-sphere/
     * SpherePointPicking
     * @param radius
     * @return
     */
    public double[] randPointInOneSphere(double radius)
    {
        double theta = 2*Math.PI*Math.random();
        double phi = Math.acos(2*Math.random() -1);
        double rho = radius*Math.cbrt(Math.random());
        double sinTheta = Math.sin(theta);
        double cosTheta = Math.cos(theta);
        double sinPhi = Math.sin(phi);
        double cosPhi = Math.cos(phi);
        double x = rho*cosTheta*sinPhi;
        double y = rho*sinTheta*sinPhi;
        double z = rho*cosPhi;
        return new double[]{x,y,z};

    }
}
