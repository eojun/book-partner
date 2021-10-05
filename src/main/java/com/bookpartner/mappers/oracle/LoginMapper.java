package com.bookpartner.mappers.oracle;

import com.bookpartner.config.annotation.Oracle;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
@Oracle
public interface LoginMapper {
	
	public CaseInsensitiveMap selectAdminById(Map param);
	

	public String selectSHA256(String param);
	

}
