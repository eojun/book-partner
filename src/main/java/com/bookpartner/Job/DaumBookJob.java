package com.bookpartner.Job;

import kr.co.libro.commons.config.PartnerConfig;
import kr.co.libro.services.DaumBookService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class DaumBookJob extends Thread implements Job{
	
	private ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory.getLogger(DaumBookJob.class);	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		applicationContext = (ApplicationContext)dataMap.get("context");
		
		String runMode = System.getProperty("spring.profiles.active", "real");
		String fileDir = "";
		
		fileDir = PartnerConfig.DAUMSHOP_PUTFILE_DIR;
		logger.info("########## PartnerConfig.DAUMSHOP_PUTFILE_DIR :  " + fileDir);
		logger.info("########## DaumBook Schedule START ########## ");
		
		
		try {
			DaumBookService daumBookService = applicationContext.getBean("daumBookService", DaumBookService.class);
			
			// 연동명	1. 서지	파일명	book_업체이름_yyyymmdd.txt
			try{
				logger.info("########## DaumBook Schdule 1. 서지 Start ########## ");
				daumBookService.doGetDaumBookEntity(fileDir);
				logger.info("########## DaumBook Schdule 1. 서지 End ########## ");
			}
			catch (Exception e)	{
				 e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			// 연동명	2. 판매상품	파일명	booksub_업체이름_yyyymmdd.txt
			try{
				Thread.sleep(1000 * 60); // 300초 쉬기
				logger.info("########## DaumBook Schdule 2. 판매상품정보 Start ########## ");
				daumBookService.doGetDaumBookSaleProductEntity(fileDir);
				logger.info("########## DaumBook Schdule 2. 판매상품정보 End ########## ");
			}
			catch (Exception e)	{
				 e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			// 연동명	5. 책 카테고리	파일명	bookcategory_업체이름_yyyymm.txt 
			try{
				Thread.sleep(1000 * 60); // 60초 쉬기
				logger.info("########## DaumBook Schdule 3. 책 카테고리 Start ########## ");
				daumBookService.doGetDaumBookCategoryEntity(fileDir);
				logger.info("########## DaumBook Schdule 3. 책 카테고리 End ########## ");
			}
			catch (Exception e)	{
				 e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			// 연동명	7. 리뷰/평점	파일명	review_업체이름_yyyymm.txt 
			try{
				Thread.sleep(1000 * 60); // 60초 쉬기
				logger.info("########## DaumBook Schdule 4. 리뷰/평점 Start ########## ");
				daumBookService.doGetDaumBookReviewEntity(fileDir);
				logger.info("########## DaumBook Schdule 4. 리뷰/평점 End ########## ");
			}
			catch (Exception e)	{
				e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			// 연동명	8. 베스트셀러	파일명	bestseller_업체이름_yyyymmdd.txt  
			try{
				Thread.sleep(1000 * 60); // 60초 쉬기
				logger.info("########## DaumBook Schdule 5. 베스트셀러 Start ########## ");
				daumBookService.doGetDaumBookBestsellerEntity(fileDir);
				logger.info("########## DaumBook Schdule 5. 베스트셀러 End ########## ");
			}
			catch (Exception e)	{
				e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			
			// xml연동명 	11. 판매정보	yyyymmdd000000_selling.xml 
			try{
				Thread.sleep(1000 * 60); // 60초 쉬기
				logger.info("########## DaumBook Schdule 11. 판매정보(xml) Start ########## ");
				daumBookService.doGetDaumBookSellingXml(fileDir);
				logger.info("########## DaumBook Schdule 11. 판매정보(xml) End ########## ");
			}
			catch (Exception e)	{
				e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			// xml연동명 	12. 이벤트정보	yyyymmdd000000_event.xml 
			try{
				Thread.sleep(1000 * 60); // 60초 쉬기
				logger.info("########## DaumBook Schdule 12. 이벤트정보(xml) Start ########## ");
				daumBookService.doGetDaumBookEventXml(fileDir);
				logger.info("########## DaumBook Schdule 12. 이벤트정보(xml) End ########## ");
			}
			catch (Exception e)	{
				e.printStackTrace();
			}
			finally {
				this.interrupt();
			}
			
			// xml연동명 	13. 이벤트중인 책 정보	yyyymmdd000000_eventbook.xml 
			try{
				Thread.sleep(1000 * 60); // 60초 쉬기
				logger.info("########## DaumBook Schdule 13. 이벤트중인 책(xml) Start ########## ");
				daumBookService.doGetDaumBookEventBookXml(fileDir);
				logger.info("########## DaumBook Schdule 13. 이벤트중인 책(xml) End ########## ");
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

			logger.info("########## DaumBook Schdule END ########## ");
	} 
		
		
		

}
