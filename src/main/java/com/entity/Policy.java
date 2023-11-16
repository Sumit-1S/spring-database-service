package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Component
public class Policy {
	@Id
	@GeneratedValue
	private Integer policyId;
	private String policyName;
	private String policyDesc;
	private Integer duration;
	private PolicyType policyType;
	private Double amount;
}
