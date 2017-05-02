package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Section;

public interface SectionDao {
	
	public List<Section> getSections();
	
	public List<Section> getSections(String name);
	
	public Section getSection(Long id);
	
    public Section saveSection(Section section);
    
    public void removeSection(Section section);

    public void removeSection(Long id);
    
    public Section updateSection(Section section);
    
}
