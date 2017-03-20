 package com.jeesite.modules.gen.service;
 
 import com.jeesite.common.persistence.Page;
 import com.jeesite.common.service.BaseService;
 import com.jeesite.common.utils.StringUtils;
 import com.jeesite.modules.gen.dao.GenTemplateDao;
 import com.jeesite.modules.gen.entity.GenTemplate;
 import org.apache.commons.lang3.StringEscapeUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional(readOnly=true)
 public class GenTemplateService
   extends BaseService
 {
   @Autowired
   private GenTemplateDao genTemplateDao;
   
   public GenTemplate get(String id)
   {
/* 30 */     return (GenTemplate)this.genTemplateDao.get(id);
   }
   
   public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate) {
/* 34 */     genTemplate.setPage(page);
/* 35 */     page.setList(this.genTemplateDao.findList(genTemplate));
/* 36 */     return page;
   }
   
   @Transactional(readOnly=false)
   public void save(GenTemplate genTemplate) {
/* 41 */     if (genTemplate.getContent() != null) {
/* 42 */       genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
     }
/* 44 */     if (StringUtils.isBlank(genTemplate.getId())) {
/* 45 */       genTemplate.preInsert();
/* 46 */       this.genTemplateDao.insert(genTemplate);return;
     }
/* 48 */     genTemplate.preUpdate();
/* 49 */     this.genTemplateDao.update(genTemplate);
   }
   
   @Transactional(readOnly=false)
   public void delete(GenTemplate genTemplate)
   {
/* 55 */     this.genTemplateDao.delete(genTemplate);
   }
 }


/* Location:              C:\Users\admin\Desktop\org.jeeframework.gencode-1.5.jar!\com\jeesite\modules\gen\service\GenTemplateService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */