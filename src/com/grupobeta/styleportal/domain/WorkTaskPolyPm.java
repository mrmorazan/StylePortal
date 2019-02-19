package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WorkTaskPolyPm extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int flowTaskID;
	private String taskName;
	private String taskDescription;
	private boolean isDecision;
	private int positionNode;
	private int nextTask;
	private int rejectTask;
	private int alternateTask;
	private String chartProperties;
	
	@Id
	@Column(name="FlowTaskID")
	public int getFlowTaskID() {
		return flowTaskID;
	}
	public void setFlowTaskID(int flowTaskID) {
		this.flowTaskID = flowTaskID;
	}
	
	@Column(name="TaskName")
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	@Column(name="TaskDescription")
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	
	@Column(name="IsDecision")
	public boolean isDecision() {
		return isDecision;
	}
	public void setDecision(boolean isDecision) {
		this.isDecision = isDecision;
	}
	
	@Column(name="PositionNode")
	public int getPositionNode() {
		return positionNode;
	}
	public void setPositionNode(int positionNode) {
		this.positionNode = positionNode;
	}
	
	@Column(name="NextTask")
	public int getNextTask() {
		return nextTask;
	}
	public void setNextTask(int nextTask) {
		this.nextTask = nextTask;
	}
	
	@Column(name="RejectTask")
	public int getRejectTask() {
		return rejectTask;
	}
	public void setRejectTask(int rejectTask) {
		this.rejectTask = rejectTask;
	}
	
	@Column(name="AlternateTask")
	public int getAlternateTask() {
		return alternateTask;
	}
	public void setAlternateTask(int alternateTask) {
		this.alternateTask = alternateTask;
	}
	@Column(name="ChartProperties")
	public String getChartProperties() {
		return chartProperties;
	}
	public void setChartProperties(String chartProperties) {
		this.chartProperties = chartProperties;
	}
	
	
	

}
