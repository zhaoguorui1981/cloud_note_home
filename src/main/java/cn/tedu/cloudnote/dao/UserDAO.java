package cn.tedu.cloudnote.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.User;
@Repository("UserDAO")
public interface UserDAO extends Serializable {
	public User findUserByName(String name);
	public void save(User u);
}
