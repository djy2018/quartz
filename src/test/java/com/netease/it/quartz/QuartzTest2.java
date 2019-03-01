package com.netease.it.quartz;

import com.netease.it.job.DumbJob;
import com.netease.it.job.DumbJob2;
import com.netease.it.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author dujiayong
 * @date 2019/02/27
 **/
public class QuartzTest2 {

    public static void main(String[] args) {
        try {
            // 从工厂中获取一个调度器实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // 启动调度器
            scheduler.start();

            // 定义一个任务，并且把它与我们的HelloJob.class联系起来
            JobDetail job = newJob(DumbJob2.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("jobSays","Hello World!")
                    .usingJobData("myFloatValue",3.141f)
                    .build();

            Map<String,List> map = new HashMap<String, List>();
            map.put("myStateData",new ArrayList());
            // 触发这个job现在运行，并且每40秒重复一次
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .usingJobData(new JobDataMap(map))
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();

            // 告诉quartz使用定义的trigger调度任务
            scheduler.scheduleJob(job, trigger);

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
