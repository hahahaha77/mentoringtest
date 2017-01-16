package rock.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name = "fk_answer_writer"))
	private User writer;
	
	public User getWriter() {
		return writer;
	}
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name = "fk_answer_to_question"))
	private Question question;
	
	@Column(nullable = false)
	private String contents;
	
	private LocalDateTime createDate;
	
	public Answer() {}
	
	public Answer(User writer, String contents,  Question question) {
		this.writer = writer;
		this.contents = contents;
		this.question = question;
		this.createDate = LocalDateTime.now();
	}
	public String getFormattedCreateDate() {
		if(createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
		
}
