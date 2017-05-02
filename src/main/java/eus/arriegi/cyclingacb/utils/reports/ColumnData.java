package eus.arriegi.cyclingacb.utils.reports;

public class ColumnData {

	private String columnName;
	private String fieldName;
	private String type;
	
	public ColumnData(){}
	
	public ColumnData(String columnName, String fieldName, String type) {
		this.columnName = columnName;
		this.fieldName = fieldName;
		this.type = type;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
