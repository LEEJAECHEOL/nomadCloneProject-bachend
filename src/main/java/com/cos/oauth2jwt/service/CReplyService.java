package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cos.oauth2jwt.domain.community.CReply;
import com.cos.oauth2jwt.domain.community.CReplyRepository;
import com.cos.oauth2jwt.handler.exception.NoDataException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CReplyService {
	private final CReplyRepository cReplyRepository;
	
	@Transactional
	public CReply 댓글저장(CReply cReply) {
		CReply cReplyEntity = cReplyRepository.save(cReply); // 실패하면 리턴까지 안가고 Exception이 뜬다.
		return cReplyEntity;
	}
	
	@Transactional
	public void 댓글삭제(long id) {
		cReplyRepository.deleteById(id); // 실패하면 리턴까지 안가고 Exception이 뜬다.
	}
	
	@Transactional(readOnly = true)
	public List<CReply> 전체댓글(){
		List<CReply> cReplys = cReplyRepository.findAll();
		return cReplys;
	}
	
	@Transactional(readOnly = true)
	public CReply 한건찾기(long id){
		CReply cReplyEntity = cReplyRepository.findById(id).orElseThrow(()->{
			throw new NoDataException("해당 댓글이 존재하지 않습니다.");
		});
		return cReplyEntity;
	}
	
}
