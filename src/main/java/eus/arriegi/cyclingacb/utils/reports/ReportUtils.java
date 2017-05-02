package eus.arriegi.cyclingacb.utils.reports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;

public class ReportUtils<T> {
	
	public final static int TITLE_BOTTOM = 20;

	private String title, pageHeader;
	private List<T> list;
	private JasperReportBuilder builder;

	public ReportUtils(String title, String pageHeader, List<T> list) {
		this.list = list;
		this.title = title;
		this.pageHeader = pageHeader;
		//init();
	}
	
	public void init() {
		builder = new JasperReportBuilder();
		Optional.ofNullable(title).ifPresent(title -> builder.addTitle(Components.text(title).setStyle(getTitleStyle())));
		Optional.ofNullable(pageHeader).ifPresent(pageHeader -> builder.pageHeader(Components.text(pageHeader).setStyle(getPageHeaderStyle())));
		builder.setColumnTitleStyle(getColumnTitleStyle());
		builder.setColumnStyle(getColumnStyle());
		builder.highlightDetailOddRows();
		builder.setDataSource(list);
	}
	
	private StyleBuilder getColumnTitleStyle() {
		StyleBuilder columnTitleBuilder = stl.style();
		columnTitleBuilder.bold();
		columnTitleBuilder.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		columnTitleBuilder.setBorder(stl.pen1Point());
		columnTitleBuilder.setBackgroundColor(Color.LIGHT_GRAY);
		return columnTitleBuilder;
	}
	
	private StyleBuilder getColumnStyle() {
		StyleBuilder columnBuilder = stl.style();
		columnBuilder.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		return columnBuilder;
	}

	private StyleBuilder getTitleStyle() {
		StyleBuilder titleBuilder = stl.style();
		titleBuilder.bold();
		titleBuilder.setFontSize(20);
		titleBuilder.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		if (pageHeader == null) {
			titleBuilder.setBottomPadding(TITLE_BOTTOM);
		}
		return titleBuilder;
	}
	
	private StyleBuilder getPageHeaderStyle() {
		StyleBuilder pageHeaderStyle = stl.style();
		pageHeaderStyle.setFontSize(16);
		pageHeaderStyle.setBottomPadding(TITLE_BOTTOM);
		return pageHeaderStyle;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setColumns(List<ColumnData> columns) {
		columns.forEach(item->{
			try {
				builder.addColumn(col.column(item.getColumnName(), item.getFieldName(), (DRIDataType) type.detectType(item.getType())));
			} catch (DRException e) {
				e.printStackTrace();
			}
		});
	}

	public JasperReportBuilder getBuilder(List<ColumnData> columns) {
		init();
		setColumns(columns);
		/*StyleBuilder titleBuilder = stl.style();
		titleBuilder.setBackgroundColor(Color.DARK_GRAY);
		builder.setBackgroundStyle(titleBuilder);*/
		return builder;
	}

}
