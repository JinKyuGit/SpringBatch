package com.java.main.batch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.main.batch.vo.AdminVo;

@Mapper
public interface AdminMapper {

	public List<AdminVo> selectAdminList() throws Exception;
}
