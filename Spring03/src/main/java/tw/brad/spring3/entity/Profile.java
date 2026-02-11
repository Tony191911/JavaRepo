package tw.brad.spring3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cname;
	private Integer age;
	
	//--------------------------------
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	// 解決json無限遞迴
	@JsonBackReference
	private Member member;
	
}
