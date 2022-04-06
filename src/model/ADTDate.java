package model;

public class ADTDate implements IDate{
    private int theDay;
    private int theMonth;
    private int theYear;
    private int elapsedDays;

    private static int daysInMonth[] = {31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    public ADTDate()
    {
        this.theDay = 1;
        this.theMonth = 1;
        this.theYear = 1970;
        this.elapsedDays = 0;
    }

    public ADTDate(int dy, int mnth, int yr)
    {
        this.theDay = dy;
        this.theMonth = mnth;
        this.theYear = yr;
    }

    @Override
    public void setDay(int day) {
        this.theDay = day;
    }

    @Override
    public int getDay() {
        return this.theDay;
    }

    @Override
    public void setMonth(int month) {
        this.theMonth = month;
    }

    @Override
    public int getMonth() {
        return this.theMonth;
    }

    @Override
    public void setYear(int year) {
        this.theYear = year;
    }

    @Override
    public int getYear() {
        return this.theYear;
    }

    @Override
    public void setElapsedDays() {
        // Check the month. Anything in January is just
        // the current date
        if(this.theMonth == 1){
            this.elapsedDays = (this.theDay);
        }
        else {
            this.elapsedDays = (this.theDay) + (daysInMonth[this.theMonth-2]);
        }

        if (isLeapYear(this.theYear))
        {
            this.elapsedDays++;
        }
    }

    @Override
    public void setElapsedDays(int dd, int mm, int yyyy) {
        int tempDaysElapsed = 0;
        if(mm == 1){
            tempDaysElapsed = dd;
        }
        else {
            tempDaysElapsed = dd + (daysInMonth[mm-2]);
        }
        if (isLeapYear(this.theYear))
        {
            tempDaysElapsed++;
        }
        this.elapsedDays = tempDaysElapsed - this.elapsedDays;
    }

    @Override
    public int getElapsedDays() {
        return this.elapsedDays;
    }

    @Override
    public void setDate(int dd, int mm, int yyyy) {
        this.theDay = dd;
        this.theMonth = mm;
        this.theYear = yyyy;
    }

    @Override
    public boolean isLeapYear(int yyyy) {
        if (yyyy%100 == 0)
        {
            if (yyyy%400 == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (yyyy%4 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        String msg = "";
        msg = String.format("%02d/%02d/%02d",this.theDay,this.theMonth, this.theYear);
        return msg;
    }
}
