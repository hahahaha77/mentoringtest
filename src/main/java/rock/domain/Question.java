package rock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private long id;
	
//	@ManyToOne
//	@JoinColumn(foreignKey=@ForeignKey(name = "fk_question_writer"))
	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String contents;
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
	public void setContents(String contents) {
		this.contents = contents;
	}	
}
