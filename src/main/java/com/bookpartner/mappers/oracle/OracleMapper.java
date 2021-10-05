package com.bookpartner.mappers.oracle;

import com.bookpartner.config.annotation.Oracle;
import com.bookpartner.domain.adminpartner.AdminPartner;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
@Oracle
public interface OracleMapper {

	Integer selectIntString(String username);

	Integer selectIntString1(String username);

	String selectOneString(String rawPass);

	String selectMapStr(Map<String, Object> condition);

	void funccall1(AdminPartner libroCustomer2);

	void updateMap1(Map<String, Object> data);

	void funccall(AdminPartner libroCustomer2);

	int insert3(AdminPartner libroCustomer);


	AdminPartner selDomainOne1(String loginid);

	AdminPartner selDomainOne2(String loginid);
}