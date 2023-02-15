package telran.spring.data;

import java.util.Arrays;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import telran.spring.data.model.Mark;
import telran.spring.data.model.Student;
import telran.spring.data.model.Subject;
import telran.spring.data.service.CollegeService;

@Component
public class RandomDbCreation {
	private static Logger LOG = LoggerFactory.getLogger(RandomDbCreation.class);
	String names[] = { "Abraham", "Sarah", "Itshak", "Rahel", "Asaf", "Yacob", "Rivka", "Yosef", "Benyanim", "Dan",
			"Ruben", "Moshe", "Aron", "Yehashua", "David", "Salomon", "Nefertity", "Naftaly", "Natan", "Asher" };
	String subjects[] = { "Java core", "Java Technologies", "Spring Data", "Spring Security", "Spring Cloud", "CSS",
			"HTML", "JS", "React", "Material-UI" };
	private int id;

	@Autowired
	CollegeService collegeService;

	@PostConstruct
	void dbCreation() {

		id = 1;
		Arrays.stream(names).forEach(x -> {
			collegeService.addStudent(new Student(id++, x));
		});
		LOG.debug("Table students name successfuly added.");
		
		id = 1;
		Arrays.stream(subjects).forEach(x -> {
			collegeService.addSubject(new Subject(id++, x));
		});
		LOG.debug("Table subjects successfuly added.");

		Stream.iterate(1, i -> i).limit(100).forEach(i -> {
			collegeService.addMark(new Mark(getRandomNumber(1, names.length), getRandomNumber(1, subjects.length),
					getRandomNumber(60, 100)));
		});
		LOG.debug("Table marks successfuly added.");
	}

	private int getRandomNumber(int min, int max) {

		return (int) (min + Math.random() * (max - min + 1));
	}
}
