package com.cos.oauth2jwt.domain.tech;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.cos.oauth2jwt.domain.file.MyFile;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Tech {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String title;
	
	@OneToOne // 자동으로 Eager 전략
	@JoinColumn(name = "fileId")
	private MyFile file;
	
	@ColumnDefault("false")
	private boolean isFilter;
	
	@CreationTimestamp
	private Timestamp createDate;
}
