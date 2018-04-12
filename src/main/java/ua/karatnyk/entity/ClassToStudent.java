package ua.karatnyk.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.enumerations.LetterToGrade;

@Entity
@Table(name = "class_for_student", indexes = @Index(columnList = "grade"))
@Getter
@Setter
@NoArgsConstructor
public class ClassToStudent extends BaseEntity{
	
	
	@Enumerated(EnumType.STRING)
	private Grade grade;
	
	@Column(name = "letter")
	@Enumerated(EnumType.STRING)
	private LetterToGrade letterToGrade;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_teacher_id")
	private UserEntity userClassTeacher;
	
	@OneToMany(mappedBy = "classStudent")
	List<UserEntity> userEntities = new ArrayList<>();

	@Override
	public String toString() {
		return this.grade.getTitle()+"-"+this.letterToGrade.getTitle();
	}
	
	

}
