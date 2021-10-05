package com.bookpartner.Job;

import com.bookpartner.config.PartnerConfig;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class NaverBookJob extends Thread implements Job{
	
	private ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory.getLogger(NaverBookJob.class);	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		applicationContext = (ApplicationContext)dataMap.get("context");
		
		String runMode = System.getProperty("spring.profiles.active", "real");
		String fileDir = "";
		
		fileDir = PartnerConfig.NAVERBOOK_PUTFILE_DIR;

		logger.info("########## PartnerConfig.DAUMSHOP_PUTFILE_DIR :  " + fileDir);
		logger.info("########## NaverBook Schdule START ########## ");
		
		try {
			//NaverBookService naverBookService = applicationContext.getBean("naverBookService", NaverBookService.class);
			
			// 1. 서지
			try{
				logger.info("########## NaverBook Schdule 1. 서지 Start ########## ");
				//naverBookService.doNaverBookEntity(fileDir);
				logger.info("########## NaverBook Schdule 1. 서지 End ########## ");
			}
			catch (Exception e)	{
				 e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			// 2.판매상품정보
			try{
				Thread.sleep(1000 * 60); // 60초 쉬기
				logger.info("########## NaverBook Schdule 2. 판매상품정보 Start ########## ");
				//naverBookService.doNaverBookSaleProductEntity(fileDir);
				logger.info("########## NaverBook Schdule 2. 판매상품정보 End ########## ");
			}
			catch (Exception e)	{
				 e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			// 3.판매량 정보
			try{
				Thread.sleep(1000 * 60); // 60초 쉬기
				logger.info("########## NaverBook Schdule 3. 판매량 정보 Start ########## ");
				//naverBookService.doNaverBookSellingCount(fileDir);
				logger.info("########## NaverBook Schdule 3. 판매량 정보 End ########## ");
			}
			catch (Exception e)	{
				 e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			// 4.베스트셀러 상품정보
			try{
				Thread.sleep(1000 * 60); // 60초 쉬기
				logger.info("########## NaverBook Schdule 4. 베스트셀러 상품정보 Start ########## ");
				//naverBookService.doNaverBookBestsellerProductEntity(fileDir);
				logger.info("########## NaverBook Schdule 4. 베스트셀러 상품정보 End ########## ");
			}
			catch (Exception e)	{
				 e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			this.interrupt();
		}

			logger.info("########## NaverBook Schdule END ########## ");
		} 

}
