package cn.tedu.cloudnote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Share;
@Repository("shareDAO")
public interface ShareDAO {
	public int saveShareNote(Share share);
	public List<Share> findLikeKeyword(String keyword);
}
