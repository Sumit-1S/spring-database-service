package com.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Component
public class PolicyOwned {
	@Id
	@GeneratedValue
	private Integer policyOwnedId;
	private String clientUsername;
	private int clientAge;
	private String clientCity;
	private String clientMedicalHistory;
	private Date date;
	private Double premium;
	private Status status;
	private Boolean clientTobaccoUser;
	@OneToOne
	@JoinColumn(name = "policy", referencedColumnName = "policyId")
	private Policy policy;
	
	
	

}
