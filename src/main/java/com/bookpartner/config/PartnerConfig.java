package com.bookpartner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PartnerConfig {

	@Value("${partner.daumshop.put.file.dir}")
	private String daumshopPutFileDir = null;

	@Value("${partner.naverbook.put.file.dir}")
	private  String naverbookPutFileDir = null;

	@PostConstruct
    public void registerInstance() {
		DAUMSHOP_PUTFILE_DIR = daumshopPutFileDir;
		NAVERBOOK_PUTFILE_DIR = naverbookPutFileDir;
	}

	public static String DAUMSHOP_PUTFILE_DIR = null;
	public static String NAVERBOOK_PUTFILE_DIR = null;
}
