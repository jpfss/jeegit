package com.jeesite.modules.test.service.note;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.persistence.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.note.TestNote;
import com.jeesite.modules.test.dao.note.TestNoteDao;

/**
 * 富文本测试Service
 * 
 * @author liugf
 * @version 2016-10-04
 */
@Service
@Transactional(readOnly = true)
public class TestNoteService extends CrudService<TestNoteDao, TestNote> {

	public TestNote get(String id) {
		return super.get(id);
	}

	public List<TestNote> findList(TestNote testNote) {
		return super.findList(testNote);
	}

	public Page<TestNote> findPage(Page<TestNote> page, TestNote testNote) {
		return super.findPage(page, testNote);
	}

	@Transactional(readOnly = false)
	public void save(TestNote testNote) {
		super.save(testNote);
	}

	@Transactional(readOnly = false)
	public void delete(TestNote testNote) {
		super.delete(testNote);
	}

}