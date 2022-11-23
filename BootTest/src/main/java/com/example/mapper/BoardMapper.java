package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.entity.parkmaeil;

// MyBatis
@Repository
@Mapper
public interface BoardMapper {
   public List<parkmaeil> getLists(); // XML Mapper file
   public void boardInsert(parkmaeil vo);
}
