package com.cg.ams.bean;

public class Record {
	private int recId;
	private String team;
	private String jobName;
	private int hrWhenItsRun;
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public int getHrWhenItsRun() {
		return hrWhenItsRun;
	}
	public void setHrWhenItsRun(int hrWhenItsRun) {
		this.hrWhenItsRun = hrWhenItsRun;
	}
	@Override
	public String toString() {
		return "Record [recId=" + recId + ", team=" + team + ", jobName="
				+ jobName + ", hrWhenItsRun=" + hrWhenItsRun + "]";
	}
	
	
	
}
