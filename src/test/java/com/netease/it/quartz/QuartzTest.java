package com.netease.it.quartz;

import com.netease.it.job.HelloJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * @author dujiayong
 * @date 2019/02/27
 **/
public class QuartzTest {

    public static void main(String[] args) {
        try {
            // 从工厂中获取一个调度器实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // 启动调度器
            scheduler.start();

            // 定义一个任务，并且把它与我们的HelloJob.class联系起来
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            // 触发这个job现在运行，并且每40秒重复一次
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();

            // 告诉quartz使用定义的trigger调度任务
            scheduler.scheduleJob(job, trigger);

            Thread.sleep(60000);

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
