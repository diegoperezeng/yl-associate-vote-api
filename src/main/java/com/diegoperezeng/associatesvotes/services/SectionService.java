package com.diegoperezeng.associatesvotes.services;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.ConstraintViolationException;

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

	public List<Section> getAllTopics() {
		return serviceUtils.handleRepositoryCall(() -> sectionRepository.findAll());
	}

	public Section findTopicById(Long id) {
		return serviceUtils.handleRepositoryCall(() -> sectionRepository.findById(id).orElse(null));
	}

	
	// Obs: The Timestamp should be provide as '2023-03-30 23:45:18'
	//
	@Transactional
	public void saveTopic(Long topicId, Timestamp startTime, Timestamp endTime) throws ConstraintViolationException {
		Section section = new Section();
		section.setTopicId(topicId);
		section.setStartTime(startTime);
		section.setEndTime(endTime);

		serviceUtils.handleRepositoryCall(() -> sectionRepository.save(section));

	}
}
