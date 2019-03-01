package com.netease.it.job;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author dujiayong
 * @date 2019/02/27
 **/
public class DumbJob2 implements Job {

    private String jobSays;
    private float myFloatValue;
    private ArrayList myStateData;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getMergedJobDataMap();

        myStateData.add(new Date());

        System.out.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is " + myFloatValue + ", state " + myStateData.toString());
    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public void setMyFloatValue(float myFloatValue) {
        this.myFloatValue = myFloatValue;
    }

    public void setMyStateData(ArrayList myStateData) {
        this.myStateData = myStateData;
    }
}
