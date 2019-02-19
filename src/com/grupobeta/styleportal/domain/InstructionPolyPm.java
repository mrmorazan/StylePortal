package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstructionPolyPm extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int instructionId;
	private int sequence;
	private String instructionName;
	private String operationCode;
	private String operation;
	private String machine;
	private String width;
	private String comments;
	private String stitchType;
	private String spi;
	private String threadText;
	private String notes;
	private String instructionSet;
	private int version;
	
	@Id
	@Column(name="InstructionID")
	public int getInstructionId() {
		return instructionId;
	}
	public void setInstructionId(int instructionId) {
		this.instructionId = instructionId;
	}
	
	@Column(name="Sequence")
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	@Column(name="InstructionName")
	public String getInstructionName() {
		return instructionName;
	}
	public void setInstructionName(String instructionName) {
		this.instructionName = instructionName;
	}
	
	@Column(name="OperationCode")
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	
	@Column(name="Operation")
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Column(name="Machine")
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	
	@Column(name="Width")
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	@Column(name="Comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column(name="StitchType")
	public String getStitchType() {
		return stitchType;
	}
	public void setStitchType(String stitchType) {
		this.stitchType = stitchType;
	}
	
	@Column(name="SPI")
	public String getSpi() {
		return spi;
	}
	public void setSpi(String spi) {
		this.spi = spi;
	}
	
	@Column(name="ThreadText")
	public String getThreadText() {
		return threadText;
	}
	public void setThreadText(String threadText) {
		this.threadText = threadText;
	}
	
	@Column(name="Notes")
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Column(name="InstructionSet")
	public String getInstructionSet() {
		return instructionSet;
	}
	public void setInstructionSet(String instructionSet) {
		this.instructionSet = instructionSet;
	}
	
	@Column(name="Version")
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	
	

}
