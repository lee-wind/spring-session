package com.wind.springsession.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public interface JobTask extends Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}
