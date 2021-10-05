package com.bookpartner.Job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component(value="libroScheduler")
public class LibroScheduler  implements ServletContextListener{


	@Autowired 
	private ApplicationContext context;	
	
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;
	
	@PostConstruct
	public void start() throws SchedulerException{
		
		/*	
	    - 매일 오전 9시에 수행 : 0 0 9 * * ?
	    - 매월 10일 오전 9시에 수행 : 0 0 9 10 * ?
	    - 매주 일요일 오전 9시에 수행 : 0 0 9 ? * 1
	    - 매월 두번재 일요일 오전 9시에 수행 : 0 0 9 ? * 1#2
	      ( [실행할 요일]#[n번째] )
	    - 매시간 10분간격으로 수행 : 0 10 * * * ?
			"0 0 12 ? * WED" : "매 수요일 오후 12시"
			"0 0/5 * * * ?" : "매 5분간격 0초에(즉, 5분0초, 10분0초, 15분0초, ...)"
			"10 0/5 * * * ?" : "매 5분간격 10초(즉, 0분10초, 5분10초, 10분10초, ...)"
			"0 30 10-12 ? * WED,FRI" : "매 수요일, 금요일의 10:30분, 11:30분, 12:30분
			0/1 * * * * ?         매 1초 간격
			0 0/1 * * * ?         매 1분 간격
			0 0 0/1 * * ?         매 1시간 간격
			0 0 0 * * ?            매일 0시 마다
			0 0 0 1 * ?            매월 1일 마다
			0 0 0 1,10,20 * ?    매월 1일, 10일, 20일 마다
	*/		

			String runMode = System.getProperty("spring.profiles.active", "real");
			
			if(runMode.equals("real")){
				
			       schedulerFactory = new StdSchedulerFactory();
			       scheduler = schedulerFactory.getScheduler();
			       scheduler.start();
			       
			       JobDataMap newJobDataMap = new JobDataMap();
			       
			       newJobDataMap.put("context", context);


			       // 2021-06-09, book-partner에서 다음북 생성 로직 중단 (다음북 데이터는 join.libro.co.kr 서버에서 생성됨.)
			       // 다음북 서비스 스케쥴. 다음북(shop) 서비스에서 새벽2시경에 데이터 가져감.
//			       JobDetail daumBookJob = JobBuilder.newJob(DaumBookJob.class).withIdentity("daumBookJob").setJobData(newJobDataMap).build();
//			       Trigger daumBookTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 10 0 * * ?")).build();
			       //Trigger daumBookTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?")).build();

			       // 네이버북 서비스 스케쥴
			       JobDetail naverBookJob = JobBuilder.newJob(NaverBookJob.class).withIdentity("naverBookJob").setJobData(newJobDataMap).build();
			       Trigger naverBookTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 40 1 * * ?")).build();
//			       Trigger naverBookTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 50 15 * * ?")).build();
			       
			       // 2021-06-09, book-partner에서 다음북 생성 로직 중단 (다음북 데이터는 join.libro.co.kr 서버에서 생성됨.) 
//			       scheduler.scheduleJob(daumBookJob, daumBookTrigger);
			       scheduler.scheduleJob(naverBookJob, naverBookTrigger);
			       
			       
			}

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	      try
	        {
	          if(scheduler!=null){
	        	  scheduler.shutdown(true);
	        	  Thread.sleep(1000);
	          }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}

}
