package com.quiz.quizapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="ques_tab")
public class Question {
@Id
@Column(name="QuesId")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int quesId;
@Column(name="Quesname")
private String quesname;
@ToString.Exclude
@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
@JoinColumn(name="Quizid")
private Quiz quiz;
@JsonIgnore
@OneToMany(mappedBy = "ques",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
private List<Option> options; 
}
