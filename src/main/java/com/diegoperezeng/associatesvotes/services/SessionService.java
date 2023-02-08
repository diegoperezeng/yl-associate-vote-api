package com.diegoperezeng.associatesvotes.services;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegoperezeng.associatesvotes.entities.Session;
import com.diegoperezeng.associatesvotes.repositories.SessionRepository;
import com.diegoperezeng.associatesvotes.services.config.TopicResult;
import com.diegoperezeng.associatesvotes.utils.ServiceUtils;

@Service
public class SessionService {

	// Method created to reuse the database server messages handler and make code
	// more clean
	ServiceUtils serviceUtils = new ServiceUtils();

	private final SessionRepository sessionRepository;

	public SessionService(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	public List<Session> getAllSessions() {
		return serviceUtils.handleRepositoryCall(() -> sessionRepository.findAll());
	}

	public Session findSessionById(Long id) {
		return serviceUtils.handleRepositoryCall(() -> sessionRepository.findById(id).orElse(null));
	}
	

	// The method sessionResult() is used to get the result of a session.
	// Item: Contabilizar os votos e dar o resultado da votação na pauta
	//
	public TopicResult sessionResult(Long topicId) {
		  Session session = serviceUtils.handleRepositoryCall(() -> sessionRepository.findById(topicId).orElse(null));
		  if (session == null) {
		    return null;
		  }
		  TopicResult topicResult = new TopicResult();
		  
		  topicResult.setTitle("Resultado da Pauta");
		  topicResult.setTopicId(session.getTopicId());
		  topicResult.setVoteCountYes(session.getVoteCountYes());
		  topicResult.setVoteCountNo(session.getVoteCountNo());
		  return topicResult;
		}
	

	
	// Obs: The LocalDateTime should be provide as '2023-03-30 23:45:18'
	//
	// The method saveSession() is used to create a new session for a topic.
	// Item: Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
	//
	@Transactional
	public void saveSession(Long topicId, LocalDateTime startTime, LocalDateTime endTime, Boolean isOpen) throws ConstraintViolationException {
		Session session = new Session();
		session.setTopicId(topicId);
		session.setStartTime(startTime);
		session.setEndTime(endTime);
		session.setCreatedAt(LocalDateTime.now());
		session.setIsOpen(isOpen);
		session.setVoteCountYes(0);
		session.setVoteCountNo(0);
		
		serviceUtils.handleRepositoryCall(() -> sessionRepository.save(session));

	}
}
