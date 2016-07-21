package com.rightsallocate.dao;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rightsallocate.common.Page;

@SuppressWarnings("unchecked")
public class PageDaoImpl extends HibernateDaoSupport {

    private String hql;
    public Page page;
    public int start;
    public Idao dao;
   

    public Idao getDao() {
		return dao;
	}

	public void setDao(Idao dao) {
		this.dao = dao;
	}

	public void init(int start,String tableName){ 
       page = new Page();
       this.hql = "from "+tableName;
       this.start = start;
       setRowCount();
       setTotalPage();
       setCurrentPage();
       setPrePage();
       setNextPage();
       setPreOrNextBoolean();
    }

    public int getRowCount(){
       List list = dao.findByHql(hql);
       if(list.isEmpty()){
        return 0;
       }
       return list.size();
    }

    public Page getPage(){
       List list = (List)getHibernateTemplate().execute(new HibernateCallback(){
        public Object doInHibernate(Session session) throws HibernateException, SQLException {
         Query query = session.createQuery(hql);
         query.setFirstResult(getStartIndex());
         query.setMaxResults(page.getPageSize());
         return query.list();
        }   
       });
       page.setList(list);
       return page;
    }
    
    public void setPreOrNextBoolean() {
        if (page.getCurrentPage() <= 1) {
            page.setHasPreviousPage(false);
        } else {
            page.setHasPreviousPage(true);
        }
        if (page.getCurrentPage() >= page.getTotalPage()) {
            page.setHasNextPage(false);
        } else {
            page.setHasNextPage(true);
        }
    }

    public void setCurrentPage() {
        if (start < 1) {
            page.setCurrentPage(1);
        } else if(start > page.getTotalPage()) {
            page.setCurrentPage(page.getTotalPage());
        }else {
        	 page.setCurrentPage(start);
        }
    }

    public void setPrePage() {
        page.setPrePage(page.getCurrentPage() - 1);
    }

    public void setNextPage() {
        page.setNextPage(page.getCurrentPage() + 1);
    }

    @Resource(name="sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

    
    public void setTotalPage() {
        int rowCount = getRowCount();
        int pageSize = page.getPageSize();
        if (rowCount > pageSize) {
            if (rowCount % pageSize == 0) {
                page.setTotalPage(rowCount / pageSize);
            } else {
                page.setTotalPage(1 + (rowCount / pageSize));
            }
        } else {
            page.setTotalPage(1);
        }
    }

    public void setRowCount() {
        page.setRowCount(getRowCount());
    }

    public int getStartIndex() {
        int startIndex = 0;
        if (start < 0) {
            startIndex = 0;
        } else {
            if (start > page.getTotalPage()) {
                startIndex = page.getPageSize() * (page.getTotalPage() - 1);
            } else {
                startIndex = page.getPageSize() * (start - 1);
            }
        }
        return startIndex;
    }
}