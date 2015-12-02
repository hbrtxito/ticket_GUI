
package com.company;

import java.util.Date;

/**
 *
 * @author
 */
public class ResolvedTicket extends Ticket {

    private String resolution;
    private Date resolutionDate;

    public String getResolution() {
        return resolution;
    }

    public ResolvedTicket(String desc, int p, String rep, Date date, Date resolutionDate, String resolution) {
        super(desc, p, rep, date);
        this.resolutionDate = resolutionDate;
        this.resolution = resolution;
    }

    public String toString() {
        //super(desc, );
        return ("ID= " + this.ticketID + " Issued: " + this.getDescription() + " Priority: "
                + this.getPriority() + " Reported by: "
                + this.getReporter() + " Reported on: " + this.getDateReported()
                + "Resolution: " + this.getResolution() + "Resolution Date: " + this.getResolutionDate());
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

}
