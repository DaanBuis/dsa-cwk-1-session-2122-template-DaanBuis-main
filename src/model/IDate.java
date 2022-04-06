package model;

public interface IDate {
    /*
    Interface methods are by default abstract and public
     */
    void setDay(int day);
    int getDay();
    void setMonth(int month);
    int getMonth();
    void setYear(int year);
    int getYear();
    void setElapsedDays();
    void setElapsedDays(int dd, int mm, int yyyy);
    int getElapsedDays( );
    void setDate(int dd, int mm, int yyyy);
    boolean isLeapYear(int yyyy);
}
