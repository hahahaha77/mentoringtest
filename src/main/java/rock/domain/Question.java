package rock.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name = "fk_question_writer"))
	private User writer;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Answer> answer;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String contents;
	
	private LocalDateTime createDate;
	
	public void setWriter(User writer) {
		this.writer = writer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", writer=" + writer + ", answer=" + answer + ", title=" + title + ", contents="
				+ contents + ", createDate=" + createDate + "]";
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Question() {}
	
	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}
	
	public String getFormattedCreateDate() {
		if(createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	public Question(long id, User writer, List<Answer> answer, String title, String contents) {
		super();
		this.id = id;
		this.writer = writer;
		this.answer = answer;
		this.title = title;
		this.contents = contents;
	}
	public User getWriter() {
		return writer;
	}
	
	public List<Answer> getAnswer() {
		return answer;
	}
		
}
