package cn.tedu.cloudnote.util;

import java.io.Serializable;

public class LoginResult implements Serializable {
	//返回前端状态值,0-登录成功,1-用户名不存在,2-密码不符
	private Integer status;
	//返回前端信息
	private String msg;
	//返回数据
	private Object data;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "LoginResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
