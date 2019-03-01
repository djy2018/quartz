package com.netease.it.job;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author dujiayong
 * @date 2019/02/27
 **/
public class DumbJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getMergedJobDataMap();

        String jobSays = dataMap.getString("jobSays");
        float myFloatValue = dataMap.getFloatValue("myFloatValue");
        ArrayList state = (ArrayList)dataMap.get("myStateData");
        state.add(new Date());

        System.out.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is " + myFloatValue + ", state " + state.toString());
    }

}
