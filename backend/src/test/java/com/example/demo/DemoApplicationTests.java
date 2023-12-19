package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.example.demo.entity.Answer;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testJpa() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());
		System.out.println("Size Test pass");

		Question q = all.get(0);
		assertEquals("Sample Subject", q.getSubject());
		System.out.println("Subject Test pass");
	}

	@Test
	void testFindById() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if (oq.isPresent()) {
			Question q = oq.get();
			assertEquals("Sample Content", q.getContent());
			System.out.println("find by id Pass");
		}
	}

	@Test
	void testFindBySubject() {
		Question q = this.questionRepository.findBySubject("Sample Subject 2");
		assertEquals(2, q.getId());
		System.out.println("find by subject pass");
	}
	@Test
	void testFindBySubjectAndContent() {
		Question q = this.questionRepository.findBySubjectAndContent("Sample Subject 2", "Sample Content 2");
		assertEquals(2, q.getId());
		System.out.println("find by subject and content pass");
	}

	@Test
	void testFindBySubjectLike() {
		List<Question> qList = this.questionRepository.findBySubjectLike("%2");
		Question q = qList.get(0);
		assertEquals("Sample Subject 2", q.getSubject());
		System.out.println("find by subject like pass");
	}

	@Test
	void testSetSubject() {
		Optional<Question> oq =this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("edited subject");
		this.questionRepository.save(q);
	}

	@Test
	void testDeleteQuestion() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
		System.out.println("Delete Question Pass");
	}

	@Test
	void testCreateAnswer() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("created by test");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
		System.out.println("pass create answer");
	}

	@Test
	void testGetAnswerByID() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
		System.out.println("pass get answer by id");
	}

	@Test
	@Transactional
	void testGetAnswerListByQuestion() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		List<Answer> aList = q.getAnswerList();

		assertEquals(1, aList.size());

		Answer a = aList.get(0);
		assertEquals("created by test", a.getContent());

		System.out.println("get answer list by question pass");

	}

}
