package com.hui.Geometry;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/13 10:38
 */
public class GeoPoint {

    public static double EARTH_RADIUS = 6378137.0D; // WGS-84, Radius of the earth . unit m.


    /**
     *https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
     * which is based on https://en.wikipedia.org/wiki/Haversine_formula
     *
     *
     *
     * http://www.census.gov/cgi-bin/geo/gisfaq?Q5.1
     *
     * where R is the radius of the Earth.
     *
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return  the distance between tow geo points.
     */
    public static double distance(double lng1, double lat1, double lng2, double lat2)
    {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(dLat/2)*Math.sin(dLat/2) + Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*Math.sin(dLng/2)*Math.sin(dLng/2);
        double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt( 1- a));
        double d = EARTH_RADIUS*c;  // distance in m
        return d;

    }

    public static void main(String[] args) {

        double lng1 = 116.532417D;
        double lng2 = 116.532455D;
        double lat1 = 39.962326D;
        double lat2 = 39.96217D;
        double[] points= {116.534607,39.965431,116.534264,39.965408}  ;
        System.out.println(distance(lng1, lat1, lng2, lat2));
        System.out.println(distance(points[0], points[1], points[2], points[3]));
    }
}
