package com.diegoperezeng.associatesvotes.services;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegoperezeng.associatesvotes.entities.Section;
import com.diegoperezeng.associatesvotes.repositories.SectionRepository;
import com.diegoperezeng.associatesvotes.utils.ServiceUtils;

@Service
public class SectionService {

	// Method created to reuse the database server messages handler and make code
	// more clean
	ServiceUtils serviceUtils = new ServiceUtils();

	private final SectionRepository sectionRepository;

	public SectionService(SectionRepository sectionRepository) {
		this.sectionRepository = sectionRepository;
	}

	public List<Section> getAllSections() {
		return serviceUtils.handleRepositoryCall(() -> sectionRepository.findAll());
	}

	public Section findSectionById(Long id) {
		return serviceUtils.handleRepositoryCall(() -> sectionRepository.findById(id).orElse(null));
	}
	

	// The method sectionResult() is used to get the result of a section.
	// Item: Contabilizar os votos e dar o resultado da votação na pauta
	//
	public TopicResult sectionResult(Long topicId) {
		  Section section = serviceUtils.handleRepositoryCall(() -> sectionRepository.findById(topicId).orElse(null));
		  if (section == null) {
		    return null;
		  }
		  TopicResult topicResult = new TopicResult();
		  
		  topicResult.setTitle("Resultado da Pauta");
		  topicResult.setTopicId(section.getTopicId());
		  topicResult.setVoteCountYes(section.getVoteCountYes());
		  topicResult.setVoteCountNo(section.getVoteCountNo());
		  return topicResult;
		}
	

	
	// Obs: The Timestamp should be provide as '2023-03-30 23:45:18'
	//
	// The method saveSection() is used to create a new section for a topic.
	// Item: Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
	//
	@Transactional
	public Section saveSection(Long topicId, Timestamp startTime, Timestamp endTime, Boolean isOpen) throws ConstraintViolationException {
		Section section = new Section();
		section.setTopicId(topicId);
		section.setStartTime(startTime);
		section.setEndTime(endTime);
		section.setIsOpen(isOpen);
		
		return serviceUtils.handleRepositoryCall(() -> sectionRepository.save(section));

	}
}
