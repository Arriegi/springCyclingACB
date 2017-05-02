package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Section;
import eus.arriegi.cyclingacb.repository.SectionDao;

@Component
public class SimpleSectionManager implements SectionManager {

	@Autowired
	private SectionDao sectionDao;
	
	public List<Section> getSections() {
		return sectionDao.getSections();
	}
	
	public Section getSection(Long id) {
		return sectionDao.getSection(id);
	}

	public void addSection(Section section) {
		sectionDao.saveSection(section);
	}

	public Section updateSection(Section section) {
		return sectionDao.updateSection(section);
	}

	public void removeSection(Section section) {
		sectionDao.removeSection(section);
	}
	
	public void removeSection(Long id) {
		sectionDao.removeSection(id);
	}

}
