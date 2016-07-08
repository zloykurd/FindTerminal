package com.example.zloykurd.findappterminals;

/**
 * Created by ZloykurD on 16.06.2016.
 */
public class Point {
    String LOG_TAG = "zloykurdClass";
    int point_id;
    String point_name;
    String date_point;
    double lat;
    double longt;

    public Point(){

    }

    public Point(int point_id, String point_name, String date_point, double lat, double longt) {
        this.point_id = point_id;
        this.point_name = point_name;
        this.date_point = date_point;
        this.lat = lat;
        this.longt = longt;
    }

    public Point(String point_name, String date_point, double lat, double longt) {
        this.point_name = point_name;
        this.date_point = date_point;
        this.lat = lat;
        this.longt = longt;
    }

    public int getPoint_id() {
        return point_id;
    }

    public void setPoint_id(int point_id) {
        this.point_id = point_id;
    }

    public String getPoint_name() {
        return point_name;
    }

    public void setPoint_name(String point_name) {
        this.point_name = point_name;
    }

    public String getDate_point() {
        return date_point;
    }

    public void setDate_point(String date_point) {
        this.date_point = date_point;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    @Override
    public String toString() {
        return "Point{" +
                "point_id=" + point_id +
                ", point_name='" + point_name + '\'' +
                ", date_point='" + date_point + '\'' +
                ", lat=" + lat +
                ", longt=" + longt +
                '}';
    }
}
