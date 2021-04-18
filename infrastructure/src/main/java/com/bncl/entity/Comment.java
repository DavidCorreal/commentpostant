package com.bncl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "commentpost", name = "comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = 4580263570243497831L;

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	@Column(length = 50)
	private String id;
	
	@NotBlank
	@Column(nullable = false, length = 2000)
	private String comment;

	@NotBlank
	@Column(nullable = false, length = 10)
	private Integer postId;
	
}
