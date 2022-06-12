package com.java.main.common;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 * 공통 dao 입니다.
 * 상속받으셔서 사용해주세요.
 * 
 * 
 * @author jinkyu
 * 
 * 2019.11.19 mapper를 인터페이스를 사용하기 때문에, 해당 클래스 사용하지 않습니다.
 * 2019.11.28 해당 클래스 재사용. -> mapper 인터페이스 출동문제발생.
 *
 */


public class CommonDao extends SqlSessionDaoSupport {

	 @Autowired(required =false)
	 @Override
	 public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
	  super.setSqlSessionFactory(sqlSessionFactory);
	 }
	 
	 @Autowired(required = false)
	 @Override
	 public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
	  super.setSqlSessionTemplate(sqlSessionTemplate);
	 }

	 
	 public String nameSpace;
	 
	 public void SetNameSpace(String nameSpace) {
		 this.nameSpace = nameSpace;
	 }
	 

	 
	 //파라미터가 없는 selectOne
	 public <T> T selectOne(String queryId) {
		 
		 return getSqlSession().selectOne(nameSpace+"."+queryId);
		 
	 }
	 
	 
	 //파라미터가 있는 selectOne
	 public <T, P> T selectOne(String queryId, P p) {
		 
		 return getSqlSession().selectOne(nameSpace+"."+queryId, p);
		 
	 }
	 
	 //파라미터가 없는 selectList
	 public <T> List<T> selectList(String queryId) {
		 
		 return getSqlSession().selectList(nameSpace+"."+queryId);
	 }
	 
	 //파라미터가 있는 selectList
	 public <T, P> List<T> selectList(String queryId, P p) {
		 
		 return getSqlSession().selectList(nameSpace+"."+queryId, p);
	 }
	 
	 //파라미터가 없는 insert
	 public int insert(String queryId) {
		 
		 return getSqlSession().insert(nameSpace+"."+queryId);
	 }
	 
	 
	 //파라미터가 있는 insert
	 public <P>int insert(String queryId, P p) {
		 
		 return getSqlSession().insert(nameSpace+"."+queryId, p);
	 }
	 
	 //파라미터가 없는 update
	 public int update(String queryId) {
		 
		 return getSqlSession().update(nameSpace+"."+queryId);
	 }
	 
	 
	 //파라미터가 있는 update
	 public <P>int update(String queryId, P p) {
		 
		 return getSqlSession().update(nameSpace+"."+queryId, p);
	 }
	 
	 //파라미터가 없는 delete
	 public int delete(String queryId) {
		 
		 return getSqlSession().delete(nameSpace+"."+queryId);
	 }
	 
	 
	 //파라미터가 있는 delete
	 public <P>int delete(String queryId, P p) {
		 
		 return getSqlSession().delete(nameSpace+"."+queryId, p);
	 }
	
}
