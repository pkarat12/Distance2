package ua.karatnyk.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.enumerations.Role;
import ua.karatnyk.enumerations.School;
import ua.karatnyk.enumerations.Subject;


@Entity
@Table(name = "users", indexes = @Index(columnList = "first_name, last_name"))
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity{
	
	@Column(nullable = true, unique = true)
	private String login;
	
	@Column(nullable = true)
	private String password;
	
	@Column(name = "password_text", nullable = true)
	private String passwordText;
	
	@Column (name = "first_name", nullable = true)
	private String firstName;
	
	@Column (name = "last_name", nullable = true)
	private String lastName;
	
	@Column (name = "middle_name")
	private String middleName;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(updatable = false, nullable = true)
	private Role role;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "number_school", updatable = false)
	private School numberSchool;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "name_foto", nullable = true)
	private String nameFoto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private ClassToStudent classStudent; //клас, в якому навчається учень. Тільки для учнів
	
	@OneToOne(mappedBy = "userClassTeacher")
	private ClassToStudent classTeacher; //клас, в якому вчитель є класним керівником. Тільки для вчителів

	@Enumerated(EnumType.STRING)
	private Subject subject;
	
	@ManyToMany
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private List<Group> groups = new ArrayList<>();
	
	@OneToMany(mappedBy = "createdByUser")
	private List<Group> createdByUserGroups = new ArrayList<>();
	
	@OneToMany(mappedBy = "createdByUser")
	private List<Course> createdByUserCourses = new ArrayList<>();
	
	@OneToMany(mappedBy = "createdByUser")
	private List<Lesson> createdByUserLessons = new ArrayList<>();
	
	@OneToMany(mappedBy = "createdByUser")
	private List<UserEntity> createdByUserUserEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "createdByUser")
	private List<ClassToStudent> createdByUserClassToStudents = new ArrayList<>();
	
	@OneToMany(mappedBy = "createdByUser")
	private List<News> createdByUserNewsList = new ArrayList<>();
	
	@OneToMany(mappedBy = "createdByUser")
	private List<TheoreticalTask> createdByUserTaskTheoreticalList = new ArrayList<>();
	
	@OneToMany(mappedBy = "createdByUser")
	private List<VideoYouTubeTask> createdByUserVideoYouTubeList = new ArrayList<>();
	
	@OneToMany(mappedBy = "createdByUser")
	private List<TestTask> createdByUserTestList = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "lastUpdateByUser")
	private List<Group> lastUpdateByUserGroups = new ArrayList<>();
	
	@OneToMany(mappedBy = "lastUpdateByUser")
	private List<Course> lastUpdateByUserCourses = new ArrayList<>();
	
	@OneToMany(mappedBy = "lastUpdateByUser")
	private List<Lesson> lastUpdateByUserLessons = new ArrayList<>();
	
	@OneToMany(mappedBy = "lastUpdateByUser")
	private List<UserEntity> lastUpdateByUserUserEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "lastUpdateByUser")
	private List<ClassToStudent> lastUpdateByUserClassToStudents = new ArrayList<>();
	
	@OneToMany(mappedBy = "lastUpdateByUser")
	private List<News> lastUpdateByUserNewsList = new ArrayList<>();
	
	@OneToMany(mappedBy = "lastUpdateByUser")
	private List<TheoreticalTask> lastUpdateByUserTheoreticalList = new ArrayList<>();
	
	@OneToMany(mappedBy = "lastUpdateByUser")
	private List<VideoYouTubeTask> lastUpdateByUserTaskVideoYouTubeList = new ArrayList<>();
	
	@OneToMany(mappedBy = "lastUpdateByUser")
	private List<TestTask> lastUpdateByUserTaskTestList = new ArrayList<>();
	
}
