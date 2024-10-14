package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzListener implements ServletContextListener {

    private Scheduler scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();

            var job = JobBuilder.newJob(Job_VerificadorCarrinhosExpirados.class)
                    .withIdentity("Job_VerificadorCarrinhosExpirados", "grupo1")
                    .build();
            var job2 = JobBuilder.newJob(Job_ValidadorOperadoraCredito.class)
                    .withIdentity("Job_ValidadorOperadoraCredito", "grupo2")
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("meuTrigger", "grupo1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(1)
                            .repeatForever())
                    .build();
            Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("meuTrigger2", "grupo2")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(15)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.scheduleJob(job2, trigger2);

            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (scheduler != null) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
