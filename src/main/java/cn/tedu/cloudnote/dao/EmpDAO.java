package cn.tedu.cloudnote.dao;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Emp;

@Repository("empDAO")
public interface EmpDAO {
	public void saveEmp(Emp emp);
}
