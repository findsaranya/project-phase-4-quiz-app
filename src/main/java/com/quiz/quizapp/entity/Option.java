package com.quiz.quizapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="ques_option_tab")
public class Option {
@Id
@Column(name="OptionId")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int optionId;
@Column(name="OptionName")
private String optionName;
@ToString.Exclude
@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
@JoinColumn(name="QuesId")
private Question ques;
@Column(name="IsAns")
private Boolean isAnswerable=false;

}
