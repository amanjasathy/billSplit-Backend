package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;

@Entity
public class Grp {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "grp_sequence", sequenceName = "grp_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grp_sequence")
	private Long grpId;
	private String mem1;
	private String mem2;
	private String mem3;
	private String mem4;
	private String mem5;
	private String grpType;
	private String transac;
	private Integer amt;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usergrpmap", joinColumns = @JoinColumn(name = "gid", referencedColumnName = "grpId"), inverseJoinColumns = @JoinColumn(name = "uid", referencedColumnName = "userId"))
	private List<User> users;

	public void addUser(User user) {
		if (users == null)
			users = new ArrayList<>();
		users.add(user);
	}

	public Long getGrpId() {
		return grpId;
	}

	public void setGrpId(Long grpId) {
		this.grpId = grpId;
	}

	public String getMem1() {
		return mem1;
	}

	public void setMem1(String mem1) {
		this.mem1 = mem1;
	}

	public String getMem2() {
		return mem2;
	}

	public void setMem2(String mem2) {
		this.mem2 = mem2;
	}

	public String getMem3() {
		return mem3;
	}

	public void setMem3(String mem3) {
		this.mem3 = mem3;
	}

	public String getMem4() {
		return mem4;
	}

	public void setMem4(String mem4) {
		this.mem4 = mem4;
	}

	public String getMem5() {
		return mem5;
	}

	public void setMem5(String mem5) {
		this.mem5 = mem5;
	}

	public String getGrpType() {
		return grpType;
	}

	public void setGrpType(String grpType) {
		this.grpType = grpType;
	}

	public String getTransac() {
		return transac;
	}

	public void setTransac(String transac) {
		this.transac = transac;
	}

	public Integer getAmt() {
		return amt;
	}

	public void setAmt(Integer amt) {
		this.amt = amt;
	}

	@Override
	public String toString() {
		return "Grp [grpId=" + grpId + ", mem1=" + mem1 + ", mem2=" + mem2 + ", mem3=" + mem3 + ", mem4=" + mem4
				+ ", mem5=" + mem5 + ", grpType=" + grpType + ", transac=" + transac + ", amt=" + amt + "]";
	}

}
