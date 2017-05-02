package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Section;

public interface SectionManager {
	
public List<Section> getSections();
	
	public Section getSection(Long id);
	
	public void addSection(Section section);

	public Section updateSection(Section section);
	
	public void removeSection(Section section);
	
	public void removeSection(Long id);
	
}
