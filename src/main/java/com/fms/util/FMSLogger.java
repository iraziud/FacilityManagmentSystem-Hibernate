package com.fms.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

public class FMSLogger extends AppenderSkeleton {
    List<LoggingEvent> eventsList = new ArrayList<LoggingEvent>();

    public static PrintWriter out;
	public static final Logger log = Logger.getLogger("fms");

    @Override
    protected void append(LoggingEvent event) {
        if(out != null) {
        	out.println(event.getMessage());
        } else {
        	System.out.println(event.getMessage());
        }
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return false;
    }

}
