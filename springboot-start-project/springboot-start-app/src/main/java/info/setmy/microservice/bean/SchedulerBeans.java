package info.setmy.microservice.bean;

import info.setmy.microservice.scheduler.ExampleSchedulerJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerBeans {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(ExampleSchedulerJob.class)
            .withIdentity("myJob")
            .storeDurably()
            .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity("myTrigger")
            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(60)
                .repeatForever())
            .build();
    }
}
