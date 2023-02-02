package com.diegoperezeng.associatesvotes.services;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegoperezeng.associatesvotes.entities.Topic;
import com.diegoperezeng.associatesvotes.repositories.TopicRepository;
import com.diegoperezeng.associatesvotes.utils.ServiceUtils;

@Service
public class TopicService {

	// Method created to reuse the database server messages handler and make code
	// more clean
	ServiceUtils serviceUtils = new ServiceUtils();

	private final TopicRepository TopicRepository;

	public TopicService(TopicRepository TopicRepository) {
		this.TopicRepository = TopicRepository;
	}

	public List<Topic> getAllTopics() {
		return serviceUtils.handleRepositoryCall(() -> TopicRepository.findAll());
	}

	public Topic findTopicById(Long id) {
		return serviceUtils.handleRepositoryCall(() -> TopicRepository.findById(id).orElse(null));
	}

	@Transactional
	public void saveTopic(String title, String description, Boolean openStatus) throws ConstraintViolationException {
		Topic topic = new Topic();
		topic.setTitle(title);
		topic.setDescription(description);
		topic.setOpenStatus(openStatus);

		serviceUtils.handleRepositoryCall(() -> TopicRepository.save(topic));

	}
}
